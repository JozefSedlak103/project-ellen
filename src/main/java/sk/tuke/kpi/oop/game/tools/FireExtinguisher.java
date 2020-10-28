package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> {
    private Reactor reactor;
    private Animation extinguisherAnim;

    public FireExtinguisher() {
        super(1);
        extinguisherAnim = new Animation("sprites/extinguisher.png");
        setAnimation(extinguisherAnim);
    }

    public FireExtinguisher(Reactor reactor) {
        super(1);
        extinguisherAnim = new Animation("sprites/extinguisher.png");
        setAnimation(extinguisherAnim);
        this.reactor = reactor;
    }
    @Override
    public void useWith(Reactor reactor) {
        if(reactor==null) {
            return;
        }
        if(reactor.extinguish()){
            super.useWith(this.reactor);
        }
    }
}
