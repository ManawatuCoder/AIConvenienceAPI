package azurestoragemanagement;

/**
 * Last access time tracking policy name.
 */
public enum LastAccessTimeTrackingPolicyName {
    /**
     * Enum value AccessTimeTracking.
     */
    ACCESS_TIME_TRACKING("AccessTimeTracking");

    /**
     * The actual serialized value for a LastAccessTimeTrackingPolicyName instance.
     */
    private final String value;

    LastAccessTimeTrackingPolicyName(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a LastAccessTimeTrackingPolicyName instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed LastAccessTimeTrackingPolicyName object, or null if unable to parse.
     */
    public static LastAccessTimeTrackingPolicyName fromString(String value) {
        if (value == null) {
            return null;
        }
        LastAccessTimeTrackingPolicyName[] items = LastAccessTimeTrackingPolicyName.values();
        for (LastAccessTimeTrackingPolicyName item : items) {
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
