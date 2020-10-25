package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class TimeBomb extends AbstractActor {
    private AbstractActor timeBomb;
    private int time;
    private boolean activated;
    private Animation bombAnim;
    private Animation activeAnim;
    private Animation explosion;


    public TimeBomb() {
        timeBomb = this;
        activated = false;
        bombAnim = new Animation("sprites/bomb.png");
        activeAnim = new Animation("sprites/bomb_activated.png",16,16,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        explosion = new Animation("sprites/small_explosion.png",16,16,0.1f, Animation.PlayMode.ONCE);
        setAnimation(bombAnim);
    }

    public void activate() {
        activated = true;
        setAnimation(activeAnim);
        time = 6;
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                time = time - 1;
                if (time ==1) {
                    setAnimation(explosion);
                    activated = false;
                } if (time ==0) {
                    timer.cancel();
                    Objects.requireNonNull(timeBomb.getScene()).removeActor(timeBomb);
                }
            }
        }, 0, 1000);
    }

    public boolean isActivated() {
        if(activated) {
            return true;
        } else {
            return false;
        }
    }
}
