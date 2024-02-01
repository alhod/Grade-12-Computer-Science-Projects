package Authentication.Controller;

import Authentication.Model.*;

public class Controller {

    boolean logged_in;
    ControllerFrame controllerFrame;

    public Controller(){
        setLogged_in(false);
    }

    public void createFrame(){
        setControllerFrame(new ControllerFrame(this));
    }

    public ControllerFrame getControllerFrame() {
        return controllerFrame;
    }

    public void setControllerFrame(ControllerFrame controllerFrame) {
        this.controllerFrame = controllerFrame;
    }

    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }

    

    

    

    
}
