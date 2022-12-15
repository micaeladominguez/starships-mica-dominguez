package edu.austral.mica.application.initializer;

import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.game.Game;
import edu.austral.mica.game.game.LiveGame;

public class ApplicationInitializer {
    public static Game selectGameStart(GameInitialization selectedStartMode, ObservableGame observableGame) {
        if (selectedStartMode == GameInitialization.NEW){
            return startNewGame(observableGame);
        }
        if(selectedStartMode == GameInitialization.LOAD){
            return startLoadedGame(observableGame);
        }
        return null;
    }

    private static Game startNewGame(ObservableGame observableGame) {
        return observableGame.getGame().createNewGame();
    }

    private static Game startLoadedGame(ObservableGame observableGame) {
        return observableGame.getGame().loadGame();
    }
}
