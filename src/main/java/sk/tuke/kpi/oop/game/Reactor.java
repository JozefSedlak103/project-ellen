package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;

import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable, Repairable {
    private int temperature;
    private int damage;
    private boolean isOn = false;
    private EnergyConsumer device;
    private Set<EnergyConsumer> devices;
    private Animation normalAnimation;
    private Animation hotAnimation;
    private Animation brokenAnimation;
    private Animation offAnimation;
    private Animation extinguishedAnimation;

    public Reactor() {
        devices = new HashSet<>();
        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        brokenAnimation = new Animation("sprites/reactor_broken.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        normalAnimation = new Animation("sprites/reactor_on.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        offAnimation = new Animation("sprites/reactor.png");
        extinguishedAnimation = new Animation("sprites/reactor_extinguished.png");
        setAnimation(offAnimation);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    private boolean test(){
        if (temperature >= 6000) {

            damage = 100;
            updateAnimation();
            return true;
        }else {
            updateAnimation();
            return false;
        }
    }

    private void tempCheck(int increment){
        if (damage >= 33 && damage <= 66) {
            temperature += Math.round(1.5 * increment);
            if (test()) return;
            damage = (int) (((temperature - 2000)) * 0.025);
            updateAnimation();
        } else if (damage > 66 && damage < 100) {
            temperature += 2 * increment;
            damage = (int) ((temperature - 2000) * 0.025);
            if (test()) return;
            updateAnimation();
        } else {
            temperature += increment;
            damage = (int) ((temperature - 2000) * 0.025);
            test();
        }
    }

    public void increaseTemperature(int increment) {
        if (isOn && increment > 0) {
            if (temperature >= 2000 || increment > 2000) {
                tempCheck(increment);
            } else {
                if (test()) return;
                temperature += increment;
                updateAnimation();
            }
        }
    }

    public void decreaseTemperature(int decrement) {
        if (isOn && decrement > 0) {
            if (temperature < 0) {
                temperature = 0;
            }
            if (damage >= 50) {
                temperature -= Math.round(decrement / 2);
                updateAnimation();
            } else {
                temperature -= decrement;
                updateAnimation();
            }
        }
    }

    private void updateAnimation() {
        if(isOn()) {
            if (temperature < 4000 && damage < 100) {
                setAnimation(normalAnimation);
            }
            if (temperature >= 4000 && damage < 100) {
                setAnimation(hotAnimation);
            }
            if (damage == 100) {
                setAnimation(brokenAnimation);
                turnOff();
                setAnimation(brokenAnimation);
            }
        }else {
            if(damage<100)
                setAnimation(offAnimation);
        }
    }

    public boolean repair() {
        int temp;
        if(damage>0&&damage<100) {
            damage -= 50;
            temp=2000+(damage*40);
            if(temp<temperature){
                setTemperature(temp);
            }

            if (damage < 0) {
                damage = 0;
            }
            updateAnimation();
            return true;
        }else {
            return false;
        }
    }


    private void setTemperature(int temperature) {
        this.temperature = temperature;
        updateAnimation();
    }

    public boolean extinguish() {
        if(temperature>0) {
            temperature -= 4000;
            if (temperature < 0) {
                temperature = 0;
            }
            setAnimation(extinguishedAnimation);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void turnOn() {
        if(damage<100) {
            isOn=true;
            setAnimation(normalAnimation);
            for (EnergyConsumer device : devices
            ) {
                device.setPowered(true);
            }
        }else {
            setAnimation(brokenAnimation);
        }
    }

    @Override
    public void turnOff() {
        isOn=false;
        if(damage<100) {
            setAnimation(offAnimation);
        }else {
            setAnimation(brokenAnimation);
        }
        for (EnergyConsumer device: devices
        ) {
            device.setPowered(false);
        }
    }

    public boolean isOn() {
        if (isOn) {return true;}
        else {return false;}
    }

    public void addDevice(EnergyConsumer device) {
        if(device!=null) {
            devices.add(device);
            if (isOn())
            device.setPowered(true);
        }
    }

    public void removeDevice(EnergyConsumer device) {
        devices.remove(device);
        device.setPowered(false);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
        turnOff();
    }
}

