package Components.Old;
import java.util.*;
import java.util.stream.Collectors;

import Components.DoubleLinkedList;
import Components.Player;

public class Board {

    private DoubleLinkedList<BoardObject> board = new DoubleLinkedList<BoardObject>();
    private int numPlayers;

    public Board(int g) {
        int[] boardValues = {
            5, 10, 8, 10, 7, 5, 9, 10, 6,
            7, 10, 6, 5, 8, 9, 5, 10, 5, 9, 
            6, 8, 7, 10, 6, 8
        };
        for (int i = 0; i < boardValues.length; i++) {
            BoardObject b = new BoardObject(boardValues[i]);
            board.addLast(b);
        }
        createPlayers(g);
        this.numPlayers = g;
    }

    private void createPlayers(int g) {
        if(g == 1){
            BoardObject b = new BoardObject("Start");
            Player p = new Player("A");
            b.addPlayer(p);
            board.addFirst(b);
            BoardObject c = new BoardObject("End");
            board.addLast(c);
        } else if(g == 2){
            BoardObject b = new BoardObject("Start");
            Player p = new Player("A");
            Player p2 = new Player("B");
            b.addPlayer(p);
            b.addPlayer(p2);
            board.addFirst(b);
            BoardObject c = new BoardObject("End");
            board.addLast(c);
        } else if(g == 3){
            BoardObject b = new BoardObject("Start");
            Player p = new Player("A");
            Player p2 = new Player("B");
            Player p3 = new Player("C");
            b.addPlayer(p);
            b.addPlayer(p2);
            b.addPlayer(p3);
            board.addFirst(b);
            BoardObject c = new BoardObject("End");
            board.addLast(c);
        } else if(g == 4){
            BoardObject b = new BoardObject("Start");
            Player p = new Player("A");
            Player p2 = new Player("B");
            Player p3 = new Player("C");
            Player p4 = new Player("D");
            b.addPlayer(p);
            b.addPlayer(p2);
            b.addPlayer(p3);
            b.addPlayer(p4);
            board.addFirst(b);
            BoardObject c = new BoardObject("End");
            board.addLast(c);
        }
    }

    public int getSize() {
        return board.size();
    }

    public void printBoard() {
        for (int i = 0; i < board.size(); i++) {
            if(i % 9 == 0 && i != 0){
                System.out.println();
            }
            BoardObject bo = board.getValueAt(i);
            ArrayList<Player> players = bo.getPlayers();
            String playerNames = players.stream().map(Player::getName).collect(Collectors.joining(", "));
            if(bo.getValue() == 0){
                System.out.print("  [" + bo.getName() + "(" + playerNames + ")" + "]  ");
            } else if (bo.getValue() == 100){
                System.out.print("  [" + bo.getName() + "(" + playerNames + ")" + "]  ");
            } else {
                System.out.print("  [" + bo.getValue() + "(" + playerNames + ")" + "]  ");
            }
        }
    }

    public BoardObject getValueAt(int i) {
        return board.getValueAt(i);
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ArrayList<Player> getAllPlayers(){
        ArrayList<Player> p = new ArrayList<Player>();
        for (int i = 0; i < board.size(); i++) {
            ArrayList<Player> players = board.getValueAt(i).getPlayers();
            for (int j = 0; j < players.size(); j++) {
                p.add(players.get(j));
            }
        }
        return p;
    }

    public void resetGame() {

        ArrayList<Player> allPlayers = getAllPlayers();

        // Remove all players from the board
        for (int i = 0; i < board.size(); i++) {
            ArrayList<Player> players = board.getValueAt(i).getPlayers();
            BoardObject bo = board.getValueAt(i);
            for (int j = 0; j < players.size(); j++) {
                Player p = players.get(j);
                bo.removePlayer(p);
            }
            for (int j = 0; j < players.size(); j++) {
                Player p = players.get(j);
                p.reset();
            }
        }

        BoardObject start = board.getValueAt(0);

        // Add players back to the start
        for (int i = 0; i < allPlayers.size(); i++) {
            Player p = allPlayers.get(i);
            start.addPlayer(p);
        }

    }

}
