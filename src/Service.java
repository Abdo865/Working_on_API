import java.io.IOException;
import java.util.ArrayList;


public class Service extends API{
    ArrayList<Field> major;
    public Service(StringBuilder filePath, int sheetNum) throws IOException {  major = new ArrayList<Field>();  }
    public Service(String filePath, int sheetNum) throws IOException {
        super(filePath, sheetNum);
        major = new ArrayList<Field>();
    }

    public String readingExcel(int r, int c) {
        row = sheet.getRow(r);
        cell = row.getCell(c);
        return cell.getStringCellValue();

    } //takes row and column as parameters and return the string inside this cell
    private String[] extractInfo(int r){
        String[] info = new String[5];
        for (int i = 0; i < 5; i++)
            info[i] = readingExcel(r,i);
        return info;
    } //getting the information of each row and return it back into an array of strings

    private String[] extractFields(String s){
        String[] fields = s.split("/",-1);
        return fields;
    } // gives us the information about Field and its Childrens in an array of string
    // it will give you an empty string at the first index of the array

    private Field fieldInitializer(int r){
        String[] info = extractInfo(r);
        String[] fields = extractFields(info[1]);
        Field f = new Field(info[0], fields[fields.length-1], info[2], info[3], info[4]);
        return f;
    } //initializes each field with its parameters

    private int findStartingRow(int startingFrom){
        int k = startingFrom;
        if (k == -1)
            return -1;
        while (true) {
            try {
                for (; k < rows; k++) {
                    String s;
                    s = readingExcel(k, 0);
                    if (s.equals("I/o"))
                        return k+1;
                }
            } catch (NullPointerException ex) {
                k++;
            }
        }
    } // gives us the first row to begin with

    private char objectOrField(int r){
        try {
            if (!(readingExcel(r, 2).equals("string")) && (extractFields(extractInfo(r)[1]).length == 2)) //normal object or major field
                return 1;
            else if (!(readingExcel(r, 2).equals("string")) && extractFields(extractInfo(r)[1]).length > 2) //object inside object
                return 2;
            if (readingExcel(r, 2).equals("string") && (extractFields(extractInfo(r)[1]).length == 2)) //normal object or major field
                return 3; //field
            return 4;
        }catch (NullPointerException ex){
            return 5;
        }
    } //tells us whether it's a children or parent

    public boolean loopOnFields(String s){
        int first = findStartingRow(apiFinder(s));
        if (first == -1){
            System.out.println("Not in the Excel");
            return false;
        }
        int inside = 0;
        Field f;
        for (int i = first; i <= rows; i++) {
            switch (objectOrField(i)) {
                case 1, 3 -> {
                    f = fieldInitializer(i);
                    major.add(f);
                    inside++;
                }
                case 2 -> {
                    f = fieldInitializer(i);
                    major.get(inside - 1).addToList(f);
                    major.add(f);
                    inside++;
                }
                case 4 -> {
                    f = fieldInitializer(i);
                    major.get(inside - 1).addToList(f);
                }
                default -> {
                    return true;
                }
            }
        }
        return true;
    } //gives the array list if the parents and childs their values

    private int apiFinder(String apiName) {
        int r = rows;
        int i= 0;
        while (r  != 0) {
            try {
                for (; i < rows; i++) {
                    if (readingExcel(i, 0).contains(apiName)) return i;
                }
            } catch (NullPointerException ex) { i++; }
            r--;
        }
        return -1;
    } //tells us whether the API is included or not

    public ArrayList<Field> getMajor() {  return major;  }
}
