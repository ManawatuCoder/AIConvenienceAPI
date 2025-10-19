package guidelinesFragmentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import config.PathConfiguration;
import java.io.IOException;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import org.junit.jupiter.api.*;

class LastModifiedCheckTest {

    private static final String PROTOCOL = "mockhttp";

    // Thread-local delegate so each test can supply its own mocked connection
    private static final ThreadLocal<MockableHttpURLConnection> CURRENT_CONN = new ThreadLocal<>();

    /**
     * Minimal interface we can easily mock with Mockito.
     */
    interface MockableHttpURLConnection {
        void setRequestMethod(String method) throws ProtocolException;

        void connect() throws IOException;

        long getLastModified();
    }

    /**
     * Adapter that satisfies java.net.HttpURLConnection by forwarding to our mockable interface.
     */
    static final class HttpURLConnectionAdapter extends HttpURLConnection {
        private final MockableHttpURLConnection delegate;

        protected HttpURLConnectionAdapter(URL u, MockableHttpURLConnection delegate) {
            super(u);
            this.delegate = delegate;
        }

        @Override
        public void setRequestMethod(String method) throws ProtocolException {
            delegate.setRequestMethod(method);
        }

        @Override
        public void connect() throws IOException {
            delegate.connect();
        }

        @Override
        public long getLastModified() {
            return delegate.getLastModified();
        }

        // Required abstract/overrides with safe defaults
        @Override
        public void disconnect() {
        }

        @Override
        public boolean usingProxy() {
            return false;
        }
    }

    /**
     * URLStreamHandler for the mock protocol that returns our adapter.
     */
    static final class MockHttpHandler extends URLStreamHandler {
        @Override
        protected URLConnection openConnection(URL u) {
            MockableHttpURLConnection conn = CURRENT_CONN.get();
            if (conn == null) {
                throw new IllegalStateException("No CURRENT_CONN set for " + u);
            }
            return new HttpURLConnectionAdapter(u, conn);
        }
    }

    /**
     * Install a factory that knows our custom protocol (ignore if already installed).
     */
    @BeforeAll
    static void installFactory() {
        try {
            URL.setURLStreamHandlerFactory(protocol -> PROTOCOL.equals(protocol) ? new MockHttpHandler() : null);
        } catch (Error already) {
            // Another factory is already installed — that's fine as long as tests run in one JVM.
        }
    }

    private static Path marker() {
        return Path.of(PathConfiguration.LAST_MODIFIED_FILE);
    }

    private static void ensureParent(Path p) throws IOException {
        Path parent = p.getParent();
        if (parent != null && !Files.exists(parent)) {
            Files.createDirectories(parent);
        }
    }

    @BeforeEach
    void cleanMarker() throws Exception {
        Path m = marker();
        ensureParent(m);
        Files.deleteIfExists(m);
    }

    @AfterEach
    void clear() {
        CURRENT_CONN.remove();
    }

    @Test
    void firstRunWrites_thenUnchangedFalse_thenChangedTrue() throws Exception {
        Path m = marker();

        // 1) First run: file missing -> true and writes millis
        MockableHttpURLConnection conn1 = mock(MockableHttpURLConnection.class);
        long t1 = Instant.parse("2024-10-01T10:00:00Z").toEpochMilli();
        when(conn1.getLastModified()).thenReturn(t1);
        CURRENT_CONN.set(conn1);

        boolean first = LastModifiedCheck.check(PROTOCOL + "://host/guidelines");
        assertTrue(first, "Expected true on first run when marker absent");
        verify(conn1).setRequestMethod("HEAD");
        verify(conn1).connect();
        assertTrue(Files.exists(m));
        assertEquals(Long.toString(t1), Files.readString(m));

        // 2) Same Last-Modified: false, file unchanged
        MockableHttpURLConnection connSame = mock(MockableHttpURLConnection.class);
        when(connSame.getLastModified()).thenReturn(t1);
        CURRENT_CONN.set(connSame);

        String before = Files.readString(m);
        boolean second = LastModifiedCheck.check(PROTOCOL + "://host/guidelines");
        assertFalse(second, "Expected false when Last-Modified unchanged");
        verify(connSame).setRequestMethod("HEAD");
        verify(connSame).connect();
        assertEquals(before, Files.readString(m));

        // 3) Different Last-Modified: true, file updated
        MockableHttpURLConnection conn2 = mock(MockableHttpURLConnection.class);
        long t2 = Instant.parse("2024-12-31T23:59:59Z").toEpochMilli();
        when(conn2.getLastModified()).thenReturn(t2);
        CURRENT_CONN.set(conn2);

        boolean third = LastModifiedCheck.check(PROTOCOL + "://host/guidelines");
        assertTrue(third, "Expected true when Last-Modified changed");
        verify(conn2).setRequestMethod("HEAD");
        verify(conn2).connect();
        String written2 = Files.readString(m);
        assertEquals(Long.toString(t2), written2);
        assertNotEquals(Long.toString(t1), written2);
    }

    @Test
    void missingHeaderActsAsZero_updatesExistingNonZeroFile_andReturnsTrue() throws Exception {
        Path m = marker();
        ensureParent(m);
        Files.writeString(m, "123456"); // existing non-zero

        MockableHttpURLConnection conn = mock(MockableHttpURLConnection.class);
        when(conn.getLastModified()).thenReturn(0L); // no Last-Modified header
        CURRENT_CONN.set(conn);

        boolean result = LastModifiedCheck.check(PROTOCOL + "://host/guidelines");
        assertTrue(result, "Missing header -> 0; different from existing -> should update and return true");
        verify(conn).setRequestMethod("HEAD");
        verify(conn).connect();
        assertEquals("0", Files.readString(m), "Marker should be updated to \"0\"");
    }

    @Test
    void missingHeaderActsAsZero_andReturnsFalseWhenFileAlreadyZero() throws Exception {
        Path m = marker();
        ensureParent(m);
        Files.writeString(m, "0"); // already zero

        MockableHttpURLConnection conn = mock(MockableHttpURLConnection.class);
        when(conn.getLastModified()).thenReturn(0L); // no Last-Modified header
        CURRENT_CONN.set(conn);

        boolean result = LastModifiedCheck.check(PROTOCOL + "://host/guidelines");
        assertFalse(result, "Already \"0\" -> no change -> should return false");
        verify(conn).setRequestMethod("HEAD");
        verify(conn).connect();
        assertEquals("0", Files.readString(m));
    }
}
