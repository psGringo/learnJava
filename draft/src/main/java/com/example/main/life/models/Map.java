package com.example.main.life.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class Map {
    @Value("${maxX:100}")
    @Setter
    private int maxX;
    @Value("${maxY:100}")
    @Setter
    private int maxY;
    private int topBorder = 0;
    private int leftBorder = 0;
    private int bottomBorder = maxY - 1;
    private int rightBorder = maxX - 1;
}
