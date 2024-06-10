package goop.tomandjerry.tania;
import java.util.Random;

/**
 * The {@code Cat} class represents a cat in the game.
 * The subclass {@code Cat} extends the abstract {@code Animal} class, inheriting its attributes and methods.
 * It also has a {@code Random} object to generate random numbers as its attribute besides the inherited ones.
 * The class contains a {@code moveRandomly()} method that allows the cat to move randomly on the grid.
 *
 * @see         Animal
 * @see         Game
 * @see         GameGUI
 * @author      Tania Argot
 */
public class Cat extends Animal {

    /** Random object to generate random numbers. */
    private final Random random = new Random();

    /**
     * Constructor of subclass {@code Cat}.
     *
     * @param catName  name of the cat
     * @param catX     x-coordinate of the cat
     * @param catY     y-coordinate of the cat
     */
    public Cat(String catName, int catX, int catY) {
        super(catName, catX, catY);
    }

    /**
     * Allows the cat to move randomly on the grid within the bounds [0, 9],
     * because the grid of the game is 10x10.
     * The method sets the new x- and y-coordinates of the cat,
     * ensuring that it can move randomly between -1, 0, or 1 in both x and y directions.
     */
    public void moveRandomly() {
        int catX = random.nextInt(3) - 1;
        int catY = random.nextInt(3) - 1;

        setX(Math.max(0, Math.min(9, getX() + catX)));
        setY(Math.max(0, Math.min(9, getY() + catY)));
    }
}