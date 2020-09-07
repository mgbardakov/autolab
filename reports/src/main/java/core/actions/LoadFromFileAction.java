package core.actions;

import core.input.Input;
import core.loader.SpbXMLFileLoader;
import core.storage.Store;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * loading records from file action.
 * @author mbardakov
 * @since 26.08.2020
 */
public final class LoadFromFileAction implements Action {
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
    /**
     * action name.
     */
    private static final String NAME = "Load data from file";
    /**
     * basic constructor.
     * @param newStore - storage
     * @param newInput - user input
     * @param newCon - consumer for user output
     */
    public LoadFromFileAction(final Store newStore, final Input newInput,
                              final Consumer<String> newCon) {
        this.store = newStore;
        this.input = newInput;
        this.con = newCon;
    }

    @Override
    public boolean execute() {
        while (true) {
            try {
                var filePath = input.askString(
                        "Enter source file path (b - return to main menu): ");
                if (filePath.equals("b")) {
                    break;
                }
                var loader = new SpbXMLFileLoader(filePath, store);
                loader.loadDataToStore();
            } catch (InvalidOperationException | IOException e) {
                con.accept("File not found, try again");
                con.accept(System.lineSeparator());
            }
        }
        return true;
    }

    @Override
    public String getActionName() {
        return NAME;
    }
}
