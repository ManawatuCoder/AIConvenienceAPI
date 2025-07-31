package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * The blob container properties be listed out.
 */
@Metadata(properties = { MetadataProperties.IMMUTABLE })
public final class ListContainerItem extends AzureEntityResource {
    /*
     * The blob container properties be listed out
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ContainerProperties properties;

    /**
     * Creates an instance of ListContainerItem class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ListContainerItem() {
    }

    /**
     * Get the properties property: The blob container properties be listed out.
     * 
     * @return the properties value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties getProperties() {
        return this.properties;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", getId());
        jsonWriter.writeStringField("name", getName());
        jsonWriter.writeStringField("type", getType());
        jsonWriter.writeStringField("etag", getEtag());
        jsonWriter.writeJsonField("properties", this.properties);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ListContainerItem from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ListContainerItem if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ListContainerItem.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ListContainerItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ListContainerItem deserializedListContainerItem = new ListContainerItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedListContainerItem.setId(reader.getString());
                } else if ("name".equals(fieldName)) {
                    deserializedListContainerItem.setName(reader.getString());
                } else if ("type".equals(fieldName)) {
                    deserializedListContainerItem.setType(reader.getString());
                } else if ("etag".equals(fieldName)) {
                    deserializedListContainerItem.setEtag(reader.getString());
                } else if ("properties".equals(fieldName)) {
                    deserializedListContainerItem.properties = ContainerProperties.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedListContainerItem;
        });
    }
}
