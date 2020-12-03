package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

public class Fire<A extends Armed> extends AbstractAction<A> {
    private A shooter;
    private boolean done;
    private Fireable bullet;
    private Move<Fireable> move;

    public Fire() {
    }

    @Nullable
    @Override
    public A getActor() {
        return shooter;
    }

    @Override
    public void setActor(@Nullable A shooter) {
        this.shooter = shooter;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void execute(float deltaTime) {
        if (!isDone()) {
            if(shooter == null){
                done = true;
                return;
            }
            bullet = shooter.getFirearm().fire();

            if (bullet == null) {
                done = true;
                return;
            }
            int bulletPosX = giveX(shooter.getAnimation().getRotation());
            int bulletPosY = giveY(shooter.getAnimation().getRotation());
            bullet.getAnimation().setRotation(shooter.getAnimation().getRotation());
            shooter.getScene().addActor(bullet, shooter.getPosX() + 8 + bulletPosX, shooter.getPosY() + 8 + bulletPosY);
            move = new Move<>(Direction.fromAngle(shooter.getAnimation().getRotation()), Float.MAX_VALUE);
            move.scheduleFor(bullet);
        }

        done = true;
    }

    private int giveX(float angle) {
        // North & South
        if (angle == 0 | angle == 180) {
            return 0;
        }
        // East & NE & SE
        if (angle == 270 | angle == 315 | angle == 225) {
            return 16;
        }
        // West & NW & SW
        if (angle == 90 | angle == 45 | angle == 135) {
            return -16;
        }
        return -1;
    }

    private int giveY(float angle) {
        // North & NE & NW
        if (angle == 0 | angle == 315 | angle == 45) {
            return 16;
        }
        // South & SE & SW
        if (angle == 180 | angle == 225 | angle == 135) {
            return -16;
        }
        // East & West
        if (angle == 270 | angle == 90) {
            return 0;
        }
        return -1;
    }

}
