import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class XlsxFileDataParser implements DataParser {
    /**
     * path to the data source file.
     */
    private String path;
    /**
     * configurations.
     */
    private Properties props;
    /**
     * column number of main data.
     */
    private static final int DATA_COLUMN = 2;

    /**
     * main constructor.
     * @param newPath - path to the source file
     * @param cfgFileName - config file name
     * @throws IOException can not reach property file
     */
    public XlsxFileDataParser(final String newPath,
                              final String cfgFileName) throws IOException {
        this.path = newPath;
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(cfgFileName);
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
                rslMap.put(key, getStringFromCell(
                        sheet.getRow(getIntegerProperty(key))
                                .getCell(DATA_COLUMN), book));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (rslMap.size() == 0) {
            throw new ObjectNotFoundException();
        }
        return rslMap;
    }
    private int getIntegerProperty(final String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    private String getStringFromCell(final Cell cell, final Workbook wb) {
        var rsl = "";
        if (cell.getCellType() == CellType.STRING) {
            rsl = cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
          rsl = String.valueOf(cell.getNumericCellValue());
          rsl = rsl.substring(0, rsl.length() - 2);
        } else if (cell.getCellType() == CellType.FORMULA) {
            FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();
            rsl = fe.evaluate(cell).getStringValue();
        }
        return rsl;
    }

}
