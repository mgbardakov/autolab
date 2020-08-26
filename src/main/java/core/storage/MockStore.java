package core.storage;

import core.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * mock class for unit tests.
 * @author mbardakov
 * @since 25.08.2020
 */
public final  class MockStore implements RecordReadable {
    /**
     * list of records.
     */
    private List<Record> records;

    /**
     * basic constructor.
     * @param newRecords mock store object with filled record list.
     */
    public MockStore(final List<Record> newRecords) {
        this.records = newRecords;
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
