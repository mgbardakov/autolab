package core;

import java.util.List;

/**
 * implementation of store interface using RAM.
 * @author mbardakov
 * @since 29.07.2020
 */
public final class MemStore implements Store {
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
    @Override
    public boolean loadData(final Loader loader) {
        return false;
    }

    @Override
    public boolean saveReport(
            final ReportGenerator generator, final Output output
    ) {
        return false;
    }
}
