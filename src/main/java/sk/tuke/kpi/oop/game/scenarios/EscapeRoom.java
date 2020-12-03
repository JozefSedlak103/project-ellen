package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.openables.Door;

public class EscapeRoom implements SceneListener {
    private Ripley ripley;

    @Override
    public void sceneCreated(@NotNull Scene scene) {
        //rip me neznam.
    }


    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = scene.getFirstActorByType(Ripley.class);
        MovableController controller = new MovableController(ripley);
        Disposable disMovController = scene.getInput().registerListener(controller);
        KeeperController keeperController = new KeeperController(ripley);
        Disposable disKeepController = scene.getInput().registerListener(keeperController);
        ShooterController shooterController = new ShooterController(ripley);
        Disposable disShootController = scene.getInput().registerListener(shooterController);

        Hammer hammer = new Hammer();
        ripley.getBackpack().add(hammer);

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ripley -> disKeepController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ripley -> disMovController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ripley -> disShootController.dispose());
    }
    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        ripley.showRipleyState(scene);
        scene.follow(ripley);
    }

    public static class Factory implements ActorFactory {
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if (name.equals("ellen")) {
                return new Ripley();
            }
            if (name.equals("front door")) {
                return new Door("front door",Door.Orientation.VERTICAL);
            }
            if(name.equals("exit door")){
                return new Door("exit door",Door.Orientation.VERTICAL);
            }
            if(name.equals("back door")){
                return new Door("back door",Door.Orientation.HORIZONTAL);
            }
            if (name.equals("energy")) {
                return new Energy();
            }
            if (name.equals("ammo")) {
                return new Ammo();
            }
            if (name.equals("alien")) {
                return new Alien();
            }
            if (name.equals("alien mother")) {
                return new AlienMother();
            }
            return null;
        }
    }
}
