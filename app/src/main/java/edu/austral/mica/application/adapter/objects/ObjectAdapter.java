package edu.austral.mica.application.adapter.objects;

import edu.austral.ingsis.starships.ui.ElementModel;
import edu.austral.mica.gameManage.interfaces.Movable;

import java.util.Map;

public interface ObjectAdapter {
    void adaptElement(Map<String, ElementModel> facade, Map<String, Movable> elements);
}
