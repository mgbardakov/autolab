import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ProjectDataParser extends XlsxFileDataParser {
    private static final int TABLE_DATA_START_ROW = 2;
    private static final String MAIN_PROJECT_PROPS = "cfgProjectExpertise.properties";
    private static final String TABLE_PROPS = "cfgTable.properties";
    public ProjectDataParser(final String newPath) throws IOException {
        super(newPath, MAIN_PROJECT_PROPS);
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
    }

    @Override
    public Map<String, String> getData() throws ObjectNotFoundException {
        return super.getData();
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
                        + " %s/%s м.",
                        getArgumentFromRow("number", row, tableProps),
                        getArgumentFromRow("name", row, tableProps),
                        getArgumentFromRow("freq", row, tableProps),
                        getArgumentFromRow("mod", row, tableProps),
                        getArgumentFromRow("trans", row, tableProps),
                        getArgumentFromRow("power", row, tableProps),
                        getArgumentFromRow("amp", row, tableProps),
                        getArgumentFromRow("gDiag", row, tableProps),
                        getArgumentFromRow("vDiag", row, tableProps),
                        getArgumentFromRow("asimut", row, tableProps),
                        getArgumentFromRow("eAngle", row, tableProps),
                        getArgumentFromRow("mAngle", row, tableProps),
                        getArgumentFromRow("groundHeight", row, tableProps),
                        getArgumentFromRow("supportHeight", row, tableProps)));
                count++;
            } else {
                run = false;
            }
        }
        return rslBuilder.toString();
    }
    private String getArgumentFromRow(final String argument, final Row row,
                                      final Properties props) {
        var argIndex = Integer.parseInt(props.getProperty(argument));
        return getStringFromCell(row.getCell(argIndex));
    }
    private String[] transformKeysToArguments( final String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            keys[i] = getArgumentFromRow(keys[i], )
        }
    }
}
