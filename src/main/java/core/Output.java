package core;

import java.io.OutputStream;

/** output interface for reports.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface Output {
    /**
     * process the file.
     * @param ros - report outputStream
     * @return success/failure
     */
    boolean unloadReport(OutputStream ros);
}
