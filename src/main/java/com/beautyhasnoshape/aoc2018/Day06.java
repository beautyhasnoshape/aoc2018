package com.beautyhasnoshape.aoc2018;

import java.util.ArrayList;
import java.util.List;

import com.beautyhasnoshape.aoc2018.SpiralWalker.Point;

public class Day06 {
    private int findClosestPoint(List<Point> points, Point ref) {
        int minDistance = Integer.MAX_VALUE;
        int minIdx = -1;
        boolean isDuplicated = false;

        for (int idx = 0; idx < points.size(); idx++) {
            Point point = points.get(idx);
            int distance = distance(point, ref);
            if (distance < minDistance) {
                minDistance = distance;
                minIdx = idx;
                isDuplicated = false;
            } else if (distance == minDistance) {
                isDuplicated = true;
            }
        }

        return isDuplicated ? -1 : minIdx;
    }

    private int[] getBoundaries(List<Point> points) {
        int minX = Integer.MAX_VALUE,
            maxX = -Integer.MAX_VALUE,
            minY = Integer.MAX_VALUE,
            maxY = -Integer.MAX_VALUE;

        for (Point point : points) {
            int x = point.getX(),
                y = point.getY();

            if (x > maxX) {
                maxX = x;
            }
            if (x < minX) {
                minX = x;
            }
            if (y > maxY) {
                maxY = y;
            }
            if (y < minY) {
                minY = y;
            }
        }
        return new int[] { minX, minY, maxX, maxY };
    }

    private int[] dump(int[] from) {
        int[] to = new int[from.length];
        System.arraycopy(from, 0, to, 0, from.length);

        return to;
    }

    public int solvePartA(List<Point> points) {
        int[] boundaries = getBoundaries(points); // xMin, yMin, xMax, yMax
        int w = boundaries[2] - boundaries[0];
        int h = boundaries[3] - boundaries[1];
        int dia = (Math.max(w, h) + 1);

        Point start = new Point(boundaries[0] + w / 2, boundaries[1] + h / 2);
        SpiralWalker walker = new SpiralWalker(start);

        int[] freqs = new int[points.size()];
        boolean stop = false;

        List<Integer> finalIds = new ArrayList<>(points.size());

        int lastRing = 0;
        int[] lastFreqs = dump(freqs);

        Point point = start;
        do {
            int idx = findClosestPoint(points, point);
            if (idx > -1) {
                ++freqs[idx];
            }

            // check whether it should stop or not
            if (lastRing < walker.getRing()) {
                int ring = walker.getRing();
                if (ring > dia) {
                    int count = processUnchangedPoints(lastFreqs, freqs, finalIds);
                    if (count == 0) {
                        stop = true;
                    }
                }

                lastFreqs = dump(freqs);
                lastRing = ring;
            }
            point = walker.next();
        } while (!stop);

        int max = Integer.MIN_VALUE;
        for (Integer freq : finalIds) {
            if (max < freqs[freq]) {
                max = freqs[freq];
            }
        }

        return max;
    }

    private int processUnchangedPoints(int[] lastFreqs, int[] newFreqs, List<Integer> finalIds) {
        int count = 0;
        for (int i = 0; i < lastFreqs.length; i++) {
            if (lastFreqs[i] == newFreqs[i] && !finalIds.contains(i)) {
                finalIds.add(i);
                ++count;
            }
        }

        return count;
    }

    public int solvePartABruteForce(List<Point> points) {
        int[] boundaries = getBoundaries(points); // xMin, yMin, xMax, yMax
        int w = boundaries[2] - boundaries[0];
        int h = boundaries[3] - boundaries[1];
        int factor = 5;
        int minX = boundaries[0] - factor * w;
        int minY = boundaries[0] - factor * h;
        int maxX = boundaries[0] + factor * w;
        int maxY = boundaries[0] + factor * h;

        int[] freqs = new int[points.size()];

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Point point = new Point(x, y);
                int idx = findClosestPoint(points, point);
                if (idx > -1) {
                    ++freqs[idx];
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > 0 && (freqs[i] > max) && (freqs[i] < w * h)) {
                max = freqs[i];
            }
        }

        return max;
    }

    private int distance(Point a, Point b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public int solvePartB(List<Point> points, int ceiling) {
        int[] boundaries = getBoundaries(points); // xMin, yMin, xMax, yMax
        int w = boundaries[2] - boundaries[0];
        int h = boundaries[3] - boundaries[1];
        int dia = (Math.max(w, h) + 1);

        Point start = new Point(boundaries[0] + w / 2, boundaries[1] + h / 2);
        SpiralWalker walker = new SpiralWalker(start);

        boolean stop = false;

        int lastRing = 0;
        int count = 0;
        int lastCount = 0;

        Point point = start;
        do {
            int sum = findDistanceSum(points, point);
            if (sum < ceiling) {
                ++count;
            }

            // check whether it should stop or not
            if (lastRing < walker.getRing()) {
                int ring = walker.getRing();
                if (ring > dia) {
                    if (lastCount == count) {
                        stop = true;
                    }
                }
                lastCount = count;
                lastRing = ring;
            }

            point = walker.next();
        } while (!stop);


        return count;


    }

    public int solvePartBBruteForce(List<Point> points, int ceiling) {
        int[] boundaries = getBoundaries(points); // xMin, yMin, xMax, yMax
        int w = boundaries[2] - boundaries[0];
        int h = boundaries[3] - boundaries[1];
        int factor = 5;
        int minX = boundaries[0] - factor * w;
        int minY = boundaries[0] - factor * h;
        int maxX = boundaries[0] + factor * w;
        int maxY = boundaries[0] + factor * h;


        int count = 0;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                Point point = new Point(x, y);
                int sum = findDistanceSum(points, point);

                if (sum < ceiling) {
                    ++count;
                }
            }
        }

        return count;
    }

    private int findDistanceSum(List<Point> points, Point ref) {
        int sum = 0;

        for (Point point : points) {
            sum += distance(point, ref);
        }

        return sum;
    }
}
