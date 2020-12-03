package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {

    private int currentHealth, maxHealth;
    private List<ExhaustionEffect> effectList;
    private boolean firstTime;

    public Health(int startHealth, int maxHealth) {
        this.currentHealth = startHealth;
        this.maxHealth = maxHealth;
        effectList = new ArrayList<>();
        firstTime = true;
    }

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        effectList = new ArrayList<>();
        firstTime = true;
    }

    public int getValue() {
        return currentHealth;
    }

    public void refill(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public void restore() {
        currentHealth = maxHealth;
    }

    public void drain(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        if(currentHealth == 0){
            exhaust();
        }
    }

    public void exhaust() {
        currentHealth = 0;
        if (firstTime) {
            effectList.forEach(ExhaustionEffect::apply);
            firstTime = false;
        }
    }

    public void onExhaustion(ExhaustionEffect effect) {
        effectList.add(effect);
    }


    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

}
