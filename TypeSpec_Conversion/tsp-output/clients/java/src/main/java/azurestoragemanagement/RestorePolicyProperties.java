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
 * The blob service properties for blob restore policy.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class RestorePolicyProperties implements JsonSerializable<RestorePolicyProperties> {
    /*
     * Blob restore is enabled if set to true
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private final boolean enabled;

    /*
     * How long this blob can be restored
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Integer days;

    /*
     * Deprecated in favor of minRestoreTime property
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private OffsetDateTime lastEnabledTime;

    /*
     * Returns the minimum date and time that the restore can be started
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private OffsetDateTime minRestoreTime;

    /**
     * Creates an instance of RestorePolicyProperties class.
     * 
     * @param enabled the enabled value to set.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public RestorePolicyProperties(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Get the enabled property: Blob restore is enabled if set to true.
     * 
     * @return the enabled value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Get the days property: How long this blob can be restored.
     * 
     * @return the days value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Integer getDays() {
        return this.days;
    }

    /**
     * Set the days property: How long this blob can be restored.
     * 
     * @param days the days value to set.
     * @return the RestorePolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public RestorePolicyProperties setDays(Integer days) {
        this.days = days;
        return this;
    }

    /**
     * Get the lastEnabledTime property: Deprecated in favor of minRestoreTime property.
     * 
     * @return the lastEnabledTime value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public OffsetDateTime getLastEnabledTime() {
        return this.lastEnabledTime;
    }

    /**
     * Set the lastEnabledTime property: Deprecated in favor of minRestoreTime property.
     * 
     * @param lastEnabledTime the lastEnabledTime value to set.
     * @return the RestorePolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public RestorePolicyProperties setLastEnabledTime(OffsetDateTime lastEnabledTime) {
        this.lastEnabledTime = lastEnabledTime;
        return this;
    }

    /**
     * Get the minRestoreTime property: Returns the minimum date and time that the restore can be started.
     * 
     * @return the minRestoreTime value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public OffsetDateTime getMinRestoreTime() {
        return this.minRestoreTime;
    }

    /**
     * Set the minRestoreTime property: Returns the minimum date and time that the restore can be started.
     * 
     * @param minRestoreTime the minRestoreTime value to set.
     * @return the RestorePolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public RestorePolicyProperties setMinRestoreTime(OffsetDateTime minRestoreTime) {
        this.minRestoreTime = minRestoreTime;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("enabled", this.enabled);
        jsonWriter.writeNumberField("days", this.days);
        jsonWriter.writeStringField("lastEnabledTime",
            this.lastEnabledTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.lastEnabledTime));
        jsonWriter.writeStringField("minRestoreTime",
            this.minRestoreTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.minRestoreTime));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of RestorePolicyProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of RestorePolicyProperties if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the RestorePolicyProperties.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static RestorePolicyProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean enabled = false;
            Integer days = null;
            OffsetDateTime lastEnabledTime = null;
            OffsetDateTime minRestoreTime = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("enabled".equals(fieldName)) {
                    enabled = reader.getBoolean();
                } else if ("days".equals(fieldName)) {
                    days = reader.getNullable(JsonReader::getInt);
                } else if ("lastEnabledTime".equals(fieldName)) {
                    lastEnabledTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("minRestoreTime".equals(fieldName)) {
                    minRestoreTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }
            RestorePolicyProperties deserializedRestorePolicyProperties = new RestorePolicyProperties(enabled);
            deserializedRestorePolicyProperties.days = days;
            deserializedRestorePolicyProperties.lastEnabledTime = lastEnabledTime;
            deserializedRestorePolicyProperties.minRestoreTime = minRestoreTime;

            return deserializedRestorePolicyProperties;
        });
    }
}
