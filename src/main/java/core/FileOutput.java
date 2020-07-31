package core;

import java.io.OutputStream;

/**
 * class that chooses destination of generated report.
 * @author mbardakov
 * @since 31.07.2020
 */
public final class FileOutput implements Output {
    @Override
    public boolean unloadReport(final OutputStream ros) {
        return false;
    }
}
