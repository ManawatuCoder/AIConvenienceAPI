package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A tag of the LegalHold of a blob container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class TagProperty implements JsonSerializable<TagProperty> {
    /*
     * The tag value
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String tag;

    /*
     * Returns the date and time the tag was added
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private OffsetDateTime timestamp;

    /*
     * Returns the Object ID of the user who added the tag
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String objectIdentifier;

    /*
     * Returns the Tenant ID that issued the token for the user who added the tag
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String tenantId;

    /*
     * Returns the User Principal Name of the user who added the tag
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String upn;

    /**
     * Creates an instance of TagProperty class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public TagProperty() {
    }

    /**
     * Get the tag property: The tag value.
     * 
     * @return the tag value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getTag() {
        return this.tag;
    }

    /**
     * Set the tag property: The tag value.
     * 
     * @param tag the tag value to set.
     * @return the TagProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public TagProperty setTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * Get the timestamp property: Returns the date and time the tag was added.
     * 
     * @return the timestamp value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Set the timestamp property: Returns the date and time the tag was added.
     * 
     * @param timestamp the timestamp value to set.
     * @return the TagProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public TagProperty setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get the objectIdentifier property: Returns the Object ID of the user who added the tag.
     * 
     * @return the objectIdentifier value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getObjectIdentifier() {
        return this.objectIdentifier;
    }

    /**
     * Set the objectIdentifier property: Returns the Object ID of the user who added the tag.
     * 
     * @param objectIdentifier the objectIdentifier value to set.
     * @return the TagProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public TagProperty setObjectIdentifier(String objectIdentifier) {
        this.objectIdentifier = objectIdentifier;
        return this;
    }

    /**
     * Get the tenantId property: Returns the Tenant ID that issued the token for the user who added the tag.
     * 
     * @return the tenantId value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Returns the Tenant ID that issued the token for the user who added the tag.
     * 
     * @param tenantId the tenantId value to set.
     * @return the TagProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public TagProperty setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the upn property: Returns the User Principal Name of the user who added the tag.
     * 
     * @return the upn value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getUpn() {
        return this.upn;
    }

    /**
     * Set the upn property: Returns the User Principal Name of the user who added the tag.
     * 
     * @param upn the upn value to set.
     * @return the TagProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public TagProperty setUpn(String upn) {
        this.upn = upn;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("tag", this.tag);
        jsonWriter.writeStringField("timestamp",
            this.timestamp == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.timestamp));
        jsonWriter.writeStringField("objectIdentifier", this.objectIdentifier);
        jsonWriter.writeStringField("tenantId", this.tenantId);
        jsonWriter.writeStringField("upn", this.upn);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TagProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of TagProperty if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the TagProperty.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static TagProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TagProperty deserializedTagProperty = new TagProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("tag".equals(fieldName)) {
                    deserializedTagProperty.tag = reader.getString();
                } else if ("timestamp".equals(fieldName)) {
                    deserializedTagProperty.timestamp
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("objectIdentifier".equals(fieldName)) {
                    deserializedTagProperty.objectIdentifier = reader.getString();
                } else if ("tenantId".equals(fieldName)) {
                    deserializedTagProperty.tenantId = reader.getString();
                } else if ("upn".equals(fieldName)) {
                    deserializedTagProperty.upn = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTagProperty;
        });
    }
}
