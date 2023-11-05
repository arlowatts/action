package action.movement;

import java.util.Collection;

import action.GameObject;

/**
 * An implementation of the MovementController interface that allows the
 * attached GameObject to drift freely in the scene. The GameObject can interact
 * with other GameObjects and will bounce off of them.
 */
public class FloatingMovementController implements MovementController {
    private GameObject gameObject;

    /**
     * Initialize a new FloatingMovementController attached to the given GameObject.
     * 
     * @param gameObject the GameObject to which this FloatingMovementController
     *                   will be attached.
     */
    public FloatingMovementController(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Check for any collisions with other GameObjects attached to the given
     * MovementControllers and then, in the case of a collision, accelerate the
     * attached GameObject away from the collision.
     */
    public void applyMovement(Collection<MovementController> movementControllers) {
        for (MovementController movementController : movementControllers) {
            
        }
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
