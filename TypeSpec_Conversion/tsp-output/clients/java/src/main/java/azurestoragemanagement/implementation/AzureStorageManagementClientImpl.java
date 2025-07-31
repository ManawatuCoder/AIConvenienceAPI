package azurestoragemanagement.implementation;

import io.clientcore.core.http.pipeline.HttpPipeline;

/**
 * Initializes a new instance of the AzureStorageManagementClient type.
 */
public final class AzureStorageManagementClientImpl {
    /**
     * Service host.
     */
    private final String endpoint;

    /**
     * Gets Service host.
     * 
     * @return the endpoint value.
     */
    public String getEndpoint() {
        return this.endpoint;
    }

    /**
     * The HTTP pipeline to send requests through.
     */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     * 
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /**
     * The BlobServicesImpl object to access its operations.
     */
    private final BlobServicesImpl blobServices;

    /**
     * Gets the BlobServicesImpl object to access its operations.
     * 
     * @return the BlobServicesImpl object.
     */
    public BlobServicesImpl getBlobServices() {
        return this.blobServices;
    }

    /**
     * The BlobContainersImpl object to access its operations.
     */
    private final BlobContainersImpl blobContainers;

    /**
     * Gets the BlobContainersImpl object to access its operations.
     * 
     * @return the BlobContainersImpl object.
     */
    public BlobContainersImpl getBlobContainers() {
        return this.blobContainers;
    }

    /**
     * Initializes an instance of AzureStorageManagementClient client.
     * 
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param endpoint Service host.
     */
    public AzureStorageManagementClientImpl(HttpPipeline httpPipeline, String endpoint) {
        this.httpPipeline = httpPipeline;
        this.endpoint = endpoint;
        this.blobServices = new BlobServicesImpl(this);
        this.blobContainers = new BlobContainersImpl(this);
    }
}
