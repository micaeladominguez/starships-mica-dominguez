package edu.austral.mica.gameManage.collision;


import edu.austral.mica.gameManage.interfaces.Movable;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public interface Collider <T extends Collider<T>> {
    @NotNull
    Shape getShape();

    Movable handleCollisionWith(@NotNull T collider);

    Movable getModel();
}
