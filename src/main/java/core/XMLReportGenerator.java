package core;

import java.io.OutputStream;

/**
 * class tha generates XML reports.
 * @author mbardakov
 * @since 31.07.2020
 */
public final class XMLReportGenerator implements ReportGenerator {
    /**
     * current store.
     */
    private Store store;
    /**
     * current output.
     */
    private Output output;

    /**
     * constructor.
     * @param dataStore - current data store
     * @param reportOutput - current report output
     */
    public XMLReportGenerator(final Store dataStore, final Output reportOutput
    ) {
        this.store = dataStore;
        this.output = reportOutput;
    }

    @Override
    public OutputStream create18Form() {
        return null;
    }
}
