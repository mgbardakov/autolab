package core;

/** report generator interface.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface ReportGenerator {
    /**
     * creates report.
     * @param currentStore - current storage
     * @return report file in file
     */
    byte[] createReport(Store currentStore);

    /**
     * creates and saves report.
     * @param currentStore - current storage
     * @param currentOutput - current output
     * @return success/failure
     */
    boolean createAndSaveReport(Store currentStore, Output currentOutput);
}
