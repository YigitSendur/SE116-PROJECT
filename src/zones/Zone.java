package zones;

public abstract class Zone {
    protected int x,y;
    protected int level;
    protected int output;
    protected int demand;

    public Zone(int x, int y) {
        this.x = x;
        this.y = y;
        this.level = 0;
        this.output = 0;
        this.demand = 1;
    }

    public abstract void updateLevel(long tick, Object context);

}

