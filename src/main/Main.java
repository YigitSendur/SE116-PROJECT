package main;
import city.Grid;
import city.InvalidMapException;
import engine.SimulationEngine;

public class Main {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Tick numbers are invalid");
            return;
        }

        String mapFile = args[0];
        int ticks;

        try {
            ticks = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Tick number must be integer: " + args[1]);
            return;
        }

        if (ticks <= 0) {
            System.err.println("Tick number must be positive " + ticks);

            return;
        }

        try {
            Grid grid = MapLoader.loadMap(mapFile);
            SimulationEngine engine = new SimulationEngine(grid, ticks);
            engine.run();
        } catch (InvalidMapException e) {
            System.err.println("Map error   : " + e.getMessage());
        }
    }
}