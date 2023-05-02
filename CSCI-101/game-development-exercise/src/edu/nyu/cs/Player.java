package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Player class represents the player-controlled spaceship in the game.
 */

public class Player {
    private PApplet p;
    private float x, y;
    private float speed;
    private boolean moveLeft, moveRight, moveUp, moveDown;
    private float direction;

    private int objectWidth = 45;
    private int objectHeight = 45;

    private PImage spaceshipImage;

    /**
     * Constructs a Player object with the specified PApplet.
     *
     * @param p the PApplet instance to be associated with the player
     */
    public Player(PApplet p) {
        this.p = p;
        x = p.width / 2;
        y = p.height / 2;
        speed = 5;
        spaceshipImage = p.loadImage("images/spaceship.png");
    }

    /**
    * Updates the player's position based on the current movement flags.
    * Performs boundary checks to ensure that the player stays within the window.
    */
    public void update() {
        if (moveLeft) {
            x -= speed;
            direction = PApplet.radians(270);
        }
        if (moveRight) {
            x += speed;
            direction = PApplet.radians(90);
        }
        if (moveUp) {
            y -= speed;
            direction = PApplet.radians(0);
        }
        if (moveDown) {
            y += speed;
            direction = PApplet.radians(180);
        }
    
        // Boundary checks
        if (x < 0) {
            x = 0;
        } else if (x > p.width - objectWidth) {
            x = p.width - objectWidth;
        }
    
        if (y < 0) {
            y = 0;
        } else if (y > p.height - objectHeight) {
            y =p.height - objectHeight;
        }
    }
    

    /**
     * Displays the player's spaceship image at its current position.
     */
    public void display() {
        p.pushMatrix();
        p.translate(x, y);
        p.rotate(direction);
        p.imageMode(PApplet.CENTER);
        p.image(spaceshipImage, 0, 0, 55, 55);
        p.popMatrix();
    }

    /**
     * Handles key press events for player movement.
     *
     * @param key the key that was pressed
     */
    public void handleKeyPress(char key) {
        if (key == 'a') moveLeft = true;
        if (key == 'd') moveRight = true;
        if (key == 'w') moveUp = true;
        if (key == 's') moveDown = true;
    }

    /**
     * Handles key release events for player movement.
     *
     * @param key the key that was released
     */
    public void handleKeyRelease(char key) {
        if (key == 'a') moveLeft = false;
        if (key == 'd') moveRight = false;
        if (key == 'w') moveUp = false;
        if (key == 's') moveDown = false;
    }

    /**
     * Checks if the player collides with a specified GameObject.
     *
     * @param gameObject the GameObject to check for collision
     * @return true if a collision occurs, false otherwise
     */
    public boolean collidesWith(GameObject gameObject) {
        float distance = PApplet.dist(x, y, gameObject.getX(), gameObject.getY());
        return distance < (25 + gameObject.getSize() / 2);
    }
}

