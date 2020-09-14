import java.util.function.Consumer;

public class Application {
    /**
     * current input.
     */
    private Input input;
    /**
     * output consumer.
     */
    private Consumer<String> consumer;

    /**
     * main constructor.
     * @param newInput current input
     * @param newConsumer output consumer.
     */
    public Application(final Input newInput,
                       final Consumer<String> newConsumer) {
        this.input = newInput;
        this.consumer = newConsumer;
    }
    /**
     * main.
     * @param args arguments
     */
    public static void main(final String[] args) {
        Consumer<String> con = System.out::print;
        var input = new ConsoleInput(con);
        Application app = new Application(input, con);
        var actions = app.initActions();
        var run = true;
        while (run) {
            app.showMenu(actions);
            int i = input.askInt("Choose wisely: ", actions.length);
            run = actions[i - 1].execute();
        }
    }
    private void showMenu(final Command[] actions) {
        for (int i = 1; i <= actions.length; i++) {
            consumer.accept(String.format("%s. %s",
                    i, actions[i - 1].getCommandName()));
            consumer.accept(System.lineSeparator());
        }

    }
    private Command[] initActions() {
        return new Command[]{
                new WriteExpertiseAndAct(input),
                new WriteExpertiseAndActRoofs(input),
                new WriteTitleAndInventory(input),
                new Exit()
        };
    }
}
