package edu.austral.mica.application;
import edu.austral.mica.application.listeners.*;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import edu.austral.ingsis.starships.ui.*;
import edu.austral.mica.application.initializer.ApplicationInitializer;
import edu.austral.mica.application.initializer.GameInitialization;
import edu.austral.mica.application.adapter.Adapter;
import edu.austral.mica.application.adapter.UIAdapter;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.awt.*;

import static java.util.Objects.requireNonNull;

public class ApplicationStarships extends Application {

    private CachedImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());
    private ElementsViewFacade facade = new ElementsViewFacade(imageResolver);
    private KeyTracker keyTracker = new KeyTracker();
    Scene gameScene = new Scene(new StackPane());

    ObservableGame observableGame;


    @Override
    public void start(Stage primaryStage) throws Exception {
        setObservableAndAdapter();
        addListeners();
        setScene(primaryStage);
        startComponents(primaryStage);
    }

    private void startComponents(Stage primaryStage) {
        facade.start();
        keyTracker.start();
        primaryStage.show();
    }

    private void setScene(Stage primaryStage) {
        Parent root = facade.getView();
        root.setId("pane");
        StackPane pane = new StackPane();
        StackPane stats = addText();
        StackPane finalText = finalStat();
        pane.getChildren().addAll(root,stats,finalText);
        addCssToFacade(pane);
        Scene scene = new Scene(pane);
        keyTracker.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setHeight(800.0);
        primaryStage.setWidth(800.0);
    }

    private StackPane finalStat() {
        Text finalText = new Text();
        HBox finalDiv = getDiv(finalText,Pos.CENTER,30.0);
        StackPane stats = new StackPane();
        facade.getTimeListenable().addEventListener(new EndGameListener(observableGame,finalText));
        stats.getChildren().addAll(finalDiv);
        return stats;
    }

    private StackPane addText() {
        Text livesText = new Text();
        HBox livesDiv = getDiv(livesText, Pos.TOP_LEFT,20.0);
        Text scoresText = new Text();
        HBox scoresDiv = getDiv(scoresText, Pos.TOP_RIGHT,20.0);
        facade.getTimeListenable().addEventListener(new ScoreAndLivesListener(observableGame, livesText, scoresText));
        StackPane stats = new StackPane();
        stats.getChildren().addAll(livesDiv, scoresDiv);
        return stats;
    }

    private HBox getDiv(Text divText, Pos position , Double size){
        divText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, size));
        divText.setFill(Color.WHITE);
        divText.setStroke(Color.BLACK);
        divText.setStrokeWidth(1.0);
        HBox div = new HBox();
        div.setAlignment(position);
        div.getChildren().addAll(divText);
        div.setPadding( new Insets( 10, 10, 10, 10));
        return div;
    }

    private void addCssToFacade(StackPane pane) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("https://images5.alphacoders.com/633/633151.jpg"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, new BackgroundPosition(Side.LEFT, 0.0, true, Side.BOTTOM, 0.0, true),
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true));
        pane.setBackground(new Background(backgroundImage));
    }

    private void addListeners() {
        facade.getTimeListenable().addEventListener(new TimeListener(observableGame,1920, 1820));
        facade.getCollisionsListenable().addEventListener(new CollisionListener(observableGame));
        keyTracker.getKeyPressedListenable().addEventListener(new KeyPressedListener(observableGame));
    }

    private void setObservableAndAdapter() {
        observableGame = new ObservableGame(readQuantityOfPlayers());
        observableGame.observe(new UIAdapter(this.facade.getElements()));
        observableGame.setGame(ApplicationInitializer.selectGameStart(GameInitialization.NEW, observableGame));
        Adapter.adaptElement(facade.getElements(), observableGame.game.getElements());
    }

    private Integer readQuantityOfPlayers() {
        return WindowReader.readQuantityOfPlayers();
    }

    @Override
    public void stop() throws Exception {
        facade.stop();
        keyTracker.stop();
    }
}
