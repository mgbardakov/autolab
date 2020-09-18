import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ProjectDataParserTest {

    @Test
    public void getZOZ() {
        try {
            ProjectDataParser dp = new ProjectDataParser("D:\\child\\pInputTemplate.xlsx");
            System.out.println(dp.getZOZ());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}