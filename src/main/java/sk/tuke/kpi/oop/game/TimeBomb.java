package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TimeBomb extends AbstractActor {
    //private Actor timeBomb;
    private float time;
    private boolean activated;
    private Animation bombAnim;
    private Animation activeAnim;
    private Animation explosion;

    public TimeBomb() {
        //timeBomb = this;
        activated = false;
        bombAnim = new Animation("sprites/bomb.png");
        activeAnim = new Animation("sprites/bomb_activated.png",16,16,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        explosion = new Animation("sprites/small_explosion.png",16,16,0.1f, Animation.PlayMode.ONCE);
        time = 20;
        setAnimation(bombAnim);
    }

    public void activate() throws InterruptedException {
        activated = true;
        setAnimation(activeAnim);
        while (time>0) {
            time -= 1;
        }
        if (time <= 0) {
            setAnimation(explosion);
            if (explosion.getCurrentFrameIndex()+1 == explosion.getFrameCount()) {
                Objects.requireNonNull(this.getScene()).removeActor(this);
            }
            activated = false;
        }
    }

    public boolean isActivated() {
        if(activated) {
            return true;
        } else {
            return false;
        }
    }
}
