package cells;

abstract public class Cell {
        private int row;
        private int col;

        public Cell(int row, int col){
            this.row = row;
            this.col = col;
        }

        public int getRow(){
            return row;
        }
        public int getCol(){
            return col;
        }

        public abstract boolean isConnectable();
        public abstract String getSymbol();

        @Override
        public String toString() {
            return getSymbol() + "(" + row + "," + col + ")";
        }
    }


