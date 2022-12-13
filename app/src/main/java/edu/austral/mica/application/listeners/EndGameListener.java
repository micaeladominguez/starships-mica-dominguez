package edu.austral.mica.application.listeners;

import edu.austral.ingsis.starships.ui.EventListener;
import edu.austral.ingsis.starships.ui.TimePassed;
import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.gameManage.ship.Ship;
import javafx.scene.text.Text;

public class EndGameListener implements EventListener<TimePassed> {
    ObservableGame observableGame;
    Text finalText;
    public EndGameListener(ObservableGame observableGame, Text finalText) {
        this.observableGame = observableGame;
        this.finalText = finalText;
    }

    @Override
    public void handle(TimePassed timePassed) {
        Ship winner = observableGame.getGame().checkForFinishedGame();
        if (winner != null){
            String id = winner.getId();
            finalText.setText("The game has ended! The winner is: " + id);
        }
    }
}
