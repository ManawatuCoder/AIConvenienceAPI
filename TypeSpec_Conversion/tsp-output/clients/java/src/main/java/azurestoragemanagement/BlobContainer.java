package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * Properties of the blob container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class BlobContainer extends AzureEntityResource {
    /*
     * Properties of the blob container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ContainerProperties properties;

    /**
     * Creates an instance of BlobContainer class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobContainer() {
    }

    /**
     * Get the properties property: Properties of the blob container.
     * 
     * @return the properties value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ContainerProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties of the blob container.
     * 
     * @param properties the properties value to set.
     * @return the BlobContainer object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobContainer setProperties(ContainerProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public BlobContainer setId(String id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public BlobContainer setName(String name) {
        super.setName(name);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public BlobContainer setType(String type) {
        super.setType(type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public BlobContainer setEtag(String etag) {
        super.setEtag(etag);
        return this;
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
     * Reads an instance of BlobContainer from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BlobContainer if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the BlobContainer.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static BlobContainer fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BlobContainer deserializedBlobContainer = new BlobContainer();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedBlobContainer.setId(reader.getString());
                } else if ("name".equals(fieldName)) {
                    deserializedBlobContainer.setName(reader.getString());
                } else if ("type".equals(fieldName)) {
                    deserializedBlobContainer.setType(reader.getString());
                } else if ("etag".equals(fieldName)) {
                    deserializedBlobContainer.setEtag(reader.getString());
                } else if ("properties".equals(fieldName)) {
                    deserializedBlobContainer.properties = ContainerProperties.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBlobContainer;
        });
    }
}
