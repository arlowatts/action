package action.objects;

import java.util.Collection;

import action.geometry.LineSegment2D;
import javafx.geometry.Point2D;

/**
 * A FloatingObject represents a GameObject that can move around the scene and
 * collide with other GameObjects. You can give a FloatingObject infinite mass
 * (Double.POSITIVE_INFINITY to be precise) and it will behave just like a very
 * very heavy object.
 */
public class FloatingObject extends GameObject {
    /**
     * Initialize a new FloatingObject with the given endpoints, radius, and mass.
     * 
     * @param a      the first endpoint of the GameObject.
     * @param b      the second endpoint of the GameObject.
     * @param radius the radius of the GameObject.
     * @param mass   the mass of the GameObject.
     */
    public FloatingObject(Point2D a, Point2D b, double radius, double mass) {
        super(a, b, radius, mass);
    }

    public void resolveCollisions(Collection<GameObject> gameObjects) {
        for (GameObject other : gameObjects) {
            if (other == this || other == null)
                continue;

            // Get the shortest line between the two game objects in the interaction
            LineSegment2D shortestLine = getShortestLine(other);

            // If they are not touching, do nothing
            if (shortestLine.getLength() > getRadius() + other.getRadius())
                continue;

            // Compute the direction of force between the two interacting objects
            Point2D forceDirection = shortestLine.getDirection();

            // Compute the reletive speed of the two interacting objects in the direction
            // of the collision
            double relativeSpeed = getVelocity().subtract(other.getVelocity()).dotProduct(forceDirection);

            // If the objects are moving apart already, do not apply any collision forces
            if (relativeSpeed <= 0)
                continue;

            double thisMass = getMass();
            double otherMass = other.getMass();

            // Adjust to avoid errors if an object has infinite mass
            if (thisMass == otherMass) {
                thisMass = 1;
                otherMass = 1;
            } else if (thisMass == Double.POSITIVE_INFINITY) {
                thisMass = 1;
                otherMass = 0;
            } else if (otherMass == Double.POSITIVE_INFINITY) {
                thisMass = 0;
                otherMass = 1;
            }

            double inverseTotalMass = 1 / (thisMass + otherMass);

            // u1 and u2 are the initial velocities in the direction of the applied force
            Point2D u1 = forceDirection.multiply(getVelocity().dotProduct(forceDirection));
            Point2D u2 = forceDirection.multiply(other.getVelocity().dotProduct(forceDirection));

            // v1 and v2 are the final velocities in the direction of the applied force
            Point2D v1 = u1.multiply((thisMass - otherMass) * inverseTotalMass)
                    .add(u2.multiply(2 * otherMass * inverseTotalMass));

            Point2D v2 = u2.multiply((otherMass - thisMass) * inverseTotalMass)
                    .add(u1.multiply(2 * thisMass * inverseTotalMass));

            // The actual final velocity can be found by subtracting the initial velocity in
            // the direction of force and adding the final velocity in the same direction
            setVelocity(getVelocity().subtract(u1).add(v1));
            other.setVelocity(other.getVelocity().subtract(u2).add(v2));
        }
    }
}
