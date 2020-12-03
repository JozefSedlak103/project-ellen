package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Random;

public class RandomlyMoving<A extends Movable> implements Behaviour<A> {
    private Movable movable;

    public RandomlyMoving(){

    }
    @Override
    public void setUp(Movable actor) {
        movable=actor;
        if (movable!=null)
            new Loop<>(new ActionSequence<>(new Invoke<>(this::pohyb),new Wait<>(0.1f))).scheduleFor(actor);
    }
    private void pohyb(){
        new Move<>(random(), 1).scheduleFor(movable);
    }

    private Direction random(){
        int x=new Random().nextInt(8);
        switch (x){
            case 0: return Direction.NORTH;
            case 1:return Direction.NORTHWEST;
            case 2: return Direction.WEST;
            case 3:return Direction.SOUTHWEST;
            case 4:return Direction.SOUTH;
            case 5:return Direction.SOUTHEAST;
            case 6: return Direction.EAST;
            case 7:return Direction.NORTHEAST;
            default:return null;
        }
    }
}
