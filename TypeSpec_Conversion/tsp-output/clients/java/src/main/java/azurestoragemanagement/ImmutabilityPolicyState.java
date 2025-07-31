package azurestoragemanagement;

/**
 * The ImmutabilityPolicy state of a blob container.
 */
public enum ImmutabilityPolicyState {
    /**
     * Enum value Locked.
     */
    LOCKED("Locked"),

    /**
     * Enum value Unlocked.
     */
    UNLOCKED("Unlocked");

    /**
     * The actual serialized value for a ImmutabilityPolicyState instance.
     */
    private final String value;

    ImmutabilityPolicyState(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ImmutabilityPolicyState instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed ImmutabilityPolicyState object, or null if unable to parse.
     */
    public static ImmutabilityPolicyState fromString(String value) {
        if (value == null) {
            return null;
        }
        ImmutabilityPolicyState[] items = ImmutabilityPolicyState.values();
        for (ImmutabilityPolicyState item : items) {
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
