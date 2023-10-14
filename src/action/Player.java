package action;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;

public class Player extends Node {
    private Point2D velocity, head, tail;
    private double radius;

    public Player(double x, double y, double width, double height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Player width and height must be positive.");

        if (width > height)
            throw new IllegalArgumentException("Player width cannot be greater than player height.");
        
        velocity = new Point2D(0, 0);

        head = new Point2D(x + width / 2, y + width / 2);
        tail = new Point2D(x + width / 2, y + height - width / 2);

        radius = width / 2;
    }

    public void addShapes(GraphicsContext context) {
        // context.fillOval(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getWidth(), boundingBox.getWidth());
        // context.fillOval(boundingBox.getMinX(), boundingBox.getMaxY() - boundingBox.getWidth(), boundingBox.getWidth(), boundingBox.getWidth());
        context.fillOval(head.getX() - radius, head.getY() - radius, radius * 2, radius * 2);
        context.fillOval(tail.getX() - radius, tail.getY() - radius, radius * 2, radius * 2);
        context.strokeOval(head.getX() - 5, head.getY() - 5, 10, 10);
        context.strokeOval(tail.getX() - 5, tail.getY() - 5, 10, 10);
    }

    public double getDistance(Point2D point) {
        return Math.min(head.distance(point), tail.distance(point));
    }

    public Point2D getVelocity() {
        return velocity;
    }
}
