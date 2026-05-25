package utilities;

import cells.Cell;

public abstract class UtilityProvider extends Cell {
    protected int capacity = 100;

    public UtilityProvider(int x,int y){
        super(x,y);
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public int getCapacity() {
        return capacity;
    }
}
