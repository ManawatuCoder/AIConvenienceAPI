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
public final class LegalHoldProperties implements JsonSerializable<LegalHoldProperties> {
    /*
     * The hasLegalHold public property
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean hasLegalHold;

    /*
     * The list of LegalHold tags of a blob container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private List<TagProperty> tags;

    /**
     * Creates an instance of LegalHoldProperties class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LegalHoldProperties() {
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
     * @return the LegalHoldProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LegalHoldProperties setHasLegalHold(Boolean hasLegalHold) {
        this.hasLegalHold = hasLegalHold;
        return this;
    }

    /**
     * Get the tags property: The list of LegalHold tags of a blob container.
     * 
     * @return the tags value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public List<TagProperty> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: The list of LegalHold tags of a blob container.
     * 
     * @param tags the tags value to set.
     * @return the LegalHoldProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public LegalHoldProperties setTags(List<TagProperty> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("hasLegalHold", this.hasLegalHold);
        jsonWriter.writeArrayField("tags", this.tags, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of LegalHoldProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of LegalHoldProperties if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the LegalHoldProperties.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static LegalHoldProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            LegalHoldProperties deserializedLegalHoldProperties = new LegalHoldProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("hasLegalHold".equals(fieldName)) {
                    deserializedLegalHoldProperties.hasLegalHold = reader.getNullable(JsonReader::getBoolean);
                } else if ("tags".equals(fieldName)) {
                    List<TagProperty> tags = reader.readArray(reader1 -> TagProperty.fromJson(reader1));
                    deserializedLegalHoldProperties.tags = tags;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedLegalHoldProperties;
        });
    }
}
