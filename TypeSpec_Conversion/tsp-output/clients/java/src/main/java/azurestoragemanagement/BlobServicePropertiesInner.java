package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.models.binarydata.BinaryData;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * The properties of a storage account's Blob service.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class BlobServicePropertiesInner implements JsonSerializable<BlobServicePropertiesInner> {
    /*
     * Specifies CORS rules for the Blob service
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private BinaryData cors;

    /*
     * DefaultServiceVersion indicates the default version to use for requests to the Blob service
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String defaultServiceVersion;

    /*
     * The blob service properties for blob soft delete
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private BinaryData deleteRetentionPolicy;

    /*
     * Versioning is enabled if set to true
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean isVersioningEnabled;

    /*
     * Deprecated in favor of isVersioningEnabled property
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean automaticSnapshotPolicyEnabled;

    /*
     * The blob service properties for change feed events
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ChangeFeed changeFeed;

    /*
     * The blob service properties for blob restore policy
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private RestorePolicyProperties restorePolicy;

    /*
     * The blob service properties for container soft delete
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private BinaryData containerDeleteRetentionPolicy;

    /*
     * The blob service property to configure last access time based tracking policy
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private LastAccessTimeTrackingPolicy lastAccessTimeTrackingPolicy;

    /**
     * Creates an instance of BlobServicePropertiesInner class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner() {
    }

    /**
     * Get the cors property: Specifies CORS rules for the Blob service.
     * 
     * @return the cors value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BinaryData getCors() {
        return this.cors;
    }

    /**
     * Set the cors property: Specifies CORS rules for the Blob service.
     * 
     * @param cors the cors value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setCors(BinaryData cors) {
        this.cors = cors;
        return this;
    }

    /**
     * Get the defaultServiceVersion property: DefaultServiceVersion indicates the default version to use for requests
     * to the Blob service.
     * 
     * @return the defaultServiceVersion value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getDefaultServiceVersion() {
        return this.defaultServiceVersion;
    }

    /**
     * Set the defaultServiceVersion property: DefaultServiceVersion indicates the default version to use for requests
     * to the Blob service.
     * 
     * @param defaultServiceVersion the defaultServiceVersion value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setDefaultServiceVersion(String defaultServiceVersion) {
        this.defaultServiceVersion = defaultServiceVersion;
        return this;
    }

    /**
     * Get the deleteRetentionPolicy property: The blob service properties for blob soft delete.
     * 
     * @return the deleteRetentionPolicy value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BinaryData getDeleteRetentionPolicy() {
        return this.deleteRetentionPolicy;
    }

    /**
     * Set the deleteRetentionPolicy property: The blob service properties for blob soft delete.
     * 
     * @param deleteRetentionPolicy the deleteRetentionPolicy value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setDeleteRetentionPolicy(BinaryData deleteRetentionPolicy) {
        this.deleteRetentionPolicy = deleteRetentionPolicy;
        return this;
    }

    /**
     * Get the isVersioningEnabled property: Versioning is enabled if set to true.
     * 
     * @return the isVersioningEnabled value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isVersioningEnabled() {
        return this.isVersioningEnabled;
    }

    /**
     * Set the isVersioningEnabled property: Versioning is enabled if set to true.
     * 
     * @param isVersioningEnabled the isVersioningEnabled value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setIsVersioningEnabled(Boolean isVersioningEnabled) {
        this.isVersioningEnabled = isVersioningEnabled;
        return this;
    }

    /**
     * Get the automaticSnapshotPolicyEnabled property: Deprecated in favor of isVersioningEnabled property.
     * 
     * @return the automaticSnapshotPolicyEnabled value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isAutomaticSnapshotPolicyEnabled() {
        return this.automaticSnapshotPolicyEnabled;
    }

    /**
     * Set the automaticSnapshotPolicyEnabled property: Deprecated in favor of isVersioningEnabled property.
     * 
     * @param automaticSnapshotPolicyEnabled the automaticSnapshotPolicyEnabled value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setAutomaticSnapshotPolicyEnabled(Boolean automaticSnapshotPolicyEnabled) {
        this.automaticSnapshotPolicyEnabled = automaticSnapshotPolicyEnabled;
        return this;
    }

    /**
     * Get the changeFeed property: The blob service properties for change feed events.
     * 
     * @return the changeFeed value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ChangeFeed getChangeFeed() {
        return this.changeFeed;
    }

    /**
     * Set the changeFeed property: The blob service properties for change feed events.
     * 
     * @param changeFeed the changeFeed value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setChangeFeed(ChangeFeed changeFeed) {
        this.changeFeed = changeFeed;
        return this;
    }

    /**
     * Get the restorePolicy property: The blob service properties for blob restore policy.
     * 
     * @return the restorePolicy value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public RestorePolicyProperties getRestorePolicy() {
        return this.restorePolicy;
    }

    /**
     * Set the restorePolicy property: The blob service properties for blob restore policy.
     * 
     * @param restorePolicy the restorePolicy value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setRestorePolicy(RestorePolicyProperties restorePolicy) {
        this.restorePolicy = restorePolicy;
        return this;
    }

    /**
     * Get the containerDeleteRetentionPolicy property: The blob service properties for container soft delete.
     * 
     * @return the containerDeleteRetentionPolicy value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BinaryData getContainerDeleteRetentionPolicy() {
        return this.containerDeleteRetentionPolicy;
    }

    /**
     * Set the containerDeleteRetentionPolicy property: The blob service properties for container soft delete.
     * 
     * @param containerDeleteRetentionPolicy the containerDeleteRetentionPolicy value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner setContainerDeleteRetentionPolicy(BinaryData containerDeleteRetentionPolicy) {
        this.containerDeleteRetentionPolicy = containerDeleteRetentionPolicy;
        return this;
    }

    /**
     * Get the lastAccessTimeTrackingPolicy property: The blob service property to configure last access time based
     * tracking policy.
     * 
     * @return the lastAccessTimeTrackingPolicy value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LastAccessTimeTrackingPolicy getLastAccessTimeTrackingPolicy() {
        return this.lastAccessTimeTrackingPolicy;
    }

    /**
     * Set the lastAccessTimeTrackingPolicy property: The blob service property to configure last access time based
     * tracking policy.
     * 
     * @param lastAccessTimeTrackingPolicy the lastAccessTimeTrackingPolicy value to set.
     * @return the BlobServicePropertiesInner object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner
        setLastAccessTimeTrackingPolicy(LastAccessTimeTrackingPolicy lastAccessTimeTrackingPolicy) {
        this.lastAccessTimeTrackingPolicy = lastAccessTimeTrackingPolicy;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (this.cors != null) {
            jsonWriter.writeFieldName("cors");
            this.cors.writeTo(jsonWriter);
        }
        jsonWriter.writeStringField("defaultServiceVersion", this.defaultServiceVersion);
        if (this.deleteRetentionPolicy != null) {
            jsonWriter.writeFieldName("deleteRetentionPolicy");
            this.deleteRetentionPolicy.writeTo(jsonWriter);
        }
        jsonWriter.writeBooleanField("isVersioningEnabled", this.isVersioningEnabled);
        jsonWriter.writeBooleanField("automaticSnapshotPolicyEnabled", this.automaticSnapshotPolicyEnabled);
        jsonWriter.writeJsonField("changeFeed", this.changeFeed);
        jsonWriter.writeJsonField("restorePolicy", this.restorePolicy);
        if (this.containerDeleteRetentionPolicy != null) {
            jsonWriter.writeFieldName("containerDeleteRetentionPolicy");
            this.containerDeleteRetentionPolicy.writeTo(jsonWriter);
        }
        jsonWriter.writeJsonField("lastAccessTimeTrackingPolicy", this.lastAccessTimeTrackingPolicy);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BlobServicePropertiesInner from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BlobServicePropertiesInner if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BlobServicePropertiesInner.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static BlobServicePropertiesInner fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BlobServicePropertiesInner deserializedBlobServicePropertiesInner = new BlobServicePropertiesInner();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("cors".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.cors
                        = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else if ("defaultServiceVersion".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.defaultServiceVersion = reader.getString();
                } else if ("deleteRetentionPolicy".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.deleteRetentionPolicy
                        = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else if ("isVersioningEnabled".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.isVersioningEnabled
                        = reader.getNullable(JsonReader::getBoolean);
                } else if ("automaticSnapshotPolicyEnabled".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.automaticSnapshotPolicyEnabled
                        = reader.getNullable(JsonReader::getBoolean);
                } else if ("changeFeed".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.changeFeed = ChangeFeed.fromJson(reader);
                } else if ("restorePolicy".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.restorePolicy = RestorePolicyProperties.fromJson(reader);
                } else if ("containerDeleteRetentionPolicy".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.containerDeleteRetentionPolicy
                        = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else if ("lastAccessTimeTrackingPolicy".equals(fieldName)) {
                    deserializedBlobServicePropertiesInner.lastAccessTimeTrackingPolicy
                        = LastAccessTimeTrackingPolicy.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBlobServicePropertiesInner;
        });
    }
}
