package core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
     * basic constructor
     * @param records mock store object with filled record list.
     */
    public MockStore(List<Record> records) {
        this.records = records;
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
