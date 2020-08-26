package core;


import org.apache.poi.ss.usermodel.Workbook;

/** output interface for reports.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface WorkbookOutput {
    /**
     * process the file.
     * @param workbook - apache POI workbook
     * @return success/failure
     */
    boolean saveReport(Workbook workbook);
}
