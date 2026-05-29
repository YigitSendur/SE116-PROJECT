package services;

import cells.Cell;
import city.Grid;
import zones.Zone;

public abstract class ServiceProvider extends Cell {
    protected int radius;

    public ServiceProvider(int x, int y, int radius){
        super(x, y);
        this.radius = radius;
    }

    @Override
    public boolean isConnectable(){
        return false;
    }

    public int getRadius(){
        return radius;
    }

    public abstract String getServiceType();

    public void provideService(Grid grid){
        for(int row = 0; row < grid.getHeight(); row++){
            for(int col = 0; col < grid.getWidth(); col++){
                Cell cell = grid.getCell(col, row);
                if(cell == null) continue;

                double dist = euclideanDistance(col, row);
                if(dist <= this.radius){
                    applyServiceToCell(cell);
                }
            }
        }
    }

    private double euclideanDistance(int targetX, int targetY){
        int dx = targetX - this.coordinateX;
        int dy = targetY - this.coordinateY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    protected abstract void applyServiceToCell(Cell cell);
}
