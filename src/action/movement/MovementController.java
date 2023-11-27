package action.movement;

import action.objects.GameObject;

public interface MovementController {
    public void applyMovement(double deltaTime);

    public GameObject getGameObject();

    public void setGameObject(GameObject gameObject);
}
