package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Obstacle extends GameObject{
    private PApplet p;
    private float x, y;
    private float size;

    private PImage obstacleImage;

    /**
     * Constructs a Obstacle object with the specified PApplet, x, y coordinates, and size.
     *
     * @param p the PApplet instance to be associated with the obstacle
     * @param x the x-coordinate of the obstacle
     * @param y the y-coordinate of the obstacle
     * @param size the size of the obstacle
     */
    public Obstacle(PApplet p, float x, float y) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.size = 40;
        obstacleImage = p.loadImage("images/enemy.png");
    }


    /**
     * Displays the obstacle's image at its current position.
     */
    public void display() {
        p.fill(0, 0, 255);
        p.imageMode(PConstants.CENTER);
        p.image(obstacleImage, x, y, 40, 40);
    }


    /**
     * Getter methods :
     */
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }
}
