package cells;

public abstract class Cell {
    protected int coordinateX;
    protected int coordinateY;

    public Cell(int x, int y){
        this.coordinateX = x;
        this.coordinateY = y;
    }
     public abstract String getSymbol();

    public int getCoordinateX(){
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
}
