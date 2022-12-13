package edu.austral.mica.game.action;

import edu.austral.mica.game.action.actionsImp.*;

public class ActionMapper {
    public static Action getShipActionForDescription(String shipId, String description){
        return switch (description){
            case "accelerate" -> new AccelerateShipAction(shipId);
            case "brake" -> new StopShipAction(shipId);
            case "rotate_clockwise" -> new RotateShipClockwiseAction(shipId);
            case "rotate_counterclockwise" -> new RotateShipCounterclockwiseAction(shipId);
            case "shoot" -> new ShootAction(shipId);
            case "change_weapon" -> new ChangeWeaponAction(shipId);
            case "pause" -> new PauseGameAction();
            case "unpause" -> new UnpauseGameAction();
            case "save" -> new SaveGameStateAction();
            default -> new AccelerateShipAction(shipId);
        };
    }
}
