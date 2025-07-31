import "@typespec/http";
import "@typespec/rest";
import "@typespec/openapi";

using Http;
using Rest;
using OpenAPI;

@service
@server("https://management.azure.com", "Azure Resource Manager endpoint")
@info(#{
    title: "Azure Storage Management API - Blob Services",
    version: "2019-06-01",
})
namespace AzureStorageManagement;

// Common Azure types
@doc("Azure subscription ID")
scalar SubscriptionId extends string;

@doc("Azure resource group name")
scalar ResourceGroupName extends string;

@doc("Storage account name")
scalar StorageAccountName extends string;

@doc("Container name between 3 and 63 characters")
@minLength(3)
@maxLength(63)
@pattern("^[a-z0-9]([a-z0-9\\-]*[a-z0-9])?$")
scalar ContainerName extends string;

// Enums
@doc("Specifies whether data in the container may be accessed publicly and the level of access")
enum PublicAccess {
    Container,
    Blob,
    None,
}

@doc("The lease status of the container")
enum LeaseStatus {
    Locked,
    Unlocked,
}

@doc("Lease state of the container")
enum LeaseState {
    Available,
    Leased,
    Expired,
    Breaking,
    Broken,
}

@doc("Specifies whether the lease on a container is of infinite or fixed duration")
enum LeaseDuration {
    Infinite,
    Fixed,
}

@doc("The ImmutabilityPolicy state of a blob container")
enum ImmutabilityPolicyState {
    Locked,
    Unlocked,
}

@doc("The ImmutabilityPolicy update type of a blob container")
enum ImmutabilityPolicyUpdateType {
    put,
    lock,
    extend,
}

@doc("Used to include the properties for soft deleted blob containers")
enum ListContainersInclude {
    deleted,
}

@doc("Last access time tracking policy name")
enum LastAccessTimeTrackingPolicyName {
    AccessTimeTracking,
}

// Base Azure Resource models
@doc("Azure Entity Resource")
model AzureEntityResource {
    @doc("Fully qualified resource ID for the resource")
    id?: string;

    @doc("The name of the resource")
    name?: string;

    @doc("The type of the resource")
    type?: string;

    @doc("Resource Etag")
    etag?: string;
}

@doc("Azure Resource")
model AzureResource {
    @doc("Fully qualified resource ID for the resource")
    id?: string;

    @doc("The name of the resource")
    name?: string;

    @doc("The type of the resource")
    type?: string;
}

// Data models
@doc("A tag of the LegalHold of a blob container")
model TagProperty {
    @doc("The tag value")
    tag?: string;

    @doc("Returns the date and time the tag was added")
    timestamp?: utcDateTime;

    @doc("Returns the Object ID of the user who added the tag")
    objectIdentifier?: string;

    @doc("Returns the Tenant ID that issued the token for the user who added the tag")
    tenantId?: string;

    @doc("Returns the User Principal Name of the user who added the tag")
    upn?: string;
}

@doc("The LegalHold property of a blob container")
model LegalHoldProperties {
    @doc("The hasLegalHold public property")
    hasLegalHold?: boolean;

    @doc("The list of LegalHold tags of a blob container")
    tags?: TagProperty[];
}

@doc("An update history of the ImmutabilityPolicy of a blob container")
model UpdateHistoryProperty {
    @doc("The ImmutabilityPolicy update type")
    update?: ImmutabilityPolicyUpdateType;

    @doc("The immutability period since creation in days")
    immutabilityPeriodSinceCreationInDays?: int32;

    @doc("Returns the date and time the ImmutabilityPolicy was updated")
    timestamp?: utcDateTime;

    @doc("Returns the Object ID of the user who updated the ImmutabilityPolicy")
    objectIdentifier?: string;

    @doc("Returns the Tenant ID that issued the token for the user")
    tenantId?: string;

    @doc("Returns the User Principal Name of the user")
    upn?: string;
}

@doc("The properties of an ImmutabilityPolicy of a blob container")
model ImmutabilityPolicyProperty {
    @doc("The immutability period for the blobs in the container since the policy creation, in days")
    immutabilityPeriodSinceCreationInDays?: int32;

    @doc("The ImmutabilityPolicy state of a blob container")
    state?: ImmutabilityPolicyState;

    @doc("Allow protected append writes")
    allowProtectedAppendWrites?: boolean;
}

@doc("The properties of an ImmutabilityPolicy of a blob container")
model ImmutabilityPolicyProperties {
    ...ImmutabilityPolicyProperty;

    @doc("ImmutabilityPolicy Etag")
    etag?: string;

    @doc("The ImmutabilityPolicy update history of the blob container")
    updateHistory?: UpdateHistoryProperty[];
}

@doc("The properties of a container")
model ContainerProperties {
    @doc("The version of the deleted blob container")
    version?: string;

    @doc("Indicates whether the blob container was deleted")
    deleted?: boolean;

    @doc("Blob container deletion time")
    deletedTime?: utcDateTime;

    @doc("Remaining retention days for soft deleted blob container")
    remainingRetentionDays?: int32;

    @doc("Default the container to use specified encryption scope for all writes")
    defaultEncryptionScope?: string;

    @doc("Block override of encryption scope from the container default")
    denyEncryptionScopeOverride?: boolean;

    @doc("Specifies whether data in the container may be accessed publicly and the level of access")
    publicAccess?: PublicAccess;

    @doc("Returns the date and time the container was last modified")
    lastModifiedTime?: utcDateTime;

    @doc("The lease status of the container")
    leaseStatus?: LeaseStatus;

    @doc("Lease state of the container")
    leaseState?: LeaseState;

    @doc("Specifies whether the lease on a container is of infinite or fixed duration")
    leaseDuration?: LeaseDuration;

    @doc("A name-value pair to associate with the container as metadata")
    metadata?: Record<string>;

    @doc("The ImmutabilityPolicy property of the container")
    immutabilityPolicy?: ImmutabilityPolicyProperties;

    @doc("The LegalHold property of the container")
    legalHold?: LegalHoldProperties;

    @doc("The hasLegalHold public property")
    hasLegalHold?: boolean;

    @doc("The hasImmutabilityPolicy public property")
    hasImmutabilityPolicy?: boolean;
}

@doc("Properties of the blob container")
model BlobContainer extends AzureEntityResource {
    @doc("Properties of the blob container")
    properties?: ContainerProperties;
}

@doc("The blob container properties be listed out")
model ListContainerItem extends AzureEntityResource {
    @doc("The blob container properties be listed out")
    properties?: ContainerProperties;
}

@doc("Response schema. Contains list of blobs returned, and if paging is requested or required, a URL to next page of containers")
model ListContainerItems {
    @doc("List of blobs containers returned")
    value?: ListContainerItem[];

    @doc("Request URL that can be used to query next page of containers")
    nextLink?: string;
}

@doc("The ImmutabilityPolicy property of a blob container")
model ImmutabilityPolicy extends AzureEntityResource {
    @doc("The properties of an ImmutabilityPolicy of a blob container")
    properties: ImmutabilityPolicyProperty;
}

@doc("The LegalHold property of a blob container")
model LegalHold {
    @doc("The hasLegalHold public property")
    hasLegalHold?: boolean;

    @doc("Each tag should be 3 to 23 alphanumeric characters")
    @minItems(1)
    tags: string[];
}

@doc("The blob service properties for change feed events")
model ChangeFeed {
    @doc("Indicates whether change feed event logging is enabled for the Blob service")
    enabled?: boolean;

    @doc("Indicates the duration of changeFeed retention in days")
    @minValue(1)
    @maxValue(146000)
    retentionInDays?: int32;
}

@doc("The blob service properties for blob restore policy")
model RestorePolicyProperties {
    @doc("Blob restore is enabled if set to true")
    enabled: boolean;

    @doc("How long this blob can be restored")
    @minValue(1)
    @maxValue(365)
    days?: int32;

    @doc("Deprecated in favor of minRestoreTime property")
    lastEnabledTime?: utcDateTime;

    @doc("Returns the minimum date and time that the restore can be started")
    minRestoreTime?: utcDateTime;
}

@doc("The blob service properties for Last access time based tracking policy")
model LastAccessTimeTrackingPolicy {
    @doc("When set to true last access time based tracking is enabled")
    enable: boolean;

    @doc("Name of the policy")
    name?: LastAccessTimeTrackingPolicyName;

    @doc("The field specifies blob object tracking granularity in days")
    trackingGranularityInDays?: int32;

    @doc("An array of predefined supported blob types")
    blobType?: string[];
}

@doc("The properties of a storage account's Blob service")
model BlobServicePropertiesInner {
    @doc("Specifies CORS rules for the Blob service")
    cors?: unknown; // Reference to external common.json

    @doc("DefaultServiceVersion indicates the default version to use for requests to the Blob service")
    defaultServiceVersion?: string;

    @doc("The blob service properties for blob soft delete")
    deleteRetentionPolicy?: unknown; // Reference to external common.json

    @doc("Versioning is enabled if set to true")
    isVersioningEnabled?: boolean;

    @doc("Deprecated in favor of isVersioningEnabled property")
    automaticSnapshotPolicyEnabled?: boolean;

    @doc("The blob service properties for change feed events")
    changeFeed?: ChangeFeed;

    @doc("The blob service properties for blob restore policy")
    restorePolicy?: RestorePolicyProperties;

    @doc("The blob service properties for container soft delete")
    containerDeleteRetentionPolicy?: unknown; // Reference to external common.json

    @doc("The blob service property to configure last access time based tracking policy")
    lastAccessTimeTrackingPolicy?: LastAccessTimeTrackingPolicy;
}

@doc("The properties of a storage account's Blob service")
model BlobServiceProperties extends AzureResource {
    @doc("The properties of a storage account's Blob service")
    properties?: BlobServicePropertiesInner;

    @doc("Sku name and tier")
    sku?: unknown; // Reference to external common.json
}

@doc("List of blob services")
model BlobServiceItems {
    @doc("List of blob services returned")
    value?: BlobServiceProperties[];
}

// Error response model
@error
model ErrorResponse {
    @doc("Error code")
    code: string;

    @doc("Error message")
    message: string;
}

// API Operations
@tag("BlobService")
@route("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/blobServices")
interface BlobServices {
    @doc("List blob services of storage account. It returns a collection of one object named default.")
    @get
    list(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @query("api-version") apiVersion: string,
    ): BlobServiceItems | ErrorResponse;

    @doc("Sets the properties of a storage account's Blob service")
    @put
    @route("{BlobServicesName}")
    setServiceProperties(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path BlobServicesName: "default",
        @query("api-version") apiVersion: string,
        @body parameters: BlobServiceProperties,
    ): BlobServiceProperties | ErrorResponse;

    @doc("Gets the properties of a storage account's Blob service")
    @get
    @route("{BlobServicesName}")
    getServiceProperties(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path BlobServicesName: "default",
        @query("api-version") apiVersion: string,
    ): BlobServiceProperties | ErrorResponse;
}

@tag("BlobContainers")
@route("/subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Storage/storageAccounts/{accountName}/blobServices/default/containers")
interface BlobContainers {
    @doc("Lists all containers and does not support a prefix like data plane")
    @get
    list(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @query("api-version") apiVersion: string,
        @query("$maxpagesize") maxpagesize?: string,
        @query("$filter") filter?: string,
        @query("$include") include?: ListContainersInclude,
    ): ListContainerItems | ErrorResponse;

    @doc("Creates a new container under the specified account")
    @put
    @route("{containerName}")
    create(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
        @body blobContainer: BlobContainer,
    ): BlobContainer | ErrorResponse;

    @doc("Updates container properties as specified in request body")
    @patch(#{ implicitOptionality: true })
    @route("{containerName}")
    update(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
        @body blobContainer: BlobContainer,
    ): BlobContainer | ErrorResponse;

    @doc("Gets properties of a specified container")
    @get
    @route("{containerName}")
    get(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
    ): BlobContainer | ErrorResponse;

    @doc("Deletes specified container under its account")
    @delete
    @route("{containerName}")
    delete(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
    ): void | ErrorResponse;

    @doc("Sets legal hold tags")
    @post
    @route("{containerName}/setLegalHold")
    setLegalHold(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
        @body LegalHold: LegalHold,
    ): LegalHold | ErrorResponse;

    @doc("Clears legal hold tags")
    @post
    @route("{containerName}/clearLegalHold")
    clearLegalHold(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
        @body LegalHold: LegalHold,
    ): LegalHold | ErrorResponse;

    @doc("Creates or updates an unlocked immutability policy")
    @put
    @route("{containerName}/immutabilityPolicies/{immutabilityPolicyName}")
    createOrUpdateImmutabilityPolicy(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @path immutabilityPolicyName: "default",
        @query("api-version") apiVersion: string,
        @body parameters?: ImmutabilityPolicy,
        @header("If-Match") ifMatch?: string,
    ): ImmutabilityPolicy | ErrorResponse;

    @doc("Gets the existing immutability policy along with the corresponding ETag")
    @get
    @route("{containerName}/immutabilityPolicies/{immutabilityPolicyName}")
    getImmutabilityPolicy(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @path immutabilityPolicyName: "default",
        @query("api-version") apiVersion: string,
        @header("If-Match") ifMatch?: string,
    ): ImmutabilityPolicy | ErrorResponse;

    @doc("Aborts an unlocked immutability policy")
    @delete
    @route("{containerName}/immutabilityPolicies/{immutabilityPolicyName}")
    deleteImmutabilityPolicy(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @path immutabilityPolicyName: "default",
        @query("api-version") apiVersion: string,
        @header("If-Match") ifMatch: string,
    ): ImmutabilityPolicy | ErrorResponse;

    @doc("Sets the ImmutabilityPolicy to Locked state")
    @post
    @route("{containerName}/immutabilityPolicies/default/lock")
    lockImmutabilityPolicy(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
        @header("If-Match") ifMatch: string,
    ): ImmutabilityPolicy | ErrorResponse;

    @doc("Extends the immutabilityPeriodSinceCreationInDays of a locked immutabilityPolicy")
    @post
    @route("{containerName}/immutabilityPolicies/default/extend")
    extendImmutabilityPolicy(
        @path subscriptionId: SubscriptionId,
        @path resourceGroupName: ResourceGroupName,
        @path accountName: StorageAccountName,
        @path containerName: ContainerName,
        @query("api-version") apiVersion: string,
        @header("If-Match") ifMatch: string,
        @body parameters?: ImmutabilityPolicy,
    ): ImmutabilityPolicy | ErrorResponse;
}
