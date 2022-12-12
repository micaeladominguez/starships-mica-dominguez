package edu.austral.mica.application;
import edu.austral.ingsis.starships.ui.*;
import edu.austral.mica.application.initializer.ApplicationInitializer;
import edu.austral.mica.application.initializer.GameInitialization;
import edu.austral.mica.application.listeners.CollisionListener;
import edu.austral.mica.application.listeners.KeyPressedListener;
import edu.austral.mica.application.listeners.TimeListener;
import edu.austral.mica.application.adapter.Adapter;
import edu.austral.mica.application.adapter.UIAdapter;
import edu.austral.mica.game.game.GameInitializer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationStarships extends Application {
    double heightAsteroid = 30.0;
    double widthAsteroid = 30.0;

    private CachedImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());
    private ElementsViewFacade facade = new ElementsViewFacade(imageResolver);
    private KeyTracker keyTracker = new KeyTracker();

    ObservableGame observableGame;


    @Override
    public void start(Stage primaryStage) throws Exception {
        observableGame = new ObservableGame();
        observableGame.observe(new UIAdapter(this.facade.getElements()));
        observableGame.setGame(ApplicationInitializer.selectGameStart(GameInitialization.NEW, observableGame));
        Adapter.adaptElement(facade.getElements(), observableGame.game.getElements());
        //TODO: descomponer en metodos, sacar lo que es fijo a lo que agarro en el archivo
        facade.getTimeListenable().addEventListener(new TimeListener(observableGame,800, 800));
        facade.getCollisionsListenable().addEventListener(new CollisionListener(observableGame));
        keyTracker.getKeyPressedListenable().addEventListener(new KeyPressedListener(observableGame));
        Scene scene = new Scene(facade.getView());
        keyTracker.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.setHeight(800.0);
        primaryStage.setWidth(800.0);
        facade.start();
        keyTracker.start();
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        facade.stop();
        keyTracker.stop();
    }


}
