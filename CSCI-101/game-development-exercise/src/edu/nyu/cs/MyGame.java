package edu.nyu.cs;

import processing.core.PApplet;
import processing.sound.SoundFile;
import processing.core.PImage;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The MyGame class represents the UI and main game logic for the game
 */
public class MyGame extends PApplet {

    private Player player;
    private ArrayList<Target> targets;
    private ArrayList<Obstacle> obstacles;
    private int score;
    private boolean gameOver;
    private GameState gameState;
    private final int initialTime = 30000; // Set the initial time in milliseconds (30 seconds)
    private float remainingTime = initialTime; // Initialize remainingTime
    private float startTime;
    private ArrayList<Integer> previousHighScores;

    private SoundFile vibraphoneSound;
    private SoundFile successSound;
    private SoundFile failureSound;

    private PImage backgroundImage; 

    private String gameName = "Space Enemies";

    private enum GameState {
        START,
        PLAYING,
        GAME_OVER
    }

    /**
     * main method for the game
     * @param args
     */
    public static void main(String[] args) {
        PApplet.main(MyGame.class.getCanonicalName());
    }

    /**
     * The settings method creates the size of the game window
     */
    public void settings() {
        size(800, 600);
    }

    /**
     * The setup method initializes the gameState, images and sounds
     */
    public void setup() {
      gameState = GameState.START;

      vibraphoneSound = new SoundFile(this, "sounds/vibraphon.mp3");

      successSound = new SoundFile(this, "sounds/targetHit.wav");

      failureSound = new SoundFile(this, "sounds/gameOver.wav");

      backgroundImage = loadImage("images/background.png");

      backgroundImage.resize(800,600);
    }

    /**
     * The draw method is called repeatedly to update the game state and render the game
     */
    public void draw() {

        switch (gameState) {
            case START:
                displayStartScreen();
                break;
            case PLAYING:
                playGame();
                break;
            case GAME_OVER:
                displayGameOverScreen();
                break;
        }
    }

    /**
     * The displayStartScreen method is called when the game is started up.
     * It shows the instructions and objectives of the game
     */
    private void displayStartScreen() {
        textSize(32);
        fill(0);
        if(!vibraphoneSound.isPlaying()) {
            vibraphoneSound.play();
        }
        float gameNameWidth = textWidth(gameName);
        text(gameName, (width - gameNameWidth) / 2, height / 2 - 100);
  
        textSize(24);
        float instructionsWidth = textWidth("Instructions:");
        text("Instructions:", (width - instructionsWidth) / 2, height / 2 - 50);
  
        float moveInstructionsWidth = textWidth("Use <a s d w> to move around");
        text("Use <a s d w> to move around", (width - moveInstructionsWidth) / 2, height / 2);
  
        float obstacleInstructionsWidth = textWidth("Avoid the enemy ships and collect the fuel cells.");
        text("Avoid the enemy ships and collect the fuel cells.", (width - obstacleInstructionsWidth) / 2, height / 2 + 25);
  
        float rocketInstructionsWidth = textWidth("You play as the rocket.");
        text("You play as the rocket.", (width - rocketInstructionsWidth) / 2, height / 2 + 50);

        float enemyInstructionsWidth = textWidth("Enemies are spider-like ships and fuel cells are green");
        text("Enemies are spider-like ships and fuel cells are green", (width - enemyInstructionsWidth) / 2, height / 2 + 75);
  
        float startInstructionsWidth = textWidth("Click to start");
        text("Click to start", (width - startInstructionsWidth) / 2, height / 2 + 125);
    }
    
    /**
     * Initializes the game by creating a new player, targets, and obstacles.
     */
    private void initGame() {
        player = new Player(this);
        targets = new ArrayList<>();
        obstacles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            targets.add(new Target(this, random(width), random(height)));
        }
        int numberOfObstacles = (int) random(9, 16);
        for (int i = 0; i < numberOfObstacles; i++) {
            obstacles.add(new Obstacle(this, random(width), random(height)));
        }
        score = 0;
        gameOver = false;
        remainingTime = 30000;
        if (previousHighScores == null) {
            previousHighScores = new ArrayList<>();
        }
        startTime = millis();
    }

    /**
     * Executes the main game loop, updating and displaying game objects, handling collisions,
     * and updating the score and time left. If the game is over, it calls the game over screen.
     */
    public void playGame() {
        background(backgroundImage);

        if (!gameOver) {
            player.update();
            player.display();

            for (Target target : targets) {
                target.display();
            }

            for (Obstacle obstacle : obstacles) {
                obstacle.display();
            }

            for (int i = targets.size() - 1; i >= 0; i--) {
                if (player.collidesWith(targets.get(i))) {
                    targets.remove(i);
                    score++;
                    successSound.play();
                    remainingTime +=500;
                    targets.add(new Target(this, random(width), random(height)));
                }
            }

            for (Obstacle obstacle : obstacles) {
                if (player.collidesWith(obstacle)) {
                    gameOver = true;
                    failureSound.play();
                    previousHighScores.add(score);
                    gameState = GameState.GAME_OVER;
                    break;
                }
            }

            textSize(24);
            fill(225);
            text("Score: " + score, 20, 40);

            fill(255);
            float rectWidth = 200;
            float rectHeight = 40;
            float rectX = 20;
            float rectY = 60;
            float cornerRadius = 10;
            rect(rectX, rectY, rectWidth, rectHeight, cornerRadius);

            fill(0);
            text("Time left: " + (remainingTime - (millis() - startTime)) / 1000, 20, 80);

            if (millis() - startTime > remainingTime) {
                gameOver = true;
                previousHighScores.add(score);
            }
        } else {
            displayGameOverScreen();
        }
    } 

    /**
    * Displays the game over screen with the player's score and high scores.
    * Prompts the user to click to restart the game.
    */
    private void displayGameOverScreen() {
        fill(255);
        float rectX = width / 2 - 150;
        float rectY = height / 2 - 175;
        float rectWidth = 300;
        float rectHeight = 350;
        float cornerRadius = 20;
        rect(rectX, rectY, rectWidth, rectHeight, cornerRadius);
        textSize(32);
        fill(0);
        text("Game Over", width / 2 - 100, height / 2 - 100);
        text("Score: " + score, width / 2 - 50, height / 2 - 50);
        text("Click to restart", width / 2 - 100, height / 2);
        displayHighScores();
    }

    /**
     * Displays the high scores in the game over screen.
     */
    private void displayHighScores() {
        Collections.sort(previousHighScores, Collections.reverseOrder());
        textSize(24);
        fill(0);
        float gameOverTextWidth = textWidth("Game Over!");
        text("High Scores:", width / 2 - gameOverTextWidth / 2, height / 2 + 50);
        for (int i = 0; i < previousHighScores.size(); i++) {
            text((i + 1) + ". " + previousHighScores.get(i), width / 2 - gameOverTextWidth / 2, height / 2 + 75 + i * 25);
        }
    }
    

    /**
     * Handles key press events to control the player's movement.
     */
    public void keyPressed() {
        player.handleKeyPress(key);
    }

    /**
     * Handles key release events to stop the player's movement.
     */
    public void keyReleased() {
        player.handleKeyRelease(key);
    }

    /**
     * Handles mouse press events to start the game.
     */
    public void mousePressed() {
        switch (gameState) {
            case START:
                initGame();
                gameState = GameState.PLAYING;
                break;
            case PLAYING:
                break;
            case GAME_OVER:
                initGame();
                gameState = GameState.PLAYING;
                break;
        }
    }

}
