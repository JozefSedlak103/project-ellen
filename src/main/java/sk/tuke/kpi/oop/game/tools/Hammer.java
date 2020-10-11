package sk.tuke.kpi.oop.game.tools;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.BreakableTool;

import java.util.Objects;

public class Hammer extends BreakableTool {
    private int uses;
    private Animation hammerAnim;

    public Hammer() {
        super(1);
        hammerAnim = new Animation("sprites/hammer.png");
        setAnimation(hammerAnim);

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
