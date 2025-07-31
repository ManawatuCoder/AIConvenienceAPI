package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * The blob service properties for change feed events.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class ChangeFeed implements JsonSerializable<ChangeFeed> {
    /*
     * Indicates whether change feed event logging is enabled for the Blob service
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean enabled;

    /*
     * Indicates the duration of changeFeed retention in days
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Integer retentionInDays;

    /**
     * Creates an instance of ChangeFeed class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ChangeFeed() {
    }

    /**
     * Get the enabled property: Indicates whether change feed event logging is enabled for the Blob service.
     * 
     * @return the enabled value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: Indicates whether change feed event logging is enabled for the Blob service.
     * 
     * @param enabled the enabled value to set.
     * @return the ChangeFeed object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ChangeFeed setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the retentionInDays property: Indicates the duration of changeFeed retention in days.
     * 
     * @return the retentionInDays value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Integer getRetentionInDays() {
        return this.retentionInDays;
    }

    /**
     * Set the retentionInDays property: Indicates the duration of changeFeed retention in days.
     * 
     * @param retentionInDays the retentionInDays value to set.
     * @return the ChangeFeed object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ChangeFeed setRetentionInDays(Integer retentionInDays) {
        this.retentionInDays = retentionInDays;
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
        jsonWriter.writeNumberField("retentionInDays", this.retentionInDays);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ChangeFeed from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ChangeFeed if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the ChangeFeed.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ChangeFeed fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ChangeFeed deserializedChangeFeed = new ChangeFeed();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("enabled".equals(fieldName)) {
                    deserializedChangeFeed.enabled = reader.getNullable(JsonReader::getBoolean);
                } else if ("retentionInDays".equals(fieldName)) {
                    deserializedChangeFeed.retentionInDays = reader.getNullable(JsonReader::getInt);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedChangeFeed;
        });
    }
}
