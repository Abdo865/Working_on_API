import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.IOException;
import java.util.ArrayList;


public class Service extends API{
    ArrayList<Field> major;
    public Service() throws IOException {
        major = new ArrayList<Field>();
    }
    public Service(String filePath, int sheetNum) throws IOException {
        super(filePath, sheetNum);
        major = new ArrayList<Field>();
    }

    public String readingExcel(int r, int c){
        row = sheet.getRow(r);
        cell = row.getCell(c);
        return cell.getStringCellValue();
    }

    public String[] extractInfo(int r){
        String[] info = new String[5];
        for (int i = 0; i < 5; i++)
            info[i] = readingExcel(r,i);
        return info;
    }

    public String[] extractFields(String s){
        String[] fields = s.split("/",-1);
        return fields;
    }

    public Field fieldInitializer(int r){
        String[] info = extractInfo(r);
        String[] fields = extractFields(info[1]);
        Field f = new Field(fields[fields.length-1], info[3], info[4], info[3]);
        return f;
    }

    public int findFirstRow(){
        int i =0;
        while (true) {
            try {
                for (; i < rows; i++) {
                    String s;
                    s = readingExcel(i, 0);
                    if (s.equals("I/o"))
                        return i;
                }
            } catch (NullPointerException ex) {
                i++;
            }
        }
    }

    private int find(String s){
        for (int i=0; i < major.size(); i++) {

        }
    }

    public void loopOnFields(){
        int first = findFirstRow();
        for (int i = first; i < rows-1; i++)
        {
            Field f = fieldInitializer(i+1);

        }
    }

    public ArrayList<Field> getMajor() {  return major;  }
}
