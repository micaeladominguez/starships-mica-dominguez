package main.collision;


import main.interfaces.Movable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public interface Collider <T extends Collider<T>> {
    @NotNull
    Shape getShape();

    void handleCollisionWith(@NotNull T collider);

    Movable getModel();
}
