
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class Main extends Application {
    static String S="", io="", name="", type="", allowedValues="",  mandatory="";
    static int n=0;

    public void start(Stage primaryStage ) {
            VBox pane = new VBox(15);

            pane.setPadding(new Insets(15, 5, 5, 5));
            pane.getChildren().addAll(new Text(name), new Text(io), new Text(type), new Text(allowedValues), new Text(mandatory));

            Scene scene = new Scene(pane, 200, 200);
            primaryStage.setTitle("ShowTEXT");
            primaryStage.setScene(scene);
            primaryStage.show();


            for(int i=0;i<n-1;i++) {
                Stage stage =new Stage();
                VBox panes = new VBox();
                panes.setPadding(new Insets(15, 5, 5, 5));
                panes.getChildren().addAll(new Text(name), new Text(io), new Text(type), new Text(allowedValues), new Text(mandatory));
                Scene scenes=new Scene(panes,200,200);
                stage.setTitle("ShowTEXT");
                stage.setScene(scenes);
                stage.show();

            }

    }

    public static void main(String[] args)  throws IOException {





        String filePath ="./DataFiles/Example.xlsx";
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
            if (!b) {
                z--;
                continue;
            }

            n=ex.getMajor().size();
            for (int i =0;i<ex.getMajor().size();i++) {

                io=ex.getMajor().get(i).getIO();
                name=(ex.getMajor().get(i).getName());
                type= (ex.getMajor().get(i).getType());
                allowedValues= (ex.getMajor().get(i).getAllowedValues());
                mandatory = (ex.getMajor().get(i).getMandatory()+'\n');

                /*for (int j = 0;j<ex.getMajor().get(i).getTree().size();j++) {
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getIO()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getName()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getType()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getAllowedValues()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getMandatory()+"\n");
                }*/

                System.out.println("__________________________________________________");
            }
            launch(args);
        }


    }


}

