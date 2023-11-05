package action.movement;

import java.util.Collection;

import action.GameObject;

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
    public abstract void applyMovement(Collection<MovementController> movementControllers);

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
}
