package core.storage;

import core.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * implementation of store interface using RAM.
 * @author mbardakov
 * @since 29.07.2020
 */
public final class MemStore implements Store, RecordReadable {
    private List<Record> records;
    private int currentId;

    public MemStore() {
        this.records = new ArrayList<>();
        this.currentId = 1;
    }

    @Override
    public Record addNewRecord(final Record record) {
          record.setId(String.valueOf(currentId++));
          records.add(record);
          return record;
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
        return records;
    }
    @Override
    public List<Record.Protocol> getAllProtocols() {
        var rsl = new ArrayList<Record.Protocol>();
        for (Record record : records) {
            rsl.addAll(record.getProtocols());
        }
        return rsl;
    }
}
