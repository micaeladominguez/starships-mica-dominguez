package edu.austral.mica.application;
import edu.austral.ingsis.starships.ui.*;
import edu.austral.mica.application.initializer.ApplicationInitializer;
import edu.austral.mica.application.initializer.GameInitialization;
import edu.austral.mica.application.listeners.CollisionListener;
import edu.austral.mica.application.listeners.KeyPressedListener;
import edu.austral.mica.application.listeners.TimeListener;
import edu.austral.mica.application.adapter.Adapter;
import edu.austral.mica.application.adapter.UIAdapter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        gameScene = new Scene(facade.getView());
        addCssToFacade();
        keyTracker.setScene(gameScene);
        primaryStage.setScene(gameScene);
        primaryStage.setHeight(800.0);
        primaryStage.setWidth(800.0);
    }

    private void addCssToFacade() {
        facade.getView().setId("facade");
        gameScene.getStylesheets().add(requireNonNull(getClass().getClassLoader().getResource("gameStyles.css")).toString());
    }

    private void addListeners() {
        facade.getTimeListenable().addEventListener(new TimeListener(observableGame,800, 800));
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
