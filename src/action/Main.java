package action;

import java.util.ArrayList;
import java.util.Collection;

import action.objects.FallingObject;
import action.objects.FloatingObject;
import action.objects.GameObject;
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

        // Create the GameObjects in the scene
        FallingObject player = new FallingObject(new Point2D(500, 50), new Point2D(550, 80), 3, 10);
        player.setVelocity(new Point2D(20, 50));
        player.setAcceleration(new Point2D(0, 200));

        FloatingObject wall1 = new FloatingObject(new Point2D(100, 200), new Point2D(900, 90), 10, 1);
        wall1.setVelocity(new Point2D(60, -10));

        FloatingObject wall2 = new FloatingObject(new Point2D(300, 300), new Point2D(800, 280), 10,
                Double.POSITIVE_INFINITY);

        FloatingObject wall3 = new FloatingObject(new Point2D(800, -10), new Point2D(900, 50), 10, 1);
        wall3.setVelocity(new Point2D(-60, 10));

        // Add the new MovementControllers to the Collection
        Collection<GameObject> gameObject = new ArrayList<>();
        gameObject.add(player);
        // gameObject.add(wall1);
        gameObject.add(wall2);
        gameObject.add(wall3);

        // Initialize the ActionTimer
        ActionTimer timer = new ActionTimer();
        timer.setGameObjects(gameObject);
        timer.setContext(context);
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
