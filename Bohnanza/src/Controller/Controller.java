/*
Date: Nov 25th
Course: ICS4U1-02
Name: Andrew, Deng
Significant help: none
Description: This class initalizes controller
*/

package Controller;

import Model.*;
import View.*;

public class Controller {

    // Model and view objects
    Model model = new Model();
    View view = new View();

    // Direct subclass to Controller, requires instance of Controller
    Menu menu;
    Game game;
    EndGame endGame;

    // Indirect subclass to Controller (subclass to Game), requires instance of
    // Controller
    Gameplay gameplay;

    // Constructor
    public Controller() {
        menu = new Menu(this);
        game = new Game(this);
        endGame = new EndGame(this);

        // gameplay = new Gameplay(this);
        setGameplay(game.getGameplay());

        // Starts game cycle
        startCycle();
    }

    public void startCycle() {
        createMenu();
        // createGame();
    }

    public void createMenu() {
        getMenu().createMenu();
    }

    public void createGame() {
        getGame().createGame();
    }

    public void createEndGame() {
        getEndGame().createEndGame();
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public EndGame getEndGame() {
        return endGame;
    }

    public void setEndGame(EndGame endGame) {
        this.endGame = endGame;
    }

}
