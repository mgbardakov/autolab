package core.output;


import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

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
    boolean saveReport(Workbook workbook) throws IOException;
}
