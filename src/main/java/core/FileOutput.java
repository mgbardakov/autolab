package core;

/**
 * class that chooses destination of generated report.
 * @author mbardakov
 * @since 31.07.2020
 */
public final class FileOutput implements Output {
    /**
     * path where to save report.
     */
    private String path;

    /**
     * main constructor.
     * @param filePath - file path where to save report
     */
    public FileOutput(final String filePath) {
        this.path = filePath;
    }

    @Override
    public boolean unloadReport(final byte[] report) {
        return false;
    }
}
