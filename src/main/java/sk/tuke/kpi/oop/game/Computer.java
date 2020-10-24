package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor implements EnergyConsumer {
    Animation computerAnimation;
    //Animation computerOff;
    private boolean isPowered;

    public Computer() {
        //computerOff = new Animation("sprites/computer.png",80,80);
        computerAnimation = new Animation("sprites/computer.png",80,48,0.2f,Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(computerAnimation);
    }

    public int add(int a, int b) {
        if(isPowered) {
            return a + b;
        } else {
            return 0;
        }
    }

    public int sub(int a,int b) {
        if(isPowered) {
            return a - b;
        } else {
            return 0;
        }
    }

    public float add(float a, float b) {
        if(isPowered) {
            return a + b;
        } else {
            return 0;
        }
    }

    public float sub(float a, float b) {
        if(isPowered) {
            return a - b;
        } else {
            return 0;
        }
    }

    private void updateAnimation() {
        if (isPowered) {
            computerAnimation.play();
        } else {
            computerAnimation.pause();

        }
    }

    @Override
    public void setPowered(boolean isPowered) {
        this.isPowered = isPowered;
        updateAnimation();
    }
}
