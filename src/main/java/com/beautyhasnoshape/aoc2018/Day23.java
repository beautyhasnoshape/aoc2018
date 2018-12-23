package com.beautyhasnoshape.aoc2018;

import java.util.*;

/**
 * --- Day 23: Experimental Emergency Teleportation ---
 * Using your torch to search the darkness of the rocky cavern, you finally locate the man's friend: a small reindeer.
 *
 * You're not sure how it got so far in this cave. It looks sick - too sick to walk - and too heavy for you
 * to carry all the way back. Sleighs won't be invented for another 1500 years, of course.
 *
 * The only option is experimental emergency teleportation.
 *
 * You hit the "experimental emergency teleportation" button on the device and push I accept the risk
 * on no fewer than 18 different warning messages.
 * Immediately, the device deploys hundreds of tiny nanobots which fly around the cavern, apparently assembling
 * themselves into a very specific formation.
 * The device lists the X,Y,Z position (pos) for each nanobot as well as its signal radius (r)
 * on its tiny screen (your puzzle input).
 *
 * Each nanobot can transmit signals to any integer coordinate which is a distance away from it less than
 * or equal to its signal radius (as measured by Manhattan distance).
 * Coordinates a distance away of less than or equal to a nanobot's signal radius are said to be in range
 * of that nanobot.
 *
 * Before you start the teleportation process, you should determine which nanobot is the strongest
 * (that is, which has the largest signal radius) and then, for that nanobot, the total number of nanobots
 * that are in range of it, including itself.
 *
 * For example, given the following nanobots:
 * - pos=<0,0,0>, r=4
 * - pos=<1,0,0>, r=1
 * - pos=<4,0,0>, r=3
 * - pos=<0,2,0>, r=1
 * - pos=<0,5,0>, r=3
 * - pos=<0,0,3>, r=1
 * - pos=<1,1,1>, r=1
 * - pos=<1,1,2>, r=1
 * - pos=<1,3,1>, r=1
 *
 * The strongest nanobot is the first one (position 0,0,0) because its signal radius, 4 is the largest.
 * Using that nanobot's location and signal radius, the following nanobots are in or out of range:
 * - The nanobot at 0,0,0 is distance 0 away, and so it is in range.
 * - The nanobot at 1,0,0 is distance 1 away, and so it is in range.
 * - The nanobot at 4,0,0 is distance 4 away, and so it is in range.
 * - The nanobot at 0,2,0 is distance 2 away, and so it is in range.
 * - The nanobot at 0,5,0 is distance 5 away, and so it is not in range.
 * - The nanobot at 0,0,3 is distance 3 away, and so it is in range.
 * - The nanobot at 1,1,1 is distance 3 away, and so it is in range.
 * - The nanobot at 1,1,2 is distance 4 away, and so it is in range.
 * - The nanobot at 1,3,1 is distance 5 away, and so it is not in range.
 * - In this example, in total, 7 nanobots are in range of the nanobot with the largest signal radius.
 *
 * Find the nanobot with the largest signal radius. How many nanobots are in range of its signals?
 *
 * Your puzzle answer was 713.
 *
 * --- Part Two ---
 * Now, you just need to figure out where to position yourself so that you're actually teleported
 * when the nanobots activate.
 *
 * To increase the probability of success, you need to find the coordinate which puts you in range of the largest number
 * of nanobots. If there are multiple, choose one closest to your position (0,0,0, measured by manhattan distance).
 *
 * For example, given the following nanobot formation:
 * - pos=<10,12,12>, r=2
 * - pos=<12,14,12>, r=2
 * - pos=<16,12,12>, r=4
 * - - pos=<14,14,14>, r=6
 * - pos=<50,50,50>, r=200
 * - pos=<10,10,10>, r=5
 *
 * Many coordinates are in range of some of the nanobots in this formation. However, only the coordinate 12,12,12 is
 * in range of the most nanobots: it is in range of the first five, but is not in range of the nanobot at 10,10,10.
 * (All other coordinates are in range of fewer than five nanobots.) This coordinate's distance from 0,0,0 is 36.
 *
 * Find the coordinates that are in range of the largest number of nanobots. What is the shortest manhattan distance
 * between any of those points and 0,0,0?
 *
 * Your puzzle answer was 104501042.
 */
public class Day23 {
    public static class Point implements Comparable<Point> {
        long x, y, z, r;

        public Point(long x, long y, long z, long r) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.r = r;
        }

        long distanceTo(Point other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
        }

        boolean isInRange(Point other) {
            return distanceTo(other) <= r;
        }

        @Override
        public int compareTo(Point o) {
            int result = Long.compare(x, o.x);
            if (result == 0 ) {
                result = Long.compare(y, o.y);
            }
            if (result == 0 ) {
                result = Long.compare(z, o.z);
            }

            return result;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + "," + z + ", r=" + r + ')';
        }
    }

    public static class Size {
        long w, d, h; // x, y, z
        long minX, minY, minZ, maxX, maxY, maxZ, minR, maxR;

        public Size(long minX, long minY, long minZ, long maxX, long maxY, long maxZ, long minR, long maxR) {
            this.minX = minX;
            this.minY = minY;
            this.minZ = minZ;
            this.maxX = maxX;
            this.maxY = maxY;
            this.maxZ = maxZ;
            this.minR = minR;
            this.maxR = maxR;

            this.w = maxX - minX;
            this.d = maxY - minY;
            this.h = maxZ - minZ;
        }

        @Override
        public String toString() {
            return "Size{" +
                    "w=" + w +
                    ", d=" + d +
                    ", h=" + h +
                    ", min=(" + minX + "," + minY + "," + minZ + ")" +
                    ", max=(" + maxX + "," + maxY + "," + maxZ + ")" +
                    ", r=(" + minR + ".." + maxR + ")" +
                    '}';
        }
    }

    private Point findStrongestNanobot(List<Point> nanobots) {
        Point result = null;
        long range = 0;

        for (Point nanobot : nanobots) {
            if (nanobot.r > range) {
                range = nanobot.r;
                result = nanobot;
            }
        }

        return result;
    }

    // Size{w=331993443, d=210962839, h=214887913, min=(-86417218,-76528417,-51892699), max=(245576225,134434422,162995214)}
    private Size findDimensions(Set<Point> nanobots) {
        long minX = Long.MAX_VALUE, minY = Long.MAX_VALUE, minZ = Long.MAX_VALUE,
             maxX = Long.MIN_VALUE, maxY = Long.MIN_VALUE, maxZ = Long.MIN_VALUE,
             minR = Long.MAX_VALUE, maxR = Long.MIN_VALUE;

        for (Point nanobot : nanobots) {
            if (nanobot.x < minX) {
                minX = nanobot.x;
            }
            if (nanobot.y < minY) {
                minY = nanobot.y;
            }
            if (nanobot.z < minZ) {
                minZ = nanobot.z;
            }
            if (nanobot.r < minR) {
                minR = nanobot.r;
            }
            if (nanobot.x > maxX) {
                maxX = nanobot.x;
            }
            if (nanobot.y > maxY) {
                maxY = nanobot.y;
            }
            if (nanobot.z > maxZ) {
                maxZ = nanobot.z;
            }
            if (nanobot.r > maxR) {
                maxR = nanobot.r;
            }
        }

        return new Size(minX, minY, minZ, maxX, maxY, maxZ, minR, maxR);
    }

    public int solvePartA(List<Point> nanobots) {
        Point strongestNanobot = findStrongestNanobot(nanobots);

        return countAllNanobotsInRange(strongestNanobot, new HashSet<>(nanobots));
    }

    public int solvePartB(List<Point> input) {
        Set<Point> nanobotSet = new TreeSet<>();
        nanobotSet.addAll(input);

        Size size = findDimensions(nanobotSet);
        Point[] nanobots = nanobotSet.toArray(new Point[] {});

        // use Monte Carlo method, search within given timeout
        Point point = findPointInHighestDensityRangeMock(nanobots, size, 30 * 1000);

        // for a given point, search for all nanobots having it in their range
        Point[] nanobotsInRange = findAllNanobotsInRange(point, nanobots).toArray(new Point[] {});
        int maxNanobotsCount = nanobotsInRange.length;
        System.out.println("Best score found is " + maxNanobotsCount + " for " + point);

        // traverse home unless leaving the range of any of the found nanobots
        long dx = 0, dy = 0, dz = 0;
        for (int d = 0; ; d++) {
            Point checkPoint = new Point(point.x - d, point.y, point.z, 0);
            if (countAllNanobotsHavingPointInRange(checkPoint, nanobotsInRange) == maxNanobotsCount) {
                dx = d;
            } else {
                break;
            }
        }

        for (int d = 0; ; d++) {
            Point checkPoint = new Point(point.x - dx, point.y - d, point.z, 0);
            if (countAllNanobotsHavingPointInRange(checkPoint, nanobotsInRange) == maxNanobotsCount) {
                dy = d;
            } else {
                break;
            }
        }

        for (int d = 0; ; d++) {
            Point checkPoint = new Point(point.x - dx, point.y - dy, point.z - d, 0);
            if (countAllNanobotsHavingPointInRange(checkPoint, nanobotsInRange) == maxNanobotsCount) {
                dz = d;
            } else {
                break;
            }
        }

        Point zeroPoint = new Point(0, 0, 0, 0);
        Point closestPoint = new Point(point.x - dx, point.y - dy, point.z - dz, 0);
        long closestDistance = zeroPoint.distanceTo(closestPoint);
        System.out.println("Found the closest point to the highest density area at " + closestPoint
                + ", with a distance of " + closestDistance);

        return (int) closestDistance;
    }
    // Found the closest point to the highest density area at (59023690,22638279,22839073, r=0), with a distance of 104501042

    private List<Point> findAllNanobotsInRange(Point point, Point[] nanobots) {
        List<Point> nanobotsInRange = new ArrayList<>(1000);
        for (Point nanobot : nanobots) {
            if (nanobot.isInRange(point)) {
                nanobotsInRange.add(nanobot);
            }
        }

        return nanobotsInRange;
    }

    private Point findPointInHighestDensityRangeMock(Point[] nanobots, Size size, long timeLimit) {
        return new Point(60007566,22638279,23515646, 0);
    }

    /**
     * Use Monte Carlo method to find the point in the most density range.
     */
    private Point findPointInHighestDensityRange(Point[] nanobots, Size size, long timeLimit) {
        int maxThreadCount = 8;

        Searcher[] searchers = new Searcher[maxThreadCount];
        Thread[] threads = new Thread[maxThreadCount];

        for (int i = 0; i < maxThreadCount; i++) {
            searchers[i] = new Searcher(timeLimit, nanobots, size);
            threads[i] = new Thread(searchers[i], "Searcher-" + i);
            threads[i].start();
        }

        int finishedThreadCount = 0;
        do {
            for (Thread thread : threads) {
                if (!thread.isAlive()) {
                    ++finishedThreadCount;
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (finishedThreadCount < maxThreadCount);

        int bestScore = 0;
        Point bestScorePoint = null;

        for (Searcher searcher : searchers) {
            if (searcher.getBestScore() > bestScore) {
                bestScore = searcher.getBestScore();
                bestScorePoint = searcher.getBestScorePoint();
            }
        }

        return bestScorePoint;
    }

    private class Searcher implements Runnable {
        private final Size size;
        private final long executionTimeLimitMillis;
        private final Point[] nanobots;
        private final Random random;

        private int bestScore;
        private Point bestScorePoint;

        public Searcher(long timeLimit, Point[] nanobots, Size size) {
            this.executionTimeLimitMillis = timeLimit;
            this.nanobots = nanobots;
            this.size = size;

            this.random = new Random();
            this.bestScore = 0;
            this.bestScorePoint = null;
        }

        public int getBestScore() {
            return bestScore;
        }

        public Point getBestScorePoint() {
            return bestScorePoint;
        }

        @Override
        public void run() {
            // use Monte Carlo method
            long stopTime = System.currentTimeMillis() + executionTimeLimitMillis;
            boolean isTimeExpired = false;

            for (int i = 0; !isTimeExpired; i++) {
                Point point = getRandomPoint(size);
                point.r = countAllNanobotsHavingPointInRange(point, nanobots);
                if (bestScore < point.r) {
                    bestScore = (int) point.r;
                    bestScorePoint = point;
                }

                if (i % 100_000 == 0) {
                    if (System.currentTimeMillis() > stopTime) {
                        isTimeExpired = true;
                    }
                }
            }
        }

        private Point getRandomPoint(Size constraints) {
            int x = random.nextInt((int) (constraints.w + 1)) + (int) constraints.minX;
            int y = random.nextInt((int) (constraints.d + 1)) + (int) constraints.minY;
            int z = random.nextInt((int) (constraints.h + 1)) + (int) constraints.minZ;

            return new Point(x, y, z, 0);
        }

        private long countAllNanobotsHavingPointInRange(Point point, Point[] nanobots) {
            int count = 0;
            for (Point nanobot : nanobots) {
                if (nanobot.isInRange(point)) {
                    ++count;
                }
            }

            return count;
        }
    }

    // Best score found is 893 for (59071634,23349147,22450060, r=893)

    private long countAllNanobotsHavingPointInRange(Point point, Point[] nanobots) {
        int count = 0;
        for (Point nanobot : nanobots) {
            if (nanobot.isInRange(point)) {
                ++count;
            }
        }

        return count;
    }

    private int countAllNanobotsInRange(Point point, Set<Point> nanobots) {
        int count = 0;
        for (Point nanobot : nanobots) {
            if (point.isInRange(nanobot)) {
                ++count;
            }
        }

        return count;
    }
}
