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
        int m = calculateM();

        if (m == 0) {
            this.level = 0;
        } else {
            switch (this.level) {
                case 0:
                    if (m > 0 && populationReceived > 0) this.level = 1;
                    break;
                case 1:
                    if (hasSecurity) this.level = 2;
                    break;
                case 2:
                    if (populationReceived > 0) {
                        this.level = 3;
                    } else if (!hasSecurity) {
                        this.level = 1;
                    }
                    break;
                case 3:
                    if (populationReceived == 0) this.level = 2;
                    break;
            }
        }

        switch (this.level) {
            case 0:
                this.goodsProduced = 0;
                break;
            case 1:
                this.goodsProduced = m;
                break;
            case 2:
                this.goodsProduced = 2 * m;
                break;
            case 3:
                this.goodsProduced = (2 * m) + this.populationReceived;
                break;
        }

        this.output = this.goodsProduced;
        this.demand = Math.max(1, this.output);
    }
    public int getGoodsProduced() {
        return this.goodsProduced;
    }

    public void resetTickData() {
        this.electricityReceived = 0;
        this.waterReceived = 0;
        this.populationReceived = 0;
        this.hasSecurity = false;
    }
}
