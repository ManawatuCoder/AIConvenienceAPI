package azurestoragemanagement;

/**
 * Used to include the properties for soft deleted blob containers.
 */
public enum ListContainersInclude {
    /**
     * Enum value deleted.
     */
    DELETED("deleted");

    /**
     * The actual serialized value for a ListContainersInclude instance.
     */
    private final String value;

    ListContainersInclude(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ListContainersInclude instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed ListContainersInclude object, or null if unable to parse.
     */
    public static ListContainersInclude fromString(String value) {
        if (value == null) {
            return null;
        }
        ListContainersInclude[] items = ListContainersInclude.values();
        for (ListContainersInclude item : items) {
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
