package org.kata.marsrover;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Point is a two-dimensional point on the grid.
New instance is created with initial x and y as coordinates.
*/
public class PointTest {

    Point point ;

    @BeforeEach
    public void beforePointTest() {
        point = new Point(1, 2);
    }

    @Test
    public void newInstanceShouldSetXandYparams() {
        assertEquals(point.getX(),1);
        assertEquals(point.getY(),2);

    }

    @Test
    public void newInstanceShouldUpdateXandYparams() {
        point.setX(5);
        point.setY(3);
        assertEquals(point.getX(),5);
        assertEquals(point.getY(),3);
    }

}
