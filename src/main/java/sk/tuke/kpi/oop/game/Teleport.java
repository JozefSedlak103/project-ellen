package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {
    private Teleport destinationTeleport;
    private boolean isOut;
    public Teleport(){
        setAnimation(new Animation("sprites/lift.png"));
        isOut =false;
    }

    public Teleport(Teleport destinationTeleport){
        setAnimation(new Animation("sprites/lift.png"));
        this.destinationTeleport = destinationTeleport;
        isOut =false;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::teleport)).scheduleFor(this);
    }

    private boolean canTeleport(Player player){
        if (!isOut){
            return true;
        }else {
            isOut = isMiddle(player);
            return false;
        }
    }


    public void teleport(){
        Player player=(Player)getScene().getFirstActorByName("Player");
        if(player == null)return;
        if((canTeleport(player)&&(this.intersects(player)&& isMiddle(player)))&& destinationTeleport !=null){
            destinationTeleport.teleportPlayer(player);
        }
    }

    private boolean isMiddle(Player player){
        boolean a=player.getPosX()+16>=this.getPosX();
        boolean b=player.getPosX()+16<=this.getPosX()+48;
        boolean c=player.getPosY()+16>=this.getPosY();
        boolean d=player.getPosY()+16<=this.getPosY()+48;
        return ((a&&b)&&(c&&d));
    }



    public void teleportPlayer(Player player){
        if(player!=null){
            this.setOut(true);
            player.setPosition(this.getPosX()+8,this.getPosY()+8);

        }
    }



    public Teleport getDestination() {
        return destinationTeleport;
    }

    public void setDestination(Teleport destinationTeleport) {
        if (destinationTeleport!=this && destinationTeleport!=null)
            this.destinationTeleport = destinationTeleport;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        this.isOut = out;
    }
}
