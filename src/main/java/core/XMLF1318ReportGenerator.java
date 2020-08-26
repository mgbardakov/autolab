package core;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * class tha generates XML reports.
 * @author mbardakov
 * @since 31.07.2020
 */
public final class XMLF1318ReportGenerator implements ReportGenerator {
    /**
     * current store.
     */
    private RecordReadable store;
    /**
     * generator properties.
     */
    private Properties props;
    /**
     * path to the report template file.
     */
    private String templatePath;
    /**
     * array of factors for report.
     */
    private static final String[] FACTORS = new String[]{
            "NADZOR", "SHUM", "IZ", "UZ", "VIBR", "GGMP", "PMP", "ESP", "E50",
            "EPK", "ERCHD", "OSV", "UF", "IK", "MK", "AER", "LI", "OTHER"
    };
    /**
     * array of places for report.
     */
    private static final String[] PLACES = new String[]{
            "ПРОД", "РМ", "ПП", "ПЖО", "ТЖЗ", "ПРОЧ"
    };
    /**
     * default constructor.
     * @param newStore - current store
     * @throws IOException properties exception
     */
    public XMLF1318ReportGenerator(final RecordReadable newStore) throws IOException {
        this(newStore, "genConfig.properties", "templateF1318.xlsx");
    }
    /**
     * advanced constructor.
     * @param newStore = current store
     * @param cfgFileName - configuration file name
     * @param newTemplatePath - path to the template file.
     * @throws IOException - properties exception
     */
    public XMLF1318ReportGenerator(final RecordReadable newStore,
                                   final String cfgFileName,
                                   final String newTemplatePath)
            throws IOException {
        this.store = newStore;
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream is = classloader
                .getResourceAsStream(cfgFileName);
        this.props = new Properties();
        assert is != null;
        this.props.load(is);
        this.templatePath = newTemplatePath;
    }

    @Override
    public Workbook createReport() {
        Workbook rsl = null;
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(templatePath);
        assert is != null;
        try {
            var workBook = new XSSFWorkbook(is);
            var valuesMap = getValuesTable();
            fillWorkbookWithValues(valuesMap, workBook);
            workBook.setForceFormulaRecalculation(true);
            rsl = workBook;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No such template");
        }
        return rsl;
    }
    private Map<String, Map<String, Integer[]>>
    getValuesTable() {
        var rslMap = new HashMap<String, Map<String, Integer[]>>();
        var protocolsList = store.getAllProtocols();
        for (Record.Protocol protocol : protocolsList) {
            var firstKey = getReportFactor(protocol.getFactor());
            addToMap(rslMap, protocol, firstKey);
            if (protocol.getPurpose() != Record.Purpose.COMMERCIAL) {
                addToMap(rslMap, protocol, "NADZOR");
            }
        }
        return rslMap;
    }
    private void addToMap(final Map<String, Map<String, Integer[]>> map,
                          final Record.Protocol protocol,
                          final String firstKey) {
        var secondKey = protocol.getPlaceType();
        if (map.containsKey(firstKey)) {
            var innerMap = map.get(firstKey);
            addToInnerMap(innerMap, protocol, secondKey);
        } else {
            var innerMap = new HashMap<String, Integer[]>();
            addToInnerMap(innerMap, protocol, secondKey);
            map.put(firstKey, innerMap);
        }
    }

    private void addToInnerMap(final Map<String, Integer[]> innerMap,
                               final Record.Protocol protocol,
                               final String secondKey) {
        if (innerMap.containsKey(secondKey)) {
            var currentValue = innerMap.get(secondKey);
            currentValue[0] +=  protocol.getPointCount();
            currentValue[1] += protocol.getIncPointCount();
            innerMap.put(secondKey, currentValue);
        } else {
            var currentValue = new Integer[2];
            currentValue[0] = protocol.getPointCount();
            currentValue[1] = protocol.getIncPointCount();
            innerMap.put(secondKey, currentValue);
        }
    }
    private String getReportFactor(final String factor) {
        return props.getProperty(factor);
    }

    private void fillWorkbookWithValues(
            Map<String, Map<String, Integer[]>> valuesMap,
            XSSFWorkbook workBook) {
        var sheet = workBook.getSheetAt(0);
        var rowCount = Integer.parseInt(props.getProperty("START_ROW"));
        var cellCount = Integer.parseInt(props.getProperty("START_CELL"));
        var defaultCellCount = cellCount;
        for (String factor : FACTORS) {
            if (valuesMap.containsKey(factor)) {
                for (String place : PLACES) {
                    if (valuesMap.get(factor).containsKey(place)) {
                        var values = valuesMap.get(factor).get(place);
                        var row = sheet.getRow(rowCount);
                        row.getCell(cellCount).setCellValue(values[0]);
                        row.getCell(cellCount + 1).setCellValue(values[1]);
                    }
                    cellCount += 2;
                }
            }
            rowCount += 1;
            cellCount = defaultCellCount;
        }
    }
}
