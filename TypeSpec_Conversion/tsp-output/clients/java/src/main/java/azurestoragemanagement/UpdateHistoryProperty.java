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

/**
 * An update history of the ImmutabilityPolicy of a blob container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class UpdateHistoryProperty implements JsonSerializable<UpdateHistoryProperty> {
    /*
     * The ImmutabilityPolicy update type
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ImmutabilityPolicyUpdateType update;

    /*
     * The immutability period since creation in days
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Integer immutabilityPeriodSinceCreationInDays;

    /*
     * Returns the date and time the ImmutabilityPolicy was updated
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private OffsetDateTime timestamp;

    /*
     * Returns the Object ID of the user who updated the ImmutabilityPolicy
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String objectIdentifier;

    /*
     * Returns the Tenant ID that issued the token for the user
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String tenantId;

    /*
     * Returns the User Principal Name of the user
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String upn;

    /**
     * Creates an instance of UpdateHistoryProperty class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public UpdateHistoryProperty() {
    }

    /**
     * Get the update property: The ImmutabilityPolicy update type.
     * 
     * @return the update value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyUpdateType getUpdate() {
        return this.update;
    }

    /**
     * Set the update property: The ImmutabilityPolicy update type.
     * 
     * @param update the update value to set.
     * @return the UpdateHistoryProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public UpdateHistoryProperty setUpdate(ImmutabilityPolicyUpdateType update) {
        this.update = update;
        return this;
    }

    /**
     * Get the immutabilityPeriodSinceCreationInDays property: The immutability period since creation in days.
     * 
     * @return the immutabilityPeriodSinceCreationInDays value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Integer getImmutabilityPeriodSinceCreationInDays() {
        return this.immutabilityPeriodSinceCreationInDays;
    }

    /**
     * Set the immutabilityPeriodSinceCreationInDays property: The immutability period since creation in days.
     * 
     * @param immutabilityPeriodSinceCreationInDays the immutabilityPeriodSinceCreationInDays value to set.
     * @return the UpdateHistoryProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public UpdateHistoryProperty
        setImmutabilityPeriodSinceCreationInDays(Integer immutabilityPeriodSinceCreationInDays) {
        this.immutabilityPeriodSinceCreationInDays = immutabilityPeriodSinceCreationInDays;
        return this;
    }

    /**
     * Get the timestamp property: Returns the date and time the ImmutabilityPolicy was updated.
     * 
     * @return the timestamp value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Set the timestamp property: Returns the date and time the ImmutabilityPolicy was updated.
     * 
     * @param timestamp the timestamp value to set.
     * @return the UpdateHistoryProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public UpdateHistoryProperty setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get the objectIdentifier property: Returns the Object ID of the user who updated the ImmutabilityPolicy.
     * 
     * @return the objectIdentifier value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getObjectIdentifier() {
        return this.objectIdentifier;
    }

    /**
     * Set the objectIdentifier property: Returns the Object ID of the user who updated the ImmutabilityPolicy.
     * 
     * @param objectIdentifier the objectIdentifier value to set.
     * @return the UpdateHistoryProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public UpdateHistoryProperty setObjectIdentifier(String objectIdentifier) {
        this.objectIdentifier = objectIdentifier;
        return this;
    }

    /**
     * Get the tenantId property: Returns the Tenant ID that issued the token for the user.
     * 
     * @return the tenantId value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Returns the Tenant ID that issued the token for the user.
     * 
     * @param tenantId the tenantId value to set.
     * @return the UpdateHistoryProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public UpdateHistoryProperty setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the upn property: Returns the User Principal Name of the user.
     * 
     * @return the upn value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getUpn() {
        return this.upn;
    }

    /**
     * Set the upn property: Returns the User Principal Name of the user.
     * 
     * @param upn the upn value to set.
     * @return the UpdateHistoryProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public UpdateHistoryProperty setUpn(String upn) {
        this.upn = upn;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("update", this.update == null ? null : this.update.toString());
        jsonWriter.writeNumberField("immutabilityPeriodSinceCreationInDays",
            this.immutabilityPeriodSinceCreationInDays);
        jsonWriter.writeStringField("timestamp",
            this.timestamp == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.timestamp));
        jsonWriter.writeStringField("objectIdentifier", this.objectIdentifier);
        jsonWriter.writeStringField("tenantId", this.tenantId);
        jsonWriter.writeStringField("upn", this.upn);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of UpdateHistoryProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of UpdateHistoryProperty if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the UpdateHistoryProperty.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static UpdateHistoryProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            UpdateHistoryProperty deserializedUpdateHistoryProperty = new UpdateHistoryProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("update".equals(fieldName)) {
                    deserializedUpdateHistoryProperty.update
                        = ImmutabilityPolicyUpdateType.fromString(reader.getString());
                } else if ("immutabilityPeriodSinceCreationInDays".equals(fieldName)) {
                    deserializedUpdateHistoryProperty.immutabilityPeriodSinceCreationInDays
                        = reader.getNullable(JsonReader::getInt);
                } else if ("timestamp".equals(fieldName)) {
                    deserializedUpdateHistoryProperty.timestamp
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("objectIdentifier".equals(fieldName)) {
                    deserializedUpdateHistoryProperty.objectIdentifier = reader.getString();
                } else if ("tenantId".equals(fieldName)) {
                    deserializedUpdateHistoryProperty.tenantId = reader.getString();
                } else if ("upn".equals(fieldName)) {
                    deserializedUpdateHistoryProperty.upn = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedUpdateHistoryProperty;
        });
    }
}
