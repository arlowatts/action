package action;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface GameObject {
    public Rectangle2D getBoundingBox();
    public Point2D getVelocity();
    public double getDistance(Point2D point);
    public void addShapes(GraphicsContext context);
}
