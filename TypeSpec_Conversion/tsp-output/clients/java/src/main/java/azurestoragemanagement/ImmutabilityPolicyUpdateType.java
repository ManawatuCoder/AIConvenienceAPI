package azurestoragemanagement;

/**
 * The ImmutabilityPolicy update type of a blob container.
 */
public enum ImmutabilityPolicyUpdateType {
    /**
     * Enum value put.
     */
    PUT("put"),

    /**
     * Enum value lock.
     */
    LOCK("lock"),

    /**
     * Enum value extend.
     */
    EXTEND("extend");

    /**
     * The actual serialized value for a ImmutabilityPolicyUpdateType instance.
     */
    private final String value;

    ImmutabilityPolicyUpdateType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a ImmutabilityPolicyUpdateType instance.
     * 
     * @param value the serialized value to parse.
     * @return the parsed ImmutabilityPolicyUpdateType object, or null if unable to parse.
     */
    public static ImmutabilityPolicyUpdateType fromString(String value) {
        if (value == null) {
            return null;
        }
        ImmutabilityPolicyUpdateType[] items = ImmutabilityPolicyUpdateType.values();
        for (ImmutabilityPolicyUpdateType item : items) {
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
