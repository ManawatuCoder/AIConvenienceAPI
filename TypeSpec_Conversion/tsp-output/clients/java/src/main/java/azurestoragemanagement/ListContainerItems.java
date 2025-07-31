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
 * Response schema. Contains list of blobs returned, and if paging is requested or required, a URL to next page of
 * containers.
 */
@Metadata(properties = { MetadataProperties.IMMUTABLE })
public final class ListContainerItems implements JsonSerializable<ListContainerItems> {
    /*
     * List of blobs containers returned
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private List<ListContainerItem> value;

    /*
     * Request URL that can be used to query next page of containers
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String nextLink;

    /**
     * Creates an instance of ListContainerItems class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ListContainerItems() {
    }

    /**
     * Get the value property: List of blobs containers returned.
     * 
     * @return the value value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public List<ListContainerItem> getValue() {
        return this.value;
    }

    /**
     * Get the nextLink property: Request URL that can be used to query next page of containers.
     * 
     * @return the nextLink value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getNextLink() {
        return this.nextLink;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("value", this.value, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("nextLink", this.nextLink);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ListContainerItems from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ListContainerItems if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ListContainerItems.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ListContainerItems fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ListContainerItems deserializedListContainerItems = new ListContainerItems();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    List<ListContainerItem> value = reader.readArray(reader1 -> ListContainerItem.fromJson(reader1));
                    deserializedListContainerItems.value = value;
                } else if ("nextLink".equals(fieldName)) {
                    deserializedListContainerItems.nextLink = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedListContainerItems;
        });
    }
}
