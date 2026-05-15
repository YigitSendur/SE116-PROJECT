package zones;
public class Housing extends Zone {
    private int populationProduced;
    private int electricityReceived;
    private int waterReceived;
    private int internetReceived;
    private int lifestyleReceived;
    private boolean hasSecurity;
    private boolean hasHealth;
    private boolean hasEducation;

    public Housing(int x, int y) {
        super(x, y);
        resetTickData();
    }

    @Override
    public void updateLevel(long tick, Object context) {
    }
    public int calculateM() {
        return Math.min(electricityReceived,
                Math.min(waterReceived, internetReceived));
    }
    public void setLevel(int newLevel) {
        if (newLevel < 0 || newLevel > 3) {
            return;
        }
        this.level = newLevel;
    }

    public int getLevel() {
        return this.level;
    }
    public void resetTickData() {
        this.electricityReceived = 0;
        this.waterReceived = 0;
        this.internetReceived = 0;
        this.lifestyleReceived = 0;
        this.hasSecurity = false;
        this.hasHealth = false;
        this.hasEducation = false;
    }
}