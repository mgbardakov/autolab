
public final class WriteExpertiseAndAct implements Command {
    /**
     * current input.
     */
    private Input input;
    /**
     * basic constructor.
     * @param newInput - current input
     */
    public WriteExpertiseAndAct(final Input newInput) {
        this.input = newInput;
    }
    @Override
    public boolean execute() {
        var dataSource = input.askString("Enter data source file path: ");
        var folderPath = input.askString("Enter destination folder path: ");
        var docWriter = new DocumentWriter(dataSource, folderPath);
        docWriter.writeDocument("cfgExploitExpertise.properties",
                "vTemplate.docx", "expertise.docx");
        docWriter.writeDocument("cfgAct.properties",
                "actTemplate.docx", "act.docx");
        return true;
    }

    @Override
    public String getCommandName() {
        return "Write exploitation expertise and act";
    }
}
