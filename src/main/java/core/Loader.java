package core;

import java.util.stream.Stream;

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
    Stream<String[]> getRecordFieldPools();
}
