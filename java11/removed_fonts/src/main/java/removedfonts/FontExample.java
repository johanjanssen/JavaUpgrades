package removedfonts;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

// https://www.basis.com/content/kb-removal-fonts-jdk-11
// https://dzone.com/articles/the-font-of-all-knowledge-about-java-and-fonts
// https://wiki.openjdk.java.net/pages/viewpage.action?pageId=17957183
public class FontExample {
    public void createWorkBook() {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        SXSSFSheet sheet = workbook.createSheet("testsheet");
    }

    {
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);
        SXSSFSheet sheet = workbook.createSheet("testsheet");
    }
}
