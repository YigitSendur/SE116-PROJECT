package engine;

import zones.Zone;
import java.util.List;

public class ResourcePool {

    private int totalPopulation;
    private int totalGoods;
    private int totalLifestyle;

    public ResourcePool() {
        this.totalPopulation = 0;
        this.totalGoods = 0;
        this.totalLifestyle = 0;
    }

    public void addPopulation(int amount) {
        if (amount > 0) totalPopulation += amount;
    }

    public void addGoods(int amount) {
        if (amount > 0) totalGoods += amount;
    }

    public void addLifestyle(int amount) {
        if (amount > 0) totalLifestyle += amount;
    }

    public void distributePopulation(List<Zone> receivers) {
        if (receivers == null || receivers.isEmpty()) return;
        int perZone = totalPopulation / receivers.size();
        for (Zone z : receivers) z.receivePopulation(perZone);
    }

    public void distributeGoods(List<Zone> receivers) {
        if (receivers == null || receivers.isEmpty()) return;
        int perZone = totalGoods / receivers.size();
        for (Zone z : receivers) z.receiveGoods(perZone);
    }

    public void distributeLifestyle(List<Zone> receivers) {
        if (receivers == null || receivers.isEmpty()) return;
        int perZone = totalLifestyle / receivers.size();
        for (Zone z : receivers) z.receiveLifestyle(perZone);
    }

    public void reset() {
        totalPopulation = 0;
        totalGoods = 0;
        totalLifestyle = 0;
    }

    public int getTotalPopulation() { return totalPopulation; }
    public int getTotalGoods()      { return totalGoods; }
    public int getTotalLifestyle()  { return totalLifestyle; }
}