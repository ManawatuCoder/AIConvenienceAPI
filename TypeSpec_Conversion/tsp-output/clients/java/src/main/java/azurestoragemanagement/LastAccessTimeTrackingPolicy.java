package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The blob service properties for Last access time based tracking policy.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class LastAccessTimeTrackingPolicy implements JsonSerializable<LastAccessTimeTrackingPolicy> {
    /*
     * When set to true last access time based tracking is enabled
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private final boolean enable;

    /*
     * Name of the policy
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private LastAccessTimeTrackingPolicyName name;

    /*
     * The field specifies blob object tracking granularity in days
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Integer trackingGranularityInDays;

    /*
     * An array of predefined supported blob types
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private List<String> blobType;

    /**
     * Creates an instance of LastAccessTimeTrackingPolicy class.
     * 
     * @param enable the enable value to set.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LastAccessTimeTrackingPolicy(boolean enable) {
        this.enable = enable;
    }

    /**
     * Get the enable property: When set to true last access time based tracking is enabled.
     * 
     * @return the enable value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public boolean isEnable() {
        return this.enable;
    }

    /**
     * Get the name property: Name of the policy.
     * 
     * @return the name value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LastAccessTimeTrackingPolicyName getName() {
        return this.name;
    }

    /**
     * Set the name property: Name of the policy.
     * 
     * @param name the name value to set.
     * @return the LastAccessTimeTrackingPolicy object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LastAccessTimeTrackingPolicy setName(LastAccessTimeTrackingPolicyName name) {
        this.name = name;
        return this;
    }

    /**
     * Get the trackingGranularityInDays property: The field specifies blob object tracking granularity in days.
     * 
     * @return the trackingGranularityInDays value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Integer getTrackingGranularityInDays() {
        return this.trackingGranularityInDays;
    }

    /**
     * Set the trackingGranularityInDays property: The field specifies blob object tracking granularity in days.
     * 
     * @param trackingGranularityInDays the trackingGranularityInDays value to set.
     * @return the LastAccessTimeTrackingPolicy object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LastAccessTimeTrackingPolicy setTrackingGranularityInDays(Integer trackingGranularityInDays) {
        this.trackingGranularityInDays = trackingGranularityInDays;
        return this;
    }

    /**
     * Get the blobType property: An array of predefined supported blob types.
     * 
     * @return the blobType value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public List<String> getBlobType() {
        return this.blobType;
    }

    /**
     * Set the blobType property: An array of predefined supported blob types.
     * 
     * @param blobType the blobType value to set.
     * @return the LastAccessTimeTrackingPolicy object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LastAccessTimeTrackingPolicy setBlobType(List<String> blobType) {
        this.blobType = blobType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("enable", this.enable);
        jsonWriter.writeStringField("name", this.name == null ? null : this.name.toString());
        jsonWriter.writeNumberField("trackingGranularityInDays", this.trackingGranularityInDays);
        jsonWriter.writeArrayField("blobType", this.blobType, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of LastAccessTimeTrackingPolicy from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of LastAccessTimeTrackingPolicy if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the LastAccessTimeTrackingPolicy.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static LastAccessTimeTrackingPolicy fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean enable = false;
            LastAccessTimeTrackingPolicyName name = null;
            Integer trackingGranularityInDays = null;
            List<String> blobType = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("enable".equals(fieldName)) {
                    enable = reader.getBoolean();
                } else if ("name".equals(fieldName)) {
                    name = LastAccessTimeTrackingPolicyName.fromString(reader.getString());
                } else if ("trackingGranularityInDays".equals(fieldName)) {
                    trackingGranularityInDays = reader.getNullable(JsonReader::getInt);
                } else if ("blobType".equals(fieldName)) {
                    blobType = reader.readArray(reader1 -> reader1.getString());
                } else {
                    reader.skipChildren();
                }
            }
            LastAccessTimeTrackingPolicy deserializedLastAccessTimeTrackingPolicy
                = new LastAccessTimeTrackingPolicy(enable);
            deserializedLastAccessTimeTrackingPolicy.name = name;
            deserializedLastAccessTimeTrackingPolicy.trackingGranularityInDays = trackingGranularityInDays;
            deserializedLastAccessTimeTrackingPolicy.blobType = blobType;

            return deserializedLastAccessTimeTrackingPolicy;
        });
    }
}
