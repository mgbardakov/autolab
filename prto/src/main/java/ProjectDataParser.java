import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProjectDataParser extends XlsxFileDataParser {
    private static final int TABLE_DATA_START_ROW = 2;
    private static final int BUILDINGS_DATA_START_ROW = 1;
    private static final int ZOZ_DATA_START_ROW = 1;
    private static final String MAIN_PROJECT_PROPS = "cfgProjectExpertise.properties";
    private static final String TABLE_PROPS = "cfgTable.properties";
    private static final String BUIlDINGS_PROPS = "cfgBuildings.properties";
    private static final String ZOZ_PROPS = "cfgZOZ.properties";
    private static final String[] TABLE_KEYS = new String[] {"number", "name",
            "freq", "mod", "trans", "power", "amp", "gDiag", "vDiag", "asimut",
            "eAngle", "mAngle", "groundHeight", "supportHeight"};
    private static final String[] BUILDING_KEYS = new String[] {"asimut", "name",
            "height","quantity", "distance"};
    public ProjectDataParser(final String newPath) throws IOException {
        super(newPath, MAIN_PROJECT_PROPS);
    }

    @Override
    public Map<String, String> getData() throws ObjectNotFoundException {
        var rslMap = super.getData();
        rslMap.put("table", getTable());
        rslMap.put("ZOZ", getZOZ());
        rslMap.put("buildings", getBuildings());
        return rslMap;
    }

    private String getTable() {
        var sheet = book.getSheetAt(1);
        var rslBuilder = new StringBuilder();
        String currentOperator = "";
        var run = true;
        var count = TABLE_DATA_START_ROW;
        var tableProps = getProperties(TABLE_PROPS);
        while (run) {
            var row = sheet.getRow(count);
            if (row != null) {
                var newOperator = getArgumentFromRow("operator",
                        row, tableProps);
                if (!newOperator.equals(currentOperator)) {
                    rslBuilder.append(newOperator)
                    .append(System.lineSeparator());
                    currentOperator = newOperator;
                }
                rslBuilder.append(String.format("%s %s; %s МГц; %s; %s шт.;"
                        + " %s Вт; %s dBi; ДН: гориз. %s град., верт. %s град.;"
                        + " %s град.; УН: электр. %s град., мех. %s град.;"
                        + " %s/%sм;", (Object[])transformKeysToArguments(TABLE_KEYS,
                        row, tableProps))).append(System.lineSeparator());
                count++;
            } else {
                run = false;
            }
        }
        return rslBuilder.toString();
    }
    private String getBuildings() {
        var sheet = book.getSheetAt(3);
        var rslBuilder = new StringBuilder();
        var run = true;
        var count = BUILDINGS_DATA_START_ROW;
        var buildProps = getProperties(BUIlDINGS_PROPS);
        while (run) {
            var row = sheet.getRow(count);
            if (row != null) {
                var args = transformKeysToArguments(BUILDING_KEYS,
                        row, buildProps);
                rslBuilder.append(String.format("- в направлении %s град. - "
                        + "%s высотой %s м, расположенн%sе на расстоянии %sм;",
                        args[0], args[1], args[2],
                        args[3].equals("ед. ч.") ? "о" : "ы", args[4]))
                        .append(System.lineSeparator());
                count++;
            } else {
                run = false;
            }
        }
        return rslBuilder.toString();
    }
    private String getZOZ() {
        var sheet = book.getSheetAt(2);
        var rslBuilder = new StringBuilder();
        var run = true;
        var count = ZOZ_DATA_START_ROW;
        var zozProps = getProperties(ZOZ_PROPS);
        var firstMap = new HashMap<String, String[]>();
        var secondMap = new HashMap<String, String[]>();
        while (run) {
            var row = sheet.getRow(count);
            if (row != null) {
                    var asimut = getArgumentFromRow("asimut", row, zozProps);
                    var height = getArgumentFromRow("height", row, zozProps);
                    var distance = getArgumentFromRow("distance", row, zozProps);
                    if (!firstMap.containsKey(asimut)) {
                        firstMap.put(asimut, new String[]{height, distance});
                    } else {
                        if (getDouble(firstMap.get(asimut)[0]) < getDouble(height)) {
                            firstMap.get(asimut)[0] = height;
                        }
                        if (getDouble(firstMap.get(asimut)[1]) > getDouble(distance)) {
                            firstMap.get(asimut)[1] = distance;
                        }
                    }
                count++;
            } else {
                run = false;
            }
        }
        for (String asimut : firstMap.keySet()) {
            var height = firstMap.get(asimut)[0];
            var distance = firstMap.get(asimut)[1];
            if (!secondMap.containsKey(height)) {
                secondMap.put(height, new String[]{asimut, distance});
            } else {
                if (getDouble(secondMap.get(height)[1]) < getDouble(distance)) {
                    secondMap.get(height)[1] = distance;
                }
                secondMap.get(height)[0] = String.format("%s, %s",
                        secondMap.get(height)[0], asimut);
            }
        }
        for (String key : secondMap.keySet()) {
            rslBuilder.append(String.format("- по азимут%s %s град. - %s м. "
                    + "от поверхности земли протяженностью не более %sм;",
                    secondMap.get(key)[0].contains(",") ? "ам" : "у",
                    secondMap.get(key)[0], key, secondMap.get(key)[1]))
                    .append(System.lineSeparator());
        }
        return rslBuilder.toString();
    }
    private String getArgumentFromRow(final String argument, final Row row,
                                      final Properties props) {
        var argIndex = Integer.parseInt(props.getProperty(argument));
        return getStringFromCell(row.getCell(argIndex));
    }
    private String[] transformKeysToArguments(final String[] keys,
                                              final Row row,
                                              final Properties props) {
        var rsl = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            rsl[i] = getArgumentFromRow(keys[i], row, props);
        }
        return rsl;
    }
    private Double getDouble(String s) {
        return Double.parseDouble(s);
    }
}
