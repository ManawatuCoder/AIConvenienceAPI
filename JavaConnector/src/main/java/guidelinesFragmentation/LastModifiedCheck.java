//Requests headers of provided url, and returns true and saves last-modified value if url 'last-modified'
// value differs from stored value.

package guidelinesFragmentation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class LastModifiedCheck {
    public static boolean check(String url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("HEAD"); // we just want headers
        conn.connect();

        String lastModified = String.valueOf(conn.getLastModified());

        try {
            Path lastModifiedPath = Path.of("src\\main\\java\\guidelinesFragmentation\\output\\last-modified.txt");

            if (!Files.exists(lastModifiedPath)) {
                Files.writeString(lastModifiedPath, lastModified,
                        StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                return true;
            }

            if (!Files.readString(lastModifiedPath).equals(lastModified)){
                Files.writeString(lastModifiedPath, lastModified);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
