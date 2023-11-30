package com.example.main.life.models.animals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Animal extends org.openapi.life.model.GodParticleUi {
    private org.openapi.life.model.SexUi sex;
    private org.openapi.life.model.MovementDirectionUi movementDirection;
    private int velocity;
}
