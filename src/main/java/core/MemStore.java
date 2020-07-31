package core;

import java.util.List;

/**
 * implementation of store interface using RAM.
 * @author mbardakov
 * @since 29.07.2020
 */
public final class MemStore implements Store {
    /**
     * current report engine.
     */
    private ReportGenerator generator;
    /**
     * current loader.
     */
    private Loader loader;

    /**
     * main  constructor.
     * @param currentGenerator - current report engine
     * @param currentLoader - current loader
     */
    public MemStore(final ReportGenerator currentGenerator,
                    final Loader currentLoader
    ) {
        this.generator = currentGenerator;
        this.loader = currentLoader;
    }

    @Override
    public Record addNewRecord() {
        return null;
    }

    @Override
    public boolean updateRecord(final String id, final Record record) {
        return false;
    }

    @Override
    public boolean deleteRecord(final String id) {
        return false;
    }

    @Override
    public List<Record> getAllRecords() {
        return null;
    }

    @Override
    public List<Record> getRecordsByFactor(final String factor) {
        return null;
    }

    @Override
    public List<Record> getRecordsByType(final String type) {
        return null;
    }

    @Override
    public List<Record> getRecordsByTypeAndFactor(
            final String type, final String factor) {
        return null;
    }
}
