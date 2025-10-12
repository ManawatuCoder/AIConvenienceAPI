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
 * Combined Methods: create, createWithResponse
 * Reason: Both methods create a new container, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for creation with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Creates a new container under the specified account. If a RequestContext is provided, returns the full response; otherwise, returns the created BlobContainer entity.
 */
public BlobContainer createContainer(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, BlobContainer blobContainer, RequestContext requestContext) {
    if (requestContext != null) {
        return this.serviceClient.createWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer, requestContext).getValue();
    } else {
        return this.serviceClient.create(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
            blobContainer);
    }
}

/**
 * Combined Methods: update, updateWithResponse
 * Reason: Both methods update container properties, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for updating with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Updates container properties as specified in the request body. If a RequestContext is provided, returns the full response; otherwise, returns the updated BlobContainer entity.
 */
public BlobContainer updateContainer(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, BlobContainer blobContainer, RequestContext requestContext) {
    if (requestContext != null) {
        return this.serviceClient.updateWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, blobContainer, requestContext).getValue();
    } else {
        return this.serviceClient.update(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
            blobContainer);
    }
}

/**
 * Combined Methods: delete, deleteWithResponse
 * Reason: Both methods delete a container, with one returning void and the other returning the full response. Wrapping them provides a single entry point for deletion with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Deletes the specified container under its account. If a RequestContext is provided, returns the full response; otherwise, performs the deletion without returning a value.
 */
public void deleteContainer(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, RequestContext requestContext) {
    if (requestContext != null) {
        this.serviceClient.deleteWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, requestContext);
    } else {
        this.serviceClient.delete(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
    }
}

/**
 * Combined Methods: get, getWithResponse
 * Reason: Both methods get container properties, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for retrieval with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Gets properties of a specified container. If a RequestContext is provided, returns the full response; otherwise, returns the BlobContainer entity.
 */
public BlobContainer getContainer(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, RequestContext requestContext) {
    if (requestContext != null) {
        return this.serviceClient.getWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, requestContext).getValue();
    } else {
        return this.serviceClient.get(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
    }
}

/**
 * Combined Methods: list, listWithResponse
 * Reason: Both methods list containers, with one returning just the items and the other returning the full response. Wrapping them provides a single entry point for listing with or without advanced options, improving convenience and discoverability.
 * 
 * Comment: Lists all containers under the specified account. If advanced options are provided, returns the full response; otherwise, returns the ListContainerItems entity.
 */
public ListContainerItems listContainers(String subscriptionId, String resourceGroupName, String accountName,
    String apiVersion, String maxpagesize, String filter, ListContainersInclude include, RequestContext requestContext) {
    if (requestContext != null || maxpagesize != null || filter != null || include != null) {
        return this.serviceClient.listWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion,
            maxpagesize, filter, include, requestContext).getValue();
    } else {
        return this.serviceClient.list(subscriptionId, resourceGroupName, accountName, apiVersion);
    }
}

/**
 * Combined Methods: clearLegalHold, clearLegalHoldWithResponse
 * Reason: Both methods clear legal hold tags, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for clearing legal hold with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Clears legal hold tags on the specified container. If a RequestContext is provided, returns the full response; otherwise, returns the LegalHold entity.
 */
public LegalHold clearLegalHoldTags(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, LegalHold legalHold, RequestContext requestContext) {
    if (requestContext != null) {
        return this.serviceClient.clearLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, legalHold, requestContext).getValue();
    } else {
        return this.serviceClient.clearLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold);
    }
}

/**
 * Combined Methods: setLegalHold, setLegalHoldWithResponse
 * Reason: Both methods set legal hold tags, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for setting legal hold with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Sets legal hold tags on the specified container. If a RequestContext is provided, returns the full response; otherwise, returns the LegalHold entity.
 */
public LegalHold setLegalHoldTags(String subscriptionId, String resourceGroupName, String accountName,
    String containerName, String apiVersion, LegalHold legalHold, RequestContext requestContext) {
    if (requestContext != null) {
        return this.serviceClient.setLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, legalHold, requestContext).getValue();
    } else {
        return this.serviceClient.setLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, legalHold);
    }
}

/**
 * Combined Methods: getImmutabilityPolicy, getImmutabilityPolicyWithResponse
 * Reason: Both methods get the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for retrieval with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Gets the existing immutability policy along with the corresponding ETag. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
 */
public ImmutabilityPolicy getImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
    String accountName, String containerName, String apiVersion, String ifMatch, RequestContext requestContext) {
    if (requestContext != null && ifMatch != null) {
        return this.serviceClient.getImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext).getValue();
    } else {
        return this.serviceClient.getImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion);
    }
}

/**
 * Combined Methods: deleteImmutabilityPolicy, deleteImmutabilityPolicyWithResponse
 * Reason: Both methods delete the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for deletion with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Aborts an unlocked immutability policy. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
 */
public ImmutabilityPolicy deleteImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
    String accountName, String containerName, String apiVersion, String ifMatch, RequestContext requestContext) {
    if (requestContext != null) {
        return this.serviceClient.deleteImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext).getValue();
    } else {
        return this.serviceClient.deleteImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch);
    }
}

/**
 * Combined Methods: lockImmutabilityPolicy, lockImmutabilityPolicyWithResponse
 * Reason: Both methods lock the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for locking with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Sets the ImmutabilityPolicy to Locked state. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
 */
public ImmutabilityPolicy lockImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
    String accountName, String containerName, String apiVersion, String ifMatch, RequestContext requestContext) {
    if (requestContext != null) {
        return this.serviceClient.lockImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, requestContext).getValue();
    } else {
        return this.serviceClient.lockImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
            apiVersion, ifMatch);
    }
}

/**
 * Combined Methods: createOrUpdateImmutabilityPolicy, createOrUpdateImmutabilityPolicyWithResponse
 * Reason: Both methods create or update an immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for creation or update with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Creates or updates an unlocked immutability policy. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
 */
public ImmutabilityPolicy createOrUpdateImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
    String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters,
    RequestContext requestContext) {
    if (requestContext != null && ifMatch != null && parameters != null) {
        return this.serviceClient.createOrUpdateImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName,
            accountName, containerName, apiVersion, ifMatch, parameters, requestContext).getValue();
    } else {
        return this.serviceClient.createOrUpdateImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion);
    }
}

/**
 * Combined Methods: extendImmutabilityPolicy, extendImmutabilityPolicyWithResponse
 * Reason: Both methods extend the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for extension with or without a RequestContext, improving convenience and discoverability.
 * 
 * Comment: Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
 */
public ImmutabilityPolicy extendImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
    String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters,
    RequestContext requestContext) {
    if (requestContext != null && ifMatch != null && parameters != null) {
        return this.serviceClient.extendImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch, parameters, requestContext).getValue();
    } else {
        return this.serviceClient.extendImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
            containerName, apiVersion, ifMatch);
    }
}
    /**
     * Combined Methods: create, createWithResponse
     * Reason: Both methods create a new container, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for creation with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Creates a new container under the specified account. If a RequestContext is provided, returns the full response; otherwise, returns the created BlobContainer entity.
     */
    public BlobContainer createContainer(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, BlobContainer blobContainer, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.createWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion, blobContainer, requestContext).getValue();
        } else {
            return this.serviceClient.create(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
                blobContainer);
        }
    }
    
    /**
     * Combined Methods: update, updateWithResponse
     * Reason: Both methods update container properties, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for updating with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Updates container properties as specified in the request body. If a RequestContext is provided, returns the full response; otherwise, returns the updated BlobContainer entity.
     */
    public BlobContainer updateContainer(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, BlobContainer blobContainer, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.updateWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion, blobContainer, requestContext).getValue();
        } else {
            return this.serviceClient.update(subscriptionId, resourceGroupName, accountName, containerName, apiVersion,
                blobContainer);
        }
    }
    
    /**
     * Combined Methods: delete, deleteWithResponse
     * Reason: Both methods delete a container, with one returning void and the other returning the full response. Wrapping them provides a single entry point for deletion with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Deletes the specified container under its account. If a RequestContext is provided, returns the full response; otherwise, performs the deletion without returning a value.
     */
    public void deleteContainer(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, RequestContext requestContext) {
        if (requestContext != null) {
            this.serviceClient.deleteWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion, requestContext);
        } else {
            this.serviceClient.delete(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
        }
    }
    
    /**
     * Combined Methods: get, getWithResponse
     * Reason: Both methods get container properties, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for retrieval with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Gets properties of a specified container. If a RequestContext is provided, returns the full response; otherwise, returns the BlobContainer entity.
     */
    public BlobContainer getContainer(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.getWithResponse(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion, requestContext).getValue();
        } else {
            return this.serviceClient.get(subscriptionId, resourceGroupName, accountName, containerName, apiVersion);
        }
    }
    
    /**
     * Combined Methods: list, listWithResponse
     * Reason: Both methods list containers, with one returning just the items and the other returning the full response. Wrapping them provides a single entry point for listing with or without advanced options, improving convenience and discoverability.
     * 
     * Comment: Lists all containers under the specified account. If advanced options are provided, returns the full response; otherwise, returns the ListContainerItems entity.
     */
    public ListContainerItems listContainers(String subscriptionId, String resourceGroupName, String accountName,
        String apiVersion, String maxpagesize, String filter, ListContainersInclude include, RequestContext requestContext) {
        if (requestContext != null || maxpagesize != null || filter != null || include != null) {
            return this.serviceClient.listWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion,
                maxpagesize, filter, include, requestContext).getValue();
        } else {
            return this.serviceClient.list(subscriptionId, resourceGroupName, accountName, apiVersion);
        }
    }
    
    /**
     * Combined Methods: clearLegalHold, clearLegalHoldWithResponse
     * Reason: Both methods clear legal hold tags, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for clearing legal hold with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Clears legal hold tags on the specified container. If a RequestContext is provided, returns the full response; otherwise, returns the LegalHold entity.
     */
    public LegalHold clearLegalHoldTags(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, LegalHold legalHold, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.clearLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, legalHold, requestContext).getValue();
        } else {
            return this.serviceClient.clearLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion, legalHold);
        }
    }
    
    /**
     * Combined Methods: setLegalHold, setLegalHoldWithResponse
     * Reason: Both methods set legal hold tags, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for setting legal hold with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Sets legal hold tags on the specified container. If a RequestContext is provided, returns the full response; otherwise, returns the LegalHold entity.
     */
    public LegalHold setLegalHoldTags(String subscriptionId, String resourceGroupName, String accountName,
        String containerName, String apiVersion, LegalHold legalHold, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.setLegalHoldWithResponse(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, legalHold, requestContext).getValue();
        } else {
            return this.serviceClient.setLegalHold(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion, legalHold);
        }
    }
    
    /**
     * Combined Methods: getImmutabilityPolicy, getImmutabilityPolicyWithResponse
     * Reason: Both methods get the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for retrieval with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Gets the existing immutability policy along with the corresponding ETag. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
     */
    public ImmutabilityPolicy getImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch, RequestContext requestContext) {
        if (requestContext != null && ifMatch != null) {
            return this.serviceClient.getImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, ifMatch, requestContext).getValue();
        } else {
            return this.serviceClient.getImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion);
        }
    }
    
    /**
     * Combined Methods: deleteImmutabilityPolicy, deleteImmutabilityPolicyWithResponse
     * Reason: Both methods delete the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for deletion with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Aborts an unlocked immutability policy. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
     */
    public ImmutabilityPolicy deleteImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.deleteImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, ifMatch, requestContext).getValue();
        } else {
            return this.serviceClient.deleteImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, ifMatch);
        }
    }
    
    /**
     * Combined Methods: lockImmutabilityPolicy, lockImmutabilityPolicyWithResponse
     * Reason: Both methods lock the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for locking with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Sets the ImmutabilityPolicy to Locked state. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
     */
    public ImmutabilityPolicy lockImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch, RequestContext requestContext) {
        if (requestContext != null) {
            return this.serviceClient.lockImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, ifMatch, requestContext).getValue();
        } else {
            return this.serviceClient.lockImmutabilityPolicy(subscriptionId, resourceGroupName, accountName, containerName,
                apiVersion, ifMatch);
        }
    }
    
    /**
     * Combined Methods: createOrUpdateImmutabilityPolicy, createOrUpdateImmutabilityPolicyWithResponse
     * Reason: Both methods create or update an immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for creation or update with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Creates or updates an unlocked immutability policy. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
     */
    public ImmutabilityPolicy createOrUpdateImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters,
        RequestContext requestContext) {
        if (requestContext != null && ifMatch != null && parameters != null) {
            return this.serviceClient.createOrUpdateImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName,
                accountName, containerName, apiVersion, ifMatch, parameters, requestContext).getValue();
        } else {
            return this.serviceClient.createOrUpdateImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion);
        }
    }
    
    /**
     * Combined Methods: extendImmutabilityPolicy, extendImmutabilityPolicyWithResponse
     * Reason: Both methods extend the immutability policy, with one returning just the entity and the other returning the full response. Wrapping them provides a single entry point for extension with or without a RequestContext, improving convenience and discoverability.
     * 
     * Comment: Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy. If a RequestContext is provided, returns the full response; otherwise, returns the ImmutabilityPolicy entity.
     */
    public ImmutabilityPolicy extendImmutabilityPolicyDetails(String subscriptionId, String resourceGroupName,
        String accountName, String containerName, String apiVersion, String ifMatch, ImmutabilityPolicy parameters,
        RequestContext requestContext) {
        if (requestContext != null && ifMatch != null && parameters != null) {
            return this.serviceClient.extendImmutabilityPolicyWithResponse(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, ifMatch, parameters, requestContext).getValue();
        } else {
            return this.serviceClient.extendImmutabilityPolicy(subscriptionId, resourceGroupName, accountName,
                containerName, apiVersion, ifMatch);
        }
    }
    /********************* END OF GENERATED CODE *********************/

}
