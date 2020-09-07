package core.loader;

import core.Record;

import java.util.List;

/**
 * interface for loading information to the storage.
 * @author mbardakov
 * @since 28.07.2020
 */
public interface Loader {
    /**
     * gets pool of values to fill new record.
     * @return string array of values
     */
    List<Record> getRecordList();

    /**
     * loads data to store.
     * @return success / failure
     */
    boolean loadDataToStore();
}
