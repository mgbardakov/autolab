package core;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * loading records from file action.
 * @author mbardakov
 * @since 26.08.2020
 */
public class LoadFromFileAction implements Action {
    /**
     * current loader.
     */
    private Store store;
    /**
     * user input.
     */
    private Input input;
    /**
     * local output consumer.
     */
    private Consumer<String> con;

    public LoadFromFileAction(final Store newStore, final Input newInput,
                              final Consumer<String> newCon) {
        this.store = newStore;
        this.input = newInput;
        this.con = newCon;
    }

    @Override
    public void execute() {
        while (true) {
            try {
                var filePath = input.askString(
                        "Enter source file path (b - return to main menu): ");
                if (filePath.equals("b")) {
                    break;
                }
                var loader = new SpbXMLFileLoader(filePath, store);
                loader.loadDataToStore();
            } catch (IOException e) {
                con.accept("File not found, try again");
            }
        }
    }
}
