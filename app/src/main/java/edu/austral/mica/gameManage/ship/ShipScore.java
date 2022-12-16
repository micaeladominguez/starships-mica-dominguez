package edu.austral.mica.gameManage.ship;

public class ShipScore {
    private Ship ship;
    private Integer score;

    public ShipScore(Ship ship, Integer score) {
        this.ship = ship;
        this.score = score;
    }

    public Ship getShip() {
        return ship;
    }

    public Integer getScore() {
        return score;
    }
}
