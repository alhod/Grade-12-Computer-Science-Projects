package Map.Controller;

public class Controller {
    
    MapController mapController;

    public Controller(){
        
    }

    public void createFrame(){
        setMapController(new MapController());
    }

    public MapController getMapController() {
        return mapController;
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    
}
