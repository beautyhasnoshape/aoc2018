package com.beautyhasnoshape.aoc2018;

import java.util.HashMap;
import java.util.Map;


public class Day13 {
    public String solvePartA(int gridSerialNumber) {
        int[][] grid = createGrid(gridSerialNumber);
        return findMax3x3(grid);
    }

    private int[][] createGrid(int gridSerialNumber) {
        int[][] grid = new int[301][301];
        for (int y = 1; y <= 300; y++) {
            for (int x = 1; x <= 300; x++) {
                grid[y][x] = calculatePower(x, y, gridSerialNumber);
            }
        }

        return grid;
    }

    private int calculatePower(int x, int y, int gridSerialNumber) {
        int rackId = x + 10;
        int power = rackId * y;
        power += gridSerialNumber;
        power *= rackId;

        String str = ("00" + power);
        int digit = Integer.parseInt(str.substring(str.length() - 3, str.length() - 2));

        power = digit - 5;

        return power;
    }

    private String findMax3x3(int[][] grid) {
        int max = 0, rx = 0, ry = 0;
        for (int y = 1; y <= 300 - 2; y++) {
            for (int x = 1; x <= 300 - 2; x++) {
                int current = 0;
                for (int dx = 0; dx <= 2; dx++) {
                    current += grid[y + 0][x + dx];
                    current += grid[y + 1][x + dx];
                    current += grid[y + 2][x + dx];
                }
                if (current > max) {
                    max = current;
                    rx = x; ry = y;
                }
            }
        }

        return rx + "," + ry;
    }

    public String solvePartB(int gridSerialNumber) {
        int[][] grid = createGrid(gridSerialNumber);

        return findMax(grid);
    }

    /**
     * This method uses dynamic programming to avoid 4-level iteration (x, y, diameter x, diameter y)
     * and to make the calculation fast.
     *
     * Within a given position, all squares calculated for smaller diameters will be reused instead of traversing
     * the whole examined square. Hence, only the border values of the examined square in the last column
     * and the last row will be calculated.
     *
     * Example:
     * For a given position (4, 2) and diameter 5, only cells marked with ? must be calculated as shown below:
     *
     *   | 1 2 3 4 5 6 7 8 9
     * --+------------------
     * 1 | . . . . . . . . .
     * 2 | . . . * * * * ? .
     * 3 | . . . * * * * ? .
     * 4 | . . . * * * * ? .
     * 5 | . . . * * * * ? .
     * 6 | . . . ? ? ? ? ? .
     * 7 | . . . . . . . . .
     *
     *
     * @param grid
     * @return
     */
    private String findMax(int[][] grid) {
        int max = 0, rx = 0, ry = 0, size = 0;
        for (int y = 1; y <= 300; y++) {
            for (int x = 1; x <= 300; x++) {
                // iterate over diameter, keep results in order to reuse them
                Map<Integer, Integer> squares = new HashMap<>(300);
                for (int d = 0; d <= 300 - Math.max(x, y); d++) {
                    int current = 0;

                    if (d == 0) {
                        current += grid[y][x];
                    } else {
                        for (int offset = 0; offset < d; offset++) {
                            current += grid[y + offset][x + d]; // skip inner square, get last column
                            current += grid[y + d][x + offset]; // skip inner square, get last row
                        }
                        current += squares.get(d - 1); // add inner square of size (d-1)
                        current += grid[y + d][x + d]; // add bottom-right corner cell
                    }

                    squares.put(d, current);

                    if (current > max) {
                        max = current;
                        rx = x; ry = y;
                        size = d;
                    }
                }
            }
        }

        return rx + "," + ry + "," + (size + 1);
    }

    private void printGrid(int[][] grid) {
        for (int y = 1; y <= 300; y++) {
            for (int x = 1; x <= 300; x++) {
                System.out.print(grid[y][x] + ",");
            }
            System.out.println();
        }
    }
}
