package removedfonts;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;


public class FontExampleTest {
    @Test
    public void testCreateWorkBook() {
        FontExample fontExample = new FontExample();
        fontExample.createWorkBook();
    }

    @Test
    public void listFonts() {
        String[] names = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        System.out.println("Found " + names.length + " fonts:");

        for (String name : names) {
            System.out.println(name);
        }
    }

    @Test
    public void testExample() {
        final Workbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet("Asset requests");
    }
}
