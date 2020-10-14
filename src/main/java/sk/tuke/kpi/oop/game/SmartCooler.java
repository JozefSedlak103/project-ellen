package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler {
    private int temperature;

    public SmartCooler(Reactor reactor) {
        super(reactor);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::smartCoolReactor)).scheduleFor(this);
    }

    private void smartCoolReactor() {
        Reactor reactor = super.getReactor();
        if(reactor!=null) {
            temperature = reactor.getTemperature();
            if (temperature > 2500) {
                this.turnOn();
            } else if (temperature <= 1500) {
                this.turnOff();
            }
        }
    }
}
