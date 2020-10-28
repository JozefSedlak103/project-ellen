package sk.tuke.kpi.oop.game;



import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor {
    private Switchable device;
    //private Animation switchAnim;

    public PowerSwitch(Switchable device) {
        this.device = device;
        Animation switchAnim = new Animation("sprites/switch.png");
        setAnimation(switchAnim);
    }
   /* public void toggle() {
        if(reactor.isOn()) {
            reactor.turnOff();
        } else {
            reactor.turnOn();
        }
    }
    */

    public Switchable getDevice() {
        return device;
    }

    public void switchOn() {
        if(device!=null) {
            device.turnOn();
            getAnimation().setTint(Color.WHITE);
        }

    }

    public void switchOff() {
        if (device != null) {
            device.turnOff();
            getAnimation().setTint(Color.GRAY);
        }
    }
}
