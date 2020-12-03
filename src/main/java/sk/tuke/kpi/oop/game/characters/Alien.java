package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {

    private int speed;
    private Health health;
    private Behaviour<? super Alien> behaviour;
    public static final Topic<Alien> ALIEN_DIED= Topic.create("alien died", Alien.class);
    public Alien() {
        health = new Health(50);
        speed = 1;
        Animation animation = new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animation);
    }

    public Alien(int healthValue, Behaviour<? super Alien> behaviour){
        setAnimation(new Animation("sprites/alien.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG));
        health=new Health(healthValue);
        this.behaviour=behaviour;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public Health getHealth() {
        return health;
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

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if(behaviour!=null)behaviour.setUp(this);
        new Loop<>(new Invoke<>(this::killAliveOnIntersect)).scheduleFor(this);
        new Loop<>(new Invoke<>(this::kill)).scheduleFor(this);
    }
}
