public final class WriteTitleAndInventory implements Command {
    /**
     * current input.
     */
    private Input input;
    /**
     * basic constructor.
     * @param newInput - current input
     */
    public WriteTitleAndInventory(final Input newInput) {
        this.input = newInput;
    }
    @Override
    public boolean execute() {
        var dataSource = input.askString("Enter data source file path: ");
        var folderPath = input.askString("Enter destination folder path: ");
        var docWriter = new DocumentWriter(dataSource, folderPath);
        docWriter.writeDocument("cfgInventory.properties",
                "vInventoryPageTemplate.docx", "inventory.docx");
        docWriter.writeDocument("cfgTitlePage.properties",
                "titlePageTemplate.docx", "titlePage.docx");
        return true;
    }

    @Override
    public String getCommandName() {
        return "Write title and inventory";
    }
}
