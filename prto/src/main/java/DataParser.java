import java.util.Map;

public interface DataParser {
    Map<String, String> getData() throws ObjectNotFoundException;
}
