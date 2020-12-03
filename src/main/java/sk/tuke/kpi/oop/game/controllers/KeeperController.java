package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class KeeperController implements KeyboardListener {

    private Keeper actor;
    private Drop<Keeper> drop;
    private Take<Keeper> take;
    private Shift<Keeper> shift;
    private Usable<?> usable;

    public KeeperController(Keeper actor){
        this.actor = actor;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key){

        if(key == Input.Key.ENTER){
            take = new Take<>();
            take.scheduleFor(actor);
        }
        if(key == Input.Key.BACKSPACE){
            drop = new Drop<>();
            drop.scheduleFor(actor);
        }
        if(key == Input.Key.U){
            usable = (Usable<?>) Objects.requireNonNull(actor.getScene()).getActors().stream().filter(actor::intersects).filter(Usable.class::isInstance)
                .map(Usable.class::cast).findFirst().orElse(null);
            if(usable != null){
                new Use<>(usable).scheduleForIntersectingWith(actor);

            }
        }
        if(key == Input.Key.S){
            shift = new Shift<>();
            shift.scheduleFor(actor);
        }
        if(key == Input.Key.B){
            if(actor.getBackpack().peek() == null | !(actor.getBackpack().peek() instanceof Usable))return;
            usable = (Usable<?>) actor.getBackpack().peek();
            if(usable != null) {
                new Use<>(usable).scheduleForIntersectingWith(actor);
            }
        }
    }
}
