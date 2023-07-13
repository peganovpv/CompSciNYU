package Components;
import java.util.*;

public class Game {

    DoubleLinkedList<NumPlays> gameBoard;
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

        DoubleLinkedList.Node<NumPlays> prev = gameBoard.head;
        DoubleLinkedList.Node<NumPlays> next = new DoubleLinkedList.Node<NumPlays>(null, null, null);

        for (int i = 0; i < 25; i++) {
            next = new DoubleLinkedList.Node<>(new NumPlays(r.nextInt(10) + 1), prev, null);

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

        DoubleLinkedList.Node<NumPlays> oldPositionSquare = gameBoard.head;
        for (int i = 0; i < currentPosition; i++) {
            oldPositionSquare = oldPositionSquare.next;
        }

        if (newPosition <= 25) { 
            DoubleLinkedList.Node<NumPlays> newPositionSquare = gameBoard.head;
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
                    DoubleLinkedList.Node<NumPlays> newPositionSquare2 = gameBoard.head;
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

        DoubleLinkedList.Node<NumPlays> t = gameBoard.head;
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
        
        res.append("Game ").append((int) gameCount).append(System.lineSeparator());

        DoubleLinkedList.Node<NumPlays> current = gameBoard.head.next;

        while (current != null) {
            for (int i = 0; i < 25; i++) {
                if (i == 9 || i == 16) {
                    res.append(System.lineSeparator());
                }
                res.append("").append(current.getElement().getNum()).append(" ");
                for (int j = 0; j < current.getElement().getPlayers().size(); j++) {
                    res.append(current.getElement().getPlayers().get(j).getName()).append(" ");
                }
                current = current.next;
            }
        }
        res.append(System.lineSeparator());
    }
}



