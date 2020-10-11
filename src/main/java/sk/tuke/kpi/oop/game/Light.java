package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {
    public boolean isOn;
    public boolean isPowered;
    Animation lightAnimOn;
    Animation lightAnimOff;

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

    public void setElectricityFlow(boolean electricityFlow) {
        this.isPowered = electricityFlow;
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

}
