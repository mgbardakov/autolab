package core.storage;

import core.Record;

import java.util.List;

/**
 * implementation of store interface using RAM.
 * @author mbardakov
 * @since 29.07.2020
 */
public final class MemStore implements Store, RecordReadable {
    @Override
    public Record addNewRecord(final Record record) {
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
    public List<Record.Protocol> getAllProtocols() {
        return null;
    }
}
