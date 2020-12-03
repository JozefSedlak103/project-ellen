package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;

public class Drop<K extends Keeper> extends AbstractAction<K> {

    private K keeper;
    private boolean done;

    public Drop(){
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
        if(!isDone()) {
            if(keeper == null){
                done = true;
                return;
            }
            Collectible item = keeper.getBackpack().peek();
            if (item != null) {
                Objects.requireNonNull(keeper.getScene()).addActor(item, keeper.getPosX() + 8, keeper.getPosY() + 8);
                keeper.getBackpack().remove(item);
            }

        }
        done = true;
    }
}
