package com.example.main.life;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import org.openapi.life.model.GodParticleUi;
import org.springframework.beans.factory.annotation.Value;


public class Utils {

    @Value("maxHealth: 100")
    private static int maxHealth;

    @Value("maxHunger: 100")
    private static int maxHunger;

    @Value("maxHungerSatisfaction: 100")
    private static int maxHungerSatisfaction;

    public static <T extends GodParticleUi> void recoverHealth(T godParticle) {
        if (godParticle.getHealth() == Const.maxHealth)
            return;

        HealthRecoverer<GodParticleUi> healthRecoverer = doRecoverHealth;

        healthRecoverer.recoverHealth(godParticle);
    }

    public static <T extends GodParticleUi> void exchangeHealth(T eater, T food) {
        if (eater.getHungerSatisfaction() == maxHungerSatisfaction || food.getHealth().equals(0))
            return;

        HealthExchanger<GodParticleUi> healthExchanger = doExchangeHealth;
        healthExchanger.changeHealth(eater, food);
    }


    private static HealthRecoverer<GodParticleUi> doRecoverHealth = (godParticleUi) -> {
        int health = godParticleUi.getHealth();
        int maxHealthIncrease = (Const.maxHealth - health);
        int healthIncrease = ThreadLocalRandom.current().nextInt(maxHealthIncrease);
        godParticleUi.setHealth(health + healthIncrease);
    };

    private static HealthExchanger<GodParticleUi> doExchangeHealth = (eater, food) -> {

        // maxHealth from food
        // hunger

        int foodHealth = food.getHealth();
        int hunger = maxHunger - eater.getHungerSatisfaction();

        int maxDiff = 0;

        // how much we can eat
        if (hunger >= foodHealth) {
            maxDiff = foodHealth;
        } else if (hunger < foodHealth) {
            maxDiff = hunger;
        }

        int diff = ThreadLocalRandom.current().nextInt(maxDiff);

        // new hunger satisfaction
        int newHungerSatisfaction = eater.getHungerSatisfaction() + diff;
        if (newHungerSatisfaction > maxHungerSatisfaction) {
            eater.setHungerSatisfaction(maxHungerSatisfaction);
        }

        // new food health
        int newFoodHealth = foodHealth - diff;
        if (newFoodHealth < 0) {
            food.setHealth(0);
        }
    };

    public static <T extends GodParticleUi> List<T> getAliveGodParticles(List<T> godParticles) {
        return godParticles.stream().filter(t -> t.getAge() >= 99).collect(Collectors.toList());
    }

}
