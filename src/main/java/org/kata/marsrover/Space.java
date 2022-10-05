package org.kata.marsrover;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Space {
    private Point coordinates;
    private Point maxCoordinates;
    private Direction direction;
    private List<Obstacle> obstacles;
    private boolean foundObstacle;

    boolean move(Direction directionValue) {
        int xLocation = coordinates.getX();
        int yLocation = coordinates.getY();
        switch (directionValue) {
            case NORTH -> yLocation = getForwardLocation(coordinates.getY(),maxCoordinates.getY());
            case EAST -> xLocation = getForwardLocation(coordinates.getX(),maxCoordinates.getX());
            case WEST -> xLocation = getBackwardLocation(coordinates.getX(), maxCoordinates.getX());
            case SOUTH -> yLocation = getBackwardLocation(coordinates.getY(), maxCoordinates.getY());
        }

        if (!hasObstacle(xLocation, yLocation)) {
            coordinates.setX(xLocation);
            coordinates.setY(yLocation);
            return true;
        } else {
            return false;
        }
    }

    private boolean hasObstacle(int xLocation, int yLocation) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == xLocation && obstacle.getY() == yLocation) {
                foundObstacle = true;
                return true;
            }
        }
        return false;
    }

    public boolean moveForward() {
        return move(direction);
    }

    public boolean moveBackward() {
        return move(direction.getBackwardDirection());
    }

    private void changeDirection(Direction directionValue, int directionStep) {
        int len = Direction.values().length;
        int index = (len + directionValue.ordinal() + directionStep) % len;
        direction = Direction.values()[index];
    }

    public void changeDirectionLeft() {
        changeDirection(direction, -1);
    }

    public void changeDirectionRight() {
        changeDirection(direction, 1);
    }
/*
If maximum location is reached, forward/backward methods wrap location.
 */
    private int getForwardLocation(int location, int maxLocation) {
        return (location +1)%(maxLocation+1);
    }

    private int getBackwardLocation(int location, int maxLocation) {
        return location > 0 ? location-1 : maxLocation;
    }

    @Override
    public String toString() {
        String status = "";
        if (foundObstacle) {
            status = " NOK";
        }
        return coordinates.getX() + " X " + coordinates.getY() + " Y " + getDirection().getShortName() + status;
    }
}
