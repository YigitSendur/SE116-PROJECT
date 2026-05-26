package city;
import cells.Cell;
    public class Grid {
     private Cell[][] mapGrid;
     private int width;
     private int height;

     public Grid(int width,int height){
         this.width = width;
         this.height = height;
         this.mapGrid = new Cell[height][width];
     }

     public void setCell(int x,int y,Cell cell){
         if (x>=0 && x<width && y>=0 && y<height){
             mapGrid[y][x] = cell;
         }
     }
     public Cell getCell(int x,int y){
         if (x>=0 && x<width && y>=0 && y<height){
             return mapGrid[y][x];
         }return null;
     }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
