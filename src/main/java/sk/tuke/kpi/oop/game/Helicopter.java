package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    private Player player;
    private Animation heliAnim;

    public Helicopter() {
        heliAnim = new Animation("sprites/heli.png",64,64, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(heliAnim);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::searchAndDestroy)).scheduleFor(this);
    }

    private int findPositionX() {
        int x =  this.getPosX();
        if (x<player.getPosX()) {
            x +=1;
        } else {
            x-=1;
        }
        return x;
    }

    private int findPositionY() {
        int y = this.getPosY();
        if(y<player.getPosY()) {
            y+=1;
        } else {
            y-=1;
        }
        return y;
    }

    public void searchAndDestroy() {
        player = (Player) getScene().getFirstActorByName("Player");
        this.setPosition(findPositionX(),findPositionY());
        if (this.intersects(player)) {
            player.setEnergy(player.getEnergy()-1);
        }
    }
}
