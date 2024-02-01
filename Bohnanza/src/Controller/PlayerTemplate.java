/*
Date: Nov 25th
Course: ICS4U1-02
Name: Andrew Deng
Significant help: none
Description: This class initializes player templates for getting user info
*/

package Controller;

public class PlayerTemplate {

    int type;
    String name;

    public PlayerTemplate(int type, String name){
        setType(type);
        setName(name);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
