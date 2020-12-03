package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift<K extends Keeper> extends AbstractAction<K> {

    private K keeper;
    private boolean done;
    public Shift(){
    }

    @Nullable
    @Override
    public K getActor() {
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

    @Override
    public void execute(float deltaTime) {
        if(!isDone() & keeper != null) {
            keeper.getBackpack().shift();
        }
        done = true;
    }
}

