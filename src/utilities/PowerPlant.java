package utilities;

import cells.Cell;
import city.Grid;
import zones.Housing;
import zones.Industrial;
import zones.Commercial;
import java.util.LinkedList;
import java.util.Queue;

public class PowerPlant extends UtilityProvider {

    public PowerPlant(int x, int y){
        super(x, y);
    }

    @Override
    public String getSymbol(){
        return "P";
    }

    @Override
    public void distribute(Grid grid){
        boolean[][] visited = new boolean[grid.getHeight()][grid.getWidth()];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{this.coordinateX, this.coordinateY});
        visited[this.coordinateY][this.coordinateX] = true;
        int remaining = this.capacity;

        while(!queue.isEmpty() && remaining > 0){
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];
            Cell cell = grid.getCell(cx, cy);

            if(cell != null && !(cell instanceof UtilityProvider)){
                if(cell instanceof Housing){
                    int demand = ((Housing) cell).getElectricityNeeded();
                    int give = Math.min(demand, remaining);
                    if (give > 0) {
                        ((Housing) cell).receiveElectricity(give);
                        remaining -= give;
                    }
                } else if(cell instanceof Industrial){
                    int demand = ((Industrial) cell).getElectricityNeeded();
                    int give = Math.min(demand, remaining);
                    if (give > 0) {
                        ((Industrial) cell).receiveElectricity(give);
                        remaining -= give;
                    }
                } else if(cell instanceof Commercial){
                    int demand = ((Commercial) cell).getElectricityNeeded();
                    int give = Math.min(demand, remaining);
                    if (give > 0) {
                        ((Commercial) cell).receiveElectricity(give);
                        remaining -= give;
                    }
                }
            }

            int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
            for(int[] d : dirs){
                int nx = cx + d[0];
                int ny = cy + d[1];
                if(nx >= 0 && nx < grid.getWidth() && ny >= 0 && ny < grid.getHeight() && !visited[ny][nx]){
                    Cell neighbor = grid.getCell(nx, ny);
                    if(neighbor != null && neighbor.isConnectable()){
                        visited[ny][nx] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
