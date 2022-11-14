package edu.austral.mica.collision;


import edu.austral.mica.interfaces.Movable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public interface Collider <T extends Collider<T>> {
    @NotNull
    Shape getShape();

    void handleCollisionWith(@NotNull T collider);

    Movable getModel();
}
