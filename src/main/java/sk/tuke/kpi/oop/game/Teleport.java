package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {
    private Teleport destinationTeleport;
    private boolean isOut;

    public Teleport() {
        setAnimation(new Animation("sprites/lift.png"));
        isOut=false;
    }
    public Teleport(Teleport destinationTeleport) {
        this.destinationTeleport = destinationTeleport;
        setAnimation(new Animation("sprites/lift.png"));
        isOut=false;
    }
    private boolean isInMiddle(Player player) {
        boolean x1=player.getPosX()+16>=this.getPosX();
        boolean x2=player.getPosX()+16<=this.getPosX()+48;
        boolean y1=player.getPosY()+16>=this.getPosY();
        boolean y2=player.getPosY()+16<=this.getPosY()+48;
        return ((x1&&x2)&&(y1&&y2));
    }

    private boolean teleporting(Player player) {
        if (!isOut) {
            return true;
        } else {
            isOut = isInMiddle(player);
            return false;
        }
    }

    public void teleport() {
        Player player=(Player)getScene().getFirstActorByName("Player");
        if(player == null)return;
        if((teleporting(player)&&(this.intersects(player)&& isInMiddle(player)))&&destinationTeleport!=null){
            destinationTeleport.teleportPlayer(player);
        }
    }

    public Teleport getDestination() {
        return destinationTeleport;
    }

    public void setDestination(Teleport destinationTeleport) {
        if (destinationTeleport!=this && destinationTeleport!=null) {
            this.destinationTeleport = destinationTeleport;
        }
    }

    private boolean teleported(Player player) {
        if (player.getPosX()>=this.getPosX() && player.getPosX()+player.getWidth()<=this.getPosX()+this.getWidth()
        && player.getPosY()>=this.getPosY() && player.getPosY()+player.getHeight()<=this.getPosY()+this.getHeight()) {
            return true;
        } else {
            return false;
        }

    }

    public void teleportPlayer(Player player) {
        if(player!=null){
            this.setIsOut(true);
            player.setPosition(this.getPosX()+8,this.getPosY()+8);

        }
    }

    public boolean isOut() {
        return isOut;
    }

    private void setIsOut(boolean isOut) {
        this.isOut = isOut;
    }
}
