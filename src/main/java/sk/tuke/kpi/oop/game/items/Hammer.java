package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> implements Collectible{
    private Animation hammerAnim = new Animation("sprites/hammer.png");
    private Reactor reactor;


    public Hammer() {
        super(1);
        setAnimation(hammerAnim);
        reactor=null;
    }

    public Hammer(Reactor reactor) {
        super(1);
        setAnimation(hammerAnim);
        this.reactor=reactor;
    }
    Hammer(int remainingUses){
        super(remainingUses);
        setAnimation(hammerAnim);
        this.reactor=null;
    }
    Hammer(int remainingUses, Reactor reactor){
        super(remainingUses);
        setAnimation(hammerAnim);
        this.reactor=reactor;
    }

    @Override
    public void useWith(Reactor reactor) {
        if(reactor==null) {
            return;
        }
        if(reactor.repair()){
            super.useWith(this.reactor);
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}
