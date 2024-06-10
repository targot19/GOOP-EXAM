package goop.tomandjerry.tania;
/**
 * The {@code Cheese} class represents a cheese in the game, which the {@code Mouse} can collect.
 * It has x- and y-coordinates as its attributes.
 * The class contains a constructor to initialize the attributes and getter methods to access them.
 * The {@code Cheese} object is not meant to be moved, so setter methods are not needed.
 *
 * @see         Game
 * @see         GameGUI
 * @author      Tania Argot
 */

public class Cheese {

    /** X-coordinate of the cheese. */
    private final int cheeseX;

    /** Y-coordinate of the cheese. */
    private final int cheeseY;

    /**
     * Constructor of the {@code Cheese} class.
     *
     * @param x     x-coordinate of the cheese
     * @param y     y-coordinate of the cheese
     */
    public Cheese(int x, int y) {
        this.cheeseX = x;
        this.cheeseY = y;
    }

    /**
     * Returns the x-coordinate of the cheese.
     *
     * @return the x-coordinate of the cheese
     */
    public int getX() {
        return cheeseX;
    }

    /**
     * Returns the y-coordinate of the cheese.
     *
     * @return the y-coordinate of the cheese
     */
    public int getY() {
        return cheeseY;
    }
}