package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

import java.util.Objects;

public class Ammo extends AbstractActor implements Usable<Armed> {

    public Ammo(){
        Animation animation = new Animation("sprites/ammo.png",16,16,0f);
        setAnimation(animation);
    }

    @Override
    public void useWith(Armed armed) {
        if (armed == null) {
            return;
        }
        armed.getFirearm().reload(armed.getFirearm().getMaxAmmo());
        Objects.requireNonNull(this.getScene()).removeActor(this);
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
