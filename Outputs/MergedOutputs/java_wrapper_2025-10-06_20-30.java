aaaapackage azurestoragemanagement;
aaaa
aaaaimport azurestoragemanagement.implementation.BlobContainersImpl;
aaaaimport io.clientcore.core.annotations.Metadata;
aaaaimport io.clientcore.core.annotations.MetadataProperties;
aaaaimport io.clientcore.core.annotations.ReturnType;
aaaaimport io.clientcore.core.annotations.ServiceClient;
aaaaimport io.clientcore.core.annotations.ServiceMethod;
aaaaimport io.clientcore.core.http.models.HttpResponseException;
aaaaimport io.clientcore.core.http.models.RequestContext;
aaaaimport io.clientcore.core.http.models.Response;
aaaa
aaaa/**
aaaa * Initializes a new instance of the synchronous AzureStorageManagementClient type.
aaaa */
aaaa@ServiceClient(builder = AzureStorageManagementClientBuilder.class)
aaaapublic final class BlobContainersClient {
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    private final BlobContainersImpl serviceClient;
aaaa
aaaa    /**
aaaa     * Initializes an instance of BlobContainersClient class.
aaaa     * 
aaaa     * @param serviceClient the service client implementation.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    BlobContainersClient(BlobContainersImpl serviceClient) {
aaaa        this.serviceClient = serviceClient;
aaaa    }
aaaa
aaaa    /**
aaaa     * Lists all containers and does not support a prefix like data plane.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param maxpagesize The maxpagesize parameter.
aaaa     * @param filter The filter parameter.
aaaa     * @param include The include parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return response schema.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<ListContainerItems> listWithResponse(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String apiVersion, String maxpagesize, String filter, ListContainersInclude include,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.listWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion,
aaaa            maxpagesize, filter, include, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Lists all containers and does not support a prefix like data plane.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param maxpagesize The maxpagesize parameter.
aaaa     * @param filter The filter parameter.
aaaa     * @param include The include parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return response schema.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ListContainerItems list(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String apiVersion, String maxpagesize, String filter, ListContainersInclude include) {
aaaa        return this.serviceClient.list(subscriptionId, resourceGroupName, accountName, apiVersion, maxpagesize, filter,
aaaa            include);
aaaa    }
aaaa
aaaa    /**
aaaa     * Lists all containers and does not support a prefix like data plane.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return response schema.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ListContainerItems list(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String apiVersion) {
aaaa        return this.serviceClient.list(subscriptionId, resourceGroupName, accountName, apiVersion);
aaaa    }
aaaa
aaaa    /**
aaaa     * Creates a new container under the specified account.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param blobContainer The blobContainer parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return properties of the blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<BlobContainer> createWithResponse(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, BlobContainer blobContainer,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.createWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, blobContainer, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Creates a new container under the specified account.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param blobContainer The blobContainer parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return properties of the blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public BlobContainer create(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion, BlobContainer blobContainer) {
aaaa        return this.serviceClient.create(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
aaaa            blobContainer);
aaaa    }
aaaa
aaaa    /**
aaaa     * Updates container properties as specified in request body.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param blobContainer The blobContainer parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return properties of the blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<BlobContainer> updateWithResponse(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, BlobContainer blobContainer,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.updateWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, blobContainer, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Updates container properties as specified in request body.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param blobContainer The blobContainer parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return properties of the blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public BlobContainer update(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion, BlobContainer blobContainer) {
aaaa        return this.serviceClient.update(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
aaaa            blobContainer);
aaaa    }
aaaa
aaaa    /**
aaaa     * Gets properties of a specified container.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return properties of a specified container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<BlobContainer> getWithResponse(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion, RequestContext requestContext) {
aaaa        return this.serviceClient.getWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Gets properties of a specified container.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return properties of a specified container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public BlobContainer get(String subscriptionId, String resourceGroupName, String accountName, String containerName,
aaaa        String apiVersion) {
aaaa        return this.serviceClient.get(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
aaaa    }
aaaa
aaaa    /**
aaaa     * Deletes specified container under its account.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the response.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<Void> deleteWithResponse(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion, RequestContext requestContext) {
aaaa        return this.serviceClient.deleteWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Deletes specified container under its account.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public void delete(String subscriptionId, String resourceGroupName, String accountName, String containerName,
aaaa        String apiVersion) {
aaaa        this.serviceClient.delete(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
aaaa    }
aaaa
aaaa    /**
aaaa     * Sets legal hold tags.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param legalHold The legalHold parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the LegalHold property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<LegalHold> setLegalHoldWithResponse(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, LegalHold legalHold,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.setLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, legalHold, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Sets legal hold tags.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param legalHold The legalHold parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the LegalHold property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public LegalHold setLegalHold(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion, LegalHold legalHold) {
aaaa        return this.serviceClient.setLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, legalHold);
aaaa    }
aaaa
aaaa    /**
aaaa     * Clears legal hold tags.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param legalHold The legalHold parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the LegalHold property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<LegalHold> clearLegalHoldWithResponse(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, LegalHold legalHold,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.clearLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, legalHold, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Clears legal hold tags.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param legalHold The legalHold parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the LegalHold property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public LegalHold clearLegalHold(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion, LegalHold legalHold) {
aaaa        return this.serviceClient.clearLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, legalHold);
aaaa    }
aaaa
aaaa    /**
aaaa     * Creates or updates an unlocked immutability policy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @param parameters The parameters parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<ImmutabilityPolicy> createOrUpdateImmutabilityPolicyWithResponse(String subscriptionId,
aaaa        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
aaaa        ImmutabilityPolicy parameters, RequestContext requestContext) {
aaaa        return this.serviceClient.createOrUpdateImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName,
aaaa            accountName, containerName, apiVersion, ifMatch, parameters, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Creates or updates an unlocked immutability policy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @param parameters The parameters parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy createOrUpdateImmutabilityPolicy(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters) {
aaaa        return this.serviceClient.createOrUpdateImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch, parameters);
aaaa    }
aaaa
aaaa    /**
aaaa     * Creates or updates an unlocked immutability policy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy createOrUpdateImmutabilityPolicy(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion) {
aaaa        return this.serviceClient.createOrUpdateImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion);
aaaa    }
aaaa
aaaa    /**
aaaa     * Gets the existing immutability policy along with the corresponding ETag.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the existing immutability policy along with the corresponding ETag.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<ImmutabilityPolicy> getImmutabilityPolicyWithResponse(String subscriptionId,
aaaa        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.getImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Gets the existing immutability policy along with the corresponding ETag.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the existing immutability policy along with the corresponding ETag.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy getImmutabilityPolicy(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion, String ifMatch) {
aaaa        return this.serviceClient.getImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, ifMatch);
aaaa    }
aaaa
aaaa    /**
aaaa     * Gets the existing immutability policy along with the corresponding ETag.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the existing immutability policy along with the corresponding ETag.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy getImmutabilityPolicy(String subscriptionId, String resourceGroupName, String accountName,
aaaa        String containerName, String apiVersion) {
aaaa        return this.serviceClient.getImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion);
aaaa    }
aaaa
aaaa    /**
aaaa     * Aborts an unlocked immutability policy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<ImmutabilityPolicy> deleteImmutabilityPolicyWithResponse(String subscriptionId,
aaaa        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.deleteImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Aborts an unlocked immutability policy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy deleteImmutabilityPolicy(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, String ifMatch) {
aaaa        return this.serviceClient.deleteImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch);
aaaa    }
aaaa
aaaa    /**
aaaa     * Sets the ImmutabilityPolicy to Locked state.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<ImmutabilityPolicy> lockImmutabilityPolicyWithResponse(String subscriptionId,
aaaa        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
aaaa        RequestContext requestContext) {
aaaa        return this.serviceClient.lockImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Sets the ImmutabilityPolicy to Locked state.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy lockImmutabilityPolicy(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, String ifMatch) {
aaaa        return this.serviceClient.lockImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
aaaa            apiVersion, ifMatch);
aaaa    }
aaaa
aaaa    /**
aaaa     * Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @param parameters The parameters parameter.
aaaa     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public Response<ImmutabilityPolicy> extendImmutabilityPolicyWithResponse(String subscriptionId,
aaaa        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
aaaa        ImmutabilityPolicy parameters, RequestContext requestContext) {
aaaa        return this.serviceClient.extendImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch, parameters, requestContext);
aaaa    }
aaaa
aaaa    /**
aaaa     * Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @param parameters The parameters parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy extendImmutabilityPolicy(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters) {
aaaa        return this.serviceClient.extendImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch, parameters);
aaaa    }
aaaa
aaaa    /**
aaaa     * Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy.
aaaa     * 
aaaa     * @param subscriptionId The subscriptionId parameter.
aaaa     * @param resourceGroupName The resourceGroupName parameter.
aaaa     * @param accountName The accountName parameter.
aaaa     * @param containerName The containerName parameter.
aaaa     * @param apiVersion The apiVersion parameter.
aaaa     * @param ifMatch The ifMatch parameter.
aaaa     * @throws IllegalArgumentException thrown if parameters fail the validation.
aaaa     * @throws HttpResponseException thrown if the service returns an error.
aaaa     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
aaaa     * @return the ImmutabilityPolicy property of a blob container.
aaaa     */
aaaa    @Metadata(properties = { MetadataProperties.GENERATED })
aaaa    @ServiceMethod(returns = ReturnType.SINGLE)
aaaa    public ImmutabilityPolicy extendImmutabilityPolicy(String subscriptionId, String resourceGroupName,
aaaa        String accountName, String containerName, String apiVersion, String ifMatch) {
aaaa        return this.serviceClient.extendImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
aaaa            containerName, apiVersion, ifMatch);
aaaa    }
aaaa

/********************* GENERATED WRAPPER CODE *********************/
/**
 * Combined Methods: setLegalHold, setLegalHoldWithResponse
 * Reason: Both methods set legal hold tags on a container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a single entry point for setting legal hold tags, with or without a context, improving discoverability and reducing overload confusion.
 *
 * Comment: Sets legal hold tags on a container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<LegalHold> setLegalHoldCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, LegalHold legalHold, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.setLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold, requestContext);
    } else {
        LegalHold result = this.serviceClient.setLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: clearLegalHold, clearLegalHoldWithResponse
 * Reason: Both methods clear legal hold tags on a container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a single entry point for clearing legal hold tags, with or without a context, improving developer experience and reducing overload confusion.
 *
 * Comment: Clears legal hold tags on a container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<LegalHold> clearLegalHoldCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, LegalHold legalHold, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.clearLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold, requestContext);
    } else {
        LegalHold result = this.serviceClient.clearLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: create, createWithResponse
 * Reason: Both methods create a new container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a unified way to create a container, with or without a context, reducing overload confusion and improving usability.
 *
 * Comment: Creates a new container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<BlobContainer> createContainerCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, BlobContainer blobContainer, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.createWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer, requestContext);
    } else {
        BlobContainer result = this.serviceClient.create(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: update, updateWithResponse
 * Reason: Both methods update a container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a single entry point for updating a container, with or without a context, improving developer experience.
 *
 * Comment: Updates a container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<BlobContainer> updateContainerCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, BlobContainer blobContainer, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.updateWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer, requestContext);
    } else {
        BlobContainer result = this.serviceClient.update(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: get, getWithResponse
 * Reason: Both methods get properties of a specified container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a unified way to get container properties, with or without a context, improving discoverability.
 *
 * Comment: Gets properties of a specified container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<BlobContainer> getContainerCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.getWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, requestContext);
    } else {
        BlobContainer result = this.serviceClient.get(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: delete, deleteWithResponse
 * Reason: Both methods delete a container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a single entry point for deleting a container, with or without a context, improving developer experience.
 *
 * Comment: Deletes a specified container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<Void> deleteContainerCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.deleteWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, requestContext);
    } else {
        this.serviceClient.delete(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
        return new SimpleResponse<>(null, 200, null, null);
    }
}

/**
 * Combined Methods: list, listWithResponse
 * Reason: Both methods list containers, differing only by whether a Response wrapper, RequestContext, and additional options are used. Wrapping them provides a unified way to list containers, with or without advanced options, improving usability and discoverability.
 *
 * Comment: Lists all containers, optionally accepting advanced options and a RequestContext, and returning a Response if desired.
 */
public Response<ListContainerItems> listContainersCombined(String subscriptionId, String resourceGroupName, String accountName,
    String apiVersion, String maxpagesize, String filter, ListContainersInclude include, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.listWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion,
            maxpagesize, filter, include, requestContext);
    } else {
        ListContainerItems result = this.serviceClient.list(subscriptionId, resourceGroupName, accountName, apiVersion);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: deleteImmutabilityPolicy, deleteImmutabilityPolicyWithResponse
 * Reason: Both methods delete an immutability policy, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a single entry point for deleting an immutability policy, with or without a context, improving developer experience.
 *
 * Comment: Deletes an immutability policy, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<ImmutabilityPolicy> deleteImmutabilityPolicyCombined(String subscriptionId, String resourceGroupName,
    String accountName, String containerName, String apiVersion, String ifMatch, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.deleteImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext);
    } else {
        ImmutabilityPolicy result = this.serviceClient.deleteImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: getImmutabilityPolicy, getImmutabilityPolicyWithResponse
 * Reason: Both methods get an immutability policy, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a unified way to get an immutability policy, with or without a context, improving discoverability.
 *
 * Comment: Gets the immutability policy of a container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<ImmutabilityPolicy> getImmutabilityPolicyCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, String ifMatch, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.getImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext);
    } else {
        ImmutabilityPolicy result = this.serviceClient.getImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion);
        return new SimpleResponse<>(null, 200, null, result);
    }
}

/**
 * Combined Methods: lockImmutabilityPolicy, lockImmutabilityPolicyWithResponse
 * Reason: Both methods lock an immutability policy, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a single entry point for locking an immutability policy, with or without a context, improving developer experience.
 *
 * Comment: Locks the immutability policy of a container, optionally accepting a RequestContext and returning a Response if desired.
 */
public Response<ImmutabilityPolicy> lockImmutabilityPolicyCombined(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, String ifMatch, RequestContext requestContext, boolean returnResponse) {
    if (returnResponse) {
        return this.serviceClient.lockImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext);
    } else {
        ImmutabilityPolicy result = this.serviceClient.lockImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch);
        return new SimpleResponse<>(null, 200, null, result);
    }
}
/********************* END OF GENERATED CODE *********************/


aaaa}
