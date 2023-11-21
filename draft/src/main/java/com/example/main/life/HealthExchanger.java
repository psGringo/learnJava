package com.example.main.life;

import org.openapi.life.model.GodParticleUi;

@FunctionalInterface
public interface HealthExchanger<T extends GodParticleUi> {
    void changeHealth(T eater, T food);
}
