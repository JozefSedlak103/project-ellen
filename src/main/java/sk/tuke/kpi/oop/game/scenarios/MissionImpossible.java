package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {
    private Ripley ripley;

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = scene.getFirstActorByType(Ripley.class);
        MovableController mController = new MovableController(ripley);
        Disposable disMController = scene.getInput().registerListener(mController);
        KeeperController kController = new KeeperController(ripley);
        Disposable disKController = scene.getInput().registerListener(kController);

        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Door) -> {
                Disposable kill = ripley.kill();
                scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ventilator) -> kill.dispose());
            }
        );

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ripley -> disKController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ripley -> disMController.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ripley -> disMController.dispose());
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
            else if (name.equals("energy")) {
                return new Energy();
            }
            else if(name.equals("door")) {
                return new LockedDoor(Door.Orientation.VERTICAL);
            }
            else if (name.equals("access card")) {
                return new AccessCard();
            }
            else if(name.equals("ventilator")) {
                return new Ventilator();
            }
            else if (name.equals("locker")) {
                return new Locker();
            }
            else return null;

        }
    }
}
