package core;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SpbXMLFileLoaderTest {

    @Test
    public void whenReadsOneObjectFromXLSXDataSource() throws Exception {
        Record expected = new Record();
        expected.setNumber("1604 (1)");
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar date = Calendar.getInstance();
        date.setTime(sdf.parse("19.12.2019"));
        expected.setDate(date);
        expected.setSpecName("врач ГИФФ СГЛ Смыслов А.А.");
        expected.setType("КГ");
        expected.setTitle("ООО \"ЛЕКС\"");
        expected.setAddress("г. Санкт-Петербург, ул. Партизана Германа, д. 36");
        expected.setDistrict("КРС");
        expected.setReason("договор");
        expected.setReasonAttributes("61271 от 29.03.2017г.");
        expected.setPurpose(Record.Purpose.COMMERCIAL);
        expected.addProtocol("РМ",
                "микроклимат", 2, 0);
        expected.addProtocol("РМ", "освещенность",
                1, 0);
        var expectedList = List.of(expected);
        Loader loader = new SpbXMLFileLoader(new StubInput(new String[] {
                "src/test/resources/testFileDataSource.xlsx",
                }), "loaderConfigTest.properties", new MemStore());
        var actual = loader.getRecordList();
        assert actual != null;
        assertThat(actual, is(expectedList));
    }
}