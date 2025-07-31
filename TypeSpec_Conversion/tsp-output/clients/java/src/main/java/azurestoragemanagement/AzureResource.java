package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * Azure Resource.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public class AzureResource implements JsonSerializable<AzureResource> {
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

    /**
     * Creates an instance of AzureResource class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureResource() {
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
     * @return the AzureResource object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureResource setId(String id) {
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
     * @return the AzureResource object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureResource setName(String name) {
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
     * @return the AzureResource object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public AzureResource setType(String type) {
        this.type = type;
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
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AzureResource from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of AzureResource if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the AzureResource.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static AzureResource fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AzureResource deserializedAzureResource = new AzureResource();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedAzureResource.id = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedAzureResource.name = reader.getString();
                } else if ("type".equals(fieldName)) {
                    deserializedAzureResource.type = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAzureResource;
        });
    }
}
