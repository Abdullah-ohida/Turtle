package turtle;

public class Pen {
    private Position penPosition;

    public Pen() {
        this.penPosition = Position.PEN_UP;
    }

    public Position getPenPosition() {
        return penPosition;
    }

    public void setPenPosition(Position penPosition) {
        this.penPosition = penPosition;
    }

}
