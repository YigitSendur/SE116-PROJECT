package services;

import cells.Cell;
import zones.Housing;

public class Hospital extends ServiceProvider {

    public Hospital(int x, int y){
        super(x, y, 3);
    }

    @Override
    public String getSymbol(){
        return "D";
    }

    @Override
    public String getServiceType(){
        return "health";
    }

    @Override
    protected void applyServiceToCell(Cell cell){
        if(cell instanceof Housing){
            ((Housing) cell).setHealth(true);
        }
    }
}
