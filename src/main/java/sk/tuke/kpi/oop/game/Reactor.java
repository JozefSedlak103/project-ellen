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

    public void increaseTemperature(int increment) {
        if(!isOn) {
            return;
        }
        if(increment<0) {
            increment=0;
        }

        if(getDamage()<33) {
            temperature = getTemperature() + increment;
        } else if(getDamage()>=33 && getDamage()<=66) {
            temperature = (int) Math.ceil(getTemperature() + (increment*1.5));
        } else if (getDamage()>66){
            temperature = (int) Math.ceil(getTemperature() + (increment*2));
        }
        if (getTemperature() >= 2000) {
            damage = (int) Math.floor((getTemperature() - 2000) / 40);
        } else if(getTemperature()<2000) {
            damage = 0;
        }
        if (getDamage()>=100) {
            damage = 100;
            updateAnimation();
            isOn=false;
            if(device!=null) {
                device.setPowered(false);
            }
        }
        updateAnimation();
    }

    private void decTempRep() {
        temperature = getTemperature() - 2000;
    }

    public void decreaseTemperature(int decrement) {
        if(!isOn) return;
        if(decrement<0) decrement=0;
        if(getDamage()>=50 && getDamage()<100 && getTemperature()>=0) {
            temperature = getTemperature() - (decrement/2);
            if (getTemperature()<0) temperature = 0;
        }
        if (getDamage()<50 && getTemperature()>=0) {
            temperature = getTemperature() - decrement;
            if (getTemperature()<0) temperature = 0;
        }
        updateAnimation();
    }

    private void updateAnimation() {
        if (isOn) {
            if (getTemperature() < 4000 && getDamage() < 100) {
                setAnimation(normalAnimation);
            } else if (getTemperature() >= 4000 && getDamage() < 100 && getTemperature()<6000) {
                setAnimation(hotAnimation);
            } else if (getDamage() >= 100 && getTemperature()>=6000) {
                setAnimation(brokenAnimation);
            } else if (getTemperature()<6000 && getDamage()>=100) {
                setAnimation(extinguishedAnimation);
            }

        } else {
            if(getDamage()>99) {
                setAnimation(brokenAnimation);
            }
            else {
                setAnimation(offAnimation);
            }
        }
    }

    public boolean repair() {
        if (getDamage()>0 && getDamage()<100) {
            damage = getDamage()-50;
            decTempRep();
            if(2000+(damage*40)<temperature) {
                setTemperature(2000 + (damage * 40));
            }

            if(getDamage()<0) damage=0;
            updateAnimation();
            return true;
        }
        return false;

    }

    //uloha 4.3 otestovat funkcnost
    //pokracovat ulohou 4.4

    private void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean extinguish() {
        temperature = temperature - 4000;
        if(temperature<0) temperature = 0;
        updateAnimation();
        return true;
    }

    public void turnOn() {
        if(device!=null && getDamage()<100) {
            device.setPowered(true);
            isOn = true;
            updateAnimation();
        }
    }

    public void turnOff() {
        if(device!=null) {
            device.setPowered(false);
            isOn = false;
            updateAnimation();
        }
    }

    public boolean isOn() {
        if (isOn) {return true;}
        else {return false;}
    }

    public void addDevice(EnergyConsumer device) {
        if(device!=null) {
            devices.add(device);
            device.setPowered(true);
        }
    }

    public void removeDevice(EnergyConsumer device) {
        if (device!=null) {
            devices.remove(device);
            device.setPowered(false);
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
        turnOff();
    }
}

