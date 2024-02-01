package Model;

import Controller.*;
import View.*;

import java.util.*;

public class Model {

    GameModel gameModel;

    public Model(){

    }

    public void createGameModel(ArrayList<PlayerTemplate>player_templates, Gameplay gameplay){
        gameModel = new GameModel(player_templates, gameplay);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    
}
