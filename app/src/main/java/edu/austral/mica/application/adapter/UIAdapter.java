package edu.austral.mica.application.adapter;

import edu.austral.ingsis.starships.ui.ElementModel;
import edu.austral.mica.application.GameObserver;
import edu.austral.mica.application.adapter.Adapter;
import edu.austral.mica.game.game.Game;

import java.util.Map;

public class UIAdapter implements GameObserver {
    Map<String, ElementModel> elementsViewFacade;

    public UIAdapter(Map<String, ElementModel> elementsViewFacade) {
        this.elementsViewFacade = elementsViewFacade;

    }

    @Override
    public void gameChanged(Game game) {
        Adapter.adaptElement(elementsViewFacade,game.getElements());
    }
}
