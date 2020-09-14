import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;

import java.util.Map;


public final class DocGenerator implements Generator {
    /**
     * name of the template file.
     */
    private String templateName;

    /**
     * main constructor.
     * @param newTemplateName template name.
     */
    public DocGenerator(final String newTemplateName) {
        this.templateName = newTemplateName;
    }

    @Override
    public XWPFDocument generateDocument(final Map<String, String> data) throws
            ObjectNotFoundException {
        ClassLoader classloader = Thread.currentThread()
                .getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(templateName);
        assert is != null;
        XWPFDocument doc = null;
        try {
            doc = getExpertise(data, is);
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }
        if (doc == null) {
            throw new ObjectNotFoundException();
        }
        return doc;
    }
    private XWPFDocument getExpertise(final Map<String, String> data,
                                      final InputStream is)
    throws ObjectNotFoundException {
        XWPFDocument document = null;
        try {
            document = new XWPFDocument(is);
            for (String key : data.keySet()) {
                TextReplacer.replace(document, String.format("[%s]", key),
                        data.get(key));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
            System.out.println("No such template");
        }
        if (document == null) {
            throw new ObjectNotFoundException();
        }
        return document;
    }

}
