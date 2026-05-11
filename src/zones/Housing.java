package zones;
public class Housing extends Zone {
    private int populationProduced;

    public Housing(int x, int y) {
        super(x, y);
    }

    @Override
    public void updateLevel(long tick, Object context) {

    }

    // Seviye kontrolü için yardımcı metod
    public void setLevel(int newLevel) {
        if (newLevel < 0 || newLevel > 3) {
            // İleride SE116ConfigurationException buraya eklenebilir
            return;
        }
        this.level = newLevel;
    }

    public int getLevel() {
        return this.level;
    }
}