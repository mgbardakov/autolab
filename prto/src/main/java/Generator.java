import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.Map;

public interface Generator {
    /**
     * generates document from map with data.
     * @param data - map with data.
     * @return generated XWPF document.
     */
    XWPFDocument generateDocument(Map<String, String> data)
            throws ObjectNotFoundException;
}
