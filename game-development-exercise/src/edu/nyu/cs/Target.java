package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

/**
 * The Target class represents a target object in the game.
 */

public class Target extends GameObject{
    private PApplet p;
    private float x, y;
    private float size;

    private PImage fuelImage;

    /**
     * Constructs a Target object with the specified PApplet, x, and y coordinates.
     *
     * @param p the PApplet instance to be associated with the target
     * @param x the x-coordinate of the target
     * @param y the y-coordinate of the target
     */
    public Target(PApplet p, float x, float y) {
        this(p,x,y,30);
    }

    /**
     * Constructs a Target object with the specified PApplet, x, y coordinates, and size.
     *
     * @param p the PApplet instance to be associated with the target
     * @param x the x-coordinate of the target
     * @param y the y-coordinate of the target
     * @param size the size of the target
     */
    public Target(PApplet p, float x, float y, float size) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.size = 30;
        fuelImage = p.loadImage("images/fuel.png");
    }

    /**
     * Displays the target's image at its current position.
     */
    public void display() {
        p.fill(0, 255, 0);
        p.rect(x - size / 2, y - size / 2, size, size);
        p.imageMode(PConstants.CENTER);
        p.image(fuelImage, x, y, 30, 30);
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

