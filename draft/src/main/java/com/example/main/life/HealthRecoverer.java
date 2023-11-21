package com.example.main.life;

import org.openapi.life.model.GodParticleUi;

@FunctionalInterface
public interface HealthRecoverer<T extends GodParticleUi> {
    void recoverHealth(T eater);
}
