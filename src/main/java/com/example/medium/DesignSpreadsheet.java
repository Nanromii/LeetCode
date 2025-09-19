package com.example.medium;

public class DesignSpreadsheet {
    class Spreadsheet {
        private int[][] sheet;

        public Spreadsheet(int rows) {
            sheet = new int[rows][26];
        }

        public void setCell(String cell, int value) {
            int j = cell.charAt(0) - 'A';
            int i = Integer.parseInt(cell.substring(1)) - 1;
            sheet[i][j] = value;
        }

        public void resetCell(String cell) {
            setCell(cell, 0);
        }

        public int getValue(String formula) {
            formula = formula.substring(1);
            String[] cells = formula.split("\\+");
            String cell1 = cells[0], cell2 = cells[1];
            if (Character.isDigit(cell1.charAt(0)) && Character.isDigit(cell2.charAt(0))) {
                return Integer.parseInt(cell1) + Integer.parseInt(cell2);
            } else if (Character.isDigit(cell1.charAt(0))) {
                return Integer.parseInt(cell1) + value(cell2);
            } else if (Character.isDigit(cell2.charAt(0))) {
                return Integer.parseInt(cell2) + value(cell1);
            } else {
                return value(cell1) + value(cell2);
            }
        }

        private int value(String cell) {
            int j = cell.charAt(0) - 'A';
            int i = Integer.parseInt(cell.substring(1)) - 1;
            return sheet[i][j];
        }
    }

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
}
