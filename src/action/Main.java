package action;

import java.util.ArrayList;
import java.util.Collection;

import action.movement.MovementController;
import action.movement.FloatingMovementController;
import action.movement.StaticMovementController;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the stage
        primaryStage.setTitle("ACTION - Movement Demo");

        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);

        // Create the MovementControllers in the scene
        FloatingMovementController player = new FloatingMovementController(new GameObject(new Point2D(500, 50), new Point2D(550, 80), 10), 1);
        player.setVelocity(new Point2D(20, 50));

        FloatingMovementController wall1 = new FloatingMovementController(new GameObject(new Point2D(100, 200), new Point2D(900, 90), 10), 1);
        wall1.setVelocity(new Point2D(60, -10));

        StaticMovementController wall2 = new StaticMovementController(new GameObject(new Point2D(300, 300), new Point2D(800, 250), 10));

        FloatingMovementController wall3 = new FloatingMovementController(new GameObject(new Point2D(800, -10), new Point2D(900, 50), 10), 1);
        wall3.setVelocity(new Point2D(-60, 10));

        // Add the new MovementControllers to the Collection
        Collection<MovementController> movementControllers = new ArrayList<>();
        movementControllers.add(player);
        movementControllers.add(wall1);
        movementControllers.add(wall2);
        movementControllers.add(wall3);

        // Initialize the ActionTimer
        ActionTimer timer = new ActionTimer();
        timer.setObjects(movementControllers);
        timer.setGraphicsContext(context);
        timer.start();

        // Show the stage
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            launch();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
