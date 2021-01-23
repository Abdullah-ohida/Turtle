package turtle;

public class Turtle {

    private Pen pen;
    private CurrentDirection currentDirection;
    private int xCoordinate;
    private int yCoordinate;
    private String[][] sketchPad = new String[20][20];

    public Turtle(Pen pen) {
        this.pen = pen;
        this.xCoordinate = 0;
        this.yCoordinate = 0;
        this.currentDirection = CurrentDirection.EAST;
    }

    public String[][] getSketchPad() {
        return sketchPad;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Pen getPen() {
        return pen;
    }

    public CurrentDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(CurrentDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void turnRight() {
        switch (currentDirection) {
            case EAST -> setCurrentDirection(CurrentDirection.SOUTH);
            case SOUTH -> setCurrentDirection(CurrentDirection.WEST);
            case WEST -> setCurrentDirection(CurrentDirection.NORTH);
            case NORTH -> setCurrentDirection(CurrentDirection.EAST);
        }
    }

    public void turnLeft() {
        switch (currentDirection) {
            case EAST -> setCurrentDirection(CurrentDirection.NORTH);
            case SOUTH -> setCurrentDirection(CurrentDirection.EAST);
            case WEST -> setCurrentDirection(CurrentDirection.SOUTH);
            case NORTH -> setCurrentDirection(CurrentDirection.WEST);
        }
    }

    public void moveForward(int moves) {
        switch (currentDirection) {
            case EAST -> {
                    for (int column = 0; column < this.sketchPad[getXCoordinate()].length; column++) {
                        if (checkStopPosition(column, moves)) {
                            break;
                        }
                        setYCoordinate(moves);
                    }
            }
            case WEST -> {
                int columnPosition = getYCoordinate();
                int newPosition = (columnPosition - moves);

                    for (int column = columnPosition; column <= this.sketchPad[getXCoordinate()].length; column--) {
                        if(checkStopPosition(column, newPosition)){
                            setYCoordinate(newPosition);
                            break;
                        }
                    }
            }

            case SOUTH -> {
                int rowPosition = getXCoordinate();
               for (int row = rowPosition; row < sketchPad.length; row++){
                   if(checkStopPosition(row, moves)){
                       setXCoordinate(moves);
                       break;
                   }
               }
           }
            case NORTH -> {
                int rowPosition = getXCoordinate();
                int newPosition = getXCoordinate() - moves;
                for (int row = rowPosition; row < sketchPad.length; row--){
                    if(checkStopPosition(row, newPosition)){
                        setXCoordinate(newPosition);
                        break;
                    }
                }

            }

        }
    }

    private boolean checkStopPosition(int row, int stopPoint){
        return  row == stopPoint;
    }

}



