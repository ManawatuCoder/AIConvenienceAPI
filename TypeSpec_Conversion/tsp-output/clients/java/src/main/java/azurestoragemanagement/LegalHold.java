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
 * The LegalHold property of a blob container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class LegalHold implements JsonSerializable<LegalHold> {
    /*
     * The hasLegalHold public property
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean hasLegalHold;

    /*
     * Each tag should be 3 to 23 alphanumeric characters
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private final List<String> tags;

    /**
     * Creates an instance of LegalHold class.
     * 
     * @param tags the tags value to set.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LegalHold(List<String> tags) {
        this.tags = tags;
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
     * @return the LegalHold object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LegalHold setHasLegalHold(Boolean hasLegalHold) {
        this.hasLegalHold = hasLegalHold;
        return this;
    }

    /**
     * Get the tags property: Each tag should be 3 to 23 alphanumeric characters.
     * 
     * @return the tags value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("tags", this.tags, (writer, element) -> writer.writeString(element));
        jsonWriter.writeBooleanField("hasLegalHold", this.hasLegalHold);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of LegalHold from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of LegalHold if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the LegalHold.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static LegalHold fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            List<String> tags = null;
            Boolean hasLegalHold = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("tags".equals(fieldName)) {
                    tags = reader.readArray(reader1 -> reader1.getString());
                } else if ("hasLegalHold".equals(fieldName)) {
                    hasLegalHold = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }
            LegalHold deserializedLegalHold = new LegalHold(tags);
            deserializedLegalHold.hasLegalHold = hasLegalHold;

            return deserializedLegalHold;
        });
    }
}
