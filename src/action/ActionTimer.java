package action;

import action.movement.MovementController;
import action.objects.GameObject;

import java.util.Collection;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * The ActionTimer class provides an AnimationTimer implementation that handles
 * all GameObject collisions and invokes their methods to move them.
 */
public class ActionTimer extends AnimationTimer {
    private long previousNow = 0;

    private Collection<GameObject> gameObjects;
    private Collection<MovementController> movementControllers;

    private GraphicsContext context;

    public void handle(long now) {
        if (previousNow == 0)
            previousNow = now;

        double deltaTime = (now - previousNow) / 1000000000.0;
        previousNow = now;

        context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());

        // Apply all MovementControllers
        for (MovementController movementController : movementControllers) {
            movementController.applyMovement(deltaTime);
        }

        // Resolve all collisions in the scene
        for (GameObject gameObject : gameObjects) {
            gameObject.resolveCollisions(gameObjects);
        }

        // Move and draw the objects
        for (GameObject gameObject : gameObjects) {
            gameObject.applyVelocity(deltaTime);
            gameObject.draw(context);
        }
    }

    /**
     * Access the Collection of GameObjects referenced by this ActionTimer.
     * 
     * @return the Collection of GameObjects.
     */
    public Collection<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Update the Collection of GameObjects referenced by this ActionTimer.
     * 
     * @param gameObjects the new Collection of GameObjects.
     */
    public void setGameObjects(Collection<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Access the Collection of MovementControllers referenced by this ActionTimer.
     * 
     * @return the Collection of MovementControllers.
     */
    public Collection<MovementController> getMovementControllers() {
        return movementControllers;
    }

    /**
     * Update the Collection of MovementControllers referenced by this ActionTimer.
     * 
     * @param gameObjects the new Collection of MovementControllers.
     */
    public void setMovementControllers(Collection<MovementController> movementControllers) {
        this.movementControllers = movementControllers;
    }

    /**
     * Access the GraphicsContext referenced by this ActionTimer.
     * 
     * @return the GraphicsContext.
     */
    public GraphicsContext getContext() {
        return context;
    }

    /**
     * Update the GraphicsContext referenced by this ActionTimer.
     * 
     * @param context the new GraphicsContext.
     */
    public void setContext(GraphicsContext context) {
        this.context = context;
    }
}
