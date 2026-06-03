package main;

import cells.Cell;
import cells.EmptyCell;
import cells.Road;
import city.Grid;
import city.InvalidMapException;
import services.Hospital;
import services.PoliceStation;
import services.School;
import utilities.InternetHub;
import utilities.PowerPlant;
import utilities.WaterPumpingStation;
import zones.Commercial;
import zones.Housing;
import zones.Industrial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    public static Grid loadMap(String filename) throws InvalidMapException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            throw new InvalidMapException("File cannot be read: " + filename);
        }

        if (lines.isEmpty()) {
            throw new InvalidMapException("Map file is empty.");
        }

        int height = lines.size();
        int width = lines.get(0).length();
        Grid grid = new Grid(width, height);

        for (int y = 0; y < height; y++) {

            String row = lines.get(y);

            if (row.length() != width) {

                throw new InvalidMapException("Map rows have inconsistent lengths.");
            }
            for (int x = 0; x < width; x++) {

                char c = row.charAt(x);
                Cell cell = createCell(c, x, y);
                grid.setCell(x, y, cell);
            }
        }

        return grid;
    }

    private static Cell createCell(char c, int x, int y) throws InvalidMapException {

        switch (c) {

            case 'E': return new EmptyCell(x, y);
            case 'R': return new Road(x, y);
            case 'H': return new Housing(x, y);
            case 'C': return new Commercial(x, y);
            case 'I': return new Industrial(x, y);
            case 'P': return new PowerPlant(x, y);
            case 'W': return new WaterPumpingStation(x, y);
            case 'T': return new InternetHub(x, y);
            case 'F': return new PoliceStation(x, y);
            case 'D': return new Hospital(x, y);
            case 'S': return new School(x, y);

            default: throw new InvalidMapException("Unknown cell type: " + c);
        }
    }
}