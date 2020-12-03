package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door {

    private boolean locked;

    public LockedDoor(Orientation orientation) {
        super(orientation);
        locked = true;
    }

    public LockedDoor(String name, Orientation orientation) {
        super(name, orientation);
        locked = true;
    }

    @Override
    public void useWith(Actor actor) {
        if(isLocked()){
            return;
        }
        if (isOpen() & !isLocked()){
            close();
            return;
        }
        if(!isOpen() & !isLocked()){
            open();
            return;
        }
    }

    public void lock(){
        locked = true;
        close();
    }
    public void unlock(){
        locked = false;
        open();
    }
    public boolean isLocked(){
        return locked;
    }
}
