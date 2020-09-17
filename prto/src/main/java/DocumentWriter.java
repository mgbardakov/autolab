import java.io.FileOutputStream;
import java.io.IOException;

public class DocumentWriter {
    /**
     * data source.
     */
    private String dataSource;
    /**
     * folder path.
     */
    private String folderPath;
    /**
     * main constructor.
     * @param newDataSource data source file
     * @param newFolderPath destination folder path
     */
    public DocumentWriter(final String newDataSource,
                          final String newFolderPath) {
        this.dataSource = newDataSource;
        this.folderPath = newFolderPath;
    }
    /**
     * writes file.
     * @param cfgFile config file
     * @param templateFile template file
     * @param rslFileName result file name
     */
    public void writeDocument(final String cfgFile,
                                     final String templateFile,
                                     final String rslFileName) {
        try (var fos = new FileOutputStream(folderPath + rslFileName);
             var book = new XlsxFileDataParser(dataSource, cfgFile)) {
             var map = book.getData();
             var doc = new DocGenerator(templateFile).generateDocument(map);
            doc.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
