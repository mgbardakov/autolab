package core;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.*;

public class XMLF1318ReportGeneratorTest {
    private List<Record> records;

    @Before
    public void init() {
        records = new ArrayList<>();
        var record1 = new Record();
        record1.setPurpose(Record.Purpose.PLANNED);
        record1.addProtocol("РМ", "OSV",
                2, 1);
        records.add(record1);
        var record2 = new Record();
        record2.setPurpose(Record.Purpose.COMMERCIAL);
        record2.addProtocol("РМ", "OSV",
                2, 1);
        records.add(record2);
    }

    @Test
    public void whenTwoRecordsInStore() throws IOException {
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream inst = classloader.getResourceAsStream("testReportF1318.xlsx");
        ReportGenerator generator = new XMLF1318ReportGenerator(
                new MockStore(records), "testGenConfig.properties",
                "testTemplateF1318.xlsx"
        );
        assert inst != null;
        Workbook expected = new XSSFWorkbook(inst);
        Workbook actual = generator.createReport();
        var expectedSheet = expected.getSheetAt(0);
        var actualSheet = actual.getSheetAt(0);
        assertEquals(actualSheet.getRow(16).getCell(6).getNumericCellValue(),
                expectedSheet.getRow(16).getCell(6).getNumericCellValue(),
                0.1d);
        assertEquals(actualSheet.getRow(16).getCell(7).getNumericCellValue(),
                expectedSheet.getRow(16).getCell(7).getNumericCellValue(),
                0.1d);
        assertEquals(actualSheet.getRow(5).getCell(6).getNumericCellValue(),
                expectedSheet.getRow(5).getCell(6).getNumericCellValue(),
                0.1d);
        assertEquals(actualSheet.getRow(5).getCell(7).getNumericCellValue(),
                expectedSheet.getRow(5).getCell(7).getNumericCellValue(),
                0.1d);
        assertEquals(actualSheet.getRow(4).getCell(6).getNumericCellValue(),
                expectedSheet.getRow(4).getCell(6).getNumericCellValue(),
                0.1d);
        assertEquals(actualSheet.getRow(4).getCell(7).getNumericCellValue(),
                expectedSheet.getRow(4).getCell(7).getNumericCellValue(),
                0.1d);
    }

}