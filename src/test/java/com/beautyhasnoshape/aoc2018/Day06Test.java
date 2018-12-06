package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.beautyhasnoshape.aoc2018.SpiralWalker.Point;

public class Day06Test {
    private Day06 testObj;

    @Before
    public void setUp() {
        testObj = new Day06();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day06Test.class.getResource("Day06.txt").toURI()));

        // when
        int result = testObj.solvePartA(convertToPoints(lines));

        // then
        Assert.assertEquals(3882, result);
    }

    private List<Point> convertToPoints(List<String> lines) {
        List<Point> points = new ArrayList<>(lines.size());

        for (String line : lines) {
            String[] split = line.split(", ");
            points.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        return points;
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day06Test.class.getResource("Day06.txt").toURI()));

        // when
        int result = testObj.solvePartB(convertToPoints(lines), 10000);

        // then
        Assert.assertEquals(43852, result);
    }

    @Test
    public void shouldSolvePartASample() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day06Test.class.getResource("Day06-sample.txt").toURI()));

        // when
        int result = testObj.solvePartA(convertToPoints(lines));

        // then
        Assert.assertEquals(17, result);
    }

    @Test
    public void shouldSolvePartBSample() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day06Test.class.getResource("Day06-sample.txt").toURI()));

        // when
        int result = testObj.solvePartB(convertToPoints(lines), 32);

        // then
        Assert.assertEquals(16, result);
    }
}
