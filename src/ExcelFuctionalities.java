import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Math.max;

public class ExcelFuctionalities {
    private FileInputStream inputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private String filePath;
    private int rows;
    private int col;

    public ExcelFuctionalities() throws IOException {
        inputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(inputStream);
    }

    public ExcelFuctionalities(String filePath,int sheetNum) throws IOException {
        inputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(sheetNum);
        rows = sheet.getLastRowNum();
        for (int i = 0;i<rows;i++)
            col = max(col,sheet.getRow(i).getLastCellNum());
    }

    public void setInputStream(String path) throws FileNotFoundException {
        inputStream = null;
        inputStream = new FileInputStream(path);
        workbook = null;
    }
    public void setSheet(int sheetNum) {  sheet =workbook.getSheetAt(sheetNum);  }
    public void setFilePath(String filePath) {  this.filePath = filePath;  }
    public String getFilePath() {  return filePath;  }
    public int getRows() {  return rows;  }
    public int getCol() {  return col;  }

    public String readingExcel(int r, int c){
        row = sheet.getRow(r);
        cell = row.getCell(c);
        return cell.getStringCellValue();
    }
}
