package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class ChainBomb extends TimeBomb {
    private Ellipse2D.Float ellipse;
    public ChainBomb(float time) {
        super(time);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        ellipse=new Ellipse2D.Float(this.getPosX()-50,this.getPosY()-50,100,100);
    }

    @Override
    protected void explode() {
        super.explode();
        List<Actor> bombs=getScene().getActors();
        Rectangle2D bomby;
        for (Actor bomb:bombs) {
            if (bomb.getClass()==TimeBomb.class||bomb.getClass()==ChainBomb.class){
            TimeBomb bombT = bomb.getClass()==TimeBomb.class?(TimeBomb)bomb:(ChainBomb) bomb;
            bomby=new Rectangle2D.Float(bomb.getPosX()-4-4,(float)bomb.getPosY()-4-4,bomb.getHeight(),bomb.getWidth());
            if ((ellipse.intersects(bomby))&&!bombT.isActivated()){
                bombT.activate();
            }
        }


        }
    }
}
