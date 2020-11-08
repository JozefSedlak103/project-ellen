package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Ripley extends AbstractActor implements Movable{
    Animation ripleyAnim = new Animation("sprites/player.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
    public Ripley() {
        super("Ellen");
        setAnimation(ripleyAnim);

    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction) {
        ripleyAnim.setRotation(direction.getAngle());
        ripleyAnim.play();
    }

    @Override
    public void stoppedMoving() {
        ripleyAnim.stop();
    }
}
