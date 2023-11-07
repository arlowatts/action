package action.movement;

import java.util.Collection;

import action.GameObject;
import javafx.geometry.Point2D;

/**
 * A MovementController stores information about how an object moves around in
 * the scene. A MovementController should be attached to a GameObject by the
 * provided methods.
 */
public interface MovementController {
    /**
     * Given the context of all other MovementControllers in the scene, apply the
     * appropriate movement to the attached GameObject. If there is no attached
     * GameObject, nothing will be done.
     * 
     * @param movementControllers a Collection of all other MovementControllers in
     *                            the scene.
     */
    public void resolveCollisions(Collection<MovementController> movementControllers);

    /**
     * Move the object by its velocity multiplied by the given amount of time in seconds.
     * 
     * @param deltaTime the amount of time by which to move the object, given in seconds.
     */
    public void applyMovement(double deltaTime);

    /**
     * Access this MovementController's attached GameObject.
     * 
     * @return the attached GameObject.
     */
    public GameObject getGameObject();

    /**
     * Attach a GameObject to this MovementController.
     * 
     * @param gameObject the GameObject to attach to this MovementContoller.
     */
    public void setGameObject(GameObject gameObject);

    /**
     * Get the current velocity.
     * 
     * @return the current velocity of this MovementController's attached
     *         GameObject.
     */
    public Point2D getVelocity();

    /**
     * Set the velocity.
     * 
     * @param velocity the new velocity value.
     */
    public void setVelocity(Point2D velocity);

    /**
     * Get the current mass.
     * 
     * @return the current mass of this MovementController's attached GameObject.
     */
    public double getMass();

    /**
     * Set the mass of this MovementController's attached GameObject. This method
     * throws an IllegalArgumentException is the given mass is 0 or less.
     * 
     * @param mass the new mass value.
     */
    public void setMass(double mass);
}
