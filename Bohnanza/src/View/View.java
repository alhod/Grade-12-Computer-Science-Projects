package View;

public class View {
    
    GameGUI gameGUI;
    MenuGUI menuGUI;

    public View(){

    }

    public void createGameGUI(){
        setGameGUI(new GameGUI());
    }

    public void createMenuGUI(){
        setMenuGUI(new MenuGUI());
    }

    public GameGUI getGameGUI() {
        return gameGUI;
    }

    public void setGameGUI(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    public MenuGUI getMenuGUI() {
        return menuGUI;
    }

    public void setMenuGUI(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;
    }

    
}
