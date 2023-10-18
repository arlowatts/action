package action;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

/**
 * A GameObject represents a physical surface in the game.
 * All GameObjects are line segments between two points with a radius of 0 or greater.
 * The radius defines a distance from the line segment that is counted as part of the shape.
 */
public class GameObject {
    private Point2D a, b, n;

    private double length, radius;

    /**
     * Initilalizes a new GameObject.
     * @param x1 the x coordinate of the first point.
     * @param y1 the y coordinate of the first point.
     * @param x2 the x coordinate of the second point.
     * @param y2 the y coordinate of the second point.
     * @param radius the radius of the line.
     */
    public GameObject(double x1, double y1, double x2, double y2, double radius) {
        setRadius(radius);

        this.a = new Point2D(x1, y1);
        this.b = new Point2D(x2, y2);

        update();
    }

    /**
     * Draws the GameObject onto the given GraphicsContext with the current stroke settings except line width and line cap.
     * @param context the GraphicsContext to draw to.
     */
    public void draw(GraphicsContext context) {
        context.save();

        context.setLineWidth(radius * 2);
        context.setLineCap(StrokeLineCap.ROUND);
        context.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());

        context.restore();
    }

    /**
     * Evaluates the shortest distance from the given point to this GameObject.
     * If the point is inside this GameObject's radius, the distance will be negative.
     * @param point the point to evaluate the distance to.
     * @return the shortest distance between the point and this GameObject.
     */
    public double getDistance(Point2D point) {
        if (length == 0)
            return a.distance(point) - radius;

        double t = point.subtract(a).dotProduct(n);

        t = Math.min(Math.max(t, 0), length);

        return a.add(n.multiply(t)).distance(point) - radius;
    }

    /**
     * Evaluates the shortest distance between two non-crossed GameObjects.
     * If the lines defined by the GameObjects intersect, the distance will not be accurate.
     * @param line the GameObject to evaluate the distance to.
     * @return the shortest distance between the two GameObjects.
     */
    public double getDistance(GameObject line) {
        return Math.min(
            Math.min(getDistance(line.a), getDistance(line.b)) - line.radius,
            Math.min(line.getDistance(a), line.getDistance(b)) - radius
        );
    }

    private void update() {
        length = a.distance(b);

        if (length > 0)
            n = b.subtract(a).multiply(1 / length);
    }

    public Point2D getA() {
        return a;
    }

    public void setA(Point2D a) {
        this.a = a;
        update();
    }

    public Point2D getB() {
        return b;
    }

    public void setB(Point2D b) {
        this.b = b;
        update();
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius < 0)
            throw new IllegalArgumentException("radius must not be less than 0.");

        this.radius = radius;
    }
}
