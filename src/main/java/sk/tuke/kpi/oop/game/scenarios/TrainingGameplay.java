package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.PowerSwitch;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.items.Hammer;

import java.util.Map;

public class TrainingGameplay extends Scenario {
    private Reactor reactor = new Reactor();
    private Cooler cooler = new Cooler(reactor);
    private Hammer hammer = new Hammer();
    private PowerSwitch powerSwitch = new PowerSwitch(reactor);
    @Override
    public void setupPlay(@NotNull Scene scene) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker reactorArea1 = markers.get("reactor-area-1");
        MapMarker coolerArea1 = markers.get("cooler-area-1");

        scene.addActor(reactor,reactorArea1.getPosX(),reactorArea1.getPosY());
        scene.addActor(cooler,coolerArea1.getPosX(),coolerArea1.getPosY());
        scene.addActor(powerSwitch,10,10);
        reactor.turnOn();

        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);

        /*new Invoke<>(new Runnable() {
            public void run() {
                reactor.repairWith(hammer);
            }
        });

         */
        /*new When<>(
            () -> {
                return reactor.getTemperature() >=3000;
            },
            new Invoke<>(() -> {
                reactor.repairWith(hammer);
            })
        ).scheduleFor(reactor);
         */

        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> reactor.repair())
        ).scheduleFor(reactor);
    }
}
