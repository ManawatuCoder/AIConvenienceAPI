package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * Azure Entity Resource.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public class AzureEntityResource implements JsonSerializable<AzureEntityResource> {
    /*
     * Fully qualified resource ID for the resource
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String id;

    /*
     * The name of the resource
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String name;

    /*
     * The type of the resource
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String type;

    /*
     * Resource Etag
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String etag;

    /**
     * Creates an instance of AzureEntityResource class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureEntityResource() {
    }

    /**
     * Get the id property: Fully qualified resource ID for the resource.
     * 
     * @return the id value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Fully qualified resource ID for the resource.
     * 
     * @param id the id value to set.
     * @return the AzureEntityResource object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureEntityResource setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the name property: The name of the resource.
     * 
     * @return the name value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name of the resource.
     * 
     * @param name the name value to set.
     * @return the AzureEntityResource object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureEntityResource setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the type property: The type of the resource.
     * 
     * @return the type value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getType() {
        return this.type;
    }

    /**
     * Set the type property: The type of the resource.
     * 
     * @param type the type value to set.
     * @return the AzureEntityResource object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureEntityResource setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the etag property: Resource Etag.
     * 
     * @return the etag value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getEtag() {
        return this.etag;
    }

    /**
     * Set the etag property: Resource Etag.
     * 
     * @param etag the etag value to set.
     * @return the AzureEntityResource object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureEntityResource setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("type", this.type);
        jsonWriter.writeStringField("etag", this.etag);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AzureEntityResource from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AzureEntityResource if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the AzureEntityResource.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static AzureEntityResource fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AzureEntityResource deserializedAzureEntityResource = new AzureEntityResource();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedAzureEntityResource.id = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedAzureEntityResource.name = reader.getString();
                } else if ("type".equals(fieldName)) {
                    deserializedAzureEntityResource.type = reader.getString();
                } else if ("etag".equals(fieldName)) {
                    deserializedAzureEntityResource.etag = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAzureEntityResource;
        });
    }
}
