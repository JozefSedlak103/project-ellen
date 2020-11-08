package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.MovableController;

public class FirstSteps implements SceneListener {
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = new Ripley();
        scene.addActor(ripley,0,0);
        MovableController movableController = new MovableController(ripley);
        Input input = scene.getInput();

        input.registerListener(movableController);


    }

// pokracovat ulohou 3.8, nejakym sposobom treba pohnut ripleyovou
}
