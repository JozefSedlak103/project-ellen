package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {
    private Animation coolerAnim;
    private Reactor reactor;
    private boolean on;

    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        on = false;
        coolerAnim = new Animation("sprites/fan.png",32,32,0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(coolerAnim);
        coolerAnim.pause();
    }

    private void coolReactor() {
        if(on) reactor.decreaseTemperature(1);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (reactor != null) {
            new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this.reactor);
        } else {
            return;
        }
    }

    public void turnOn() {
        on=true;
        coolerAnim.play();
    }

    public void turnOff() {
        on=false;
        coolerAnim.pause();
    }

    public boolean isOn() {
        if(on) {
            return true;
        } else {
            return false;
        }
    }

    public Reactor getReactor() {
        return reactor;
    }
}
