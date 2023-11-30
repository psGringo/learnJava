package com.example.main.life.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class Map implements Cloneable {
    @Value("${maxX:100}")
    private int maxX;
    @Value("${maxY:100}")
    private int maxY;

    private int topBorder = 0;
    private int leftBorder = 0;
    private int bottomBorder = -1;
    private int rightBorder = -1;

    public void setMaxX(int maxX) {
        this.maxX = maxX;
        rightBorder = maxX - 1;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
        bottomBorder = maxY - 1;
    }

    @SneakyThrows
    public Map clone() {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(this);
        Map deepCopy = objectMapper.readValue(value, Map.class);
        return deepCopy;
    }
}
