package Contest.Biweekly.ct152;

public class DesignSpreadsheet {
    private int[][] grid;
    private final char CONVERT_TO_IDX = 'A';
    public DesignSpreadsheet(int rows) {
        grid = new int[rows][26];
    }

    public void setCell(String cell, int value) {
        int col = cell.charAt(0) - CONVERT_TO_IDX;
        int row = Integer.parseInt(cell.substring(1)) - 1;
        grid[row][col] = value;
    }

    public void resetCell(String cell) {
        int col = cell.charAt(0) - CONVERT_TO_IDX;
        int row = Integer.parseInt(cell.substring(1)) - 1;
        grid[row][col] = 0;
    }

    public int getValueOf(String cell) {
        int col = cell.charAt(0) - CONVERT_TO_IDX;
        int row = Integer.parseInt(cell.substring(1)) - 1;
        return grid[row][col];
    }

    public int getValue(String formula) {
        String[] cells = formula.substring(1).split("\\+");
        if (Character.isDigit(cells[0].charAt(0)) && Character.isDigit(cells[1].charAt(0))) {
            return Integer.parseInt(cells[0]) + Integer.parseInt(cells[1]);
        }
        if (Character.isDigit(cells[0].charAt(0))) {
            return Integer.parseInt(cells[0]) + getValueOf(cells[1]);
        }
        if (Character.isDigit(cells[1].charAt(0))) {
            return Integer.parseInt(cells[1]) + getValueOf(cells[0]);
        }
        return getValueOf(cells[0]) + getValueOf(cells[1]);
    }
}
