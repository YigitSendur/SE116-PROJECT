package zones;

public class Commercial extends Zone {

    private int lifestyleProduced;
    private int electricityReceived;
    private int waterReceived;
    private int internetReceived;
    private int populationReceived;
    private int goodsReceived;
    private boolean hasSecurity;

    public Commercial(int x, int y) {
        super(x, y);
        this.lifestyleProduced = 0;
        resetTickData();
    }

    @Override
    public String getSymbol() {
        return "C";
    }

    public int calculateM() {
        return Math.min(electricityReceived,
                Math.min(waterReceived, internetReceived));
    }

    @Override
    public void updateLevel(long tick, Object context) {
        int oldLevel = this.level;
        int m = calculateM();

        if (m == 0 || populationReceived == 0 || goodsReceived == 0) {
            this.level = 0;
        }else {

            switch (this.level) {
                case 0:
                    if (populationReceived > 0 && goodsReceived > 0 && m > 0) {
                        this.level = 1;
                    }
                    break;
                case 1:
                    if (hasSecurity) {
                        this.level = 2;
                    }
                    break;
                case 2:
                    if (populationReceived > m && goodsReceived > m) {
                        this.level = 3;
                    } else if (!hasSecurity || populationReceived <= m || goodsReceived <= m) {
                        this.level = 1;
                    }
                    break;
                case 3:
                    if (populationReceived <= m || goodsReceived <= m) {
                        this.level = 2;
                    }
                    break;
            }
        }
        switch (this.level) {
            case 0:
                this.lifestyleProduced = 0;
                break;
            case 1:
                this.lifestyleProduced = m;
                break;
            case 2:
                this.lifestyleProduced = 2 * m;
                break;
            case 3:
                this.lifestyleProduced = (2 * m) + Math.min(populationReceived, goodsReceived);
                break;
        }

        this.output = this.lifestyleProduced;
        this.demand = Math.max(1, this.output);
        System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") generated " + this.lifestyleProduced + " lifestyle");

        if (this.level > oldLevel) {
            System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") levels up from " + oldLevel + " to " + this.level);
        } else if (this.level < oldLevel) {
            System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") levels down from " + oldLevel + " to " + this.level);
        }
    }

    @Override
    public void receivePopulation(int amount) {
        this.populationReceived = amount;
        System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " population");
    }

    @Override
    public void receiveGoods(int amount) {
        this.goodsReceived = amount;
        System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " goods");
    }

    public void receiveElectricity(int amount) {
        this.electricityReceived += amount;
        System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " electricity");
    }

    public void receiveWater(int amount) {
        this.waterReceived += amount;
        System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " water");
    }

    public void receiveInternet(int amount) {
        this.internetReceived += amount;
        System.out.println("Commercial at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " internet");
    }

    public void setSecurity(boolean value) {
        this.hasSecurity = value;
    }

    public int getLifestyleProduced() {
        return this.lifestyleProduced;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int newLevel) {
        if (newLevel < 0 || newLevel > 3) return;
        this.level = newLevel;
    }

    public void resetTickData() {
        this.electricityReceived = 0;
        this.waterReceived = 0;
        this.internetReceived = 0;
        this.populationReceived = 0;
        this.goodsReceived = 0;
        this.hasSecurity = false;
    }
    public void receiveLifestyle(int amount) {}
}