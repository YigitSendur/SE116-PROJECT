package cells;

public class Road extends Cell{
    public Road(int x, int y){
        super(x, y);
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
