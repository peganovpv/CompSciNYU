package edu.nyu.cs;

/**
 * The GameObject class represents a generic game object in the game.
 */
public abstract class GameObject {

    protected float x, y;

    /**
     * Getter methods:
     */
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract float getSize();
}

