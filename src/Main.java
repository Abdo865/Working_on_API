import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath ="./DataFiles/Example2.xlsx";
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter number of APIs: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        String[] s = new String[num]; //to contain API names
        for (int z = 0;z<num;z++){
            Service ex= new Service(filePath,0);
            boolean b =false; //to exit if the API has entered and mismatched APIs
            System.out.printf("Enter API name: ");
            s[z] = scanner.nextLine();
            for (int k = 0;k<z;k++) {
                if (s[k].equals(s[z])) {
                    System.out.println("this API has been Entered before");
                    z--;
                    b = true;
                    break;
                }
            }
            if(b)  continue;
            b = ex.loopOnFields(s[z]);
            if (!b){
                z--;
                continue;
            }
            for (int i =0;i<ex.getMajor().size();i++) {
                System.out.println(ex.getMajor().get(i).getName());
                for (int j = 0;j<ex.getMajor().get(i).getTree().size();j++)
                    System.out.println(ex.getMajor().get(i).getTree().get(j).getName());
                System.out.println("__________________________________________________");
            }
        }
    }
}

