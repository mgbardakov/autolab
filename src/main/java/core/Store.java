package core;

import java.util.List;

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
    /**
     * gets all records from the storage.
     * @return list of all records
     */
    List<Record> getAllRecords();
    /**
     * gets all records with certain harm factor.
     * @param factor - harm factor
     * @return list of all records with the same factor
     */
    List<Record> getRecordsByFactor(String factor);
    /**
     * gets all records by type.
     * @param type - object type
     * @return list of records with the same object type
     */
    List<Record> getRecordsByType(String type);
    /**
     * gets records by combination of object type and harm factor.
     * @param type - object type
     * @param factor - harm factor
     * @return list of records with the same type and factor
     */
    List<Record> getRecordsByTypeAndFactor(String type, String factor);

    /**
     * loads data from some source.
     * @param loader - specific loader
     * @return success/failure
     */
    boolean loadData(Loader loader);

    /**
     * saves specific report into specific output.
     * @param generator - specific generator
     * @param output - specific output
     * @return success/failure
     */
    boolean saveReport(ReportGenerator generator, Output output);
}
