package edu.austral.mica.application.adapter.objects;

import edu.austral.ingsis.starships.ui.ElementModel;
import edu.austral.ingsis.starships.ui.ImageRef;
import edu.austral.mica.application.adapter.objects.ObjectAdapter;
import edu.austral.mica.gameManage.interfaces.Movable;
import edu.austral.ingsis.starships.ui.ElementColliderType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ShipAdapter implements ObjectAdapter {
    public void adaptElement(Map<String, ElementModel> facade, Map<String, Movable> elements) {
        elements.forEach((k, v) -> {
            if(k != null && v != null && k.startsWith("ship")){
                    facade.put(k, getElementModel(k, v));
            }
        });
    }

    @NotNull
    private static ElementModel getElementModel(String k, Movable v) {
        ImageRef im = getImageRef(k);
        return new ElementModel(k,
                v.getPosition().getX(), v.getPosition().getY(),
                AdapterConstants.heightShip, AdapterConstants.widthShip, v.getDirection().getAngle(),
                ElementColliderType.Triangular, im);
    }

    private static ImageRef getImageRef(String k) {
        ImageRef im;
        if(k.equals("ship1RED")){
            im = AdapterConstants.ship1RED;
        }else if(k.equals("ship2BLUE")){
            im = AdapterConstants.ship2BLUE;
        }else{
            im = AdapterConstants.defaultShipRef;
        }
        return im;
    }
}
