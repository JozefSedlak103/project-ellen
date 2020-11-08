package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovableController implements KeyboardListener {
    private Movable movable;
    private Move<Movable> move;
    private List<Direction> directions=new ArrayList<>();


    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST)
    );

    public MovableController(Movable movable) {
        this.movable = movable;
    }
    @Override
    public void keyPressed(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            directions.add(keyDirectionMap.get(key));
            move();
        }
    }

    @Override
    public void keyReleased(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key)) {
            directions.remove(keyDirectionMap.get(key));
            move();
        }
    }

    private void move() {
        Direction direction = Direction.NONE;
        for (Direction direction1:directions) {
            direction = direction.combine(direction1);
        }
        if (directions.isEmpty() || move!=null) {
            move.stop();
        }
        if (direction!=Direction.NONE) {
            move = new Move<>(direction,5);
            move.setActor(movable);
            move.scheduleFor(movable);
        }
    }
}
