package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {
    int temperature;
    int damage;
    boolean isOn = false;
    public Light light;
    Animation normalAnimation;
    Animation hotAnimation;
    Animation brokenAnimation;
    Animation offAnimation;

    public Reactor() {
        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        brokenAnimation = new Animation("sprites/reactor_broken.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        normalAnimation = new Animation("sprites/reactor_on.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        offAnimation = new Animation("sprites/reactor.png");
        setAnimation(offAnimation);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    public void increaseTemperature(int increment) {
        if(!isOn) return;
        if(increment<0) {
            increment=0;
        }

        if(getDamage()<33) {
            temperature = getTemperature() + increment;
        }
        if(getDamage()>=33 && getDamage()<=66) {
            temperature = (int) Math.ceil(getTemperature() + (increment*1.5));
        }
        if (getDamage()>66){
            temperature = (int) Math.ceil(getTemperature() + (increment*2));
        }
        if (getTemperature() >= 0 && getTemperature()<6000) {
            damage = (int) Math.floor((getTemperature() - 2000) / 40);
        }
        if (getTemperature() >=6000 || getDamage()>=100) {
        damage = 100;
        isOn=false;
        light.setElectricityFlow(false);
        }
        if (getDamage()<0) damage=0;
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
            } else if (getTemperature() >= 4000 && getDamage() < 100) {
                setAnimation(hotAnimation);
            } else if (getTemperature() >= 6000 || getDamage() >= 100)
                setAnimation(brokenAnimation);
        } else {
            if(getDamage()>=100) {
                setAnimation(brokenAnimation);
            }
            else {
                setAnimation(offAnimation);
            }
        }
    }
    //urcite nefunguje spravne uloha 2.1
    public void repairWith(Hammer hammer) {
        if (hammer!=null && getDamage()>0 && getDamage()<100) {
            damage = getDamage()-50;
            decTempRep();
            hammer.use();
            if(getDamage()<0) damage=0;
            updateAnimation();
        }
    }

    public void turnOn() {
        isOn = true;
        updateAnimation();
        if(getDamage()<100) {
            light.setElectricityFlow(true);
        }
    }

    public void turnOff() {
        isOn = false;
        updateAnimation();
        light.setElectricityFlow(false);
    }

    public boolean isRunning() {
        if (isOn) {return true;}
        else {return false;}
    }

    public boolean isOk() {
        if(getDamage()<100) {
            return true;
        }
        else {
            return false;
        }
    }

    public void addLight(Light light) {
        if(this.light==null) {
            light.setElectricityFlow(true);
        }
        this.light = light;
    }

    public void removeLight() {
        if(this.light!=null) {
            light.setElectricityFlow(false);
        }
        this.light = null;
    }

}

