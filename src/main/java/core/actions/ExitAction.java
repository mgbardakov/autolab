package core.actions;

/**
 * user exit action.
 * @author mbardakov
 * @since 26.08.2020
 */
public final class ExitAction implements Action {
    /**
     * action name.
     */
    private static final String NAME = "Exit";
    /**
     * getter for name.
     * @return name
     */
    @Override
    public String getActionName() {
        return NAME;
    }

    @Override
    public boolean execute() {
        return false;
    }
}
