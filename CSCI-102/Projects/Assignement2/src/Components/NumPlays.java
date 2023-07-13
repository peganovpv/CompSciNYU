package Components;

import java.util.*;

public class NumPlays {

    private int num;
    LinkedList<Player> players = new LinkedList<>();

    NumPlays(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }
}