package Components;

import java.util.*;

public class Plays {

    private int num;
    LinkedList<Player> players = new LinkedList<>();

    Plays(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }
}