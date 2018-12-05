package beauty.aoc2018;

import java.util.List;

public class Day03 {
    private int[][] fabric = new int[1000][1000];

    public int solvePartA(List<String> lines) {
        for (String line : lines) {
            int[] values = parseLine(line);
            fillRectangle(fabric, values[1], values[2], values[3], values[4]);
        }

        return calculateOverlapping(fabric);
    }

    public int solvePartB(List<String> lines) {
        solvePartA(lines);

        for (String line : lines) {
            int[] values = parseLine(line);
            if (!isRectangleOverlapping(fabric, values[1], values[2], values[3], values[4])) {
                return values[0];
            }
        }

        throw new IllegalStateException("Result not found");
    }

    private boolean isRectangleOverlapping(int[][] rect, int left, int top, int w, int h) {
        for (int x = left; x < left + w; x++) {
            for (int y = top; y < top + h; y++) {
                if (rect[x][y] > 1) {
                    return true;
                }
            }
        }

        return false;
    }

    private int calculateOverlapping(int[][] rect) {
        int overlappings = 0;
        for (int x = 0; x < rect[0].length; x++) {
            for (int y = 0; y < rect[0].length; y++) {
                if (rect[x][y] > 1) {
                    ++overlappings;
                }
            }
        }

        return overlappings;
    }

    private void fillRectangle(int[][] rect, int left, int top, int w, int h) {
        for (int x = left; x < left + w; x++) {
            for (int y = top; y < top + h; y++) {
                rect[x][y] = rect[x][y] + 1;
            }
        }
    }

    // #1 @ 935,649: 22x22
    private int[] parseLine(String line) {
        line = line.replaceAll("#", "");
        line = line.replaceAll("@ ", "");
        line = line.replaceAll(",", " ");
        line = line.replaceAll(":", "");
        line = line.replaceAll("x", " ");

        String[] split = line.split(" ");
        int[] result = new int[5];

        result[0] = Integer.parseInt(split[0]);
        result[1] = Integer.parseInt(split[1]);
        result[2] = Integer.parseInt(split[2]);
        result[3] = Integer.parseInt(split[3]);
        result[4] = Integer.parseInt(split[4]);

        return result;
    }
}
