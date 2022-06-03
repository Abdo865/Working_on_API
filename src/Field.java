import java.util.ArrayList;


public class Field {
    private String IO;
    private String name;
    private String type;
    private String allowedValues;
    private String mandatory;
    private ArrayList<Field> tree;

    public Field(String IO, String name, String type, String allowedValues, String mandatory) {
        this.name = name;
        this.allowedValues = allowedValues;
        this.mandatory = mandatory;
        this.IO = IO;
        this.type = type;
        tree = new ArrayList<Field>();
    }

    public ArrayList<Field> getTree() {  return tree;  }

    public String getName() {  return name;  }

    public String getAllowedValues() {
        return allowedValues;
    }

    public String getMandatory() {
        return mandatory;
    }

    public String getIO() {
        return IO;
    }

    public String getType() {
        return type;
    }

    public void addToList(Field f){  tree.add(f);  }
}