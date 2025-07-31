package azurestoragemanagement;

/**
 * Lease state of the container.
 */
public enum LeaseState {
    /**
     * Enum value Available.
     */
    AVAILABLE("Available"),

    /**
     * Enum value Leased.
     */
    LEASED("Leased"),

    /**
     * Enum value Expired.
     */
    EXPIRED("Expired"),

    /**
     * Enum value Breaking.
     */
    BREAKING("Breaking"),

    /**
     * Enum value Broken.
     */
    BROKEN("Broken");

    /**
     * The actual serialized value for a LeaseState instance.
     */
    private final String value;

    LeaseState(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a LeaseState instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed LeaseState object, or null if unable to parse.
     */
    public static LeaseState fromString(String value) {
        if (value == null) {
            return null;
        }
        LeaseState[] items = LeaseState.values();
        for (LeaseState item : items) {
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
