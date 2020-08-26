package core.actions;

/**
 * interface of user actions.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface Action {
    /**
     * executes some action.
     * @return true / false
     */
    boolean execute();

    /**
     * returns action title.
     * @return action title
     */
    String getActionName();
}
