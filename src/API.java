import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Math.max;

public abstract class API {
    protected FileInputStream inputStream;
    protected XSSFWorkbook workbook;
    protected XSSFSheet sheet;
    protected XSSFRow row;
    protected XSSFCell cell;
    protected String filePath;
    protected int rows;


    public API() throws IOException {
        inputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(inputStream);
    }

    public API(String filePath, int sheetNum) throws IOException {
        inputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(sheetNum);
        rows = sheet.getLastRowNum();
    }

    public void setInputStream(String path) throws FileNotFoundException {
        inputStream = null;
        inputStream = new FileInputStream(path);
        workbook = null;
    }

    public void setSheet(int sheetNum) {  sheet = workbook.getSheetAt(sheetNum);  }
    public void setFilePath(String filePath) {  this.filePath = filePath;  }
    public String getFilePath() {  return filePath;  }
    public int getRows() {  return rows;  }


}
