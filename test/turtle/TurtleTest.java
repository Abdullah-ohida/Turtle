package turtle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurtleTest {
    Turtle turtle;
    Pen pen;
    SketchPad sketchBoard;

    @BeforeEach
    void setUp() {
        pen = new Pen();
        turtle = new Turtle(pen);
        sketchBoard = new SketchPad(20, 20);
    }

    @AfterEach
    void tearDown() {
        turtle = null;
    }

    @Test
    void turtle_hasAPen(){
        assertNotNull(turtle.getPen());
    }

    @Test
    void turtlePen_PositionCanBeUp(){
        turtle.getPen().setPenPosition(Position.PEN_UP);
        Position actual = turtle.getPen().getPenPosition();
        assertEquals(Position.PEN_UP, actual);
    }

    @Test
    void turtlePen_PositionCanBeDown(){
        turtle.getPen().setPenPosition(Position.PEN_DOWN);
        Position actual = turtle.getPen().getPenPosition();
        assertEquals(Position.PEN_DOWN, actual);
    }

    @Test
    void turtlePen_canTurnRight(){
        turtle.turnRight();
        assertEquals(Direction.SOUTH, turtle.getCurrentDirection());

        turtle.turnRight();
        assertEquals(Direction.WEST, turtle.getCurrentDirection());

        turtle.turnRight();
        assertEquals(Direction.NORTH, turtle.getCurrentDirection());

        turtle.turnRight();
        assertEquals(Direction.EAST, turtle.getCurrentDirection());
    }

    @Test
    void turtlePen_canTurnLeft(){
        turtle.turnLeft();
        assertEquals(Direction.NORTH, turtle.getCurrentDirection());

        turtle.turnLeft();
        assertEquals(Direction.WEST, turtle.getCurrentDirection());

        turtle.turnLeft();
        assertEquals(Direction.SOUTH, turtle.getCurrentDirection());

        turtle.turnLeft();
        assertEquals(Direction.EAST, turtle.getCurrentDirection());
    }

    @Test
    void turtleSketchPad_canMoveEastWard_onSketchPad_whilePenIsUp(){
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0, 0));
        int stepToMoves = 7;
        turtle.move(sketchBoard, stepToMoves);
        assertEquals(new SketchPadPosition(0, 6), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtleSketchPad_canMoveSouthWard_onSketchPad_whilePenIsUp(){
        turtle.turnRight();
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0, 0));
        int stepToMoves = 7;
        turtle.move(sketchBoard, stepToMoves);
        assertEquals(new SketchPadPosition(6, 0), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtleSketchPad_canMoveWestWard_onSketchPad_whilePenIsUp(){
        turtle.setCurrentDirection(Direction.WEST);
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0, 7));
        int stepToMoves = 7;
        turtle.move(sketchBoard, stepToMoves);
        assertEquals(new SketchPadPosition(0, 1), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtleSketchPad_canMoveNorthWard_onSketchPad_whilePenIsUp(){
        turtle.setCurrentDirection(Direction.NORTH);
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(7, 0));
        int stepToMoves = 5;
        turtle.move(sketchBoard, stepToMoves);
        assertEquals(new SketchPadPosition(3, 0), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtle_canWriteOnSketchPad_whileFacingEast(){
        turtle.getPen().setPenPosition(Position.PEN_DOWN);
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0, 0));
        int numberOfSteps = 7;
        turtle.move(sketchBoard, numberOfSteps);

        for(int column = 0; column < numberOfSteps; column++){
            int expected = sketchBoard.getSketchBoard()[0][column];
            assertEquals(1, expected);
            System.out.println(sketchBoard.getSketchBoard()[0][column]);
        }

        assertEquals(new SketchPadPosition(0, 6), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtle_canWriteOnSketchPad_whileFacingSouth(){
        turtle.getPen().setPenPosition(Position.PEN_DOWN);
        turtle.turnRight();
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0, 0));
        int numberOfSteps = 7;
        turtle.move(sketchBoard, numberOfSteps);

        for(int row = 0; row < numberOfSteps; row++){
            int expected = sketchBoard.getSketchBoard()[row][0];
            assertEquals(1, expected);
            System.out.println(sketchBoard.getSketchBoard()[row][0]);
        }
        assertEquals(new SketchPadPosition(6, 0), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtle_canWriteOnSketchPad_whileFacingWest(){
        turtle.getPen().setPenPosition(Position.PEN_DOWN);
        turtle.setCurrentDirection(Direction.WEST);
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0, 7));
        int numberOfSteps = 5;
        turtle.move(sketchBoard, numberOfSteps);

        for(int column = 7; column > numberOfSteps; column--){
            int expected = sketchBoard.getSketchBoard()[0][column];
            assertEquals(1, expected);
            System.out.println(sketchBoard.getSketchBoard()[0][column]);
        }
        assertEquals(new SketchPadPosition(0, 3), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtle_canWriteOnSketchPad_whileFacingNorth(){
        turtle.getPen().setPenPosition(Position.PEN_DOWN);
        turtle.setCurrentDirection(Direction.NORTH);
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(8, 0));
        int numberOfSteps = 6;
        turtle.move(sketchBoard, numberOfSteps);

        for(int row = 8; row > numberOfSteps; row--){
            int expected = sketchBoard.getSketchBoard()[row][0];
            assertEquals(1, expected);
            System.out.println(sketchBoard.getSketchBoard()[row][0]);
        }
        assertEquals(new SketchPadPosition(3, 0), turtle.getCurrentSketchPadPosition());
    }

    @Test
    void turtleSketchPad_canDraw(){
        turtle.getPen().setPenPosition(Position.PEN_DOWN);
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0,0));
        sketchBoard = new SketchPad(1, 2);
        turtle.move(sketchBoard, 2);
        String expectedDraw = "# # \n";
        assertEquals(expectedDraw, sketchBoard.displayTurtleDesign());
    }

    @Test
    void turtleSketchPad_canDisplayDiagram(){
        turtle.getPen().setPenPosition(Position.PEN_DOWN);
        turtle.setCurrentSketchPadPosition(new SketchPadPosition(0, 0));
        sketchBoard = new SketchPad(20, 20);
        turtle.move(sketchBoard, 10);

        turtle.turnRight();
        turtle.move(sketchBoard, 10);

        turtle.turnRight();
        turtle.move(sketchBoard, 10);

        turtle.turnRight();
        turtle.move(sketchBoard, 8);

        turtle.turnRight();
        turtle.move(sketchBoard, 8);

        turtle.turnRight();
        turtle.move(sketchBoard, 8);

        turtle.turnRight();
        turtle.move(sketchBoard, 6);

        turtle.turnRight();
        turtle.move(sketchBoard, 6);

        turtle.turnRight();
        turtle.move(sketchBoard, 6);

        turtle.turnRight();
        turtle.move(sketchBoard, 4);

        turtle.turnRight();
        turtle.move(sketchBoard, 4);

        turtle.turnRight();
        turtle.move(sketchBoard, 4);

        turtle.turnRight();
        turtle.move(sketchBoard, 2);

        turtle.turnRight();
        turtle.move(sketchBoard, 2);

        turtle.turnRight();
        turtle.move(sketchBoard, 2 );

        System.out.println(sketchBoard.displayTurtleDesign());
    }

}