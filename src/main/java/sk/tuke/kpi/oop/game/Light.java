package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable, EnergyConsumer {
    private boolean isOn;
    private boolean isPowered;
    private Animation lightAnimOn;
    private Animation lightAnimOff;

    public Light() {
        this.isPowered = false;
        this.isOn = false;
        lightAnimOff = new Animation("sprites/light_off.png");
        lightAnimOn = new Animation("sprites/light_on.png");
        setAnimation(lightAnimOff);
    }

    public void toggle() {
        if (isOn) {
            isOn=false;
        } else {
            isOn = true;
        }
        updateAnimation();
    }


    private void updateAnimation() {
        if(isOn && isPowered) {
            setAnimation(lightAnimOn);
        }
        else {
            setAnimation(lightAnimOff);
        }
    }

    @Override
    public void turnOn() {
        isOn = true;
        updateAnimation();
    }

    @Override
    public void turnOff() {
        isOn = false;
        updateAnimation();

    }

    @Override
    public boolean isOn() {
        return isOn;
    }

    @Override
    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
        updateAnimation();
    }
}
