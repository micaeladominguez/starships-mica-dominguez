package edu.austral.mica.application.adapter;

import edu.austral.ingsis.starships.ui.ElementColliderType;
import edu.austral.ingsis.starships.ui.ElementModel;
import edu.austral.ingsis.starships.ui.ImageRef;
import edu.austral.mica.application.adapter.objects.AsteroidAdapter;
import edu.austral.mica.application.adapter.objects.BulletAdapter;
import edu.austral.mica.application.adapter.objects.ObjectAdapter;
import edu.austral.mica.application.adapter.objects.ShipAdapter;
import edu.austral.mica.gameManage.interfaces.Movable;

import java.util.ArrayList;
import java.util.Map;

public class Adapter {
    private static ArrayList<ObjectAdapter> objectAdapters = addAdapters();

    private static ArrayList<ObjectAdapter> addAdapters() {
        ArrayList<ObjectAdapter> adapters = new ArrayList<>();
        adapters.add(new BulletAdapter());
        adapters.add(new AsteroidAdapter());
        adapters.add(new ShipAdapter());
        return adapters;
    }

    public static void adaptElement(Map<String, ElementModel> facade, Map<String, Movable> elements){
        facade.clear();
        for (ObjectAdapter s : objectAdapters) {
            s.adaptElement(facade, elements);
        }
    }
}
