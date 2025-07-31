package azurestoragemanagement.implementation;

import azurestoragemanagement.BlobServiceItems;
import azurestoragemanagement.BlobServiceProperties;
import azurestoragemanagement.ErrorResponse;
import io.clientcore.core.annotations.ReturnType;
import io.clientcore.core.annotations.ServiceInterface;
import io.clientcore.core.annotations.ServiceMethod;
import io.clientcore.core.http.RestProxy;
import io.clientcore.core.http.annotations.BodyParam;
import io.clientcore.core.http.annotations.HeaderParam;
import io.clientcore.core.http.annotations.HostParam;
import io.clientcore.core.http.annotations.HttpRequestInformation;
import io.clientcore.core.http.annotations.PathParam;
import io.clientcore.core.http.annotations.QueryParam;
import io.clientcore.core.http.annotations.UnexpectedResponseExceptionDetail;
import io.clientcore.core.http.models.HttpMethod;
import io.clientcore.core.http.models.HttpResponseException;
import io.clientcore.core.http.models.RequestContext;
import io.clientcore.core.http.models.Response;
import io.clientcore.core.http.pipeline.HttpPipeline;
import java.lang.reflect.InvocationTargetException;

/**
 * An instance of this class provides access to all the operations defined in BlobServices.
 */
public final class BlobServicesImpl {
    /**
     * The proxy service used to perform REST calls.
     */
    private final BlobServicesService service;

    /**
     * The service client containing this operation class.
     */
    private final AzureStorageManagementClientImpl client;

    /**
     * Initializes an instance of BlobServicesImpl.
     * 
     * @param client the instance of the service client containing this operation class.
     */
    BlobServicesImpl(AzureStorageManagementClientImpl client) {
        this.service = RestProxy.create(BlobServicesService.class, client.getHttpPipeline());
        this.client = client;
    }

    /**
     * The interface defining all the services for AzureStorageManagementClientBlobServices to be used by the proxy
     * service to perform REST calls.
     */
    @ServiceInterface(name = "AzureStorageManageme", host = "{endpoint}")
    public interface BlobServicesService {
        static BlobServicesService getNewInstance(HttpPipeline pipeline) {
            try {
                Class<?> clazz = Class.forName("azurestoragemanagement.implementation.BlobServicesServiceImpl");
                return (BlobServicesService) clazz.getMethod("getNewInstance", HttpPipeline.class)
                    .invoke(null, pipeline);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InvocationTargetException e) {
                throw new RuntimeException(e);
            }

        }

        @HttpRequestInformation(
            method = HttpMethod.GET,
            path = "/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/blobServices",
            expectedStatusCodes = { 200 })
        @UnexpectedResponseExceptionDetail(exceptionBodyClass = ErrorResponse.class)
        Response<BlobServiceItems> list(@HostParam("endpoint") String endpoint,
            @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName,
            @QueryParam("api-version") String apiVersion, @HeaderParam("Accept") String accept,
            RequestContext requestContext);

        @HttpRequestInformation(
            method = HttpMethod.PUT,
            path = "/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/blobServices/{BlobServicesName}",
            expectedStatusCodes = { 200 })
        @UnexpectedResponseExceptionDetail(exceptionBodyClass = ErrorResponse.class)
        Response<BlobServiceProperties> setServiceProperties(@HostParam("endpoint") String endpoint,
            @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName,
            @PathParam("BlobServicesName") String blobServicesName, @QueryParam("api-version") String apiVersion,
            @HeaderParam("Content-Type") String contentType, @HeaderParam("Accept") String accept,
            @BodyParam("application/json") BlobServiceProperties parameters, RequestContext requestContext);

        @HttpRequestInformation(
            method = HttpMethod.GET,
            path = "/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/blobServices/{BlobServicesName}",
            expectedStatusCodes = { 200 })
        @UnexpectedResponseExceptionDetail(exceptionBodyClass = ErrorResponse.class)
        Response<BlobServiceProperties> getServiceProperties(@HostParam("endpoint") String endpoint,
            @PathParam("subscriptionId") String subscriptionId,
            @PathParam("resourceGroupName") String resourceGroupName, @PathParam("accountName") String accountName,
            @PathParam("BlobServicesName") String blobServicesName, @QueryParam("api-version") String apiVersion,
            @HeaderParam("Accept") String accept, RequestContext requestContext);
    }

    /**
     * List blob services of storage account. It returns a collection of one object named default.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return list of blob services.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BlobServiceItems> listWithResponse(String subscriptionId, String resourceGroupName,
        String accountName, String apiVersion, RequestContext requestContext) {
        final String accept = "application/json";
        return service.list(this.client.getEndpoint(), subscriptionId, resourceGroupName, accountName, apiVersion,
            accept, requestContext);
    }

    /**
     * List blob services of storage account. It returns a collection of one object named default.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return list of blob services.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BlobServiceItems list(String subscriptionId, String resourceGroupName, String accountName,
        String apiVersion) {
        return listWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion, RequestContext.none())
            .getValue();
    }

    /**
     * Sets the properties of a storage account's Blob service.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param parameters The parameters parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of a storage account's Blob service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BlobServiceProperties> setServicePropertiesWithResponse(String subscriptionId,
        String resourceGroupName, String accountName, String apiVersion, BlobServiceProperties parameters,
        RequestContext requestContext) {
        final String blobServicesName = "default";
        final String contentType = "application/json";
        final String accept = "application/json";
        return service.setServiceProperties(this.client.getEndpoint(), subscriptionId, resourceGroupName, accountName,
            blobServicesName, apiVersion, contentType, accept, parameters, requestContext);
    }

    /**
     * Sets the properties of a storage account's Blob service.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param parameters The parameters parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of a storage account's Blob service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BlobServiceProperties setServiceProperties(String subscriptionId, String resourceGroupName,
        String accountName, String apiVersion, BlobServiceProperties parameters) {
        return setServicePropertiesWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion, parameters,
            RequestContext.none()).getValue();
    }

    /**
     * Gets the properties of a storage account's Blob service.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @param requestContext The context to configure the HTTP request before HTTP client sends it.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of a storage account's Blob service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BlobServiceProperties> getServicePropertiesWithResponse(String subscriptionId,
        String resourceGroupName, String accountName, String apiVersion, RequestContext requestContext) {
        final String blobServicesName = "default";
        final String accept = "application/json";
        return service.getServiceProperties(this.client.getEndpoint(), subscriptionId, resourceGroupName, accountName,
            blobServicesName, apiVersion, accept, requestContext);
    }

    /**
     * Gets the properties of a storage account's Blob service.
     * 
     * @param subscriptionId The subscriptionId parameter.
     * @param resourceGroupName The resourceGroupName parameter.
     * @param accountName The accountName parameter.
     * @param apiVersion The apiVersion parameter.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws HttpResponseException thrown if the service returns an error.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the properties of a storage account's Blob service.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BlobServiceProperties getServiceProperties(String subscriptionId, String resourceGroupName,
        String accountName, String apiVersion) {
        return getServicePropertiesWithResponse(subscriptionId, resourceGroupName, accountName, apiVersion,
            RequestContext.none()).getValue();
    }
}
