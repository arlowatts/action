package action.movement;

import action.GameObject;
import action.geometry.LineSegment2D;

import java.util.Collection;

import javafx.geometry.Point2D;

/**
 * An implementation of the MovementController interface that allows the
 * attached GameObject to drift freely in the scene. The GameObject can interact
 * with other GameObjects and will bounce off of them.
 */
public class FloatingMovementController implements MovementController {
    private GameObject gameObject;

    private Point2D velocity;

    private double mass;

    /**
     * Initialize a new FloatingMovementController attached to the given GameObject.
     * 
     * @param gameObject the GameObject to which this FloatingMovementController
     *                   will be attached.
     */
    public FloatingMovementController(GameObject gameObject, double mass) {
        this.gameObject = gameObject;
        this.velocity = Point2D.ZERO;
        this.mass = mass;
    }

    /**
     * Check for any collisions with other GameObjects attached to the given
     * MovementControllers and then, in the case of a collision, accelerate the
     * attached GameObject away from the collision.
     */
    public void resolveCollisions(Collection<MovementController> movementControllers) {
        if (gameObject == null)
            return;

        for (MovementController other : movementControllers) {
            if (this == other)
                continue;

            if (other.getGameObject() == null)
                continue;

            // Get the shortest line between the two game objects in the interaction
            LineSegment2D shortestLine = gameObject.getShortestLine(other.getGameObject());

            // If they are not touching, do nothing
            if (shortestLine.getLength() > gameObject.getRadius() + other.getGameObject().getRadius())
                continue;

            // Compute the direction of force between the two interacting objects
            Point2D forceDirection = shortestLine.getDirection();

            // Compute the reletive speed of the two interacting objects in the direction
            // of the collision
            double relativeSpeed = velocity.subtract(other.getVelocity()).dotProduct(forceDirection);

            // If the objects are moving apart, do not apply any collision forces
            if (relativeSpeed < 0)
                continue;

            double thisMass, otherMass;

            // If the other object reports negative mass, treat it like infinite mass
            if (other.getMass() <= 0) {
                thisMass = 0;
                otherMass = 1;
            } else {
                thisMass = mass;
                otherMass = other.getMass();
            }

            double inverseTotalMass = 1 / (thisMass + otherMass);

            // u1 and u2 are the initial velocities in the direction of the applied force
            Point2D u1 = forceDirection.multiply(velocity.dotProduct(forceDirection));
            Point2D u2 = forceDirection.multiply(other.getVelocity().dotProduct(forceDirection));

            // v1 and v2 are the final velocities in the direction of the applied force
            Point2D v1 = u1.multiply((thisMass - otherMass) * inverseTotalMass)
                    .add(u2.multiply(2 * otherMass * inverseTotalMass));

            Point2D v2 = u2.multiply((otherMass - thisMass) * inverseTotalMass)
                    .add(u1.multiply(2 * thisMass * inverseTotalMass));

            // The actual final velocity can be found by subtracting the initial velocity in
            // the direction of force and adding the final velocity in the same direction
            setVelocity(velocity.subtract(u1).add(v1));
            other.setVelocity(other.getVelocity().subtract(u2).add(v2));
        }
    }

    public void applyMovement(double deltaTime) {
        gameObject.move(velocity.multiply(deltaTime));
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Point2D getVelocity() {
        return velocity;
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        if (mass <= 0) {
            throw new IllegalArgumentException("mass must be greater than 0.");
        }

        this.mass = mass;
    }
}
