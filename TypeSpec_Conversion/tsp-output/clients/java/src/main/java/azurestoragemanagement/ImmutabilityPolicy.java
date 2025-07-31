package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * The ImmutabilityPolicy property of a blob container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class ImmutabilityPolicy extends AzureEntityResource {
    /*
     * The properties of an ImmutabilityPolicy of a blob container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private final ImmutabilityPolicyProperty properties;

    /**
     * Creates an instance of ImmutabilityPolicy class.
     * 
     * @param properties the properties value to set.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicy(ImmutabilityPolicyProperty properties) {
        this.properties = properties;
    }

    /**
     * Get the properties property: The properties of an ImmutabilityPolicy of a blob container.
     * 
     * @return the properties value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperty getProperties() {
        return this.properties;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public ImmutabilityPolicy setId(String id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public ImmutabilityPolicy setName(String name) {
        super.setName(name);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public ImmutabilityPolicy setType(String type) {
        super.setType(type);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public ImmutabilityPolicy setEtag(String etag) {
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
     * Reads an instance of ImmutabilityPolicy from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ImmutabilityPolicy if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ImmutabilityPolicy.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ImmutabilityPolicy fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            String name = null;
            String type = null;
            String etag = null;
            ImmutabilityPolicyProperty properties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("name".equals(fieldName)) {
                    name = reader.getString();
                } else if ("type".equals(fieldName)) {
                    type = reader.getString();
                } else if ("etag".equals(fieldName)) {
                    etag = reader.getString();
                } else if ("properties".equals(fieldName)) {
                    properties = ImmutabilityPolicyProperty.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            ImmutabilityPolicy deserializedImmutabilityPolicy = new ImmutabilityPolicy(properties);
            deserializedImmutabilityPolicy.setId(id);
            deserializedImmutabilityPolicy.setName(name);
            deserializedImmutabilityPolicy.setType(type);
            deserializedImmutabilityPolicy.setEtag(etag);

            return deserializedImmutabilityPolicy;
        });
    }
}
