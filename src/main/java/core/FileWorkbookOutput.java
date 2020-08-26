package core;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * class that chooses destination of generated report.
 * @author mbardakov
 * @since 31.07.2020
 */
public final class FileWorkbookOutput implements WorkbookOutput {
    /**
     * path where to save report.
     */
    private String path;

    /**
     * main constructor.
     * @param filePath - file path where to save report
     */
    public FileWorkbookOutput(final String filePath) {
        this.path = filePath;
    }

    @Override
    public boolean saveReport(Workbook workbook) {
        try (var fos = new FileOutputStream(path)) {
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }
        return false;
    }
}
