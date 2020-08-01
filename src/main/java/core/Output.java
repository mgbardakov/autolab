package core;


/** output interface for reports.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface Output {
    /**
     * process the file.
     * @param report - report byte array
     * @return success/failure
     */
    boolean unloadReport(byte[] report);
}
