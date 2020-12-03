package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Actor> {

    private boolean used;

    public Locker(){
        used = false;
        Animation animation = new Animation("sprites/locker.png");
        setAnimation(animation);
    }

    public boolean isUsed(){
        return used;
    }
    public void setUsed(boolean set){
        used = set;
    }

    @Override
    public void useWith(Actor actor) {
        if(!isUsed()){
            int x = this.getPosX();
            int y = this.getPosY();
            actor.getScene().addActor(new Hammer(),x,y);
        }
        setUsed(true);
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}
