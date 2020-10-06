package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Reactor extends AbstractActor {
    int temperature;
    int damage;
    Animation normalAnimation;
    Animation hotAnimation;
    Animation brokenAnimation;

    public Reactor() {
        normalAnimation = new Animation("sprites/reactor_on.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
        brokenAnimation = new Animation("sprites/reactor_broken.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(normalAnimation);
    }

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    public void increaseTemperature(int increment) {
        if(increment<0) return;
        if (getTemperature() > 2000 && getTemperature()<6000) {
            if(getDamage()<33) {
                temperature = getTemperature() + increment;
            }
            if(getDamage()>=33 && getDamage()<=66) {
                temperature = (int) Math.ceil(getTemperature() + (increment*1.5));
            }
            if (getDamage()>66){
                temperature = (int) Math.ceil(getTemperature() + (increment*2));
            }

            damage = (int) Math.floor((getTemperature() - 2000) / 40);
            if (getTemperature() >4000) {
                updateAnimation();
                //setAnimation(hotAnimation);
            }
        }
        if (getTemperature() >=6000 && getDamage()>=100) {
            damage = 100;
            updateAnimation();
            //setAnimation(brokenAnimation);
        }
    }

    public void decreaseTemperature(int decrement) {
        if(decrement<0) return;
        if(getDamage()>=50 && getDamage()<100 && getTemperature()>=0) {
            temperature = getTemperature() - (decrement/2);
            if (getTemperature()<0) temperature = 0;
        }
        if (getDamage()<50 && getTemperature()>=0) {
            temperature = getTemperature() - decrement;
            if (getTemperature()<0) temperature = 0;
        }
        if (getDamage()>=100) {
            temperature = getTemperature();
        }
        if (getTemperature()<=4000) {
            updateAnimation();
            //setAnimation(normalAnimation);
        }
    }

    private void updateAnimation() {
        if (getTemperature()>=0 && getTemperature()<4000)
            setAnimation(normalAnimation);
        if (getTemperature()>=4000 && getTemperature()<6000)
            setAnimation(hotAnimation);
        if (getTemperature()>6000)
            setAnimation(brokenAnimation);
    }
    //urcite nefunguje spravne uloha 2.1
    private void repairWith(Hammer hammer) {
        if (hammer!=null && getDamage()>0 && getDamage()<100) {
            damage = getDamage()-50;
            if(getDamage()<0) damage=0;
            updateAnimation();
        }
    }

}

