import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.control.*;

public class Main extends Application {
    String filePath = "./DataFiles/Example.xlsx";
    Service ex = new Service(filePath, 0);
    HashMap<String, Integer> hash_map = new HashMap<String, Integer>(), new_hash_map = new HashMap<String, Integer>();
    ComboBox<String> combo = new ComboBox<String>(), comboFields = new ComboBox<String>();
    ObservableList<String> list = combo.getItems(), listFields = comboFields.getItems();
    GridPane paneG = new GridPane();
    Text io = new Text("io"), vals = new Text ("vals"), mand = new Text ("mand"), ttype = new Text ("type");
    Label label = new Label("Select Object:"), label2 = new Label ("Select Field: "), Mandatory = new Label ("Mandatory State: "),
            IO = new Label ("I/O State: "), AllowedVals = new Label ("Allowed Values: "), Type = new Label ("Type: ");
    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
    int idx;
    public Main() throws IOException {
    }

    public void start(Stage primaryStage) throws IOException {
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
            int n = ex.getMajor().size();
            paneG.setAlignment(Pos.CENTER);
            paneG.setHgap(20);
            paneG.setVgap(10);
            label.setFont(font); label2.setFont(font);AllowedVals.setFont(font);IO.setFont(font);Mandatory.setFont(font);Type.setFont(font);
            combo.setPromptText("Select Object");
            comboFields.setPromptText("Select Field");
            for (int i = 0; i < n; i++) {
                list.add(ex.getMajor().get(i).getName());
                hash_map.put(ex.getMajor().get(i).getName(), i);
            }

            //Event Handling
            combo.setOnAction(e-> {
                String selected = combo.getValue();
                getFields(selected);
            });
            comboFields.setOnAction(e-> {
                String selected2 = comboFields.getValue();
                int idx2=getIndex2(selected2);
                io.setText(ex.getMajor().get(idx).getTree().get(idx2).getIO());
                mand.setText(ex.getMajor().get(idx).getTree().get(idx2).getMandatory());
                ttype.setText(ex.getMajor().get(idx).getTree().get(idx2).getType());
                vals.setText(ex.getMajor().get(idx).getTree().get(idx2).getAllowedValues());
            });

            //                col, row
            paneG.add(label, 0, 0); paneG.add(combo, 1, 0);
            paneG.add(label2, 0, 1); paneG.add(comboFields, 1, 1);
            paneG.add(Mandatory, 0, 2); paneG.add(mand, 1, 2);
            paneG.add(IO, 0, 3); paneG.add(io, 1, 3);
            paneG.add(Type, 0, 4); paneG.add(ttype, 1, 4);
            paneG.add(AllowedVals, 0, 5); paneG.add(vals, 1, 5);

            Scene scene = new Scene(paneG, 500, 500);

            primaryStage.setTitle("Our Components");
            primaryStage.setScene(scene);
            primaryStage.show();

        }

    }
    public int getIndex(String s){
        return hash_map.get(s);
    }
    public int getIndex2(String s2){
        int j;
        for(j=0;j<ex.getMajor().get(idx).getTree().size();j++)
            if(ex.getMajor().get(idx).getTree().get(j).getName().equals(s2))
                return j;
        return j;
    }
    public void getFields(String s){
        idx=getIndex(s);
        listFields.clear();
        for(int j=0;j<ex.getMajor().get(idx).getTree().size();j++) {
            listFields.add(ex.getMajor().get(idx).getTree().get(j).getName());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

// name of the api: REST Operation Mapping (API_NAME)
