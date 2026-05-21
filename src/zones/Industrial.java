package zones;

public class Industrial extends Zone{
    private int goodsProduced;
    private int electricityReceived;
    private int waterReceived;
    private int populationReceived;
    private boolean hasSecurity;

    public Industrial(int x, int y) {
        super(x, y);
        this.goodsProduced = 0;
    }

    public int calculateM() {
        return Math.min(electricityReceived, waterReceived);
    }


    @Override
    public void updateLevel(long tick, Object context) {
        //su anlik bos birakiyorum seviye atlamalariyla uretme algoritmalari buraya gelcek
    }

}
