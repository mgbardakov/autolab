import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        DataParser parser = new XlsxFileDataParser("D:\\childish\\vInputTemplate.xlsx");
        ExpGenerator generator = new DocExpertiseGenerator("D:\\childish\\expertise.docx", "vTemplate.docx");
        try {
            generator.generateExpertise(parser.getData());
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}
