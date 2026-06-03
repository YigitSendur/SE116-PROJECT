package engine;

import cells.Cell;
import city.Grid;
import services.ServiceProvider;
import utilities.UtilityProvider;
import zones.Commercial;
import zones.Housing;
import zones.Industrial;
import zones.Zone;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {

    private final Grid grid;
    private final int totalTicks;
    private final ResourcePool pool;
    private int currentTick;

    public SimulationEngine(Grid grid, int totalTicks) {
        this.grid = grid;
        this.totalTicks = totalTicks;
        this.pool = new ResourcePool();
        this.currentTick = 0;
    }

    public void run() {
        for (int tick = 1; tick <= totalTicks; tick++) {
            currentTick = tick;
            System.out.println("Tick " + tick);
            resetAllZones();
            step1_distributeServices();
            step2_distributeUtilities();
            step3_distributeResources();
            step4_updateZones();
            step5_accumulateProduction();
        }
    }
    private void resetAllZones() {
        for (Zone z : getAllZones()) {
            z.resetTickData();
        }
    }

    private List<Cell> getAllCells() {
        List<Cell> cells = new ArrayList<>();
        for (int y = 0; y < grid.getHeight(); y++) {
            for (int x = 0; x < grid.getWidth(); x++) {
                Cell c = grid.getCell(x, y);
                if (c != null) cells.add(c);
            }
        }
        return cells;
    }

    private List<Zone> getAllZones() {
        List<Zone> zones = new ArrayList<>();
        for (Cell c : getAllCells()) {
            if (c instanceof Zone) zones.add((Zone) c);
        }
        return zones;
    }

    private List<Zone> getHousingZones() {
        List<Zone> list = new ArrayList<>();
        for (Cell c : getAllCells()) {
            if (c instanceof Housing) list.add((Zone) c);
        }
        return list;
    }

    private List<Zone> getIndustrialZones() {
        List<Zone> list = new ArrayList<>();
        for (Cell c : getAllCells()) {
            if (c instanceof Industrial) list.add((Zone) c);
        }
        return list;
    }

    private List<Zone> getCommercialZones() {
        List<Zone> list = new ArrayList<>();
        for (Cell c : getAllCells()) {
            if (c instanceof Commercial) list.add((Zone) c);
        }
        return list;
    }

    private List<ServiceProvider> getAllServiceBuildings() {
        List<ServiceProvider> list = new ArrayList<>();
        for (Cell c : getAllCells()) {
            if (c instanceof ServiceProvider) list.add((ServiceProvider) c);
        }
        return list;
    }

    private List<UtilityProvider> getAllUtilityProviders() {
        List<UtilityProvider> list = new ArrayList<>();
        for (Cell c : getAllCells()) {
            if (c instanceof UtilityProvider) list.add((UtilityProvider) c);
        }
        return list;
    }

    private void step1_distributeServices() {
        for (ServiceProvider s : getAllServiceBuildings()) {
            s.provideService(grid);
        }
    }

    private void step2_distributeUtilities() {
        for (UtilityProvider u : getAllUtilityProviders()) {
            u.distribute(grid);
        }
    }

    private void step3_distributeResources() {
        List<Zone> popReceivers = new ArrayList<>();
        popReceivers.addAll(getIndustrialZones());
        popReceivers.addAll(getCommercialZones());
        pool.distributePopulation(popReceivers);
        pool.distributeGoods(getCommercialZones());
        pool.distributeLifestyle(getHousingZones());
    }

    private void step4_updateZones() {
        for (Zone z : getAllZones()) {
            z.updateLevel(currentTick, null);
        }
    }

    private void step5_accumulateProduction() {
        pool.reset();
        for (Zone z : getAllZones()) {
            if (z instanceof Housing) {
                pool.addPopulation(z.getOutput());
            } else if (z instanceof Industrial) {
                pool.addGoods(z.getOutput());
            } else if (z instanceof Commercial) {
                pool.addLifestyle(z.getOutput());
            }
        }
    }
}