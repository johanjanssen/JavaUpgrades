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
        try (final Workbook workbook = new XSSFWorkbook()) {
            final Sheet sheet = workbook.createSheet("Asset requests");
            int rowNum = 0;
            int colNum = 0;
            int maxColNum;
            final Row headerRow = sheet.createRow(rowNum++);
            headerRow.createCell(colNum++).setCellValue("Asset request title");
            headerRow.createCell(colNum++).setCellValue("Asset request type");
            headerRow.createCell(colNum++).setCellValue("Asset request description");
            headerRow.createCell(colNum++).setCellValue("Content item");
            maxColNum = colNum;

        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
