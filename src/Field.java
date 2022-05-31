import java.util.ArrayList;


public class Field {
    private String name;
    private String allowedValues;
    private String mandatory;
    private String IO;
    private String type;
    private ArrayList<Field> tree;

    public Field(String name, String allowedValues, String mandatory, String IO, String type) {
        this.name = name;
        this.allowedValues = allowedValues;
        this.mandatory = mandatory;
        this.IO = IO;
        this.type = type;
        tree = new ArrayList<Field>();
    }

    public ArrayList<Field> getTree() {  return tree;  }

    public String getName() {  return name;  }

    public void addToList(Field f){  tree.add(f);  }
}