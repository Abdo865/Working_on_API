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
                System.out.printf(ex.getMajor().get(i).getIO()+", ");
                System.out.printf(ex.getMajor().get(i).getName()+", ");
                System.out.printf(ex.getMajor().get(i).getType()+", ");
                System.out.printf(ex.getMajor().get(i).getAllowedValues()+", ");
                System.out.printf(ex.getMajor().get(i).getMandatory()+'\n');
                for (int j = 0;j<ex.getMajor().get(i).getTree().size();j++) {
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getIO()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getName()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getType()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getAllowedValues()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getMandatory()+"\n");
                }
                System.out.println("__________________________________________________");
            }
        }
    }
}

