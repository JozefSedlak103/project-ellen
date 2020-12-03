package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving<A extends Movable> implements Behaviour<A> {

    public RandomlyMoving(){

    }

    @Override
    public void setUp(A actor) {
        new Loop<>(
            new ActionSequence<>(
                new Wait<>(1),
                new Move<>(Direction.Random, Float.MAX_VALUE)
            )
        ).scheduleFor(actor);
    }
}
