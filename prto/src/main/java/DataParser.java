import java.util.Map;

public interface DataParser {
    /**
     * gets data from data source.
     * @return map with data
     * @throws ObjectNotFoundException if map is empty
     */
    Map<String, String> getData() throws ObjectNotFoundException;
}
