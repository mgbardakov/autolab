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

    /**
     * get int from user.
     * @param question - output for user
     * @return user int
     */
    Integer askInt(String question);
    /**
     * get int from user from 0 to maximum value.
     * @param question - output for user
     * @param maximum - max value of int
     * @return user int
     */
    Integer askInt(String question, int maximum);
}
