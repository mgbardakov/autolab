package core;

import java.util.List;
import java.util.Set;

/** storage interface.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface Store {
    /**
     * adds new record to the storage.
     * @param record to add.
     * @return created record
     */
    Record addNewRecord(Record record);
    /**
     * updates record by id.
     * @param id - record id
     * @param record - replacement
     * @return - success/failure
     */
    boolean updateRecord(String id, Record record);
    /**
     * deletes record by id.
     * @param id - record id
     * @return success/failure
     */
    boolean deleteRecord(String id);
}
