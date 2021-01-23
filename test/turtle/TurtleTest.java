package turtle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTest {
    Turtle turtle;
    Pen turtlePen;
    SketchPad sketchPad;

    @BeforeEach
    void setUp() {
        sketchPad = new SketchPad();
        sketchPad = new SketchPad();
        turtlePen = new Pen();
        turtle  = new Turtle(turtlePen);
    }

    @AfterEach
    void tearDown() {
        turtlePen = null;
        turtle = null;
    }

    @Test
    void turtle_canHasAPen(){
        assertNotNull(turtle.getPen());
    }

    @Test
    void turtlePen_canBeSet(){
       turtle.getPen().setPenOrientation(PenOrientation.PEN_UP);
        PenOrientation actual = turtle.getPen().getPenOrientation();
        assertEquals(PenOrientation.PEN_UP, actual);
    }

    @Test
    void turtle_canTurnRight(){
       turtle.turnRight();
       assertEquals(CurrentDirection.SOUTH, turtle.getCurrentDirection());

        turtle.turnRight();
        assertEquals(CurrentDirection.WEST, turtle.getCurrentDirection());

        turtle.turnRight();
        assertEquals(CurrentDirection.NORTH, turtle.getCurrentDirection());

        turtle.turnRight();
        assertEquals(CurrentDirection.EAST, turtle.getCurrentDirection());
    }

    @Test
    void turtle_canTurnLeft(){
        turtle.turnLeft();
        assertEquals(CurrentDirection.NORTH, turtle.getCurrentDirection());

        turtle.turnLeft();
        assertEquals(CurrentDirection.WEST, turtle.getCurrentDirection());

        turtle.turnLeft();
        assertEquals(CurrentDirection.SOUTH, turtle.getCurrentDirection());

        turtle.turnLeft();
        assertEquals(CurrentDirection.EAST, turtle.getCurrentDirection());
    }


    @Test
    void turtle_canUseSketchPad(){
        assertNotNull(turtle.getSketchPad());
    }

    @Test
    void turtlePen_canMoveForward(){
        turtle.setYCoordinate(0);
        turtle.setXCoordinate(0);

        turtle.setCurrentDirection(CurrentDirection.EAST);
        turtle.moveForward(12);
        assertEquals(12, turtle.getYCoordinate());


        turtle.setCurrentDirection(CurrentDirection.WEST);
        turtle.moveForward(3);
        assertEquals(9, turtle.getYCoordinate());

        turtle.setCurrentDirection(CurrentDirection.SOUTH);
        turtle.moveForward(8);
        assertEquals(8, turtle.getXCoordinate());

        turtle.setCurrentDirection(CurrentDirection.NORTH);
        turtle.moveForward(6);
        assertEquals(2, turtle.getXCoordinate());
    }
}