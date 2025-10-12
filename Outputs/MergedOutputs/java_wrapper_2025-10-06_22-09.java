package azurestoragemanagement;

import azurestoragemanagement.implementation.BlobContainersImpl;
import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.annotations.ReturnType;
import io.clientcore.core.annotations.ServiceClient;
import io.clientcore.core.annotations.ServiceMethod;
import io.clientcore.core.http.models.HttpResponseException;
import io.clientcore.core.http.models.RequestContext;
import io.clientcore.core.http.models.Response;

/**
 * Initializes a new instance of the synchronous AzureStorageManagementClient type.
 */
@ServiceClient(builder = AzureStorageManagementClientBuilder.class)
public final class BlobContainersClient {
    @Metadata(properties = { MetadataProperties.GENERATED })
    private final BlobContainersImpl serviceClient;

    /**
     * Initializes an instance of BlobContainersClient class.
     * 
     * @param serviceClient the service client implementation.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    BlobContainersClient(BlobContainersImpl serviceClient) {
        this.serviceClient = serviceClient;
    }

    /**
     * Lists all containers and does not support a prefix like data plane.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param maxpagesize The maxpagesize parameter.
     * @param filter The filter parameter.
     * @param include The include parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return response schema.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ListContainerItems> listWithResponse(String subscriptionId, String resourceGroupName,
        String accountName, String apiVersion, String maxpagesize, String filter, ListContainersInclude include,
        RequestContext requestContext) {
        return this.serviceClient.listWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion,
            maxpagesize, filter, include, requestContext);
    }

    /**
     * Lists all containers and does not support a prefix like data plane.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param maxpagesize The maxpagesize parameter.
     * @param filter The filter parameter.
     * @param include The include parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return response schema.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ListContainerItems list(String subscriptionId, String resourceGroupName, String accountName,
        String apiVersion, String maxpagesize, String filter, ListContainersInclude include) {
        return this.serviceClient.list(subscriptionId, resourceGroupName, accountName, apiVersion, maxpagesize, filter,
            include);
    }

    /**
     * Lists all containers and does not support a prefix like data plane.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return response schema.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ListContainerItems list(String subscriptionId, String resourceGroupName, String accountName,
        String apiVersion) {
        return this.serviceClient.list(subscriptionId, resourceGroupName, accountName, apiVersion);
    }

    /**
     * Creates a new container under the specified account.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param blobContainer The blobContainer parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return properties of the blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BlobContainer> createWithResponse(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, BlobContainer blobContainer,
        RequestContext requestContext) {
        return this.serviceClient.createWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer, requestContext);
    }

    /**
     * Creates a new container under the specified account.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param blobContainer The blobContainer parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return properties of the blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BlobContainer create(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, BlobContainer blobContainer) {
        return this.serviceClient.create(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
            blobContainer);
    }

    /**
     * Updates container properties as specified in request body.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param blobContainer The blobContainer parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return properties of the blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BlobContainer> updateWithResponse(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, BlobContainer blobContainer,
        RequestContext requestContext) {
        return this.serviceClient.updateWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer, requestContext);
    }

    /**
     * Updates container properties as specified in request body.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param blobContainer The blobContainer parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return properties of the blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BlobContainer update(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, BlobContainer blobContainer) {
        return this.serviceClient.update(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
            blobContainer);
    }

    /**
     * Gets properties of a specified container.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return properties of a specified container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BlobContainer> getWithResponse(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, RequestContext requestContext) {
        return this.serviceClient.getWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, requestContext);
    }

    /**
     * Gets properties of a specified container.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return properties of a specified container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BlobContainer get(String subscriptionId, String resourceGroupName, String accountName, String containerName,
        String apiVersion) {
        return this.serviceClient.get(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
    }

    /**
     * Deletes specified container under its account.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteWithResponse(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, RequestContext requestContext) {
        return this.serviceClient.deleteWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, requestContext);
    }

    /**
     * Deletes specified container under its account.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void delete(String subscriptionId, String resourceGroupName, String accountName, String containerName,
        String apiVersion) {
        this.serviceClient.delete(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
    }

    /**
     * Sets legal hold tags.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param legalHold The legalHold parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the LegalHold property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<LegalHold> setLegalHoldWithResponse(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, LegalHold legalHold,
        RequestContext requestContext) {
        return this.serviceClient.setLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, legalHold, requestContext);
    }

    /**
     * Sets legal hold tags.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param legalHold The legalHold parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the LegalHold property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LegalHold setLegalHold(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, LegalHold legalHold) {
        return this.serviceClient.setLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold);
    }

    /**
     * Clears legal hold tags.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param legalHold The legalHold parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the LegalHold property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<LegalHold> clearLegalHoldWithResponse(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, LegalHold legalHold,
        RequestContext requestContext) {
        return this.serviceClient.clearLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, legalHold, requestContext);
    }

    /**
     * Clears legal hold tags.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param legalHold The legalHold parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the LegalHold property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public LegalHold clearLegalHold(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, LegalHold legalHold) {
        return this.serviceClient.clearLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold);
    }

    /**
     * Creates or updates an unlocked immutability policy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @param parameters The parameters parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ImmutabilityPolicy> createOrUpdateImmutabilityPolicyWithResponse(String subscriptionId,
        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
        ImmutabilityPolicy parameters, RequestContext requestContext) {
        return this.serviceClient.createOrUpdateImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName,
            accountName, containerName, apiVersion, ifMatch, parameters, requestContext);
    }

    /**
     * Creates or updates an unlocked immutability policy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @param parameters The parameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy createOrUpdateImmutabilityPolicy(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters) {
        return this.serviceClient.createOrUpdateImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, parameters);
    }

    /**
     * Creates or updates an unlocked immutability policy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy createOrUpdateImmutabilityPolicy(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion) {
        return this.serviceClient.createOrUpdateImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion);
    }

    /**
     * Gets the existing immutability policy along with the corresponding ETag.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the existing immutability policy along with the corresponding ETag.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ImmutabilityPolicy> getImmutabilityPolicyWithResponse(String subscriptionId,
        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
        RequestContext requestContext) {
        return this.serviceClient.getImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext);
    }

    /**
     * Gets the existing immutability policy along with the corresponding ETag.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the existing immutability policy along with the corresponding ETag.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy getImmutabilityPolicy(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, String ifMatch) {
        return this.serviceClient.getImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, ifMatch);
    }

    /**
     * Gets the existing immutability policy along with the corresponding ETag.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the existing immutability policy along with the corresponding ETag.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy getImmutabilityPolicy(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion) {
        return this.serviceClient.getImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion);
    }

    /**
     * Aborts an unlocked immutability policy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ImmutabilityPolicy> deleteImmutabilityPolicyWithResponse(String subscriptionId,
        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
        RequestContext requestContext) {
        return this.serviceClient.deleteImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext);
    }

    /**
     * Aborts an unlocked immutability policy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy deleteImmutabilityPolicy(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch) {
        return this.serviceClient.deleteImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch);
    }

    /**
     * Sets the ImmutabilityPolicy to Locked state.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ImmutabilityPolicy> lockImmutabilityPolicyWithResponse(String subscriptionId,
        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
        RequestContext requestContext) {
        return this.serviceClient.lockImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext);
    }

    /**
     * Sets the ImmutabilityPolicy to Locked state.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy lockImmutabilityPolicy(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch) {
        return this.serviceClient.lockImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, ifMatch);
    }

    /**
     * Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @param parameters The parameters parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ImmutabilityPolicy> extendImmutabilityPolicyWithResponse(String subscriptionId,
        String resourceGroupName, String accountName, String containerName, String apiVersion, String ifMatch,
        ImmutabilityPolicy parameters, RequestContext requestContext) {
        return this.serviceClient.extendImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, parameters, requestContext);
    }

    /**
     * Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @param parameters The parameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy extendImmutabilityPolicy(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters) {
        return this.serviceClient.extendImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, parameters);
    }

    /**
     * Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param containerName The containerName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param ifMatch The ifMatch parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the ImmutabilityPolicy property of a blob container.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ImmutabilityPolicy extendImmutabilityPolicy(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch) {
        return this.serviceClient.extendImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch);
    }


    /********************* GENERATED WRAPPER CODE *********************/
    /**
     * Combined Methods: setLegalHold, setLegalHoldWithResponse
     * Reason: Both methods perform the same operation of setting legal hold tags, differing only by whether a Response wrapper and RequestContext are used. Wrapping them provides a single entry point using the options pattern for convenience and future extensibility.
     * 
     * Comment: Sets legal hold tags on a blob container, with optional support for request context and response details.
     */
    public Response<LegalHold> setLegalHold(SetLegalHoldOptions options, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.setLegalHoldWithResponse(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getLegalHold(),
                requestContext
            );
        } else {
            LegalHold result = this.serviceClient.setLegalHold(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getLegalHold()
            );
            return new SimpleResponse<>(null, 200, null, result);
        }
    }
    
    /**
     * Combined Methods: clearLegalHold, clearLegalHoldWithResponse
     * Reason: Both methods clear legal hold tags, with the only difference being the presence of a Response wrapper and RequestContext. Wrapping them into a single method using the options pattern simplifies usage and allows for future extensibility.
     * 
     * Comment: Clears legal hold tags on a blob container, with optional support for request context and response details.
     */
    public Response<LegalHold> clearLegalHold(ClearLegalHoldOptions options, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.clearLegalHoldWithResponse(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getLegalHold(),
                requestContext
            );
        } else {
            LegalHold result = this.serviceClient.clearLegalHold(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getLegalHold()
            );
            return new SimpleResponse<>(null, 200, null, result);
        }
    }
    
    /**
     * Combined Methods: create, createWithResponse
     * Reason: Both methods create a new container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them using the options pattern provides a single, extensible entry point for container creation.
     * 
     * Comment: Creates a new blob container, with optional support for request context and response details.
     */
    public Response<BlobContainer> create(CreateContainerOptions options, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.createWithResponse(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getBlobContainer(),
                requestContext
            );
        } else {
            BlobContainer result = this.serviceClient.create(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getBlobContainer()
            );
            return new SimpleResponse<>(null, 200, null, result);
        }
    }
    
    /**
     * Combined Methods: update, updateWithResponse
     * Reason: Both methods update container properties, differing only by whether a Response wrapper and RequestContext are used. Wrapping them into a single method using the options pattern improves usability and extensibility.
     * 
     * Comment: Updates properties of a blob container, with optional support for request context and response details.
     */
    public Response<BlobContainer> update(UpdateContainerOptions options, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.updateWithResponse(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getBlobContainer(),
                requestContext
            );
        } else {
            BlobContainer result = this.serviceClient.update(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                options.getBlobContainer()
            );
            return new SimpleResponse<>(null, 200, null, result);
        }
    }
    
    /**
     * Combined Methods: get, getWithResponse
     * Reason: Both methods retrieve properties of a container, with the only difference being the presence of a Response wrapper and RequestContext. Wrapping them using the options pattern provides a unified and extensible API.
     * 
     * Comment: Gets properties of a specified blob container, with optional support for request context and response details.
     */
    public Response<BlobContainer> get(GetContainerOptions options, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.getWithResponse(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                requestContext
            );
        } else {
            BlobContainer result = this.serviceClient.get(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion()
            );
            return new SimpleResponse<>(null, 200, null, result);
        }
    }
    
    /**
     * Combined Methods: delete, deleteWithResponse
     * Reason: Both methods delete a container, differing only by whether a Response wrapper and RequestContext are used. Wrapping them using the options pattern provides a single, extensible entry point for container deletion.
     * 
     * Comment: Deletes a specified blob container, with optional support for request context and response details.
     */
    public Response<Void> delete(DeleteContainerOptions options, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.deleteWithResponse(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion(),
                requestContext
            );
        } else {
            this.serviceClient.delete(
                options.getSubscriptionId(),
                options.getResourceGroupName(),
                options.getAccountName(),
                options.getContainerName(),
                options.getApiVersion()
            );
            return new SimpleResponse<>(null, 204, null, null);
        }
    }

    /********************* END OF GENERATED CODE *********************/

}
