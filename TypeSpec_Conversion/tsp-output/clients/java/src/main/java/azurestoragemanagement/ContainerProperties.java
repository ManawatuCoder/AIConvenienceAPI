package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * The properties of a container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class ContainerProperties implements JsonSerializable<ContainerProperties> {
    /*
     * The version of the deleted blob container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String version;

    /*
     * Indicates whether the blob container was deleted
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean deleted;

    /*
     * Blob container deletion time
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private OffsetDateTime deletedTime;

    /*
     * Remaining retention days for soft deleted blob container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Integer remainingRetentionDays;

    /*
     * Default the container to use specified encryption scope for all writes
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String defaultEncryptionScope;

    /*
     * Block override of encryption scope from the container default
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean denyEncryptionScopeOverride;

    /*
     * Specifies whether data in the container may be accessed publicly and the level of access
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private PublicAccess publicAccess;

    /*
     * Returns the date and time the container was last modified
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private OffsetDateTime lastModifiedTime;

    /*
     * The lease status of the container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private LeaseStatus leaseStatus;

    /*
     * Lease state of the container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private LeaseState leaseState;

    /*
     * Specifies whether the lease on a container is of infinite or fixed duration
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private LeaseDuration leaseDuration;

    /*
     * A name-value pair to associate with the container as metadata
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Map<String, String> metadata;

    /*
     * The ImmutabilityPolicy property of the container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ImmutabilityPolicyProperties immutabilityPolicy;

    /*
     * The LegalHold property of the container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private LegalHoldProperties legalHold;

    /*
     * The hasLegalHold public property
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean hasLegalHold;

    /*
     * The hasImmutabilityPolicy public property
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean hasImmutabilityPolicy;

    /**
     * Creates an instance of ContainerProperties class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties() {
    }

    /**
     * Get the version property: The version of the deleted blob container.
     * 
     * @return the version value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version of the deleted blob container.
     * 
     * @param version the version value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get the deleted property: Indicates whether the blob container was deleted.
     * 
     * @return the deleted value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isDeleted() {
        return this.deleted;
    }

    /**
     * Set the deleted property: Indicates whether the blob container was deleted.
     * 
     * @param deleted the deleted value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    /**
     * Get the deletedTime property: Blob container deletion time.
     * 
     * @return the deletedTime value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public OffsetDateTime getDeletedTime() {
        return this.deletedTime;
    }

    /**
     * Set the deletedTime property: Blob container deletion time.
     * 
     * @param deletedTime the deletedTime value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setDeletedTime(OffsetDateTime deletedTime) {
        this.deletedTime = deletedTime;
        return this;
    }

    /**
     * Get the remainingRetentionDays property: Remaining retention days for soft deleted blob container.
     * 
     * @return the remainingRetentionDays value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Integer getRemainingRetentionDays() {
        return this.remainingRetentionDays;
    }

    /**
     * Set the remainingRetentionDays property: Remaining retention days for soft deleted blob container.
     * 
     * @param remainingRetentionDays the remainingRetentionDays value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setRemainingRetentionDays(Integer remainingRetentionDays) {
        this.remainingRetentionDays = remainingRetentionDays;
        return this;
    }

    /**
     * Get the defaultEncryptionScope property: Default the container to use specified encryption scope for all writes.
     * 
     * @return the defaultEncryptionScope value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getDefaultEncryptionScope() {
        return this.defaultEncryptionScope;
    }

    /**
     * Set the defaultEncryptionScope property: Default the container to use specified encryption scope for all writes.
     * 
     * @param defaultEncryptionScope the defaultEncryptionScope value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setDefaultEncryptionScope(String defaultEncryptionScope) {
        this.defaultEncryptionScope = defaultEncryptionScope;
        return this;
    }

    /**
     * Get the denyEncryptionScopeOverride property: Block override of encryption scope from the container default.
     * 
     * @return the denyEncryptionScopeOverride value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isDenyEncryptionScopeOverride() {
        return this.denyEncryptionScopeOverride;
    }

    /**
     * Set the denyEncryptionScopeOverride property: Block override of encryption scope from the container default.
     * 
     * @param denyEncryptionScopeOverride the denyEncryptionScopeOverride value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setDenyEncryptionScopeOverride(Boolean denyEncryptionScopeOverride) {
        this.denyEncryptionScopeOverride = denyEncryptionScopeOverride;
        return this;
    }

    /**
     * Get the publicAccess property: Specifies whether data in the container may be accessed publicly and the level of
     * access.
     * 
     * @return the publicAccess value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public PublicAccess getPublicAccess() {
        return this.publicAccess;
    }

    /**
     * Set the publicAccess property: Specifies whether data in the container may be accessed publicly and the level of
     * access.
     * 
     * @param publicAccess the publicAccess value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setPublicAccess(PublicAccess publicAccess) {
        this.publicAccess = publicAccess;
        return this;
    }

    /**
     * Get the lastModifiedTime property: Returns the date and time the container was last modified.
     * 
     * @return the lastModifiedTime value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public OffsetDateTime getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    /**
     * Set the lastModifiedTime property: Returns the date and time the container was last modified.
     * 
     * @param lastModifiedTime the lastModifiedTime value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setLastModifiedTime(OffsetDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
        return this;
    }

    /**
     * Get the leaseStatus property: The lease status of the container.
     * 
     * @return the leaseStatus value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LeaseStatus getLeaseStatus() {
        return this.leaseStatus;
    }

    /**
     * Set the leaseStatus property: The lease status of the container.
     * 
     * @param leaseStatus the leaseStatus value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setLeaseStatus(LeaseStatus leaseStatus) {
        this.leaseStatus = leaseStatus;
        return this;
    }

    /**
     * Get the leaseState property: Lease state of the container.
     * 
     * @return the leaseState value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LeaseState getLeaseState() {
        return this.leaseState;
    }

    /**
     * Set the leaseState property: Lease state of the container.
     * 
     * @param leaseState the leaseState value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setLeaseState(LeaseState leaseState) {
        this.leaseState = leaseState;
        return this;
    }

    /**
     * Get the leaseDuration property: Specifies whether the lease on a container is of infinite or fixed duration.
     * 
     * @return the leaseDuration value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LeaseDuration getLeaseDuration() {
        return this.leaseDuration;
    }

    /**
     * Set the leaseDuration property: Specifies whether the lease on a container is of infinite or fixed duration.
     * 
     * @param leaseDuration the leaseDuration value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setLeaseDuration(LeaseDuration leaseDuration) {
        this.leaseDuration = leaseDuration;
        return this;
    }

    /**
     * Get the metadata property: A name-value pair to associate with the container as metadata.
     * 
     * @return the metadata value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: A name-value pair to associate with the container as metadata.
     * 
     * @param metadata the metadata value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the immutabilityPolicy property: The ImmutabilityPolicy property of the container.
     * 
     * @return the immutabilityPolicy value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperties getImmutabilityPolicy() {
        return this.immutabilityPolicy;
    }

    /**
     * Set the immutabilityPolicy property: The ImmutabilityPolicy property of the container.
     * 
     * @param immutabilityPolicy the immutabilityPolicy value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setImmutabilityPolicy(ImmutabilityPolicyProperties immutabilityPolicy) {
        this.immutabilityPolicy = immutabilityPolicy;
        return this;
    }

    /**
     * Get the legalHold property: The LegalHold property of the container.
     * 
     * @return the legalHold value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LegalHoldProperties getLegalHold() {
        return this.legalHold;
    }

    /**
     * Set the legalHold property: The LegalHold property of the container.
     * 
     * @param legalHold the legalHold value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setLegalHold(LegalHoldProperties legalHold) {
        this.legalHold = legalHold;
        return this;
    }

    /**
     * Get the hasLegalHold property: The hasLegalHold public property.
     * 
     * @return the hasLegalHold value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isHasLegalHold() {
        return this.hasLegalHold;
    }

    /**
     * Set the hasLegalHold property: The hasLegalHold public property.
     * 
     * @param hasLegalHold the hasLegalHold value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setHasLegalHold(Boolean hasLegalHold) {
        this.hasLegalHold = hasLegalHold;
        return this;
    }

    /**
     * Get the hasImmutabilityPolicy property: The hasImmutabilityPolicy public property.
     * 
     * @return the hasImmutabilityPolicy value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isHasImmutabilityPolicy() {
        return this.hasImmutabilityPolicy;
    }

    /**
     * Set the hasImmutabilityPolicy property: The hasImmutabilityPolicy public property.
     * 
     * @param hasImmutabilityPolicy the hasImmutabilityPolicy value to set.
     * @return the ContainerProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties setHasImmutabilityPolicy(Boolean hasImmutabilityPolicy) {
        this.hasImmutabilityPolicy = hasImmutabilityPolicy;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("version", this.version);
        jsonWriter.writeBooleanField("deleted", this.deleted);
        jsonWriter.writeStringField("deletedTime",
            this.deletedTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.deletedTime));
        jsonWriter.writeNumberField("remainingRetentionDays", this.remainingRetentionDays);
        jsonWriter.writeStringField("defaultEncryptionScope", this.defaultEncryptionScope);
        jsonWriter.writeBooleanField("denyEncryptionScopeOverride", this.denyEncryptionScopeOverride);
        jsonWriter.writeStringField("publicAccess", this.publicAccess == null ? null : this.publicAccess.toString());
        jsonWriter.writeStringField("lastModifiedTime",
            this.lastModifiedTime == null
                ? null
                : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.lastModifiedTime));
        jsonWriter.writeStringField("leaseStatus", this.leaseStatus == null ? null : this.leaseStatus.toString());
        jsonWriter.writeStringField("leaseState", this.leaseState == null ? null : this.leaseState.toString());
        jsonWriter.writeStringField("leaseDuration", this.leaseDuration == null ? null : this.leaseDuration.toString());
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeString(element));
        jsonWriter.writeJsonField("immutabilityPolicy", this.immutabilityPolicy);
        jsonWriter.writeJsonField("legalHold", this.legalHold);
        jsonWriter.writeBooleanField("hasLegalHold", this.hasLegalHold);
        jsonWriter.writeBooleanField("hasImmutabilityPolicy", this.hasImmutabilityPolicy);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ContainerProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ContainerProperties if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ContainerProperties.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ContainerProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ContainerProperties deserializedContainerProperties = new ContainerProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("version".equals(fieldName)) {
                    deserializedContainerProperties.version = reader.getString();
                } else if ("deleted".equals(fieldName)) {
                    deserializedContainerProperties.deleted = reader.getNullable(JsonReader::getBoolean);
                } else if ("deletedTime".equals(fieldName)) {
                    deserializedContainerProperties.deletedTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("remainingRetentionDays".equals(fieldName)) {
                    deserializedContainerProperties.remainingRetentionDays = reader.getNullable(JsonReader::getInt);
                } else if ("defaultEncryptionScope".equals(fieldName)) {
                    deserializedContainerProperties.defaultEncryptionScope = reader.getString();
                } else if ("denyEncryptionScopeOverride".equals(fieldName)) {
                    deserializedContainerProperties.denyEncryptionScopeOverride
                        = reader.getNullable(JsonReader::getBoolean);
                } else if ("publicAccess".equals(fieldName)) {
                    deserializedContainerProperties.publicAccess = PublicAccess.fromString(reader.getString());
                } else if ("lastModifiedTime".equals(fieldName)) {
                    deserializedContainerProperties.lastModifiedTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("leaseStatus".equals(fieldName)) {
                    deserializedContainerProperties.leaseStatus = LeaseStatus.fromString(reader.getString());
                } else if ("leaseState".equals(fieldName)) {
                    deserializedContainerProperties.leaseState = LeaseState.fromString(reader.getString());
                } else if ("leaseDuration".equals(fieldName)) {
                    deserializedContainerProperties.leaseDuration = LeaseDuration.fromString(reader.getString());
                } else if ("metadata".equals(fieldName)) {
                    Map<String, String> metadata = reader.readMap(reader1 -> reader1.getString());
                    deserializedContainerProperties.metadata = metadata;
                } else if ("immutabilityPolicy".equals(fieldName)) {
                    deserializedContainerProperties.immutabilityPolicy = ImmutabilityPolicyProperties.fromJson(reader);
                } else if ("legalHold".equals(fieldName)) {
                    deserializedContainerProperties.legalHold = LegalHoldProperties.fromJson(reader);
                } else if ("hasLegalHold".equals(fieldName)) {
                    deserializedContainerProperties.hasLegalHold = reader.getNullable(JsonReader::getBoolean);
                } else if ("hasImmutabilityPolicy".equals(fieldName)) {
                    deserializedContainerProperties.hasImmutabilityPolicy = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedContainerProperties;
        });
    }
}
