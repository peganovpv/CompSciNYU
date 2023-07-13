package Components.Old;
import java.util.*;

import Components.Player;

public class BoardObject {

    private ArrayList<Player> Players = new ArrayList<Player>();
    private int value;
    private String name;

    public BoardObject(int v) {
        this.value = v;
    }
    public BoardObject(String n) {
        this.name = n;
        this.value = 0;
    }

    public int getValue() {
        return this.value;   
    }
    
    public String getName() {
        return this.name;
    }

    public void addPlayer(Player p) {
        Players.add(p);
    }

    public void removePlayer(Player p){
        Players.remove(p);
    }

    public void removeAllPlayers() {
        Players.clear();
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

}
