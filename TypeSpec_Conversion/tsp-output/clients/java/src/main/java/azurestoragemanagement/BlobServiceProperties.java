package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.models.binarydata.BinaryData;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * The properties of a storage account's Blob service.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class BlobServiceProperties extends AzureResource {
    /*
     * The properties of a storage account's Blob service
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private BlobServicePropertiesInner properties;

    /*
     * Sku name and tier
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private BinaryData sku;

    /**
     * Creates an instance of BlobServiceProperties class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServiceProperties() {
    }

    /**
     * Get the properties property: The properties of a storage account's Blob service.
     * 
     * @return the properties value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServicePropertiesInner getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: The properties of a storage account's Blob service.
     * 
     * @param properties the properties value to set.
     * @return the BlobServiceProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServiceProperties setProperties(BlobServicePropertiesInner properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get the sku property: Sku name and tier.
     * 
     * @return the sku value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BinaryData getSku() {
        return this.sku;
    }

    /**
     * Set the sku property: Sku name and tier.
     * 
     * @param sku the sku value to set.
     * @return the BlobServiceProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public BlobServiceProperties setSku(BinaryData sku) {
        this.sku = sku;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public BlobServiceProperties setId(String id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public BlobServiceProperties setName(String name) {
        super.setName(name);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public BlobServiceProperties setType(String type) {
        super.setType(type);
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
        jsonWriter.writeJsonField("properties", this.properties);
        if (this.sku != null) {
            jsonWriter.writeFieldName("sku");
            this.sku.writeTo(jsonWriter);
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BlobServiceProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of BlobServiceProperties if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BlobServiceProperties.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static BlobServiceProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BlobServiceProperties deserializedBlobServiceProperties = new BlobServiceProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedBlobServiceProperties.setId(reader.getString());
                } else if ("name".equals(fieldName)) {
                    deserializedBlobServiceProperties.setName(reader.getString());
                } else if ("type".equals(fieldName)) {
                    deserializedBlobServiceProperties.setType(reader.getString());
                } else if ("properties".equals(fieldName)) {
                    deserializedBlobServiceProperties.properties = BlobServicePropertiesInner.fromJson(reader);
                } else if ("sku".equals(fieldName)) {
                    deserializedBlobServiceProperties.sku
                        = reader.getNullable(nonNullReader -> BinaryData.fromObject(nonNullReader.readUntyped()));
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBlobServiceProperties;
        });
    }
}
