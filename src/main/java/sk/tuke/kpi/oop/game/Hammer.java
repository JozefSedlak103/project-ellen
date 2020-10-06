package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private int uses;
    Animation hammer;

    public Hammer() {
        this.uses = 1;
        hammer = new Animation("sprites/hammer.png");

    }
    //nemusi fungovat spravne uloha 1.2
    public void use() {
        this.uses = getUses()-1;
        if (getUses()<=0) {
            setPosition(-100,-100);
        }
    }

    public int getUses() {
        return uses;
    }
}
