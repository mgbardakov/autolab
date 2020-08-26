package core;

import java.util.List;

public interface RecordReadable {
    /**
     * gets all protocols from store.
     * @return set of protocols
     */
    List<Record.Protocol> getAllProtocols();
    /**
     * gets all records from the storage.
     * @return list of all records
     */
    List<Record> getAllRecords();
}
