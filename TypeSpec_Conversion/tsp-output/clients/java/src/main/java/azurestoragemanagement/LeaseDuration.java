package azurestoragemanagement;

/**
 * Specifies whether the lease on a container is of infinite or fixed duration.
 */
public enum LeaseDuration {
    /**
     * Enum value Infinite.
     */
    INFINITE("Infinite"),

    /**
     * Enum value Fixed.
     */
    FIXED("Fixed");

    /**
     * The actual serialized value for a LeaseDuration instance.
     */
    private final String value;

    LeaseDuration(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a LeaseDuration instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed LeaseDuration object, or null if unable to parse.
     */
    public static LeaseDuration fromString(String value) {
        if (value == null) {
            return null;
        }
        LeaseDuration[] items = LeaseDuration.values();
        for (LeaseDuration item : items) {
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
