package edu.austral.mica.application.listeners;

import edu.austral.ingsis.starships.ui.EventListener;
import edu.austral.ingsis.starships.ui.TimePassed;
import edu.austral.mica.application.ObservableGame;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Map;

public class ScoreAndLivesListener implements EventListener<TimePassed> {
    ObservableGame observableGame;
    Text livesText;
    Text scoresText;
    public ScoreAndLivesListener(ObservableGame observableGame, Text livesText, Text scoresText) {
        this.observableGame = observableGame;
        this.livesText = livesText;
        this.scoresText = scoresText;
    }

    @Override
    public void handle(TimePassed timePassed) {
        Map<String, Integer> scoresByShip = observableGame.getGame().getScoresForShip();
        Map<String, Integer> livesByShip = observableGame.getGame().getLivesForShip();
        ArrayList<String> arrS = new ArrayList<>();
        scoresByShip.forEach((k,v) ->{
            String s = k + ":" + v;
            arrS.add(s);
        });
        scoresText.setText(converToString(arrS));

        arrS.clear();
        livesByShip.forEach((k,v) ->{
            String s = k + ":" + v;
            arrS.add(s);
        });
        livesText.setText(converToString(arrS));
    }

    private String converToString(ArrayList<String> scores) {
        String s = " ";
        for (String score : scores) {
            s += score;
        }
        return s;
    }
}
