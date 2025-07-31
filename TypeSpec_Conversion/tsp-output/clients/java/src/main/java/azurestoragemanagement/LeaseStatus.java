package azurestoragemanagement;

/**
 * The lease status of the container.
 */
public enum LeaseStatus {
    /**
     * Enum value Locked.
     */
    LOCKED("Locked"),

    /**
     * Enum value Unlocked.
     */
    UNLOCKED("Unlocked");

    /**
     * The actual serialized value for a LeaseStatus instance.
     */
    private final String value;

    LeaseStatus(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a LeaseStatus instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed LeaseStatus object, or null if unable to parse.
     */
    public static LeaseStatus fromString(String value) {
        if (value == null) {
            return null;
        }
        LeaseStatus[] items = LeaseStatus.values();
        for (LeaseStatus item : items) {
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
