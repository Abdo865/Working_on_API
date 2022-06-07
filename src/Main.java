import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class Main extends Application {

    public void start(Stage primaryStage) throws IOException {
        String filePath = "./DataFiles/Example.xlsx";
        Service ex = new Service(filePath, 0);
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter number of APIs: ");
        int num = scanner.nextInt();
        scanner.nextLine();
        String[] s = new String[num]; //to contain API names
        for (int z = 0; z < num; z++) {
            boolean b = false;      //to exit if the API has entered and mismatched APIs
            System.out.printf("Enter API name: ");
            s[z] = scanner.nextLine();
            for (int k = 0; k < z; k++) {
                if (s[k].equals(s[z])) {
                    System.out.println("this API has been Entered before");
                    z--;
                    b = true;
                    break;
                }
            }
            if (b) continue;
            b = ex.loopOnFields(s[z]);
            if (!b) {
                z--;
                continue;
            }
            GridPane paneG = new GridPane();
            paneG.setAlignment(Pos.CENTER);
            paneG.setHgap(10);
            paneG.setVgap(10);
            int n = ex.getMajor().size();
            for (int i = 0; i < n; i++) {
                paneG.add(new Text(ex.getMajor().get(i).getName()), i, 0);

                for(int j=0;j<ex.getMajor().get(i).getTree().size();j++) {
                    paneG.add(new Text(ex.getMajor().get(i).getTree().get(j).getName()), i, j+1);
                }
            }
            Scene scene = new Scene(paneG, 200, 200);

            primaryStage.setTitle("Our Components");
            primaryStage.setScene(scene);

            primaryStage.show();

        }
    }

    public static void main(String[] args) {

/*
        n = ex.getMajor().size();
        for (int i = 0; i < ex.getMajor().size(); i++) {

            io = ex.getMajor().get(i).getIO();
            name = (ex.getMajor().get(i).getName());
            type = (ex.getMajor().get(i).getType());
            allowedValues = (ex.getMajor().get(i).getAllowedValues());
            mandatory = (ex.getMajor().get(i).getMandatory() + '\n');

                /*for (int j = 0;j<ex.getMajor().get(i).getTree().size();j++) {
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getIO()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getName()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getType()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getAllowedValues()+", ");
                    System.out.printf(ex.getMajor().get(i).getTree().get(j).getMandatory()+"\n");
                }*/
        launch(args);

        System.out.println("__________________________________________________");
    }
}

// name of the api: REST Operation Mapping (API_NAME)
