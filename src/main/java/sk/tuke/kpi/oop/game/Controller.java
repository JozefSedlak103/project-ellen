package sk.tuke.kpi.oop.game;



import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Controller extends AbstractActor {
    public Reactor reactor;
    Animation switchAnim;

    public Controller(Reactor reactor) {
        this.reactor = reactor;
        switchAnim = new Animation("sprites/switch.png");
        setAnimation(switchAnim);
    }
    public void toggle() {
        if(reactor.isRunning()) {
            reactor.turnOff();
        } else {
            reactor.turnOn();
        }
    }
}
