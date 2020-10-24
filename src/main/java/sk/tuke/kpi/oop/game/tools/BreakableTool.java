package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

import java.util.Objects;

abstract class BreakableTool<B extends Actor> extends AbstractActor implements Usable<B> {
    private int remainingUses;

    public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }

    @Override
    public void useWith(B tool) {
        if (tool!=null && remainingUses>0)
            this.remainingUses = remainingUses-1;
        if(remainingUses <= 0) {
            Objects.requireNonNull(this.getScene()).removeActor(this);
        }
    }
    public int getRemainingUses() {
        return remainingUses;
    }
}
