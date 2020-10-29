package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class TimeBomb extends AbstractActor {
    private float time;
    private boolean activated;
    private Animation bombAnim;
    private Animation activeAnim;
    private Animation explosion;


    public TimeBomb(float time) {
        this.time = time;
        activated = false;
        bombAnim = new Animation("sprites/bomb.png");
        activeAnim = new Animation("sprites/bomb_activated.png",16,16,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        explosion = new Animation("sprites/small_explosion.png",16,16,0.1f, Animation.PlayMode.ONCE);
        setAnimation(bombAnim);
    }

    protected void explode() {
        setAnimation(explosion);
        new When<>(
            ()-> getAnimation().getCurrentFrameIndex() >= 7,
            new Invoke<>(() -> getScene().removeActor(this))
        ).scheduleFor(this);
    }

    public void activate() {
        activated = true;
        setAnimation(activeAnim);
        new ActionSequence<>(
            new Wait<>(time),
            new Invoke<>(this::explode)
        ).scheduleFor(this);


    }

    public boolean isActivated() {
        if(activated) {
            return true;
        } else {
            return false;
        }
    }
}
