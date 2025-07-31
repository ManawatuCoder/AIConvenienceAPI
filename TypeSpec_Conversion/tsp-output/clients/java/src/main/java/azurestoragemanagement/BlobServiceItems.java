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
 * List of blob services.
 */
@Metadata(properties = { MetadataProperties.IMMUTABLE })
public final class BlobServiceItems implements JsonSerializable<BlobServiceItems> {
    /*
     * List of blob services returned
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private List<BlobServiceProperties> value;

    /**
     * Creates an instance of BlobServiceItems class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private BlobServiceItems() {
    }

    /**
     * Get the value property: List of blob services returned.
     * 
     * @return the value value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public List<BlobServiceProperties> getValue() {
        return this.value;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("value", this.value, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BlobServiceItems from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BlobServiceItems if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the BlobServiceItems.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static BlobServiceItems fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BlobServiceItems deserializedBlobServiceItems = new BlobServiceItems();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    List<BlobServiceProperties> value
                        = reader.readArray(reader1 -> BlobServiceProperties.fromJson(reader1));
                    deserializedBlobServiceItems.value = value;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBlobServiceItems;
        });
    }
}
