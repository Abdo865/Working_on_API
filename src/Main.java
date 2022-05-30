
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath ="./DataFiles/Example.xlsx";
        ExcelFuctionalities ex= new ExcelFuctionalities(filePath,0);
        System.out.println(ex.readingExcel(12,3));
    }
}

