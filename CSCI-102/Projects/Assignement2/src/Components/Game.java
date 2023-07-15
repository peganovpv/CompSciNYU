package Components;
import java.util.*;

public class Game {

    DoubleLinkedList<Plays> gameBoard;
    int numPlayers;
    Player[] players;
    Random random;
    int totalPlayed = 0;
    String playerNames = "";

    public Game(int numPlayers) {

        this.numPlayers = numPlayers;
        this.players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            char playerName = (char) ('A' + i);
            players[i] = new Player(String.valueOf(playerName));
        }

        this.random = new Random();
        this.gameBoard = new DoubleLinkedList<>();

        createBoard();
    }

    private void createBoard() {

        Random r = new Random();

        gameBoard.addFirst(null);
        gameBoard.addLast(null); 

        DoubleLinkedList.Node<Plays> prev = gameBoard.head;
        DoubleLinkedList.Node<Plays> next = new DoubleLinkedList.Node<Plays>(null, null, null);

        for (int i = 0; i < 25; i++) {
            next = new DoubleLinkedList.Node<>(new Plays(r.nextInt(10) + 1), prev, null);

            if (prev != null) {
                prev.setNext(next);
            }

            prev = next;
        }

        gameBoard.tail.setPrev(next);
    }

    private int diceRoll() {
        int dice = random.nextInt(6) + 1;
        return dice;
    }

    private void movePlayer(Player player, int move) {

        int currentPosition = player.getPosition();
        int newPosition = currentPosition + move;

        DoubleLinkedList.Node<Plays> oldPositionSquare = gameBoard.head;
        for (int i = 0; i < currentPosition; i++) {
            oldPositionSquare = oldPositionSquare.next;
        }

        if (newPosition <= 25) { 
            DoubleLinkedList.Node<Plays> newPositionSquare = gameBoard.head;
            for (int i = 0; i < newPosition; i++) {
                newPositionSquare = newPositionSquare.next;
            }

            if (newPositionSquare.getElement().getPlayers().isEmpty()) {
                player.setPosition(newPosition);
                player.addScore(newPositionSquare.getElement().getNum());
                newPositionSquare.getElement().getPlayers().add(player);
                if (currentPosition != 0) {
                    oldPositionSquare.getElement().getPlayers().remove(player);
                }
            } else {
                if (newPosition - 7 > 0) {
                    player.setPosition(newPosition - 7);
                    DoubleLinkedList.Node<Plays> newPositionSquare2 = gameBoard.head;
                    for (int i = 0; i < newPosition - 7; i++) {
                        newPositionSquare2 = newPositionSquare2.next;
                    }
                    newPositionSquare2.getElement().getPlayers().add(player);
                    oldPositionSquare.getElement().getPlayers().remove(player);
                } else {
                    player.setPosition(0);
                    if (currentPosition != 0) {
                        oldPositionSquare.getElement().getPlayers().remove(player);
                    }
                }
            }
        } else {
            if (player.getScore() < 44) {
                player.setPosition(0);
                oldPositionSquare.getElement().getPlayers().remove(player);
            } else {
                player.setPosition(25);
            }
        }
    }

    private void resetPlayers() {
        
        for (Player player : players) {
            player.reset();
        }

        DoubleLinkedList.Node<Plays> t = gameBoard.head;
        for (int i = 0; i < 25; i++) {
            t = t.next;
            t.getElement().players = new LinkedList<>();
        }
    }

    public double[][] playGame() {

        double gameCount = 0;
        int[] movesPerGame = new int[numPlayers];
        int[] winsPerGame = new int[numPlayers];
        int tempMove = 0;
        int[] totalMoves = new int[numPlayers];
        StringBuilder result = new StringBuilder();

        while (gameCount < 1000) {
            boolean gameOver = false;
            while (!gameOver) {
                for (int i = 0; i < numPlayers; i++) {
                    if (players[i].getPosition() >= 19) {
                        int roll = gameBoard.size();
                        movePlayer(players[i], roll);
                        movesPerGame[i] += roll;
                        tempMove++;
                        if (players[i].getPosition() == 25 && players[i].getScore() >= 44) {
                            winsPerGame[i]++;
                            gameOver = true;
                            totalMoves[i] += tempMove;
                            tempMove = 0;
                            break;
                        }
                    } else {
                        int dice = diceRoll();
                        movePlayer(players[i], dice);
                        movesPerGame[i] += dice;
                        tempMove++;
                        if (players[i].getPosition() == 25 && players[i].getScore() >= 44) {
                            winsPerGame[i]++;
                            gameOver = true;
                            totalMoves[i] += tempMove;
                            tempMove = 0;
                            break;
                        }
                    }
                }
            }
            

            if (gameCount % 100 == 0) {

                showBoard(result, gameCount);
                System.out.println(result);
                result.setLength(0); 
            }
            gameCount++;

            resetPlayers();
            
        }

        double[][] res = new double[numPlayers][2]; 

        for (int i = 0; i < numPlayers; i++) {
            res[i][0] = (double) totalMoves[i] / gameCount; 
            res[i][1] = (double) winsPerGame[i] / gameCount; 
        }

        return res;
    }

    public void showBoard(StringBuilder res, double gameCount) {

        res.append("=== Game ").append((int) gameCount).append(" ===\n");
    
        DoubleLinkedList.Node<Plays> current = gameBoard.head.next;
        int count = 0;
    
        while (current != null) {
            count++;
            // add dividers and row numbers
            if (count == 1 || count == 10 || count == 17) {
                res.append("-----Row ").append(count < 10 ? "1" : count < 17 ? "2" : "3").append("-----\n");
            }
    
            res.append("|").append(current.getElement().getNum()).append("| ");
    
            for (int j = 0; j < current.getElement().getPlayers().size(); j++) {
                res.append("(").append(current.getElement().getPlayers().get(j).getName()).append(") ");
            }
    
            // end of a row
            if (count == 9 || count == 16 || count == 25) {
                res.append("\n");
            }
            
            current = current.next;
        }
    
        res.append("====================\n");
    }
    
}



