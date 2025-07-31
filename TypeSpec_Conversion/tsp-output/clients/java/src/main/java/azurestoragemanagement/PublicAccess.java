package azurestoragemanagement;

/**
 * Specifies whether data in the container may be accessed publicly and the level of access.
 */
public enum PublicAccess {
    /**
     * Enum value Container.
     */
    CONTAINER("Container"),

    /**
     * Enum value Blob.
     */
    BLOB("Blob"),

    /**
     * Enum value None.
     */
    NONE("None");

    /**
     * The actual serialized value for a PublicAccess instance.
     */
    private final String value;

    PublicAccess(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a PublicAccess instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed PublicAccess object, or null if unable to parse.
     */
    public static PublicAccess fromString(String value) {
        if (value == null) {
            return null;
        }
        PublicAccess[] items = PublicAccess.values();
        for (PublicAccess item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
