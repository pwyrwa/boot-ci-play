package org.pio.error;

/**
 * Exception indicating that und
 * <p/>
 * Created 2/15/16.
 */
public class RequiredServiceNotReachebleException extends RuntimeException {

    public enum MODULE {
        USER_REPOSITORY,
        NOT_DETERMINED
    }

    private final MODULE module;

    public RequiredServiceNotReachebleException(MODULE module, String message) {
        super(message);
        this.module = module != null ? module : MODULE.NOT_DETERMINED;
    }

    public MODULE getModule() {
        return module;
    }
}
