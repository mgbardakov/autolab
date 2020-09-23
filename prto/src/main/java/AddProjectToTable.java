import java.io.IOException;

public class AddProjectToTable implements Command {
    private static final String SOURCE_PATH = "D:\\child\\pInputTemplate.xlsx";
    private static final String TABLE_PATH = "D:\\child\\projectList.xlsx";

    @Override
    public boolean execute() {
        try (var parser = new ProjectDataParser(SOURCE_PATH)) {
            var map = parser.getData();
            var tableWriter = new ProjectTableWriter();
            tableWriter.writeTable(TABLE_PATH, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public String getCommandName() {
        return "Add project to table.";
    }
}
