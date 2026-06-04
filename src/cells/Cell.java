package cells;

public abstract class Cell {
    protected int coordinateX;
    protected int coordinateY;

    public Cell(int x, int y){
        this.coordinateX = x;
        this.coordinateY = y;
    }
    public abstract String getSymbol();
    public abstract boolean isConnectable();

    public int getCoordinateX(){
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }
    public String formatCoordinates(){
        return "(" + coordinateY + "," + coordinateX + ")";
    }
    @Override
    public String toString(){
        return getSymbol() + formatCoordinates();
    }
}
