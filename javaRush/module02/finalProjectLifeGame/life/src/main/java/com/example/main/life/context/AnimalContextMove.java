package com.example.main.life.context;

import com.example.main.life.models.Map;
import com.example.main.life.models.animals.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class AnimalContextMove {
    private Integer correctIfNeededXPosition(int position, Map map) {
        if (position < map.getLeftBorder())
            return map.getLeftBorder();
        else if (position > map.getRightBorder()) {
            return map.getRightBorder();
        } else return position;
    }

    private Integer correctIfNeededYPosition(int position, Map map) {
        if (position < map.getTopBorder()) {
            return map.getTopBorder();
        } else if (position > map.getBottomBorder()) {
            return map.getBottomBorder();
        } else return position;
    }

    private void correctIfNeededDirection(Animal animal, Map map) {

        boolean isNearTopBorder = animal.getPositionY() <= map.getTopBorder();
        boolean isNearBottomBorder = animal.getPositionY() >= map.getBottomBorder();
        boolean isNearLeftBorder = animal.getPositionX() <= map.getLeftBorder();
        boolean isNearRightBorder = animal.getPositionX() >= map.getRightBorder();

        boolean isInTheTopLeftCorner = isNearTopBorder && isNearLeftBorder;
        boolean isInTheTopRightCorner = isNearTopBorder && isNearRightBorder;
        boolean isInTheBottomLeftCorner = isNearBottomBorder && isNearLeftBorder;
        boolean isInTheBottomRightCorner = isNearBottomBorder && isNearRightBorder;

        List<org.openapi.life.model.MovementDirectionUi> possibleDirections = new ArrayList<>();

        // choose direction
        if (isInTheTopLeftCorner) { // check corners

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.RIGHT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.DOWN);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.BOTTOMRIGHT);

        } else if (isInTheTopRightCorner) {

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.LEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.DOWN);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.BOTTOMLEFT);

        } else if (isInTheBottomLeftCorner) {

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.RIGHT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UP);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UPRIGHT);

        } else if (isInTheBottomRightCorner) {

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.LEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UP);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UPLEFT);

        } else if (isNearBottomBorder) { // borders

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.LEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.RIGHT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UP);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UPLEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UPRIGHT);

        } else if (isNearTopBorder) {

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.LEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.RIGHT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.DOWN);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.BOTTOMLEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.BOTTOMRIGHT);

        } else if (isNearLeftBorder) {

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.RIGHT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UP);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.DOWN);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UPRIGHT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.BOTTOMRIGHT);

        } else if (isNearRightBorder) {

            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.LEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UP);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.DOWN);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.UPLEFT);
            possibleDirections.add(org.openapi.life.model.MovementDirectionUi.BOTTOMLEFT);

        } else {
            // free random direction
            for (org.openapi.life.model.MovementDirectionUi value : org.openapi.life.model.MovementDirectionUi.values()) {
                possibleDirections.add(value);
            }
        }

        Assert.isTrue(possibleDirections.size() > 0);
        var currentAnimalDirection = animal.getMovementDirection();

        boolean needChangeDirection = currentAnimalDirection == null || !possibleDirections.contains(currentAnimalDirection);

        // changing direction if needed
        // TODO check possibleDirections.contains(currentAnimalDirection) works normally
        if (needChangeDirection) {
            Random random = new Random();
            int nextDirectionIndex = random.nextInt(possibleDirections.size());
            org.openapi.life.model.MovementDirectionUi newDirection = possibleDirections.get(nextDirectionIndex);
            animal.setMovementDirection(newDirection);
        }
    }

    private void handleMoveAnimal(Animal animal, Map map) {

        org.openapi.life.model.MovementDirectionUi direction = animal.getMovementDirection();

        if (direction == org.openapi.life.model.MovementDirectionUi.UP) {

            int newPosition = animal.getPositionY() - animal.getVelocity();
            newPosition = correctIfNeededYPosition(newPosition, map);
            animal.setPositionY(newPosition);

        } else if (direction == org.openapi.life.model.MovementDirectionUi.DOWN) {

            int newPosition = animal.getPositionY() + animal.getVelocity();
            newPosition = correctIfNeededYPosition(newPosition, map);
            animal.setPositionY(newPosition);

        } else if (direction == org.openapi.life.model.MovementDirectionUi.LEFT) {

            int newPosition = animal.getPositionX() - animal.getVelocity();
            newPosition = correctIfNeededXPosition(newPosition, map);
            animal.setPositionX(newPosition);

        } else if (direction == org.openapi.life.model.MovementDirectionUi.RIGHT) {

            int newPosition = animal.getPositionX() + animal.getVelocity();
            newPosition = correctIfNeededXPosition(newPosition, map);
            animal.setPositionX(newPosition);

        } else if (direction == org.openapi.life.model.MovementDirectionUi.UPLEFT) {
            int newPositionX = animal.getPositionX() - animal.getVelocity();
            newPositionX = correctIfNeededXPosition(newPositionX, map);
            animal.setPositionX(newPositionX);

            int newPositionY = animal.getPositionY() - animal.getVelocity();
            newPositionY = correctIfNeededYPosition(newPositionY, map);
            animal.setPositionY(newPositionY);

        } else if (direction == org.openapi.life.model.MovementDirectionUi.UPRIGHT) {
            int newPositionX = animal.getPositionX() + animal.getVelocity();
            newPositionX = correctIfNeededXPosition(newPositionX, map);
            animal.setPositionX(newPositionX);

            int newPositionY = animal.getPositionY() - animal.getVelocity();
            newPositionY = correctIfNeededYPosition(newPositionY, map);
            animal.setPositionY(newPositionY);

        } else if (direction == org.openapi.life.model.MovementDirectionUi.BOTTOMLEFT) {

            int newPositionX = animal.getPositionX() - animal.getVelocity();
            newPositionX = correctIfNeededXPosition(newPositionX, map);
            animal.setPositionX(newPositionX);

            int newPositionY = animal.getPositionY() + animal.getVelocity();
            newPositionY = correctIfNeededYPosition(newPositionY, map);
            animal.setPositionY(newPositionY);

        } else if (direction == org.openapi.life.model.MovementDirectionUi.BOTTOMRIGHT) {
            int newPositionX = animal.getPositionX() + animal.getVelocity();
            newPositionX = correctIfNeededXPosition(newPositionX, map);
            animal.setPositionX(newPositionX);

            int newPositionY = animal.getPositionY() + animal.getVelocity();
            newPositionY = correctIfNeededYPosition(newPositionY, map);
            animal.setPositionY(newPositionY);
        }

        correctIfNeededDirection(animal, map);
    }

    /**
     * @param animal someAnimal
     * @param map    just a map for all god particles
     */
    private void moveAnimal(Animal animal, Map map) {

        correctIfNeededDirection(animal, map);
        handleMoveAnimal(animal, map);
    }

    public void move(List<Animal> animals, Map map) {
        animals.forEach(animal -> {
            moveAnimal(animal, map);
        });
    }
}
