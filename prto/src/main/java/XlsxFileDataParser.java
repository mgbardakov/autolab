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

public class XlsxFileDataParser implements DataParser, AutoCloseable {
    /**
     * source file.
     */
    protected XSSFWorkbook book;
    /**
     * column number of main data.
     */
    private static final int DATA_COLUMN = 2;
    private String propFileName;
    /**
     * number of the main information sheet
     */
    private static final int MAIN_INFORMATION_SHEET = 0;
    /**
     * main constructor.
     * @param newPath - path to the source file
     * @param newPropFileName - prop file name
     * @throws IOException can not reach property file
     */
    public XlsxFileDataParser(final String newPath,
                              final String newPropFileName) throws IOException {
        this.book = new XSSFWorkbook(newPath);
        this.propFileName = newPropFileName;
    }
    @Override
    public Map<String, String> getData() throws ObjectNotFoundException {
        Map<String, String> rslMap = new HashMap<>();
        var props = getProperties(propFileName);
            var sheet = book.getSheetAt(MAIN_INFORMATION_SHEET);
            for (String key : props.stringPropertyNames()) {
                rslMap.put(key, getStringFromCell(
                        sheet.getRow(getIntegerProperty(key, props))
                                .getCell(DATA_COLUMN)));
            }
        if (rslMap.size() == 0) {
            throw new ObjectNotFoundException();
        }
        return rslMap;
    }
    private int getIntegerProperty(final String key, Properties props) {
        return Integer.parseInt(props.getProperty(key));
    }
    protected String getStringFromCell(final Cell cell) {
        var rsl = "";
        if (cell.getCellType() == CellType.STRING) {
            rsl = cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            double d = cell.getNumericCellValue();
          rsl = getNiceSNumber(d);
         // rsl = rsl.substring(0, rsl.length() - 2);
        } else if (cell.getCellType() == CellType.FORMULA) {
            FormulaEvaluator fe = book.getCreationHelper().createFormulaEvaluator();
            rsl = fe.evaluate(cell).getStringValue();
        }
        return rsl;
    }
    private String getNiceSNumber(final double d) {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }
    protected Properties getProperties(final String cfgFileName) {
        var rsl = new Properties();
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        try(InputStream is = classloader.getResourceAsStream(cfgFileName)) {
            assert is != null;
            rsl.load(is);
        } catch (IOException e ) {
            e.printStackTrace();
        }
       return rsl;
    }
    @Override
    public void close() throws Exception {
        book.close();
    }
}
