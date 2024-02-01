package Model;

import Controller.*;
import View.*;

import java.util.*;

public class Players {

    Gameplay gameplay;
    int num_players;
    ArrayList<Player>players;

    public Players(ArrayList<PlayerTemplate>player_templates, Gameplay gameplay){
        setPlayers(new ArrayList<Player>());
        setGameplay(gameplay);
        setNum_players(0);

        for(int i=0; i<player_templates.size(); i++){
            // System.out.println("player name: "+player_templates.get(i).getName());
            // System.out.println("type: "+player_templates.get(i).getType());
            
            if(player_templates.get(i).getType()==0){
                players.add(new Player(getNum_players(), player_templates.get(i).getName(), gameplay));
            } else{
                players.add(new AI(getNum_players(), player_templates.get(i).getName(), gameplay));
            }
            setNum_players(getNum_players()+1);
        }
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getNum_players() {
        return num_players;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
    }

    
}
