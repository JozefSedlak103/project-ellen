package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {

    private int speed;
    private int energy = 100;
    private Backpack backpack;
    private Animation moving;
    private Animation dead;
    private Health health;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);
    private Firearm gun;


    public Ripley() {
        super("Ellen");
        speed = 2;
        gun = new Gun(50, 50);
        health = new Health(100);
        moving = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        dead = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(moving);
        moving.pause();
        backpack = new Backpack("Ripley's backpack", 10);
    }

    public void showRipleyState(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if (ripley == null) return;
        int healthValue = ripley.health.getValue();
        int ammoValue = ripley.getAmmo();

        scene.getGame().getOverlay().drawText("Energy: " + healthValue, 150, yTextPos);
        scene.getGame().getOverlay().drawText("Ammo: " + ammoValue, 350, yTextPos);

        scene.getGame().pushActorContainer(ripley.getBackpack());

    }

    public int getAmmo() {
        return gun.getAmmo();
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        moving.setRotation(direction.getAngle());
        moving.play();
    }

    @Override
    public void stoppedMoving() {
        moving.stop();
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Firearm getFirearm() {
        return gun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.gun = weapon;
    }

    public void stab() {
        if (health.getValue() <= 100 & health.getValue() > 0)
            health.drain(1);
        if (health.getValue() < 1) {
            setAnimation(dead);
            getScene().getMessageBus().publish(RIPLEY_DIED, this);
        }

    }

    public Disposable kill() {
        return new Loop<>(
            new ActionSequence<>(
                new Invoke<>(this::stab),
                new Wait<>(1)
            )
        ).scheduleFor(this);
    }

    public void die(){
        setAnimation(dead);
        getScene().getMessageBus().publish(RIPLEY_DIED, this);
        getScene().cancelActions(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        health.onExhaustion(this::die);
    }
}
