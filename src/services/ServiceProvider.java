package services;

import cells.Cell;
import city.Grid;

public abstract class ServiceProvider extends Cell {
    protected int radius;

    public ServiceProvider(int x,int y,int radius){
        super(x,y);
        this.radius = radius;
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    public int getRadius() {
        return radius;
    }

    public abstract void provideService(Grid grid);
}
