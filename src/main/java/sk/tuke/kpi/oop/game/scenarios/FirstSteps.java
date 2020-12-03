package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

public class FirstSteps implements SceneListener {

    private Ripley ripley = new Ripley();
    private Energy energy;
    private Ammo ammo;
    private boolean enebool = true;
    private boolean ammobool = true;


    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        scene.addActor(ripley,0,0);
        energy = new Energy();
        ammo = new Ammo();
        //ripley.setAmmo(30);
        MovableController movableController = new MovableController(ripley);
        Input input = scene.getInput();

        input.registerListener(movableController);

        KeeperController keeperController = new KeeperController(ripley);
        input.registerListener(keeperController);

        Hammer hammer = new Hammer();
        FireExtinguisher fireExtinguisher = new FireExtinguisher();
        Wrench wrench = new Wrench();
        scene.addActor(wrench, 50, 0);
        scene.addActor(fireExtinguisher, -50, 20);


    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        if(ripley == null) return;
        int energyValue = ripley.getEnergy();
        //int ammoValue = ripley.getAmmo();
        scene.getGame().getOverlay().drawText("Energy: " + String.valueOf(energyValue), 150, yTextPos);
        //scene.getGame().getOverlay().drawText("Ammo: " + String.valueOf(ammoValue), 350, yTextPos);

        if(ripley.intersects(energy)){
            if(enebool){
                scene.removeActor(energy);
                new Use<>(energy).scheduleFor(ripley);
            }
            enebool = false;
        }
        if(ripley.intersects(ammo)){
            if(ammobool) {
                scene.removeActor(ammo);
                new Use<>(ammo).scheduleFor(ripley);
            }
            ammobool = false;
        }
        scene.getGame().pushActorContainer(ripley.getBackpack());
    }

    // pokracovat ulohou 3.8, nejakym sposobom treba pohnut ripleyovou
}
