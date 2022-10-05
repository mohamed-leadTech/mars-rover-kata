package org.kata.marsrover;

public enum Direction {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    private char shortname;

    Direction(char name) {
        shortname = name;
    }

    public Direction getBackwardDirection() {
        return values()[(this.ordinal()+ Direction.values().length/2) % 4];
    }


    public char getShortName() {
        return shortname;
    }
}
