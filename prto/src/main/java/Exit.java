public final class Exit implements Command {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public String getCommandName() {
        return "Exit";
    }
}
