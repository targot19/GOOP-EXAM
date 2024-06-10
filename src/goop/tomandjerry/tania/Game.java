package goop.tomandjerry.tania;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.*;

/**
 * The {@code Game} class represents the game logic.
 * It contains {@code Cat}, {@code Mouse}, and {@code Cheese} objects.
 * Furthermore, it has grid dimensions and a list of collected cheeses.
 * The {@code Game} class has a singleton design pattern to ensure only one instance of the game is created.
 * It has methods to update the game, check if the game is over, reset the game,
 * move the mouse, generate cheese, and get the number of collected cheeses.
 * The {@code Game} class also has a method to throw a {@code RuntimeException} when the game is over.
 *
 * @see         Cat
 * @see         Mouse
 * @see         Cheese
 * @see         GameGUI
 * @see         Animal
 * @author      Tania Argot
 */
public class Game {

    /** Singleton instance of the Game object. */
    private static Game instance;

    /** The Cat object in the game. */
    private Cat cat;

    /** The Mouse object in the game. */
    private Mouse mouse;

    /** Width of the game grid. */
    private final int gridWidth;

    /** Height of the game grid. */
    private final int gridHeight;

    /** The Cheese object in the game. */
    private Cheese cheese;

    /** List of collected cheese objects. */
    public ArrayList<Cheese> collectedCheeses;

    /** Image for the dialog pane. */
    Image icon = new Image("resources/6.png");

    /** Image for the dialog icon. */
    Image icon1 = new Image("resources/5.png");

    /**
     * Constructor of the {@code Game} class.
     * The constructor is private to ensure that only one instance of the game is created.
     * It sets the grid width and height to 10.
     * Then, it creates a new {@code Mouse} object in the top left corner,
     * a new {@code Cat} object in the bottom right corner, and a new {@code Cheese} object.
     * The constructor initializes the list of collected cheeses as an empty {@code ArrayList}.
     */
    private Game() {
        gridWidth = 10;
        gridHeight = 10;
        mouse = new Mouse("Jerry", 0, 0);
        cat = new Cat("Tom", gridWidth - 1, gridHeight - 1);
        cheese = new Cheese(4, 6);
        collectedCheeses = new ArrayList<>();
    }

    /**
     * Returns an instance of the {@code Game} object.
     * The method uses the singleton design pattern to ensure only one instance of the game is created.
     * If the instance is null, a new {@code Game} object is created.
     *
     * @return the instance of the {@code Game} object
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Returns the {@code Cat} object.
     *
     * @return the {@code Cat} object
     */
    public Cat getCat() {
        return cat;
    }

    /**
     * Returns the {@code Mouse} object.
     *
     * @return the {@code Mouse} object
     */
    public Mouse getMouse() {
        return mouse;
    }

    /**
     * Returns the {@code Cheese} object.
     *
     * @return the {@code Cheese} object
     */
    public Cheese getCheese() {
        return cheese;
    }

    /**
     * Updates the game state.
     * The cat begins to move randomly.
     * If the cat catches the mouse, the method displays a Game Over dialog.
     * The dialog shows the collected cheeses and asks the user if they want to play again.
     * If the user clicks the restart button, the game is reset.
     * If the user clicks the exit button, the game exits.
     * If the dialog fails to show, a {@code RuntimeException} is thrown.
     *
     * @throws RuntimeException if the dialog is closed instead of the user clicking "Restart Game".
     */
    public void update() throws RuntimeException {
        cat.moveRandomly();

        if (isGameOver()) {
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                ImageView imageView = new ImageView(icon);
                imageView.setFitHeight(75);
                imageView.setFitWidth(100);
                alert.getDialogPane().setGraphic(imageView);
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon1);
                ButtonType restart = new ButtonType("Restart Game");
                alert.getButtonTypes().setAll(restart);
                alert.setTitle("Game Over");
                alert.setHeaderText("You were caught :(");
                alert.setContentText(mouse.getName() + " collected " + getCollectedCheesesCount() + " cheeses.\n" + "Do you want to play again?");
                alert.showAndWait();

                if (alert.getResult() == restart) {
                    reset();
                } else {
                    throw new RuntimeException("The game was closed.");
                }

            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * Checks if the game is over.
     * The method returns true if the cat catches the mouse.
     * Otherwise, the method returns false.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return cat.getX() == mouse.getX() && cat.getY() == mouse.getY();
    }

    /**
     * Resets the game.
     * The method clears the collected cheeses and creates new {@code Mouse} and {@code Cat} objects.
     */
    public void reset() {
        collectedCheeses.clear();
        mouse = new Mouse("Jerry", 0, 0);
        cat = new Cat("Tom", gridWidth - 1, gridHeight - 1);
    }

    /**
     * Moves the mouse.
     * The method updates the mouse position and checks if the mouse collects a cheese.
     * If the mouse collects a cheese, the cheese is added to the list of collected cheeses.
     * Then, a new cheese is generated.
     *
     * @param mouseX the x-coordinate of the mouse
     * @param mouseY the y-coordinate of the mouse
     */
    public void moveMouse(int mouseX, int mouseY) {
        mouse.moveByUser(mouseX, mouseY);

        if (cheese != null && cheese.getX() == mouse.getX() && cheese.getY() == mouse.getY()) {
            collectedCheeses.add(cheese);
            cheese = null;
            generateCheese();
        }
    }

    /**
     * Generates a new cheese at a random location.
     * The method uses the {@code Random} class to generate random x- and y-coordinates.
     */
    public void generateCheese() {
        Random random = new Random();
        int x = random.nextInt(gridWidth);
        int y = random.nextInt(gridHeight);
        cheese = new Cheese(x, y);
    }

    /**
     * Returns the number of collected cheeses.
     *
     * @return the number of collected cheeses
     */
    public int getCollectedCheesesCount() {
        return collectedCheeses.size();
    }
}