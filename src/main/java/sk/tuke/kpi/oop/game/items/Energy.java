package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;


public class Energy extends AbstractActor implements Usable<Ripley> {
    public Energy() {
        setAnimation(new Animation("sprites/energy.png"));
    }

    @Override
    public void useWith(Ripley actor) {
        if (actor != null && actor.getEnergy()<100) {
            actor.setEnergy(100);
            this.getScene().removeActor(this);
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
