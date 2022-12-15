package edu.austral.mica.application.listeners;

import edu.austral.ingsis.starships.ui.ElementsViewFacade;
import edu.austral.ingsis.starships.ui.EventListener;
import edu.austral.ingsis.starships.ui.KeyTracker;
import edu.austral.ingsis.starships.ui.TimePassed;
import edu.austral.mica.application.ObservableGame;
import edu.austral.mica.application.WindowReader;
import edu.austral.mica.gameManage.ship.Ship;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndGameListener implements EventListener<TimePassed> {
    ObservableGame observableGame;
    KeyTracker keyTracker;
    ElementsViewFacade facade;
    Stage primaryStage;
    Scene gameScene;

    Text finalText;
    public EndGameListener(ObservableGame observableGame, Text finalText, KeyTracker keyTracker, Stage primaryStage, ElementsViewFacade facade) {
        this.observableGame = observableGame;
        this.finalText = finalText;
        this.keyTracker= keyTracker;
        this.primaryStage = primaryStage;
        this.facade = facade;
    }

    @Override
    public void handle(TimePassed timePassed) {
        Ship winner = observableGame.getGame().checkForFinishedGame();
        if (winner != null){
            String id = winner.getId();
            facade.getElements().clear();
            finalText.setText("The game has ended! The winner is: " + Utils.getColor(id));
            setScene();
        }
    }

    private void addCssToFacade(StackPane pane) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://upload.wikimedia.org/wikipedia/commons/6/68/Solid_black.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0.0, true, Side.BOTTOM, 0.0, true),
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        pane.setBackground(new Background(backgroundImage));
    }

    private void setScene() {
        Parent root = facade.getView();
        root.setId("pane");
        StackPane pane = new StackPane();
        pane.getChildren().addAll(root, finalText);
        addCssToFacade(pane);
        gameScene = new Scene(pane);
        keyTracker.setScene(gameScene);
        primaryStage.setScene(gameScene);
        primaryStage.setHeight(WindowReader.readHeight());
        primaryStage.setWidth(WindowReader.readWidth());
    }
}
