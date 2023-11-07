package action.geometry;

import javafx.geometry.Point2D;

/**
 * 2D line segment that holds two 2D points.
 */
public class LineSegment2D {
    private Point2D a, b;

    private double length;

    /**
     * Initialize a new LineSegment with the given endpoints.
     * 
     * @param a the first point.
     * @param b the second point.
     */
    public LineSegment2D(Point2D a, Point2D b) {
        this.a = a;
        this.b = b;

        length = a.distance(b);
    }

    /**
     * Access the first endpoint of this LineSegment.
     * 
     * @return the first endpoint of this LineSegment.
     */
    public Point2D getA() {
        return a;
    }

    /**
     * Set the first endpoint of this LineSegment.
     * 
     * @param a the new first endpoint.
     */
    public void setA(Point2D a) {
        this.a = a;
        length = a.distance(b);
    }

    /**
     * Access the second endpoint of this LineSegment.
     * 
     * @return the second endpoint of this LineSegment.
     */
    public Point2D getB() {
        return b;
    }

    /**
     * Set the second endpoint of this LineSegment.
     * 
     * @param b the new second endpoint.
     */
    public void setB(Point2D b) {
        this.b = b;
        length = a.distance(b);
    }

    /**
     * Get the length of this LineSegment.
     * 
     * @return this LineSegment's length.
     */
    public double getLength() {
        return length;
    }

    /**
     * Get the direction vector of this LineSegment from A to B.
     * 
     * @return this LineSegment's direction.
     */
    public Point2D getDirection() {
        return b.subtract(a).normalize();
    }
}
