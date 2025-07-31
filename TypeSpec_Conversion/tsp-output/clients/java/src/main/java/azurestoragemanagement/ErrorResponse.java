package azurestoragemanagement;

import io.clientcore.core.annotations.Metadata;
import io.clientcore.core.annotations.MetadataProperties;
import io.clientcore.core.serialization.json.JsonReader;
import io.clientcore.core.serialization.json.JsonSerializable;
import io.clientcore.core.serialization.json.JsonToken;
import io.clientcore.core.serialization.json.JsonWriter;
import java.io.IOException;

/**
 * The ErrorResponse model.
 */
@Metadata(properties = { MetadataProperties.IMMUTABLE })
public final class ErrorResponse implements JsonSerializable<ErrorResponse> {
    /*
     * Error code
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private final String code;

    /*
     * Error message
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private final String message;

    /**
     * Creates an instance of ErrorResponse class.
     * 
     * @param code the code value to set.
     * @param message the message value to set.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    private ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Get the code property: Error code.
     * 
     * @return the code value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getCode() {
        return this.code;
    }

    /**
     * Get the message property: Error message.
     * 
     * @return the message value.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public String getMessage() {
        return this.message;
    }

    /**
     * {@inheritDoc}
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("message", this.message);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ErrorResponse from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ErrorResponse if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ErrorResponse.
     */
    @Metadata(properties = { MetadataProperties.GENERATED })
    public static ErrorResponse fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String code = null;
            String message = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("code".equals(fieldName)) {
                    code = reader.getString();
                } else if ("message".equals(fieldName)) {
                    message = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new ErrorResponse(code, message);
        });
    }
}
