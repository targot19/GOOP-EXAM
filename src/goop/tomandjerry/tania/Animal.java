package goop.tomandjerry.tania;
/**
 * The class {@code Animal} is an abstract superclass.
 * It has the attributes {@code String name}, {@code int x}, and {@code int y}.
 * It contains getter methods for all attributes and setter methods for the
 * x- and y-coordinates.
 * The class is inherited by the {@code Cat} and {@code Mouse} subclasses.
 *
 * @see         Cat
 * @see         Mouse
 * @author      Tania Argot
 */

public abstract class Animal {

    /** Name of the animal. */
    private final String name;

    /** X-coordinate of the animal. */
    private int x;

    /** Y-coordinate of the animal. */
    private int y;

    /**
     * Constructor of the abstract superclass {@code Animal}.
     *
     * @param animalName  name of the animal
     * @param animalX     x-coordinate of the animal
     * @param animalY     y-coordinate of the animal
     */
    public Animal(String animalName, int animalX, int animalY) {
        this.name = animalName;
        this.x = animalX;
        this.y = animalY;
    }

    /**
     * Returns the name of the animal.
     *
     * @return the name of the animal
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x-coordinate of the animal.
     *
     * @return the x-coordinate of the animal
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the animal.
     *
     * @return the y-coordinate of the animal
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-coordinate of the animal.
     *
     * @param xCoor the new x-coordinate of the animal
     */
    public void setX(int xCoor) {
        this.x = xCoor;
    }

    /**
     * Sets the y-coordinate of the animal.
     *
     * @param yCoor the new y-coordinate of the animal
     */
    public void setY(int yCoor) {
        this.y = yCoor;
    }
}