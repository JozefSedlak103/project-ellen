package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move<A extends Movable> implements Action<A> {
    private boolean done = false;
    private float time;
    private boolean x = true;
    private Direction direction;
    private float duration;
    private A actor;

    public Move(Direction direction, float duration) {
        this.duration = duration;
        this.direction = direction;
    }

    public Move(Direction direction) {
        this.direction = direction;
    }


    @Nullable
    @Override
    public A getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable A actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void execute(float deltaTime) {
        if (actor == null) {
            return;
        }
        if (!isDone()) {
            if (x) {
                actor.startedMoving(direction);
                x = false;
            }
            move(deltaTime);
            if (time>=duration) {
                stop();
            }
        } else {
            move(deltaTime);
            if (time>=duration) {
                stop();
            }
        }
    }

    private void move(float deltaTime) {

        if (actor==null)return;
        if (!actor.getScene().getMap().intersectsWithWall(actor)){
            actor.setPosition(actor.getPosX() + direction.getDx() * actor.getSpeed(), actor.getPosY() + direction.getDy() * actor.getSpeed());

            if (actor.getScene().getMap().intersectsWithWall(actor)){
                //actor.collidedWithWall();
                if(actor==null)return;
                actor.setPosition(actor.getPosX() - direction.getDx() * actor.getSpeed(), actor.getPosY() - direction.getDy() * actor.getSpeed());
            }
        }else{
            stop();
        }
        time = time + deltaTime;
    }

    public void stop(){
        if(actor!=null) {
            done = true;
            time = duration;
            actor.stoppedMoving();
        }
    }

    @Override
    public void reset() {
        done = false;
    }
}
