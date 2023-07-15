import java.text.DecimalFormat;

import Components.Game;

public class Main {
    
    public static void main(String[] args) {

        double[][][] gameResults = new double[5][][]; // Store the results of the 4 games

        String[] games = {"Game 1", "Game 2", "Game 3", "Game 4"}; // Store the names of the games

        int totalPlayed = 0; // Total number of games played

        // Play the 4 games
        for (int i = 1; i <= 4; i++) {
            Game g = new Game(i);            
            double[][] gameResult = g.playGame();
            gameResults[i] = gameResult; 
            totalPlayed += 1000; 
        }

        // Print the results
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("\nTotal Results for " + totalPlayed + " Games\n");
        System.out.printf("%-30s %-30s %-30s %-30s %-30s%n", "Game", "A: avg moves / win %", "B: avg moves / win %", "C: avg moves / win %", "D: avg moves / win %");
        for (int i = 1; i <= 4; i++) {
            System.out.printf("%-30s", games[i - 1]);
            for (int j = 0; j < i; j++) {
                System.out.printf("%-30s", "avg: " + df.format(gameResults[i][j][0]) + " moves, win: " + df.format(gameResults[i][j][1] * 100) + "%");
            }
            System.out.println();
        }


    }
    

}
