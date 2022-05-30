
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath ="./DataFiles/Example.xlsx";
        Service ex= new Service(filePath,0);
        ex.loopOnFields();
        for (int i =0;i<ex.getMajor().size();i++)
            System.out.println(ex.getMajor().get(i).getName());
    }
}

