package action.movement;

import action.objects.GameObject;

public interface MovementController {
    /**
     * Apply this MovementController's movement to the attached GameObject by an
     * amount determined by the deltaTime parameter, giving the length of the
     * current frame.
     * 
     * @param deltaTime the length of the the current frame for which to apply this
     *                  movement, in seconds.
     */
    public void applyMovement(double deltaTime);

    /**
     * Access this MovementController's attached GameObject.
     * 
     * @return the attached GameObject.
     */
    public GameObject getGameObject();

    /**
     * Update this MovementController's attached GameObject.
     * 
     * @param gameObject the new GameObject.
     */
    public void setGameObject(GameObject gameObject);
}
