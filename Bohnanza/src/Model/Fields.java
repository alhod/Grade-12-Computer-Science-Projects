package Model;

import java.util.*;

public class Fields {

    ArrayList<Field> fields;

    // Initializes Field objects, including unpurchased 3rd Field
    public Fields(){
        setFields(new ArrayList<Field>());

        for(int i=0; i<3; i++){
            getFields().add(new Field((i==2?false:true)));
        }
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    
}
