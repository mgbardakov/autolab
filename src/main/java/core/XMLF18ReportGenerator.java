package core;

/**
 * class tha generates XML reports.
 * @author mbardakov
 * @since 31.07.2020
 */
public final class XMLF18ReportGenerator implements ReportGenerator {
    @Override
    public byte[] createReport(final Store currentStore) {
        return new byte[0];
    }

    @Override
    public boolean createAndSaveReport(
            final Store currentStore, final Output currentOutput) {
        return false;
    }
}
