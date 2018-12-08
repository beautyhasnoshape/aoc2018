package com.beautyhasnoshape.aoc2018;

/**
 * Spiral walker starts in the given point on the grid and goes around, whereas the first step is right, then up.
 *
 * Example:
 * Starting in the middle, marked as (1), then following with 2, 3, 4, ..., up to 8, as shown below:
 *  5 4 3
 *  6 1 2
 *  7 8
 */
public class SpiralWalker {
    private Point startPosition;
    private Point currentPosition;

    public SpiralWalker() {
        this(new Point());
    }

    public SpiralWalker(Point startPosition) {
        this.startPosition = startPosition;
        this.currentPosition = new Point(startPosition);
    }

    public void start(Point from) {
        startPosition = from;
        currentPosition = new Point(from);
    }

    private static final Point SHIFT_RIGHT = new Point( 1,  0);
    private static final Point SHIFT_LEFT  = new Point(-1,  0);
    private static final Point SHIFT_UP    = new Point( 0,  1);
    private static final Point SHIFT_DOWN  = new Point( 0, -1);

    public Point next() {
        Point shift;

        int ring = getRing();
        if (ring == 0) {
            // starting point, navigate right
            shift = SHIFT_RIGHT;
        } else {
            // determine vertical direction UP or DOWN
            int v = currentPosition.y - startPosition.y;
            int h = currentPosition.x - startPosition.x;

            // in order to keep it simple and maintenable I separate it into corner cases and border cases
            if (v == ring && h == ring) { // upper right corner, go left
                shift = SHIFT_LEFT;
            } else if (v == ring && h == -ring) { // upper left corner, go down
                shift = SHIFT_DOWN;
            } else if (v == -ring && h == ring) { // lower right corner, go right (promote to next ring)
                shift = SHIFT_RIGHT;
            } else if (v == -ring && h == -ring) { // lower left corner, go right
                shift = SHIFT_RIGHT;
            } else if (v == ring) { // upper border, go left
                shift = SHIFT_LEFT;
            } else if (v == -ring) { // lower border, go right
                shift = SHIFT_RIGHT;
            } else if (h == ring) { // right border, go up
                shift = SHIFT_UP;
            } else if (h == -ring) { // right border, go down
                shift = SHIFT_DOWN;
            } else {
                throw new IllegalStateException("Invalid state");
            }
        }

        currentPosition.translate(shift);

        return currentPosition;
    }



    /**
     * Calculates where the current element is positioned to the starting point.
     * First coordinate of the point represents horizontal axis, the second represents vertical one.
     * Positive value of the coordinate means on right or top (respectively to horizontal and vertical axis),
     * a negative one on left or under.
     * @return
     */
    protected Point getShiftDirection() {
        int v = currentPosition.y - startPosition.y;
        int h = currentPosition.x - startPosition.x;

        int vDirection = 0,
            hDirection = 0;
        if (v != 0) {
            vDirection = v > 0 ? 1 : -1;
        }
        if (h != 0) {
            hDirection = h > 0 ? 1 : -1;
        }

        return new Point(hDirection, vDirection);
    }
    /**
     * A maximum of vertical and horizontal distances to the starting point.
     *
     * Considering (0) as a starting point, distances can be shown as follows:
     * 4 4 4 4 4 4 4 4 4
     * 4 3 3 3 3 3 3 3 4
     * 4 3 2 2 2 2 2 3 4
     * 4 3 2 1 1 1 2 3 4
     * 4 3 2 1 0 1 2 3 4
     * 4 3 2 1 1 1 2 3 4
     * 4 3 2 2 2 2 2 3 4
     * 4 3 3 3 3 3 3 3 4
     * 4 4 4 4 4 4 4 4 4
     *
     * @return
     */
    protected int getRing() {
        return Math.max(Math.abs(startPosition.x - currentPosition.x), Math.abs(startPosition.y - currentPosition.y));
    }

    public Point current() {
        return currentPosition;
    }

    private Point shift(Point shift) {
        return new Point(currentPosition.x + shift.x, currentPosition.y + shift.y);
    }

    /**
     * Mutable class!
     */
    public static class Point {
        private int x, y;

        public Point() {
            this(0, 0);
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point point) {
            this(point.x, point.y);
        }

        public int manhattanDistance(Point to) {
            return Math.abs(this.x - to.getX()) + Math.abs(this.y - to.getY());
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Point translate(Point t) {
            this.x += t.getX();
            this.y += t.getY();

            return this;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
