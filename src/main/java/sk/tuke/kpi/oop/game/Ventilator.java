package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable {

    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);

    private boolean broken;
    private Animation animation;

    public Ventilator(){
        animation = new Animation("sprites/ventilator.png",32,32, 0.1f, Animation.PlayMode.LOOP);
        setAnimation(animation);
        animation.stop();
        broken = true;
    }

    @Override
    public boolean repair() {
        if(broken) {
            animation.play();
            broken = false;
            getScene().getMessageBus().publish(VENTILATOR_REPAIRED, this);
            return true;
        }
        return false;
    }

}
