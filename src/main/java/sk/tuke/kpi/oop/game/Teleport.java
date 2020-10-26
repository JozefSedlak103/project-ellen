package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Teleport extends AbstractActor {
    public Teleport destinationTeleport;

    public Teleport() {
        setAnimation(new Animation("sprites/lift.png"));
    }
    public Teleport(Teleport destinationTeleport) {
        this.destinationTeleport = destinationTeleport;
        setAnimation(new Animation("sprites/lift.png"));
    }

    public Teleport getDestination() {
        return destinationTeleport;
    }

    public void setDestination(Teleport destinationTeleport) {
        this.destinationTeleport = destinationTeleport;
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

        if (destinationTeleport != null ) {
            if (player.getPosX()+(player.getWidth()/2)>=this.getPosX() &&
            (player.getPosX()+(player.getWidth()/2))<=this.getPosX()+this.getWidth() &&
            (player.getPosY()+(player.getHeight()/2))>=this.getPosY() &&
            (player.getPosY()+(player.getHeight()/2))<=this.getPosY()+this.getHeight()) {
                player.setPosition(
                    destinationTeleport.getPosX() + ((destinationTeleport.getHeight() / 2) - (player.getHeight() / 2)),
                    destinationTeleport.getPosY() + ((destinationTeleport.getWidth() / 2) - (player.getWidth() / 2)));
            }
        }
    }
}
