package org.kata.marsrover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    private Rover rover;
    private Space roverSpace;
    private final Direction direction = Direction.NORTH;
    private Point coordinates;
    private Point maxCoordinates;
    private List<Obstacle> obstacles;

    @BeforeEach
    public void beforeRoverTest() {
        coordinates = new Point(1,2);
        maxCoordinates = new Point(10,10);
        obstacles = new ArrayList<>();
        roverSpace = new Space(coordinates, maxCoordinates, direction, obstacles, false);
        rover = new Rover(roverSpace);
    }

    @Test
    public void newInstanceShouldSetRoverCoordinatesAndDirection() {
        assertSame(rover.getSpace(),roverSpace);
    }

    @Test
    public void receiveSingleCommandShouldMoveForwardWhenCommandIsF() throws Exception {
        int expected = coordinates.getY() + 1;
        rover.receiveSingleCommand('F');
        assertEquals(rover.getSpace().getCoordinates().getY(),expected);
    }

    @Test
    public void receiveSingleCommandShouldMoveBackwardWhenCommandIsB() throws Exception {
        int expected = coordinates.getY() - 1;
        rover.receiveSingleCommand('B');
        assertEquals(rover.getSpace().getCoordinates().getY(),expected);
    }

    @Test
    public void receiveSingleCommandShouldTurnLeftWhenCommandIsL() throws Exception {
        rover.receiveSingleCommand('L');
        assertEquals(rover.getSpace().getDirection(),Direction.WEST);
    }

    @Test
    public void receiveSingleCommandShouldTurnRightWhenCommandIsR() throws Exception {
        rover.receiveSingleCommand('R');
        assertEquals(rover.getSpace().getDirection(),Direction.EAST);
    }

    @Test
    public void receiveSingleCommandShouldIgnoreCase() throws Exception {
        rover.receiveSingleCommand('r');
        assertEquals(rover.getSpace().getDirection(),Direction.EAST);
    }

    @Test
    public void receiveSingleCommandShouldThrowExceptionWhenCommandIsUnknown() {
       assertThrows(Exception.class, ()->{
           rover.receiveSingleCommand('X');
       });
    }

    @Test
    public void receiveCommandsShouldBeAbleToReceiveMultipleCommands() throws Exception {
        int expected = coordinates.getX() + 1;
        rover.receiveCommands("RFR");
        assertEquals(rover.getSpace().getCoordinates().getX(),expected);
        assertEquals(rover.getSpace().getDirection(),Direction.SOUTH);
    }


    @Test
    public void receiveCommandShouldWhatFromOneEdgeOfTheGridToAnother() throws Exception {
        int expected = maxCoordinates.getX() + coordinates.getX() - 2;
        rover.receiveCommands("LFFF");
        assertEquals(rover.getSpace().getCoordinates().getX(),expected);
    }

    @Test
    public void receiveCommandsShouldStopWhenObstacleIsFound() throws Exception {
        int expected = coordinates.getX() + 1;
        rover.getSpace().setObstacles(List.of(new Obstacle(expected + 1, coordinates.getY())));
        rover.getSpace().setDirection(Direction.EAST);
        rover.receiveCommands("FFFRF");
        assertEquals(rover.getSpace().getCoordinates().getX(),expected);
        assertEquals(rover.getSpace().getDirection(),Direction.EAST);
    }

    @Test
    public void positionShouldReturnXYAndDirection() throws Exception {
        rover.receiveCommands("LFFFRFF");
        assertEquals(rover.getPosition(),"9 X 4 Y N");
    }

    @Test
    public void positionShouldReturnNokWhenObstacleIsFound() throws Exception {
        rover.getSpace().setObstacles(List.of(new Obstacle(coordinates.getX() + 1, coordinates.getY())));
        rover.getSpace().setDirection(Direction.EAST);
        rover.receiveCommands("F");
        assertTrue(rover.getPosition().endsWith(" NOK"));
    }

}
