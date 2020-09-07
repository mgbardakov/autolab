import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class XlsxFileDataParser implements DataParser {
    private String path;
    private Properties props;
    private final static int DATA_COLUMN = 2;

    public XlsxFileDataParser(String path) throws IOException {
        this.path = path;
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("cfg.properties");
        this.props = new Properties();
        assert is != null;
        props.load(is);
    }

    @Override
    public Map<String, String> getData() throws ObjectNotFoundException {
        Map<String, String> rslMap = new HashMap<>();
        try (var book = new XSSFWorkbook(path)) {
            var sheet = book.getSheetAt(0);
            for (String key : props.stringPropertyNames()) {
                rslMap.put(key, sheet.getRow(getIntegerProperty(key))
                                .getCell(DATA_COLUMN).getStringCellValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (rslMap.size() == 0) {
            throw new ObjectNotFoundException();
        }
        return rslMap;
    }
    private int getIntegerProperty(String key) {
        return Integer.parseInt(props.getProperty(key));
    }
}
