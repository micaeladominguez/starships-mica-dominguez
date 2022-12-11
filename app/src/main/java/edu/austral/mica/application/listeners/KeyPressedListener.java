package edu.austral.mica.application.listeners;

import edu.austral.ingsis.starships.ui.EventListener;
import edu.austral.ingsis.starships.ui.KeyPressed;
import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.game.action.Action;
import edu.austral.mica.game.action.ActionMapper;
import edu.austral.mica.game.game.Game;
import edu.austral.mica.persistence.Constants;
import javafx.scene.input.KeyCode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class KeyPressedListener implements EventListener<KeyPressed> {
    Map<KeyCode, Action> keyMap = initialize();

    ObservableGame observableGame;

    public KeyPressedListener(ObservableGame observableGame) {
        this.observableGame = observableGame;
    }

    private Map<KeyCode, Action> initialize() {
        HashMap<KeyCode, Action> map = new HashMap<>();
        try {
           int id = 1;
           Iterator it = readJsonAndGetIterator();
           while (it.hasNext()) {
               JSONObject binding = (JSONObject) it.next();
               createActionAndAdd(binding, ("ship"+id), map);
               id++;
           }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private void createActionAndAdd(JSONObject binding, String shipId, HashMap<KeyCode, Action> map) {
        Set<String> existingActions = (Set<String>) binding.keySet();
        for (String existingAction : existingActions) {
            Action correspondingAction = ActionMapper.getShipActionForDescription(shipId, existingAction);
            map.put(KeyCode.valueOf((String) binding.get(existingAction)), correspondingAction);
        }
    }

    private Iterator readJsonAndGetIterator() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader(Constants.KEYBINDINGS_FILE_PATH));
        JSONArray  keyBindings = (JSONArray) obj ;
        Iterator it = keyBindings.iterator();
        return it;
    }





    @Override
    public void handle(KeyPressed event) {
        if(observableGame != null) {
            if(keyMap.containsKey(event.getKey())){
                Game newGame= keyMap.get(event.getKey()).runAction(observableGame);
                observableGame.setGame(newGame);
            }
        }
    }

}
