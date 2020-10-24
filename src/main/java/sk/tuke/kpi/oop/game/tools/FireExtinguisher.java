package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {
    int uses;
    private Reactor reactor;
    Animation extinguisherAnim;

    public FireExtinguisher() {
        super(1);
        extinguisherAnim = new Animation("sprites/extinguisher.png");
        setAnimation(extinguisherAnim);
    }

    public FireExtinguisher(Reactor reactor) {
        super(1);
        extinguisherAnim = new Animation("sprites/extinguisher.png");
        this.reactor = reactor;
    }

    public void useWith(Reactor reactor) {
        if (reactor!=null) {
            if (this.reactor == reactor) {
                reactor.extinguish();
                super.useWith(reactor);
            }
        }
    }

    public int getUses() {
        return uses;
    }
}
