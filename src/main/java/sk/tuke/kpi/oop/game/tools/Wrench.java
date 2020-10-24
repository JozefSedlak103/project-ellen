package sk.tuke.kpi.oop.game.tools;


import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class Wrench extends BreakableTool {
    private int uses;
    private Animation wrenchanim;

    public Wrench() {
        super(2);
        wrenchanim = new Animation("sprites/wrench.png");
        setAnimation(wrenchanim);
    }

    public void use() {
        if (getUses()>0) this.uses = getUses()-1;
        if(getUses() <= 0) {
            Objects.requireNonNull(this.getScene()).removeActor(this);
        }
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses=uses;
    }
}
