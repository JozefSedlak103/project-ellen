package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class Hammer extends AbstractActor {
    private int uses;
    private Animation hammerAnim;

    public Hammer() {
        this.uses = 1;
        hammerAnim = new Animation("sprites/hammer.png");
        setAnimation(hammerAnim);

    }
    //nemusi fungovat spravne uloha 1.2
    public void use() {
        if (getUses()>0) this.uses = getUses()-1;
        if(getUses() <= 0) {
            Objects.requireNonNull(this.getScene()).removeActor(this);
        }
    }

    public int getUses() {
        return uses;
    }
}
