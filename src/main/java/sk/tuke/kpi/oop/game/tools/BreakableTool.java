package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.framework.AbstractActor;

import java.util.Objects;

abstract class BreakableTool extends AbstractActor {
    private int remainingUses;

    public BreakableTool(int remainingUses) {
        this.remainingUses = remainingUses;
    }


    public void use() {
        if (remainingUses>0) this.remainingUses = remainingUses-1;
        if(remainingUses <= 0) {
            Objects.requireNonNull(this.getScene()).removeActor(this);
        }
    }
}
