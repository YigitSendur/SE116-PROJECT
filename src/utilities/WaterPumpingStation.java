package utilities;

public class WaterPumpingStation extends UtilityProvider {

    public WaterPumpingStation(int x, int y){
        super(x, y);
    }

    @Override
    public String getSymbol(){
        return "W";
    }
}
