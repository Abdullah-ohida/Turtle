package turtle;

public class Turtle {
    private Pen pen;
    private Direction currentDirection;
    private SketchPadPosition currentSketchPadPosition;


    public Turtle(Pen pen){
        this.currentSketchPadPosition = new SketchPadPosition(0, 0);
        this.currentDirection = Direction.EAST;
        this.pen = new Pen();
    }

    public Pen getPen() {
        return pen;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setCurrentSketchPadPosition(SketchPadPosition currentSketchPadPosition) {
        this.currentSketchPadPosition = currentSketchPadPosition;
    }

    public SketchPadPosition getCurrentSketchPadPosition() {
        return currentSketchPadPosition;
    }

    public void turnRight(){
        switch (currentDirection){
            case EAST -> setCurrentDirection(Direction.SOUTH);
            case SOUTH -> setCurrentDirection(Direction.WEST);
            case WEST -> setCurrentDirection(Direction.NORTH);
            case NORTH -> setCurrentDirection(Direction.EAST);
        }
    }

    public void turnLeft(){
        switch (currentDirection){
            case EAST -> setCurrentDirection(Direction.NORTH);
            case SOUTH -> setCurrentDirection(Direction.EAST);
            case WEST -> setCurrentDirection(Direction.SOUTH);
            case NORTH -> setCurrentDirection(Direction.WEST);
        }
    }

    public void move(SketchPad sketchBoard, int numberOfSteps){
        numberOfSteps -= 1;
        int[][] floor = sketchBoard.getSketchBoard();

        if(getPen().getPenPosition() == Position.PEN_UP){
           movePenOnSketchPad(numberOfSteps);
        }
        if(getPen().getPenPosition() == Position.PEN_DOWN){
            drawOnSketchPad(floor, numberOfSteps);
        }
    }


    private void movePenOnSketchPad(int numberOfSteps){
        int currentRow = getCurrentSketchPadPosition().getRowPosition();
        int currentColumn = getCurrentSketchPadPosition().getColumnPosition();
        SketchPadPosition currentSketchPadPosition = getCurrentSketchPadPosition();
        switch (currentDirection){
            case EAST -> { currentSketchPadPosition.setColumnPosition(currentColumn + numberOfSteps); }
            case SOUTH -> {currentSketchPadPosition.setRowPosition(currentRow + numberOfSteps);}
            case WEST -> {currentSketchPadPosition.setColumnPosition(currentColumn - numberOfSteps);}
            case NORTH -> {currentSketchPadPosition.setRowPosition(currentRow - numberOfSteps);}
        }
    }

    private void drawOnSketchPad(int[][] floor, int numberOfSteps){
        int currentRow = currentSketchPadPosition.getRowPosition();
        int currentColumn = currentSketchPadPosition.getColumnPosition();

        switch (currentDirection){
            case EAST -> { DisplayOnColumn(floor, numberOfSteps, currentColumn + numberOfSteps); }
            case SOUTH -> { DisplayOnRow(floor, numberOfSteps, currentRow + numberOfSteps); }
            case WEST -> { DisplayOnColumn(floor, numberOfSteps, currentColumn - numberOfSteps); }
            case NORTH -> { DisplayOnRow(floor, numberOfSteps, currentRow - numberOfSteps); }
        }
    }

    private void DisplayOnRow(int[][] floor, int numberOfSteps, int newPosition) {
        drawInVerticalDirection(numberOfSteps, floor, currentDirection);
        currentSketchPadPosition.setRowPosition(newPosition);
    }

    private void DisplayOnColumn(int[][] floor, int numberOfSteps, int newPosition) {
        drawInHorizontalDirection(numberOfSteps, floor, currentDirection);
        currentSketchPadPosition.setColumnPosition(newPosition);
    }

    private void drawInHorizontalDirection(int numberOfSteps, int[][] floor, Direction currentDirection) {
        int currentRow = currentSketchPadPosition.getRowPosition();
        int currentColumn = currentSketchPadPosition.getColumnPosition();
        int startingPoint = currentSketchPadPosition.getColumnPosition();


        if(currentDirection == Direction.EAST){
            while (startingPoint <= numberOfSteps) {
                floor[currentRow][startingPoint] = 1;
                startingPoint++;
            }
        }else {
            int newPenPosition = currentColumn - numberOfSteps;
            while (startingPoint >= newPenPosition) {
                floor[currentRow][startingPoint] = 1;
                startingPoint--;
            }
        }

    }

    private void drawInVerticalDirection(int numberOfSteps, int[][] floor, Direction currentDirection){
        int currentColumn = currentSketchPadPosition.getColumnPosition();
        int currentRow = currentSketchPadPosition.getRowPosition();
        int startingPoint = currentSketchPadPosition.getRowPosition();

        if(currentDirection == Direction.SOUTH){
            while (startingPoint <= numberOfSteps){
                floor[startingPoint][currentColumn] = 1;
                startingPoint++;
            }
        }else {
            int newPenPosition = currentRow - numberOfSteps;
            while (startingPoint >= newPenPosition){
                floor[startingPoint][currentColumn] = 1;
                startingPoint--;
            }
        }
    }


}
