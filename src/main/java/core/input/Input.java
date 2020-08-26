package core.input;

/**
 * interface for basic input.
 * @author mbardakov
 * @since  28.07.2020
 */
public interface Input {
    /**
     * gets string from user.
     * @param question - output for user
     * @return user string
     */
    String askString(String question);
}
