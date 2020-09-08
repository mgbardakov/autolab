import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Map;


public class DocExpertiseGenerator implements ExpGenerator {
    private String path;
    private String templateName;


    public DocExpertiseGenerator(String path, String templateName) {
        this.path = path;
        this.templateName = templateName;
    }

    @Override
    public void generateExpertise(Map<String, String> data) {
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(templateName);
        assert is != null;
        try(FileOutputStream fos = new FileOutputStream(path)) {
            var doc = getExpertise(data, is);
            doc.write(fos);
        } catch (ObjectNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
    private XWPFDocument getExpertise(Map<String,String> data, InputStream is)
    throws ObjectNotFoundException{
        XWPFDocument document = null;
        try {
            document = new XWPFDocument(is);
            for (String key : data.keySet()) {
                TextReplacer.replace(document, String.format("[%s]", key), data.get(key));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println("No such template");
        }
        if(document == null) {
            throw new ObjectNotFoundException();
        }
        return document;
    }

}
