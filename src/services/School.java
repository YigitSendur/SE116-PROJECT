package services;

import cells.Cell;
import zones.Housing;

public class School extends ServiceProvider {

    public School(int x, int y){
        super(x, y, 4);
    }

    @Override
    public String getSymbol(){
        return "S";
    }

    @Override
    public String getServiceType(){
        return "education";
    }

    @Override
    protected void applyServiceToCell(Cell cell){
        if(cell instanceof Housing){
            ((Housing) cell).setEducation(true);
        }
    }
}
