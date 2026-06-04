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
        System.out.println("DEBUG Housing x=" + x + " y=" + y);
        resetTickData();
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public void updateLevel(long tick, Object context) {
        int oldLevel = this.level;
        int m = calculateM();
        if (m == 0) {
            this.level = 0;
        }else {
            switch (this.level) {
                case 0:
                    if (m > 0) {
                        this.level = 1;
                    }
                    break;
                case 1:
                    if (hasSecurity && hasHealth && hasEducation) {
                        this.level = 2;
                    }
                    break;
                case 2:
                    if (lifestyleReceived > 0) {
                        this.level = 3;
                    } else if (!(hasSecurity && hasHealth && hasEducation)) {
                        this.level = 1;
                    }
                    break;
                case 3:
                    if (lifestyleReceived == 0) {
                        this.level = 2;
                    } else if (!(hasSecurity && hasHealth && hasEducation)) {
                        this.level = 1;
                    }
                    break;
            }
        }
        switch (this.level) {
            case 0:
                this.populationProduced = 0;
                break;
            case 1:
                this.populationProduced = m;
                break;
            case 2:
                this.populationProduced = 2 * m;
                break;
            case 3:
                this.populationProduced = (2 * m) + this.lifestyleReceived;
                break;
        }
        this.output = this.populationProduced;
        this.demand = Math.max(1, this.output);
        System.out.println("House at (" + this.coordinateX + "," + this.coordinateY + ") generated " + this.populationProduced + " population");

        if (this.level > oldLevel) {
            System.out.println("House at (" + this.coordinateX + "," + this.coordinateY + ") levels up from " + oldLevel + " to " + this.level);
        } else if (this.level < oldLevel) {
            System.out.println("House at (" + this.coordinateX + "," + this.coordinateY + ") levels down from " + oldLevel + " to " + this.level);
        }
        }
    public int getPopulationProduced() {
        return this.populationProduced;
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
    public void setSecurity(boolean value) {
        this.hasSecurity = value;
    }

    public void setHealth(boolean value) {
        this.hasHealth = value;
    }

    public void setEducation(boolean value) {
        this.hasEducation = value;
    }

    public void receiveElectricity(int amount) {
        this.electricityReceived += amount;
        System.out.println("House at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " electricity");
    }

    public void receiveWater(int amount) {
        this.waterReceived += amount;
        System.out.println("House at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " water");
    }

    public void receiveInternet(int amount) {
        this.internetReceived += amount;
        System.out.println("House at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " internet");
    }

    @Override
    public void receiveLifestyle(int amount) {
        this.lifestyleReceived += amount;
        System.out.println("House at (" + this.coordinateX + "," + this.coordinateY + ") received " + amount + " lifestyle");
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
    @Override
    public void receiveGoods(int amount) {}
}