package edu.austral.mica.application.adapter.objects;

import edu.austral.ingsis.starships.ui.ElementModel;
import edu.austral.mica.application.adapter.objects.ObjectAdapter;
import edu.austral.mica.gameManage.interfaces.Movable;

import java.util.Map;
import edu.austral.ingsis.starships.ui.ImageRef;
import edu.austral.ingsis.starships.ui.ElementColliderType;
import org.jetbrains.annotations.NotNull;


public class BulletAdapter implements ObjectAdapter {

    public void adaptElement(Map<String, ElementModel> facade, Map<String, Movable> elements) {
        elements.forEach((k, v) -> {
            if(k != null && v != null && k.startsWith("proj")){
                    facade.put(k, getElementModel(k, v));
            }
        });
    }

    @NotNull
    private static ElementModel getElementModel(String k, Movable v) {
        return new ElementModel(k,
                v.getPosition().getX(), v.getPosition().getY(),
                AdapterConstants.heightProjectile, AdapterConstants.widthProjectile, v.getDirection().getAngle(),
                ElementColliderType.Elliptical, getProjectileImage(v));
    }

    private static ImageRef getProjectileImage(Movable movable) {
        return AdapterConstants.laser;
    }
}
