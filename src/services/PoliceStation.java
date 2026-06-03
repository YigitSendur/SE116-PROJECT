package services;

import cells.Cell;
import zones.Housing;
import zones.Industrial;
import zones.Commercial;

public class PoliceStation extends ServiceProvider {

    public PoliceStation(int x, int y){
        super(x, y, 5);
    }

    @Override
    public String getSymbol(){
        return "F";
    }

    @Override
    public String getServiceType(){
        return "security";
    }

    @Override
    protected void applyServiceToCell(Cell cell){
        String type = "";
        if(cell instanceof Housing){
            ((Housing) cell).setSecurity(true);
            type = "House";
        } else if(cell instanceof Industrial){
            ((Industrial) cell).setSecurity(true);
            type = "Industrial";
        } else if(cell instanceof Commercial){
            ((Commercial) cell).setSecurity(true);
            type = "Commercial";
        }
        if(!type.isEmpty()) {
            System.out.println(type + " at (" + cell.getCoordinateX() + "," + cell.getCoordinateY() + ") received security service");
        }
    }
}
