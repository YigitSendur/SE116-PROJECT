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

    @Override
    public String getSymbol() {
        return "I";
    }

    public int calculateM() {
        return Math.min(electricityReceived, waterReceived);
    }

    @Override
    public void updateLevel(long tick, Object context) {
        int oldLevel = this.level;
        int m = calculateM();

        if (m == 0) {
            this.level = 0;
        } else {
            switch (this.level) {
                case 0:
                    if (electricityReceived > 0 && waterReceived > 0){
                        this.level = 1;
                    }
                    break;
                case 1:
                    if (hasSecurity) {
                        this.level = 2;
                    }
                    break;
                case 2:
                    if (populationReceived > 0) {
                        this.level = 3;
                    } else if (!hasSecurity) {
                        this.level = 1;
                    }
                    break;
                case 3:
                    if (populationReceived == 0) {
                        this.level = 2;
                    }else if (!hasSecurity) {
                        this.level = 1;
                    }
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
        System.out.println("Industrial at " + formatCoordinates()+ " generated " + this.goodsProduced + " goods");

        if (this.level > oldLevel) {
            System.out.println("Industrial at " +formatCoordinates()+ " levels up from " + oldLevel + " to " + this.level);
        } else if (this.level < oldLevel) {
            System.out.println("Industrial at " +formatCoordinates() + " levels down from " + oldLevel + " to " + this.level);
        }
    }
    public int getGoodsProduced() {
        return this.goodsProduced;
    }

    public void setSecurity(boolean value) {
        this.hasSecurity = value;
    }

    public void receiveElectricity(int amount) {
        this.electricityReceived += amount;
        System.out.println("Industrial at " + formatCoordinates()+ " received " + amount + " electricity");
    }

    public void receiveWater(int amount) {
        this.waterReceived += amount;
        System.out.println("Industrial at " + formatCoordinates()+ " received " + amount + " water");
    }

    @Override
    public void receivePopulation(int amount) {
        this.populationReceived = amount;
        System.out.println("Industrial at " + formatCoordinates()+ " received " + amount + " population");
    }

    public int getLevel() {
        return this.level;
    }

    public void resetTickData() {
        this.electricityReceived = 0;
        this.waterReceived = 0;
        this.populationReceived = 0;
        this.hasSecurity = false;
    }
    public void receiveLifestyle(int amount) {}

    public int getElectricityNeeded() {
        return Math.max(0, getDemand() - electricityReceived);
    }

    public int getWaterNeeded() {
        return Math.max(0, getDemand() - waterReceived);
    }
}
