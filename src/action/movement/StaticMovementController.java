package action.movement;

import java.util.Collection;

import action.GameObject;

/**
 * An implementation of the MovementController interface that never moves the
 * attached GameObject. The GameObject can still interact physically with
 * moveable GameObjects in the scene.
 */
public class StaticMovementController implements MovementController {
    private GameObject gameObject;

    /**
     * Initialize a new StaticMovementController attached to the given GameObject.
     * 
     * @param gameObject the GameObject to which this StaticMovementController will
     *                   be attached.
     */
    public StaticMovementController(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * This method does nothing as the StaticMovementController is meant for
     * immoveable objects.
     */
    public void applyMovement(Collection<MovementController> movementControllers) {
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
