package core.actions;

import core.output.FileWorkbookOutput;
import core.input.Input;
import core.generator.ReportGenerator;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * user action for creation and saving report.
 * @author mbardakov
 * @since 26.08.2020
 */
public final class GenerateReportAction implements Action {
    /**
     * user input.
     */
    private Input input;
    /**
     * report generator.
     */
    private ReportGenerator generator;
    /**
     * consumer for user output.
     */
    private Consumer<String> con;

    /**
     * basic constructor.
     * @param currentInput user input
     * @param currentGenerator generator of the report
     * @param newCon consumer for user output
     */
    public GenerateReportAction(final Input currentInput,
                                final ReportGenerator currentGenerator,
                                final Consumer<String> newCon) {
        this.input = currentInput;
        this.generator = currentGenerator;
        this.con = newCon;
    }

    @Override
    public boolean execute() {
        var run = true;
        while (run) {
            var path = input.askString("Enter destination path: ");
            var output = new FileWorkbookOutput(path);
            try {
                output.saveReport(generator.createReport());
                run = false;
            } catch (IOException e) {
                con.accept("Problems with report path, try again");
             }
        }
        return true;
    }

    @Override
    public String getActionName() {
        return String.format("Generate %s", generator.getReportName());
    }
}
