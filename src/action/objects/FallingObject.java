package action.objects;

import javafx.geometry.Point2D;

/**
 * The FallingObject class extends the FloatingObject class and adds the ability
 * to set a constant "gravity" that applies acceleration to the object every
 * frame. This acceleration can be in any direction and can vary between
 * objects.
 */
public class FallingObject extends FloatingObject {
    private Point2D acceleration;

    /**
     * Initialize a new FallingObject with the given endpoints, radius, and mass.
     * 
     * @param a      the first endpoint of the GameObject.
     * @param b      the second endpoint of the GameObject.
     * @param radius the radius of the GameObject.
     * @param mass   the mass of the GameObject.
     */
    public FallingObject(Point2D a, Point2D b, double radius, double mass) {
        super(a, b, radius, mass);
    }

    /**
     * Adjust the current velocity by this FallingObject's acceleration, then move
     * the object by its current velocity multiplied by the given amount of time in
     * seconds.
     * 
     * @param deltaTime the amount of time by which to move the object, given in
     *                  seconds.
     */
    @Override
    public void applyVelocity(double deltaTime) {
        setVelocity(getVelocity().add(acceleration.multiply(deltaTime)));
        super.applyVelocity(deltaTime);
    }

    /**
     * Access the current acceleration value.
     * 
     * @return the current acceleration.
     */
    public Point2D getAcceleration() {
        return acceleration;
    }

    /**
     * Update the acceletation value.
     * 
     * @param acceleration the new acceleration value.
     */
    public void setAcceleration(Point2D acceleration) {
        this.acceleration = acceleration;
    }
}
