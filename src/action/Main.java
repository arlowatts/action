package action;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {
    public static final int FPS = 60;
    public static final int MS_PER_FRAME = 1000 / FPS;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the stage
        primaryStage.setTitle("ACTION - Movement Demo");

        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext context = canvas.getGraphicsContext2D();

        drawShapes(context);

        GameObject player = new GameObject(500, 70, 550, 100, 10);
        player.draw(context);

        root.getChildren().add(canvas);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        distanceTest(context, player, 400, 0);
        distanceTest(context, player, 500, 150);
        distanceTest(context, player, 500, 0);
        distanceTest(context, player, 550, 50);

        distanceTest2(context, player, 400, 0, 500, 200);
        distanceTest2(context, player, 600, 0, 525, 50);
        distanceTest2(context, player, 510, 90, 550, 200);
    }

    private void distanceTest(GraphicsContext context, GameObject object, double x, double y) {
        double distance = object.getDistance(new Point2D(x, y));
        context.fillOval(x - distance, y - distance, distance * 2, distance * 2);
    }

    private void distanceTest2(GraphicsContext context, GameObject object, double x1, double y1, double x2, double y2) {
        GameObject o = new GameObject(x1, y1, x2, y2, 0);
        double distance = Math.max(object.getDistance(o), 0.1);
        GameObject o2 = new GameObject(x1, y1, x2, y2, distance);
        o2.draw(context);
    }

    private void drawShapes(GraphicsContext context) {
        context.setFill(Color.GREEN);
        context.setStroke(Color.BLUE);
        context.setLineWidth(5);
        context.strokeLine(40, 10, 10, 40);
        context.fillOval(10, 60, 30, 30);
        context.strokeOval(60, 60, 30, 30);
        context.fillRoundRect(110, 60, 30, 30, 10, 10);
        context.strokeRoundRect(160, 60, 30, 30, 10, 10);
        context.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        context.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        context.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        context.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        context.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        context.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        context.fillPolygon(new double[]{10, 40, 10, 40},
                       new double[]{210, 210, 240, 240}, 4);
        context.strokePolygon(new double[]{60, 90, 60, 90},
                         new double[]{210, 210, 240, 240}, 4);
        context.strokePolyline(new double[]{110, 140, 110, 140},
                          new double[]{210, 210, 240, 240}, 4);
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
