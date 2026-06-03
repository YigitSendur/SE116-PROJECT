package zones;

import cells.Cell;

public abstract class Zone extends Cell {
    protected int level;
    protected int output;
    protected int demand;

    public Zone(int x, int y) {
        super(x, y);
        this.level = 0;
        this.output = 0;
        this.demand = 1;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public int getLevel() {
        return this.level;
    }

    public int getOutput() {
        return this.output;
    }

    public int getDemand() {
        return this.demand;
    }

    public abstract void updateLevel(long tick, Object context);
    public void receivePopulation(int amount) {}
    public void receiveGoods(int amount) {}
    public void receiveLifestyle(int amount) {}
    public abstract void resetTickData();
}
