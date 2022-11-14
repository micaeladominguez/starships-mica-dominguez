package edu.austral.mica.application;
import edu.austral.ingsis.starships.ui.*;
import edu.austral.mica.game.Game;
import edu.austral.mica.interfaces.Movable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ApplicationStarships extends Application {
    double heightAsteroid = 30.0;
    double widthAsteroid = 30.0;

    ImageRef starshipRef = new ImageRef("spaceship", 70.0, 70.0);
    ImageRef asteroidRef = new ImageRef("asteroid", heightAsteroid, widthAsteroid);


    private CachedImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());
    private ElementsViewFacade facade = new ElementsViewFacade(imageResolver);
    private KeyTracker keyTracker = new KeyTracker();

    ObservableGame observableGame;


    @Override
    public void start(Stage primaryStage) throws Exception {
        observableGame = new ObservableGame();
        System.out.println("Elements facade  :" + facade.getElements());
        observableGame.observe(new UIAdapter(this.facade.getElements()));
        observableGame.setGame(observableGame.game.defineFirstMap(800, 800));
        Adapter.adaptElement(facade.getElements(), observableGame.game.getElements());
        facade.getTimeListenable().addEventListener(new TimeListener(observableGame,800, 800));
        facade.getCollisionsListenable().addEventListener(new CollisionListener(observableGame));
        //TODO : ME FALTA EL LISTENER DEL STARSHIPS
       // keyTracker.getKeyPressedListenable().addEventListener(new KeyPressedListener(starship));
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
