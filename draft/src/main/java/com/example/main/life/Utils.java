package com.example.main.life;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.openapi.life.model.GodParticleUi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Utils {


    private static int maxHealth;

    private static int maxHunger;

    private static int maxHungerSatisfaction;

    @Value("#{T(java.lang.Integer).parseInt('${maxHealth}')}")
    private int maxHealthInjection;

    @Value("#{T(java.lang.Integer).parseInt('${maxHunger}')}")
    private int maxHungerInjection;

    @Value("#{T(java.lang.Integer).parseInt('${maxHungerSatisfaction}')}")
    private int maxHungerSatisfactionInjection;

    @PostConstruct
    public void init() {
        maxHealth = maxHealthInjection;
        maxHunger = maxHungerInjection;
        maxHungerSatisfaction = maxHungerSatisfactionInjection;
    }


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
        } else {
            eater.setHungerSatisfaction(newHungerSatisfaction);
        }

        // new food health
        int newFoodHealth = foodHealth - diff;
        if (newFoodHealth < 0) {
            food.setHealth(0);
        } else {
            food.setHealth(newFoodHealth);
        }
    };

    public static <T extends GodParticleUi> List<T> getAliveGodParticles(List<T> godParticles) {
        return godParticles.stream().filter(t -> t.getHealth() > 0 && t.getAge() <= 99).collect(Collectors.toList());
    }

}
