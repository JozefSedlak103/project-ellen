package sk.tuke.kpi.oop.game.tools;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.DefectiveLight;

public class Wrench extends BreakableTool<DefectiveLight> {

    public Wrench() {
        super(2);
        Animation wrenchanim = new Animation("sprites/wrench.png");
        setAnimation(wrenchanim);
    }

    @Override
    public void useWith(DefectiveLight actor) {
        if (actor != null && actor.repair()) {
            super.useWith(actor);
        }
    }
}
