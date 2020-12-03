package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private boolean opened;
    private Animation doorAnim;
    private char oriented;

    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public Door(Orientation orientation){
        opened = false;
        if(orientation == Orientation.VERTICAL) {
            oriented = 'V';
            doorAnim = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnim);
        }
        if(orientation == Orientation.HORIZONTAL){
            oriented = 'H';
            doorAnim = new Animation("sprites/hdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnim);
        }
        setAnimation(doorAnim);
    }
    public Door(String name, Orientation orientation){
        super(name);
        opened = false;
        if(orientation == Orientation.VERTICAL) {
            oriented = 'V';
            doorAnim = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnim);
        }
        if(orientation == Orientation.HORIZONTAL){
            oriented = 'H';
            doorAnim = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE);
            setAnimation(doorAnim);
        }
    }


    @Override
    public void useWith(Actor actor) {
        if (isOpen()){
            close();
        } else {
            open();
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    @Override
    public void open() {
        opened = true;
        doorAnim.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        doorAnim.play();
        doorAnim.stop();
        int x = this.getPosX();
        int y = this.getPosY();

        if(oriented == 'V') {
            getScene().getMap().getTile(x / 16, y / 16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile(x / 16, y / 16 + 1).setType(MapTile.Type.CLEAR);
        }

        if(oriented == 'H') {
            getScene().getMap().getTile(x / 16, y / 16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile(x / 16 + 1, y / 16 ).setType(MapTile.Type.CLEAR);
        }

        getScene().getMessageBus().publish(DOOR_OPENED, this);
    }

    @Override
    public void close() {
        opened = false;
        doorAnim.setPlayMode(Animation.PlayMode.ONCE);
        doorAnim.play();
        doorAnim.stop();

        int x = this.getPosX();
        int y = this.getPosY();

        if(oriented == 'V') {
            getScene().getMap().getTile(x / 16, y / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(x / 16, y / 16 + 1).setType(MapTile.Type.WALL);
        }

        if(oriented == 'H') {
            getScene().getMap().getTile(x / 16, y / 16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(x / 16 + 1, y / 16 ).setType(MapTile.Type.WALL);
        }
        getScene().getMessageBus().publish(DOOR_CLOSED, this);
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        close();
    }
}
