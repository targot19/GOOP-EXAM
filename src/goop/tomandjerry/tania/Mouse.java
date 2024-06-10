package goop.tomandjerry.tania;
/**
 * The {@code Mouse} class represents a mouse in the game.
 * The subclass {@code Mouse} extends the abstract {@code Animal} class, inheriting its attributes and methods.
 * It contains a {@code moveByUser(int mouseX, int mouseY)} method that allows the mouse to move based on user input.
 *
 * @see         Animal
 * @see         Game
 * @see         GameGUI
 * @author      Tania Argot
 */

public class Mouse extends Animal {

    /**
     * Constructor of subclass {@code Mouse}.
     *
     * @param mouseName  name of the mouse
     * @param mouseX     x-coordinate of the mouse
     * @param mouseY     y-coordinate of the mouse
     */
    public Mouse(String mouseName, int mouseX, int mouseY) {
        super(mouseName, mouseX, mouseY);
    }

    /**
     * Allows the user to move the mouse on the grid within the bounds [0, 9],
     * because the grid of the game is 10x10.
     *
     * @param mouseX    x-coordinate of the mouse
     * @param mouseY    y-coordinate of the mouse
     */
    public void moveByUser(int mouseX, int mouseY) {
        setX(Math.max(0, Math.min(9, getX() + mouseX)));
        setY(Math.max(0, Math.min(9, getY() + mouseY)));
    }
}