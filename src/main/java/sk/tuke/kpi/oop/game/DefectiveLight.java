package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light {
    public DefectiveLight() {
        super();
    }

    //skoncil som 4. cviko, este dorobit doplnujuce ulohy

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::blink)).scheduleFor(this);
    }



    private void blink() {
        int blik = (int) (Math.random() * 20) + 1;
        if (blik == 1) {
            this.toggle();
        }
    }
}
