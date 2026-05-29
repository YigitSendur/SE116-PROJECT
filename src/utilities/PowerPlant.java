package utilities;

public class PowerPlant extends UtilityProvider {

    public PowerPlant(int x, int y){
        super(x, y);
    }

    @Override
    public String getSymbol(){
        return "P";
    }
}
