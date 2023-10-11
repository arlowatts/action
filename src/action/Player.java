package action;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public class Player implements GameObject {
    private Rectangle2D boundingBox;
    private Point2D velocity;

    private Point2D head, tail;

    public Player(double width, double height, double x, double y) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Player width and height must be positive.");

        if (width > height)
            throw new IllegalArgumentException("Width cannot be greater than height.");
        
        boundingBox = new Rectangle2D(x, y, width, height);
        velocity = new Point2D(0, 0);

        head = new Point2D(x + width / 2, y + width / 2);
        tail = new Point2D(x + width / 2, y + height - width / 2);
    }

    public void addShapes(GraphicsContext context) {
        context.fillOval(boundingBox.getMinX(), boundingBox.getMinY(), boundingBox.getWidth(), boundingBox.getWidth());
        context.fillOval(boundingBox.getMinX(), boundingBox.getMaxY() - boundingBox.getWidth(), boundingBox.getWidth(), boundingBox.getWidth());
        context.strokeOval(head.getX() - 5, head.getY() - 5, 10, 10);
        context.strokeOval(tail.getX() - 5, tail.getY() - 5, 10, 10);
    }

    public double getDistance(Point2D point) {
        return Math.min(head.distance(point), tail.distance(point));
    }

    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public Point2D getVelocity() {
        return velocity;
    }
}
