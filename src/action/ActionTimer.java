package action;

import action.movement.MovementController;

import java.util.Collection;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

/**
 * The ActionTimer class provides an AnimationTimer implementation that handles
 * all GameObject collisions and invokes the methods of their
 * MovementControllers to move them.
 */
public class ActionTimer extends AnimationTimer {
    private long previousNow = 0;

    private Collection<MovementController> movementControllers;

    private GraphicsContext context;

    /**
     * Update the Collection of MovementControllers this ActionTimer executes each
     * frame.
     * 
     * @param movementControllers the new Collection of MovementControllers to
     *                            override the previous value.
     */
    public void setObjects(Collection<MovementController> movementControllers) {
        this.movementControllers = movementControllers;
    }

    /**
     * Set the GraphicsContext this ActionTimer draws each frame to.
     * 
     * @param context the new GraphicsContext.
     */
    public void setGraphicsContext(GraphicsContext context) {
        this.context = context;
    }

    public void handle(long now) {
        if (previousNow == 0)
            previousNow = now;

        double deltaTime = (now - previousNow) / 1000000000.0;
        previousNow = now;

        context.clearRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());

        for (MovementController movementController : movementControllers) {
            movementController.resolveCollisions(movementControllers);
        }

        for (MovementController movementController : movementControllers) {
            movementController.applyMovement(deltaTime);
            movementController.getGameObject().draw(context);
        }
    }
}
