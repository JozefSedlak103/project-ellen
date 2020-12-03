package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {

    private int speed;
    private Health health;
    public Alien() {
        health = new Health(100);
        speed = 1;
        Animation animation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animation);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::killAliveOnIntersect)).scheduleFor(this);
        new Loop<>(new Invoke<>(this::kill)).scheduleFor(this);
    }

    public void kill() {
        if (this.getHealth().getValue() <= 0) {
            getScene().cancelActions(this);
            getScene().removeActor(this);
        }
    }


    public void killAliveOnIntersect() {
        for (Actor actor : getScene().getActors()) {

            if (actor.intersects(this) & actor instanceof Alive & !(actor instanceof Enemy)) {
                ((Alive) actor).getHealth().drain(1);
            }

        }
    }
}
