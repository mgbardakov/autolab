package core;

import org.apache.poi.ss.usermodel.Workbook;

/** report generator interface.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface ReportGenerator {
    /**
     * creates report.
     * @return report file in file
     */
    Workbook createReport();
}
