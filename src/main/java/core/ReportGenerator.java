package core;

import java.io.OutputStream;
/** report generator interface.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface ReportGenerator {
    /**
     * creates for 18 report.
     * @return output stream of the report file
     */
    OutputStream create18Form();
}
