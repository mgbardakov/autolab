package core;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;


/**
 * implementation of loader using xml file input.
 * @author mbardakov
 * @since  29.07.2020
 */
public final class SpbXMLFileLoader implements Loader {
    /**
     * current input.
     */
    private Input input;
    /**
     * cells indexes from configuration file.
     */
    private Properties props;
    /**
     * pool of harm factors.
     */
    private Map<Integer, String> factorPool;
    /**
     * storage for data load.
     */
    private Store store;
    /**
     * default configuration file name.
     */
    private static final  String DEF_CONF_FILE = "loaderconfig.properties";
    /**
     * basic constructor.
     * @param currentInput - user input source
     * @param cfgFileName - configuration file name
     * @param currentStore - storage
     * @throws IOException - properties exception
     */
    public SpbXMLFileLoader(final Input currentInput,
                            final String cfgFileName,
                            final Store currentStore) throws IOException {
        this.input = currentInput;
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(cfgFileName);
        this.props = new Properties();
        assert is != null;
        this.props.load(is);
        factorsInit();
        this.store = currentStore;
    }

    /**
     * returns object with default configs.
     * @param currentInput - user input source
     * @param currentStore - storage.
     * @throws IOException properties exception
     */
    public SpbXMLFileLoader(final Input currentInput,
                            final Store currentStore) throws IOException {
        this(currentInput, DEF_CONF_FILE, currentStore);
    }

    @Override
    public boolean loadDataToStore() {
        var recordList = getRecordList();
        for (Record record : recordList) {
            store.addNewRecord(record);
        }
        return true;
    }

    @Override
    public List<Record> getRecordList() {
        var path = input.askString("Enter source file path");
        var list = new ArrayList<Record>();
        try (var workBook = new XSSFWorkbook(path)) {
            var sheet = workBook.getSheetAt(0);
            var run = true;
            var count = Integer.parseInt(props.getProperty("START_OF_DATA"));
            while (run) {
                var row = sheet.getRow(count);
                if (row != null && row.getCell(0)
                        .getCellType() != CellType.BLANK) {
                    var record = new Record();
                    initRecord(record, row);
                    list.add(record);
                    count++;
                } else {
                    run = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No such file");
        }

        return list;
    }

    private String getNumberFromRow(final Row row) {
        return row.getCell(getIntProperty("NUMBER")).getStringCellValue();
    }

    private Calendar getDateFromRow(final Row row) {
        var dateIndex = getIntProperty("DATE");
        var sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar rsl = Calendar.getInstance();
        try {
            rsl.setTime(sdf.parse(row.getCell(dateIndex)
                    .getStringCellValue()));
        } catch (ParseException e) {
            rsl = null;
            e.printStackTrace();
        }
        return rsl;
    }

    private String getSpecFromRow(final Row row) {
        return getStringFromCell(row, getIntProperty("SPEC"));
    }

    private String getTypeFromRow(final Row row) {
        return getStringFromCell(row, getIntProperty("TYPE"));
    }

    private String getTitleFromRow(final Row row) {
        return getStringFromCell(row, getIntProperty("TITLE"));
    }

    private String getAddressFromRow(final Row row) {
        return getStringFromCell(row, getIntProperty("ADDRESS"));
    }

    private String getDistrictFromRow(final Row row) {
        return getStringFromCell(row, getIntProperty("DISTRICT"));
    }

    private String getReasonFromRow(final Row row) {
        return getStringFromCell(row, getIntProperty("REASON"));
    }

    private String getReasonAttRow(final Row row) {
        return getStringFromCell(row, getIntProperty("ATTS"));
    }
    private boolean setProtocolsFromRow(final Row row, final Record record) {
        for (Integer index : factorPool.keySet()) {
            if (row.getCell(index).getCellType() != CellType.BLANK) {
                record.addProtocol(getStringFromCell(
                        row, getIntProperty("PLACE")),
                  factorPool.get(index), getIntFromCell(row, index),
                        getIntFromCell(row, index + 1));
            }
        }
        return true;
    }
    private Record.Purpose getPurposeFromRow(final Row row) {
        Record.Purpose rsl;
        switch (getStringFromCell(row,
                getIntProperty("PURP")).toUpperCase()) {
            case "П" : rsl = Record.Purpose.PLANNED;
                       break;
            case "В" : rsl = Record.Purpose.NOTPLANNED;
                       break;
            case "К" : rsl = Record.Purpose.COMMERCIAL;
                       break;
            default: rsl = Record.Purpose.OTHER;
                    break;
        }
        return rsl;
    }
    private Integer getIntProperty(final String key) {
        return Integer.parseInt(props.getProperty(key));
    }
    private void factorsInit() {
        factorPool = new HashMap<>();
        factorPool.put(getIntProperty("MK"), "микроклимат");
        factorPool.put(getIntProperty("OSV"), "освещенность");
        factorPool.put(getIntProperty("SHUM"), "шум");
        factorPool.put(getIntProperty("VIBR"), "вибрация");
        factorPool.put(getIntProperty("EPK"), "эмиПК");
        factorPool.put(getIntProperty("E50"), "эми50Гц");
        factorPool.put(getIntProperty("MD"), "МД");
        factorPool.put(getIntProperty("EROA"), "ЭРОА");
        factorPool.put(getIntProperty("PPR"), "ППР");
        factorPool.put(getIntProperty("AER"), "аэроионы");
        factorPool.put(getIntProperty("IZ"), "инфразвук");
        factorPool.put(getIntProperty("IK"), "ИК");
        factorPool.put(getIntProperty("UF"), "УФ");
        factorPool.put(getIntProperty("EPPE"), "эмиППЭ");
        factorPool.put(getIntProperty("PULS"), "пульсация");
    }

    private void initRecord(final Record record, final Row row) {
        record.setNumber(getNumberFromRow(row));
        record.setDate(getDateFromRow(row));
        record.setSpecName(getSpecFromRow(row));
        record.setType(getTypeFromRow(row));
        record.setTitle(getTitleFromRow(row));
        record.setAddress(getAddressFromRow(row));
        record.setDistrict(getDistrictFromRow(row));
        record.setReason(getReasonFromRow(row));
        record.setReasonAttributes(getReasonAttRow(row));
        record.setPurpose(getPurposeFromRow(row));
        setProtocolsFromRow(row, record);
    }

    private int getIntFromCell(final Row row, final int index) {
        var rsl = 0;
        var cell = row.getCell(index);
        if (cell.getCellType() != CellType.BLANK) {
            var rslDouble = Double.parseDouble(getStringFromCell(row, index));
            rsl = (int) rslDouble;
        }
        return rsl;
    }

    private String getStringFromCell(final Row row, final int index) {
        var cell = row.getCell(index);
        return cell.getCellType() == CellType.STRING
                ? cell.getStringCellValue()
                : String.valueOf(cell.getNumericCellValue());
    }
}
