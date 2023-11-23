package action.objects;

import action.geometry.LineSegment2D;
import java.util.Collection;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;

/**
 * A GameObject represents a physical surface in the game. All GameObjects are
 * line segments between two points with a radius of 0 or greater. The radius
 * defines a distance from the line segment that is considered part of the
 * shape.
 */
public abstract class GameObject extends LineSegment2D {
    private double radius, mass;

    private Point2D velocity;

    /**
     * Given the context of all other GameObjects in the scene, apply the
     * appropriate acceleration to the attached GameObject to resolve collisions.
     * 
     * @param gameObjects a Collection of all other GameObjects in the scene.
     */
    public abstract void resolveCollisions(Collection<GameObject> gameObjects);

    /**
     * Initialize a new GameObject with the given endpoints, radius, and mass.
     * 
     * @param a      the first endpoint of the GameObject.
     * @param b      the second endpoint of the GameObject.
     * @param radius the radius of the GameObject.
     * @param mass   the mass of the GameObject.
     */
    public GameObject(Point2D a, Point2D b, double radius, double mass) {
        super(a, b);
        setRadius(radius);
        setMass(mass);
        setVelocity(Point2D.ZERO);
    }

    /**
     * Draw the GameObject onto the given GraphicsContext. The current stroke
     * settings are used except for line width and line cap, which are set just for
     * this draw call.
     * 
     * @param context the GraphicsContext to draw to.
     */
    public void draw(GraphicsContext context) {
        context.save();

        context.setLineWidth(radius * 2);
        context.setLineCap(StrokeLineCap.ROUND);
        context.strokeLine(getA().getX(), getA().getY(), getB().getX(), getB().getY());

        context.restore();
    }

    /**
     * Get the point along the center of this GameObject that is nearest to the
     * given point.
     * 
     * @param point the other point.
     * @return the point along this GameObject's center that is closest to the given
     *         point.
     */
    public Point2D getNearestPoint(Point2D point) {
        if (getLength() == 0) {
            return getA();
        }

        Point2D c = getB().subtract(getA());

        double t = point.subtract(getA()).dotProduct(c) / c.dotProduct(c);

        t = Math.min(1, Math.max(0, t));

        return getA().add(c.multiply(t));
    }

    /**
     * Get a shortest line segment that touches the center of both GameObjects. The
     * first endpoint of the new line segment will always lie on this GameObject.
     * 
     * @param gameObject the other GameObject.
     * @return a shortest line segment that touches each GameObject.
     */
    public LineSegment2D getShortestLine(GameObject gameObject) {
        LineSegment2D aToOther = new LineSegment2D(getA(), gameObject.getNearestPoint(getA()));
        LineSegment2D bToOther = new LineSegment2D(getB(), gameObject.getNearestPoint(getB()));
        LineSegment2D toOtherA = new LineSegment2D(getNearestPoint(gameObject.getA()), gameObject.getA());
        LineSegment2D toOtherB = new LineSegment2D(getNearestPoint(gameObject.getB()), gameObject.getB());

        LineSegment2D shortestLine = aToOther;

        if (bToOther.getLength() < shortestLine.getLength()) {
            shortestLine = bToOther;
        }

        if (toOtherA.getLength() < shortestLine.getLength()) {
            shortestLine = toOtherA;
        }

        if (toOtherB.getLength() < shortestLine.getLength()) {
            shortestLine = toOtherB;
        }

        return shortestLine;
    }

    /**
     * Evaluate the shortest distance from the given point to this GameObject. If
     * the point is inside this GameObject's radius, the distance will be negative.
     * 
     * @param point the point to evaluate the distance to.
     * @return the shortest distance between the point and this GameObject.
     */
    public double getShortestDistance(Point2D point) {
        return getNearestPoint(point).distance(point) - radius;
    }

    /**
     * Evaluate the shortest distance between two non-crossed GameObjects. If the
     * lines defined by the GameObjects intersect, the distance will not be
     * accurate.
     * 
     * @param gameObject the GameObject to evaluate the distance to.
     * @return the shortest distance between the two GameObjects.
     */
    public double getShortestDistance(GameObject gameObject) {
        return getShortestLine(gameObject).getLength() - radius - gameObject.radius;
    }

    /**
     * Move the object by its current velocity multiplied by the given amount of
     * time in seconds.
     * 
     * @param deltaTime the amount of time by which to move the object, given in
     *                  seconds.
     */
    public void applyVelocity(double deltaTime) {
        move(getVelocity().multiply(deltaTime));
    }

    /**
     * Shift the entire GameObject by the given 2D vector.
     * 
     * @param offset the offset to add to both of this GameObject's endpoints.
     */
    public void move(Point2D offset) {
        setA(getA().add(offset));
        setB(getB().add(offset));
    }

    /**
     * Get the radius of this GameObject.
     * 
     * @return the radius of this GameObject.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Set the radius of this GameObject. This method throws an
     * IllegalArgumentException if the given radius is less than 0.
     * 
     * @param radius the new radius of this GameObject.
     */
    public void setRadius(double radius) {
        if (radius < 0) {
            throw new IllegalArgumentException("radius must greater than or equal to 0");
        }

        this.radius = radius;
    }

    /**
     * Get the current mass.
     * 
     * @return the current mass of this GameObject.
     */
    public double getMass() {
        return mass;
    }

    /**
     * Set the mass of this GameObject. This method throws an
     * IllegalArgumentException is the given mass is 0 or less.
     * 
     * @param mass the new mass value.
     */
    public void setMass(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("mass must be greater than 0");
        }

        this.mass = mass;
    }

    /**
     * Get the current velocity.
     * 
     * @return the current velocity of this GameObject.
     */
    public Point2D getVelocity() {
        return velocity;
    }

    /**
     * Set the velocity.
     * 
     * @param velocity the new velocity value.
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }
}
