package com.beautyhasnoshape.aoc2018;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**

 */
public class Day10 {

    /*
    public String solvePartA(List<String> lines) throws IOException {
        OutputStream out = new FileOutputStream(new File("/Users/vito/dev/git/aoc2018/day10-out.txt"));
        List<int[]> input = convert(lines);

        long area = Long.MAX_VALUE;
        int step = 0;
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        // -input.get(0)[0] / input.get(0)[2] - 100
        for (int i = 0; i < 10200; i++) {
            for (int[] data : input) {
                data[0] = data[0] + data[2];
                data[1] = data[1] + data[3];
                if (data[0] < minX) minX = data[0];
                if (data[0] > maxX) maxX = data[0];
                if (data[1] < minY) minY = data[1];
                if (data[1] > maxY) maxY = data[1];
            }

            System.out.println(i + ": " + ((maxX - minX) * (maxY - minY)));
            if (Math.abs(maxY - minY) < 20) {
                area = (maxX - minX) * (maxY - minY);
                step = i;
                break;
            }

        }


        int[][] grid = new int[maxX - minX][maxY - minY];
        for (int[] data : input) {
            grid[data[1] + step * data[3] - minY][data[0] + step * data[2] - minX] = 1;
        }

        for (int[] row : grid) {
            for (int ch : row) {
                System.out.print(ch == 1 ? "X" : " ");
            }
        }

        return null;
    }*/

    // position=< 50769, -40375> velocity=<-5,  4>
    // x, y, dx, dy
    public String solvePartA(List<String> lines) throws IOException {
        OutputStream out = new FileOutputStream(new File("/Users/wiktor.sroka/dev/git/aoc2018/day10-out.txt"));
        List<int[]> input = convert(lines);

        int solutionStep = findSolutionStep(input);
        System.out.println(solutionStep);


        for (int i = 0; i < 10150; i++) {
            for (int[] data : input) {
                data[0] += data[2];
                data[1] += data[3];
            }

            // minX, maxX, minY, maxY
            int[] max = getMax(input);

            //print(out, toGrid(max[1], max[3], input));

            if (i == 10123) {
                print(out, toGrid(max[1], max[3], input));
                for (int[] data : input) {
                    System.out.println(data[0] + "," + data[1]);
                }
//                System.out.println("It was: " + i);
//                out.write(("It was: " + i).getBytes());
//                out.write('\0');
                continue;
            }
        }

        out.flush();
        out.close();
        return null;
    }

    private int findSolutionStep(List<int[]> points) {
        int start = Integer.MAX_VALUE;
        for (int[] point : points) {
            if (Math.abs(point[0]/point[2]) < start) {
                start = Math.abs(point[0]/point[2]);
            } else if (Math.abs(point[1]/point[3]) < start) {
                start = Math.abs(point[1] / point[3]);
            }
        }

        System.out.println(start);

        long range = 0;


        for (int i = start;; i++) {
            int minX = Integer.MAX_VALUE,
                maxX = Integer.MIN_VALUE,
                minY = Integer.MAX_VALUE,
                maxY = Integer.MIN_VALUE;
            for (int[] point : points) {
                int x = point[0] + i * point[2];
                int y = point[1] + i * point[3];
                if (x < 0 || y < 0) {
                    continue;
                }
                if (x < minX) minX = x;
                if (x > maxX) maxX = x;
                if (y < minY) minY = y;
                if (y > maxY) maxY = y;
            }

            long currentRange = (maxX - minX) * (long) (maxY - minY);
            System.out.println(currentRange);
            if (range == 0) {
                range = currentRange;
            } else if (range > currentRange) {
                range = currentRange;
            } else {
                // stop, previous result is the solution
                start = i - 1;
                break;
            }
        }

        return start;
    }

    public String solvePartASample(List<String> lines) throws IOException {
        OutputStream out = new FileOutputStream(new File("/Users/vito/dev/git/aoc2018/day10-sample-out.txt"));
        List<int[]> input = convertSample(lines);

        for (int i = 0; i < 5; i++) {
            for (int[] data : input) {
                data[0] += data[2];
                data[1] += data[3];
            }

            // minX, maxX, minY, maxY
            int[] max = getMax(input);

            //print(out, toGrid(max[1], max[3], input));

            if (i > 10070 || (max[0] >= 0 && max[2] >= 0 && max[1] <= 210 && max[2] <= 210)) {
                print(out, toGrid(max[1], max[3], input));
                out.write(("It was: " + i).getBytes());
                out.write('\0');
                continue;
            }
        }

        out.flush();
        out.close();
        return null;
    }

    private int[][] toGrid(int maxX, int maxY, List<int[]> input) {
        int[][] grid = new int[210][210];
        for (int[] data : input) {
            if (data[0] >= 0 && data[0] <= 210 && data[1] >= 0 && data[1] <= 210) {
                grid[data[1]][data[0]] = 1;
            }
        }

        return grid;
    }

    private int[] getMax(List<int[]> input) {
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] data : input) {
            if (data[0] > maxX) {
                maxX = data[0];
            }
            if (data[0] < minX) {
                minX = data[0];
            }
            if (data[1] > maxY) {
                maxY = data[1];
            }
            if (data[1] < minY) {
                minY = data[1];
            }
        }

        return new int[] { minX, maxX, minY, maxY };
    }

    private void print(OutputStream out, int[][] grid) throws IOException {
        for (int[] rows : grid) {
            for (int row : rows) {
                System.out.print(row == 1 ? "X" : " ");
                out.write(row == 1 ? 'X' : ' ');
            }
            System.out.println();
            out.write('\n');
        }

        System.out.println();
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println();
    }

    public String solvePartB(List<String> lines) {
        return null;
    }

    // position=< 9,  1> velocity=< 0,  2>
    private List<int[]> convertSample(List<String> lines) {
        List<int[]> result = new ArrayList<>(lines.size());
        for (String line : lines) {
            int[] d = new int[4];
            d[0] = Integer.parseInt(line.substring(10, 12).trim());
            d[1] = Integer.parseInt(line.substring(14, 16).trim());
            d[2] = Integer.parseInt(line.substring(28, 30).trim());
            d[3] = Integer.parseInt(line.substring(32, 34).trim());

            result.add(d);
        }

        return result;
    }

    // position=< 50769, -40375> velocity=<-5,  4>
    private List<int[]> convert(List<String> lines) {
        List<int[]> result = new ArrayList<>(lines.size());
        for (String line : lines) {
            int[] d = new int[4];
            d[0] = Integer.parseInt(line.substring(10, 16).trim());
            d[1] = Integer.parseInt(line.substring(18, 24).trim());
            d[2] = Integer.parseInt(line.substring(36, 38).trim());
            d[3] = Integer.parseInt(line.substring(40, 42).trim());

            result.add(d);
        }

        return result;
    }

}
