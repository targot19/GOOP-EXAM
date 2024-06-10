package goop.tomandjerry.tania;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code GameGUI} class represents the graphical user interface of the game.
 * It sets the stage and scene for the game.
 * The class contains the grid, images, buttons, and labels.
 * The class extends the {@code Application} class from the JavaFX library.
 *
 * @see         Game
 * @version     1.0
 * @author      Tania Argot
 */
public class GameGUI extends Application {

    /** Instance of the {@code Game} class. */
    private final Game game = Game.getInstance();

    /** 2D array of {@code ImageView}s for the game grid. */
    private ImageView[][] grid;

    /** Image of a cat. */
    private final Image imageCat = new Image("resources/1.png");

    /** Image of a mouse. */
    private final Image imageMouse = new Image("resources/2.png");

    /** Image of an empty cell. */
    private final Image imageEmpty = new Image("resources/3.png");

    /** Image of a cheese. */
    private final Image imageCheese = new Image("resources/4.png");

    /** Image for the icon. */
    Image icon = new Image("resources/5.png");

    /** Label to display the number of collected cheeses. */
    private Label cheeseLabel;

    /** Scene for the game. */
    private Scene scene;

    /** Default constructor for the GameGUI class. */
    public GameGUI() {}

    /**
     * Sets the stage and scene for the game.
     * The method creates a navigation bar, grid, and labels for the game.
     * It handles key events for moving the mouse.
     * At last, it starts the game and updates the grid.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set
     */
    @Override
    public void start(Stage primaryStage) {

        /*
         * Creates a navigation bar with a button and label.
         * Adds a button for instructions and a label for the number of collected cheeses.
         * Sets padding, spacing, and alignment for the navigation bar.
         */
        HBox nav = new HBox();
        Button instructions = new Button("Instructions");
        instructions.setOnAction(event -> instructions());
        cheeseLabel = new Label();
        cheeseLabel.setText("Collected Cheeses: " + game.getCollectedCheesesCount());
        nav.getChildren().addAll(instructions, cheeseLabel);
        nav.setPadding(new Insets(12, 10, 12, 10));
        nav.setSpacing(10);
        nav.setAlignment(Pos.CENTER);

        /*
         * Creates a grid for the game.
         * Sets the horizontal and vertical gap for the grid.
         * Initializes a 2D array of ImageViews for the grid.
         * Sets padding for the grid.
         */
        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(1);
        grid = new ImageView[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new ImageView(imageEmpty);
                grid[i][j].setFitWidth(45);
                grid[i][j].setFitHeight(45);
                gridPane.add(grid[i][j], j, i);
            }
        }

        gridPane.setPadding(new Insets(0, 10, 10, 10));

        /*
         * Creates a VBox layout as the root node to hold the navigation bar and grid.
         * Sets the scene with the root node.
         */
        VBox root = new VBox(nav, gridPane);
        this.scene = new Scene(root, 485, 525);

        /*
         * Handles key events for moving the mouse.
         * When arrow keys are pressed, move the mouse.
         */
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                moveMouse(0, -1);
            } else if (event.getCode() == KeyCode.DOWN) {
                moveMouse(0, 1);
            } else if (event.getCode() == KeyCode.LEFT) {
                moveMouse(-1, 0);
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveMouse(1, 0);
            }
        });

        /*
         * Sets the stage for the game.
         * Sets the title, icon, scene, and prevent resizing of the window.
         * Shows the stage.
         */
        primaryStage.setTitle("Tom & Jerry");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        /*
         * Sets focus on the scene to ensure key events are captured.
         * Then, starts the game.
         */
        scene.getRoot().requestFocus();
        startGame();
    }

    /**
     * Initializes the game state.
     * The method resets the game state, generates cheeses, and updates the grid.
     * Then, it sets the initial positions of the mouse and cat.
     */
    private void startGame() {
        game.reset();
        game.generateCheese();
        updateGrid();
        game.getMouse().setX(0);
        game.getMouse().setY(0);
        game.getCat().setX(9);
        game.getCat().setY(9);
    }

    /**
     * Displays the game instructions.
     * The method creates an Alert dialog to display the instructions.
     * It sets the title, header text, and content text for the dialog.
     * It prevents resizing of the dialog and requests focus for the scene.
     * Then, it shows the Alert dialog.
     */
    private void instructions() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        alert.setTitle("Welcome to Tom & Jerry");
        alert.setHeaderText("How to play");
        alert.setContentText("""
                Use the arrow keys to move Jerry.
                Tom will move randomly.
                Collect as many cheeses as you can.
                The game ends when Tom catches Jerry.""");
        alert.setResizable(false);
        alert.setOnHidden(e -> scene.getRoot().requestFocus());
        alert.show();
    }

    /**
     * Moves the mouse on the game grid.
     * The method updates the game state, grid, and cheese count label.
     *
     * @param mouseX the x-coordinate to move the mouse
     * @param mouseY the y-coordinate to move the mouse
     */
    private void moveMouse(int mouseX, int mouseY) {
        game.moveMouse(mouseX, mouseY);
        game.update();
        updateGrid();
        updateCheeseLabel(cheeseLabel);
    }

    /**
     * Updates the game grid.
     * The method iterates through the grid and updates the images.
     * If there is a cheese, it updates the grid with the cheese image.
     * It updates the grid with the mouse and cat images.
     */
    private void updateGrid() {
        for (ImageView[] images : grid) {
            for (ImageView image : images) {
                image.setImage(imageEmpty);
            }
        }

        if (game.getCheese() != null) {
            grid[game.getCheese().getY()][game.getCheese().getX()].setImage(imageCheese);
        }

        grid[game.getMouse().getY()][game.getMouse().getX()].setImage(imageMouse);
        grid[game.getCat().getY()][game.getCat().getX()].setImage(imageCat);
    }

    /**
     * Updates the cheese count label.
     * The method sets the text of the label to display the number of collected cheeses.
     *
     * @param cheeseLabel the label to display the number of collected cheeses
     */
    private void updateCheeseLabel(Label cheeseLabel) {
        cheeseLabel.setText("Collected Cheeses: " + game.getCollectedCheesesCount());
    }

    /**
     * The main method to start the application.
     * This method launches the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}