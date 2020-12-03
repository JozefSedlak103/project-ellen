package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Bullet extends AbstractActor implements Fireable {

    private int speed;
    private Animation animation;

    public Bullet(){
        speed = 4;
        animation = new Animation("sprites/bullet.png", 16,16);
        setAnimation(animation);
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        animation.setRotation(direction.getAngle());
    }
    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::collidedWithPlayer)
            )
        ).scheduleFor(this);
    }

    @Override
    public void collidedWithWall(){
        getScene().cancelActions(this);
        getScene().removeActor(this);
    }

    private void collidedWithPlayer(){
        for(Actor actor: getScene().getActors()){
            if(actor.intersects(this) & actor instanceof Alive & !(actor instanceof Ripley)) {
                ((Alive) actor).getHealth().drain(50);
                getScene().cancelActions(this);
                getScene().removeActor(this);
            }
        }
    }
}
