package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable {
    private Disposable blinking;
    private boolean x;

    public DefectiveLight() {
        super();
        x=true;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        breakDown();
    }

    public void breakDown(){blinking=new Loop<>(new Invoke<>(this::blink)).scheduleFor(this);}
    public void notYet(){x=!x;}


    private void blink() {
        int blik = (int) (Math.random() * 20) + 1;
        if (blik == 1) {
            this.toggle();
        }
    }

    @Override
    public boolean repair() {
        if(x){
            notYet();
            blinking.dispose();

            new ActionSequence<>(
                new Wait<>(10),
                new Invoke<>(this::notYet),
                new Invoke<>(this::breakDown)
            ).scheduleFor(this);
            return true;
        }else {
            return false;
        }
    }
}
