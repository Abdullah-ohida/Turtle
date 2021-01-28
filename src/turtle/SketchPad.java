package turtle;

public class SketchPad {
    private int[][] sketchBoard;

    public SketchPad(int numberOfRow, int numberOfColumn){
        this.sketchBoard = new int[numberOfRow][numberOfColumn];
    }

    public int[][] getSketchBoard() {
        return sketchBoard;
    }

    public String displayTurtleDesign() {
        StringBuilder draw = new StringBuilder();
        for(int[] row : sketchBoard){
            for (int column : row){
                if(column == 1){
                    draw.append("# ");
                }else {
                    draw.append("  ");
                }
            }
            draw.append("\n");
        }
        return draw.toString();
    }

}
