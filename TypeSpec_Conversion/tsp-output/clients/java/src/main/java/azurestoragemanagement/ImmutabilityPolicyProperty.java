package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * The properties of an ImmutabilityPolicy of a blob container.
 */
@Metadata(properties = { MetadataProperties.FLUENT })
public final class ImmutabilityPolicyProperty implements JsonSerializable<ImmutabilityPolicyProperty> {
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

    /**
     * Creates an instance of ImmutabilityPolicyProperty class.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperty() {
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
     * @return the ImmutabilityPolicyProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperty
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
     * @return the ImmutabilityPolicyProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperty setState(ImmutabilityPolicyState state) {
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
     * @return the ImmutabilityPolicyProperty object itself.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public ImmutabilityPolicyProperty setAllowProtectedAppendWrites(Boolean allowProtectedAppendWrites) {
        this.allowProtectedAppendWrites = allowProtectedAppendWrites;
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
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ImmutabilityPolicyProperty from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ImmutabilityPolicyProperty if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ImmutabilityPolicyProperty.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ImmutabilityPolicyProperty fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ImmutabilityPolicyProperty deserializedImmutabilityPolicyProperty = new ImmutabilityPolicyProperty();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("immutabilityPeriodSinceCreationInDays".equals(fieldName)) {
                    deserializedImmutabilityPolicyProperty.immutabilityPeriodSinceCreationInDays
                        = reader.getNullable(JsonReader::getInt);
                } else if ("state".equals(fieldName)) {
                    deserializedImmutabilityPolicyProperty.state
                        = ImmutabilityPolicyState.fromString(reader.getString());
                } else if ("allowProtectedAppendWrites".equals(fieldName)) {
                    deserializedImmutabilityPolicyProperty.allowProtectedAppendWrites
                        = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedImmutabilityPolicyProperty;
        });
    }
}
