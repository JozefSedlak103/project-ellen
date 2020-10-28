package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable {
    private Disposable blinking;

    public DefectiveLight() {
        super();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        blinking = new Loop<>(new Invoke<>(this::blink)).scheduleFor(this);
    }



    private void blink() {
        int blik = (int) (Math.random() * 20) + 1;
        if (blik == 1) {
            this.toggle();
        }
    }

    @Override
    public boolean repair() {
        blinking.dispose();
        return true;
    }
}
