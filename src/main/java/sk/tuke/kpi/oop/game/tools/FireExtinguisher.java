package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.BreakableTool;

import java.util.Objects;

public class FireExtinguisher extends BreakableTool {
    int uses;
    Animation extinguisherAnim;

    public FireExtinguisher() {
        super(1);
        extinguisherAnim = new Animation("sprites/extinguisher.png");
        setAnimation(extinguisherAnim);
    }

    public void use() {
        if(getUses()>0) this.uses = getUses() - 1;
        if(getUses() <= 0) {
            Objects.requireNonNull(this.getScene()).removeActor(this);
        }
    }

    public int getUses() {
        return uses;
    }
}
