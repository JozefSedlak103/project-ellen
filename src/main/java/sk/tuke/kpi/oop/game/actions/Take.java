package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Take<K extends Keeper> extends AbstractAction<K> {

    private K keeper;
    private boolean done;

    @Override
    public void execute(float deltaTime) {
        if (!isDone() & keeper != null) {
            for (Actor T : keeper.getScene().getActors()) {
                if (T.intersects(keeper) & T instanceof Collectible) {
                    try {
                        keeper.getBackpack().add((Collectible) T);
                        keeper.getScene().removeActor(T);
                        break;
                    } catch (IllegalStateException ex) {
                        keeper.getScene().getOverlay().drawText(ex.getMessage(), -130, 0).showFor(2);
                    }
                }
            }

        }
        done = true;
    }

    public K getKeeper() {
        return keeper;
    }

    @Override
    public void setActor(@Nullable K keeper) {
        this.keeper = keeper;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
