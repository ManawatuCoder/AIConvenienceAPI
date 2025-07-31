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
 * The properties of an ImmutabilityPolicy of a blob container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class ImmutabilityPolicyProperties implements JsonSerializable<ImmutabilityPolicyProperties> {
    /*
     * The immutability period for the blobs in the container since the policy creation, in days
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Integer immutabilityPeriodSinceCreationInDays;

    /*
     * The ImmutabilityPolicy state of a blob container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ImmutabilityPolicyState state;

    /*
     * Allow protected append writes
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private Boolean allowProtectedAppendWrites;

    /*
     * ImmutabilityPolicy Etag
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private String etag;

    /*
     * The ImmutabilityPolicy update history of the blob container
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private List<UpdateHistoryProperty> updateHistory;

    /**
     * Creates an instance of ImmutabilityPolicyProperties class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperties() {
    }

    /**
     * Get the immutabilityPeriodSinceCreationInDays property: The immutability period for the blobs in the container
     * since the policy creation, in days.
     * 
     * @return the immutabilityPeriodSinceCreationInDays value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Integer getImmutabilityPeriodSinceCreationInDays() {
        return this.immutabilityPeriodSinceCreationInDays;
    }

    /**
     * Set the immutabilityPeriodSinceCreationInDays property: The immutability period for the blobs in the container
     * since the policy creation, in days.
     * 
     * @param immutabilityPeriodSinceCreationInDays the immutabilityPeriodSinceCreationInDays value to set.
     * @return the ImmutabilityPolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperties
        setImmutabilityPeriodSinceCreationInDays(Integer immutabilityPeriodSinceCreationInDays) {
        this.immutabilityPeriodSinceCreationInDays = immutabilityPeriodSinceCreationInDays;
        return this;
    }

    /**
     * Get the state property: The ImmutabilityPolicy state of a blob container.
     * 
     * @return the state value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyState getState() {
        return this.state;
    }

    /**
     * Set the state property: The ImmutabilityPolicy state of a blob container.
     * 
     * @param state the state value to set.
     * @return the ImmutabilityPolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperties setState(ImmutabilityPolicyState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the allowProtectedAppendWrites property: Allow protected append writes.
     * 
     * @return the allowProtectedAppendWrites value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public Boolean isAllowProtectedAppendWrites() {
        return this.allowProtectedAppendWrites;
    }

    /**
     * Set the allowProtectedAppendWrites property: Allow protected append writes.
     * 
     * @param allowProtectedAppendWrites the allowProtectedAppendWrites value to set.
     * @return the ImmutabilityPolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperties setAllowProtectedAppendWrites(Boolean allowProtectedAppendWrites) {
        this.allowProtectedAppendWrites = allowProtectedAppendWrites;
        return this;
    }

    /**
     * Get the etag property: ImmutabilityPolicy Etag.
     * 
     * @return the etag value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getEtag() {
        return this.etag;
    }

    /**
     * Set the etag property: ImmutabilityPolicy Etag.
     * 
     * @param etag the etag value to set.
     * @return the ImmutabilityPolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperties setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Get the updateHistory property: The ImmutabilityPolicy update history of the blob container.
     * 
     * @return the updateHistory value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public List<UpdateHistoryProperty> getUpdateHistory() {
        return this.updateHistory;
    }

    /**
     * Set the updateHistory property: The ImmutabilityPolicy update history of the blob container.
     * 
     * @param updateHistory the updateHistory value to set.
     * @return the ImmutabilityPolicyProperties object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperties setUpdateHistory(List<UpdateHistoryProperty> updateHistory) {
        this.updateHistory = updateHistory;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("immutabilityPeriodSinceCreationInDays",
            this.immutabilityPeriodSinceCreationInDays);
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeBooleanField("allowProtectedAppendWrites", this.allowProtectedAppendWrites);
        jsonWriter.writeStringField("etag", this.etag);
        jsonWriter.writeArrayField("updateHistory", this.updateHistory, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ImmutabilityPolicyProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ImmutabilityPolicyProperties if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ImmutabilityPolicyProperties.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ImmutabilityPolicyProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ImmutabilityPolicyProperties deserializedImmutabilityPolicyProperties = new ImmutabilityPolicyProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("immutabilityPeriodSinceCreationInDays".equals(fieldName)) {
                    deserializedImmutabilityPolicyProperties.immutabilityPeriodSinceCreationInDays
                        = reader.getNullable(JsonReader::getInt);
                } else if ("state".equals(fieldName)) {
                    deserializedImmutabilityPolicyProperties.state
                        = ImmutabilityPolicyState.fromString(reader.getString());
                } else if ("allowProtectedAppendWrites".equals(fieldName)) {
                    deserializedImmutabilityPolicyProperties.allowProtectedAppendWrites
                        = reader.getNullable(JsonReader::getBoolean);
                } else if ("etag".equals(fieldName)) {
                    deserializedImmutabilityPolicyProperties.etag = reader.getString();
                } else if ("updateHistory".equals(fieldName)) {
                    List<UpdateHistoryProperty> updateHistory
                        = reader.readArray(reader1 -> UpdateHistoryProperty.fromJson(reader1));
                    deserializedImmutabilityPolicyProperties.updateHistory = updateHistory;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedImmutabilityPolicyProperties;
        });
    }
}
