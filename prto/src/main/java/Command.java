public interface Command {
    /**
     * executes some action.
     * @return false if interrupt
     */
    boolean execute();
    /**
     * gets command name.
     * @return command name.
     */
    String getCommandName();
}
