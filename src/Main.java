import java.io.IOException;
import java.util.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class Main extends Application {
    HashMap<String, Integer> hash_map = new HashMap<String, Integer>(), new_hash_map = new HashMap<String, Integer>();
    ComboBox<String> combo = new ComboBox<String>(), comboFields = new ComboBox<String>();
    ObservableList<String> list = combo.getItems(), listFields = comboFields.getItems();
    GridPane paneG = new GridPane();
    Text io = new Text("N/A"), vals = new Text ("N/A"), mand = new Text ("N/A"), ttype = new Text ("N/A");
    TextField file_path = new TextField(), APIname = new TextField(), nnum = new TextField();

    Label label = new Label("Select Object:"), label2 = new Label ("Select Field: "), Mandatory = new Label ("Mandatory State: "),
            IO = new Label ("I/O State: "), AllowedVals = new Label ("Allowed Values: "), Type = new Label ("Type: "), FileP= new Label("Enter File Path: ");
    Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
    Button goBtn = new Button("Go");
    int idx;

    public void start(Stage primaryStage) throws IOException {
        /**
            Please make sure that you set your file properties
            manually in the filepath variable in line 35-36
         */
        String filePath="C:\\Users\\Dell\\Desktop\\Example.xlsx", API_NNAME="API_NAME";
        int num=1;

//        while(filePath.equals("")||API_NAME.equals("")||num==0){
//            goBtn.setOnAction(e ->{
//                filePath.append(file_path.getText());
//                API_NAME.append(APIname.getText());
//                int number = Integer.parseInt(nnum.getText());
//
//            });
//        }
        Service ex = new Service(filePath, 0);
        String[] s = new String[1]; //to contain API names
        for (int z = 0; z < 1; z++) {
            boolean b = false;      //to exit if the API has entered and mismatched APIs
            s[z] = API_NNAME;
            for (int k = 0; k < z; k++) {
                if (s[k].equals(s[z])) {
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
            label.setFont(font);
            label2.setFont(font);
            AllowedVals.setFont(font);
            IO.setFont(font);
            Mandatory.setFont(font);
            Type.setFont(font);
            combo.setPromptText("Select Object");
            comboFields.setPromptText("Select Field");
            for (int i = 0; i < n; i++) {
                list.add(ex.getMajor().get(i).getName());
                hash_map.put(ex.getMajor().get(i).getName(), i);
            }

            //Event Handling
            combo.setOnAction(e -> {
                String selected = combo.getValue();
                getFields(selected, ex);
            });
            comboFields.setOnAction(e -> {
                String selected2 = comboFields.getValue();
                int idx2 = getIndex2(selected2, ex);
                io.setText(ex.getMajor().get(idx).getTree().get(idx2).getIO());
                mand.setText(ex.getMajor().get(idx).getTree().get(idx2).getMandatory());
                ttype.setText(ex.getMajor().get(idx).getTree().get(idx2).getType());
                vals.setText(ex.getMajor().get(idx).getTree().get(idx2).getAllowedValues());
            });


            //                col, row
            paneG.add(label, 0, 0);
            paneG.add(combo, 1, 0);
            paneG.add(label2, 0, 1);
            paneG.add(comboFields, 1, 1);
            paneG.add(Mandatory, 0, 2);
            paneG.add(mand, 1, 2);
            paneG.add(IO, 0, 3);
            paneG.add(io, 1, 3);
            paneG.add(Type, 0, 4);
            paneG.add(ttype, 1, 4);
            paneG.add(AllowedVals, 0, 5);
            paneG.add(vals, 1, 5);
            paneG.add(new Label("Enter File Path"), 0, 6);
            paneG.add(file_path, 1, 6);
            paneG.add(new Label("Enter API Name"), 0, 7);
            paneG.add(new TextField(), 1, 7);
            paneG.add(new Label("Enter number of APIs"), 0, 8);
            paneG.add(new TextField(), 1, 8);
            paneG.add(goBtn, 1, 10);
            Scene scene = new Scene(paneG, 500, 500);

            primaryStage.setTitle("Our Components");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

    }
    public int getIndex(String s){
        return hash_map.get(s);
    }
    public int getIndex2(String s2, Service ser){
        int j;
        for(j=0;j<ser.getMajor().get(idx).getTree().size();j++)
            if(ser.getMajor().get(idx).getTree().get(j).getName().equals(s2))
                return j;
        return j;
    }
    public void getFields(String s, Service ser){
        idx=getIndex(s);
        listFields.clear();
        for(int j=0;j<ser.getMajor().get(idx).getTree().size();j++) {
            listFields.add(ser.getMajor().get(idx).getTree().get(j).getName());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}

// name of the api: REST Operation Mapping (API_NAME)
//file path: C:\Users\Dell\Desktop\Example.xlsx