package org.kata.marsrover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SpaceTest {

    private Point coordinates;
    private Point maxCoordinates;
    private List<Obstacle> obstacles;
    private final Direction direction = Direction.NORTH;
    private Space space;

    @BeforeEach
    public void beforeCoordinatesTest() {
        coordinates = new Point(1, 2);
        maxCoordinates = new Point(99, 99);
        obstacles = List.of(new Obstacle(20, 20), new Obstacle(30, 30));
        space = new Space(coordinates, maxCoordinates, direction, obstacles, false);
    }

    @Test
    public void newInstanceShouldSetXAndYParams() {
        assertSame(space.getCoordinates(),coordinates);
        assertSame(space.getMaxCoordinates(), maxCoordinates);
    }

    @Test
    public void newInstanceShouldSetDirection() {
        assertEquals(space.getDirection(),direction);
    }

    @Test
    public void newInstanceShouldSetObstacles() {
        assertSame(space.getObstacles(),obstacles);
    }

    @Test
    public void moveForwardShouldIncreaseYWhenDirectionIsNorth() {
        Point expected = new Point(coordinates.getX(), coordinates.getY()+1);
        space.setDirection(Direction.NORTH);
        space.moveForward();
        assertEquals(space.getCoordinates(),expected);
    }

    @Test
    public void moveForwardShouldIncreaseXWhenDirectionIsEast() {
        Point expected = new Point(coordinates.getX() + 1, coordinates.getY());
        space.setDirection(Direction.EAST);
        space.moveForward();
        assertEquals(space.getCoordinates(),expected);
    }

    @Test
    public void moveForwardShouldDecreaseYWhenDirectionIsSouth() {
        Point expected = new Point(coordinates.getX(), coordinates.getY()-1);
        space.setDirection(Direction.SOUTH);
        space.moveForward();
        assertEquals(space.getCoordinates(),expected);
    }

    @Test
    public void moveForwardShouldDecreaseXWhenDirectionIsWest() {
        Point expected = new Point(coordinates.getX() - 1, coordinates.getY());
        space.setDirection(Direction.WEST);
        space.moveForward();
        assertEquals(space.getCoordinates(),expected);
    }

    @Test
    public void moveForwardShouldNotChangeLocationsWhenObstacleIsFound() {
        int expected = coordinates.getX();
        space.setDirection(Direction.EAST);
        space.setObstacles(List.of(new Obstacle(coordinates.getX() + 1, coordinates.getY())));
        space.move(space.getDirection());
        assertEquals(coordinates.getX(),expected);
    }

    @Test
    public void moveBackwardShouldDecreaseYWhenDirectionIsNorth() {
        Point expected = new Point(coordinates.getX(), coordinates.getY()-1);
        space.setDirection(Direction.NORTH);
        space.moveBackward();
        assertEquals(coordinates,expected);
    }

    @Test
    public void moveBackwardShouldDecreaseXWhenDirectionIsEast() {
        Point expected = new Point(coordinates.getX() - 1, coordinates.getY());
        space.setDirection(Direction.EAST);
        space.moveBackward();
        assertEquals(coordinates, expected);
    }

    @Test
    public void moveBackwardShouldIncreaseYWhenDirectionIsSouth() {
        Point expected = new Point(coordinates.getX(), coordinates.getY()+1);
        space.setDirection(Direction.SOUTH);
        space.moveBackward();
        assertEquals(coordinates,expected);
    }

    @Test
    public void moveBackwardShouldIncreaseXWhenDirectionIsWest() {
        Point expected = new Point(coordinates.getX() + 1, coordinates.getY());
        space.setDirection(Direction.WEST);
        space.moveBackward();
        assertEquals(coordinates,expected);
    }

    @Test
    public void toStringShouldReturnXAndY() {
        String expected = coordinates.getX() + " X " + coordinates.getY() + " Y " + direction.getShortName();
        assertEquals(space.toString(),expected);
    }

}

