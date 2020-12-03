package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class AlienMother extends Alien{

    private Health health;

    public AlienMother(){
        health = new Health(200);
        Animation animation = new Animation("sprites/mother.png", 112,162, 0.2f, Animation.PlayMode.LOOP);
        setAnimation(animation);
    }

    @Override
    public Health getHealth() {
        return health;
    }
}
