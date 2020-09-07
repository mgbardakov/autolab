import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

public class DocExpertiseGenerator implements ExpGenerator {
    private String path;
    private String templateName;
    private final static Pattern KEY_PATTERN = Pattern.compile("\\$\\{[^}]*}");


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
    private String getReplacement(Map<String, String> dataMap, String mark) {
        String rslString = "";
        var key = mark.substring(2, mark.length() - 1);
        if (dataMap.containsKey(key)) {
            rslString = dataMap.get(key);
        }
        return rslString;
    }
    private XWPFDocument getExpertise(Map<String,String> data, InputStream is)
    throws ObjectNotFoundException{
        XWPFDocument document = null;
        try {
            document = new XWPFDocument(is);
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                for (XWPFRun run : paragraph.getRuns()) {
                    var rslText = run.getText(0);
                    var matcher = KEY_PATTERN.matcher(rslText);
                    var markSet = new HashSet<String>();
                    while (matcher.find()) {
                        markSet.add(matcher.group());
                    }
                    for (String mark : markSet) {
                        var replacement = getReplacement(data, mark);
                        var markMatcher = KEY_PATTERN.matcher(rslText);
                        rslText = markMatcher.replaceAll(replacement);
                    }
                    run.setText(rslText);
                }
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
