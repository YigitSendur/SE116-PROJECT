package cells;

public class EmptyCell extends Cell{
    public EmptyCell(int x,int y){
        super(x,y);
    }

    @Override
    public boolean isConnectable() {
        return false;
    }

    @Override
    public String getSymbol() {
        return "E";
    }
}
