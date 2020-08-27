package core;

import core.actions.Action;
import core.actions.ExitAction;
import core.actions.GenerateReportAction;
import core.actions.LoadFromFileAction;
import core.generator.XMLF1318ReportGenerator;
import core.input.ConsoleInput;
import core.input.Input;
import core.storage.MemStore;
import core.storage.RecordReadable;
import core.storage.Store;

import java.util.function.Consumer;

public class MainUI {
    private Input input;
    private Store store;
    private RecordReadable readable;
    private Consumer<String> con;

    public MainUI(Input input, Store store, RecordReadable readable,
                  Consumer<String> con) {
        this.input = input;
        this.store = store;
        this.readable = readable;
        this.con = con;
    }

    public static void main(String[] args) {
        Consumer<String> con = System.out::print;
        var store = new MemStore();
        var input = new ConsoleInput(con);
        MainUI app = new MainUI(input, store, store, con);
        var actions = app.initActions();
        var run = true;
        while (run) {
            app.showMenu(actions);
            int i = input.askInt("Choose wisely: ", 3);
            run = actions[i - 1].execute();
        }
    }

    private void showMenu(Action[] actions) {
        for (int i = 1; i <= actions.length; i++) {
            con.accept(String.format("%s. %s", i, actions[i - 1].getActionName()));
            con.accept(System.lineSeparator());
        }

    }
    private Action[] initActions(){
        return new Action[]{
                new LoadFromFileAction(store, input, con),
                new GenerateReportAction(
                        input, new XMLF1318ReportGenerator(readable),
                        con),
                new ExitAction()
        };
    }
}
