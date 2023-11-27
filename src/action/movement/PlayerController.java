package action.movement;

import action.objects.GameObject;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerController implements MovementController {
    private GameObject gameObject;

    private double speed;

    private boolean up, down, left, right;

    /**
     * Create a new PlayerController with the specified GameObject, speed, scene,
     * and keys.
     * 
     * @param gameObject the GameObject to apply the movement to.
     * @param speed      the speed of the movement, in pixels per second squared.
     * @param scene      the current scene in which to check for key press events.
     * @param upKey      the key associated with upward movement.
     * @param downKey    the key associated with downward movement.
     * @param leftKey    the key associated with leftward movement.
     * @param rightKey   the key associated with rightward movement.
     */
    public PlayerController(GameObject gameObject, double speed, Scene scene, KeyCode upKey, KeyCode downKey,
            KeyCode leftKey, KeyCode rightKey) {
        this.gameObject = gameObject;
        this.speed = speed;

        EventHandler<KeyEvent> keyPress = new EventHandler<>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode eventCode = event.getCode();

                if (eventCode == upKey) {
                    up = true;
                } else if (eventCode == downKey) {
                    down = true;
                } else if (eventCode == leftKey) {
                    left = true;
                } else if (eventCode == rightKey) {
                    right = true;
                }
            }
        };

        EventHandler<KeyEvent> keyRelease = new EventHandler<>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode eventCode = event.getCode();

                if (eventCode == upKey) {
                    up = false;
                } else if (eventCode == downKey) {
                    down = false;
                } else if (eventCode == leftKey) {
                    left = false;
                } else if (eventCode == rightKey) {
                    right = false;
                }
            }
        };

        scene.setOnKeyPressed(keyPress);
        scene.setOnKeyReleased(keyRelease);
    }

    public void applyMovement(double deltaTime) {
        double totalUp = ((up ? 1 : 0) - (down ? 1 : 0)) * speed * deltaTime;
        double totalRight = ((right ? 1 : 0) - (left ? 1 : 0)) * speed * deltaTime;

        // totalUp must be negated because lower y coordinates appear higher on the
        // screen
        Point2D newVelocity = gameObject.getVelocity().add(totalRight, -totalUp);

        gameObject.setVelocity(newVelocity);
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
