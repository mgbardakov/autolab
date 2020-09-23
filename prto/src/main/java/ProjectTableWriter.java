import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class ProjectTableWriter implements TableWriter {
    private static final String[] KEYS = new String[]{"number", "title",
    "orderNumber", "orderDate", "customer", "customerLawAddress", "cusINN",
    "cusOGRN", "factAddress", "coordinates", "specialist", "developer",
    "developerLawAddress", "devINN", "devOGRN", "operator", "materials",
    "whatsDone", "bsHardwareLocation", "bsAntennasLocation", "buildingTypes",
    "buildingAddresses", "prtoOrBSPlusSideOperators", "table", "buildings",
    "ZOZ", "sideOperators", "bsName"};

    @Override
    public void writeTable(String path, Map<String, String> map) {
        try (var book = new XSSFWorkbook(new FileInputStream(path))) {
            var sheet = book.getSheetAt(0);
            var index = sheet.getLastRowNum() + 1;
            var row = sheet.createRow(index);
            var count = 0;
            for (String key : KEYS) {
                if (map.containsKey(key)) {
                    var cell = row.createCell(count);
                    cell.setCellValue(map.get(key));
                }
                count++;
            }
            book.write(new FileOutputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
