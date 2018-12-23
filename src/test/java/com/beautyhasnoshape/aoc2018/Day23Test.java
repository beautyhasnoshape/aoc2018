package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day23Test {
    private Day23 testObj;

    @Before
    public void setUp() {
        testObj = new Day23();
    }

    private List<Day23.Point> convertToPoints(List<String> lines) {
        List<Day23.Point> nanobots = new ArrayList<>(lines.size());

        for (String line : lines) {
            // pos=<0,0,0>, r=4
            line = line.replaceAll("pos=<", "");
            line = line.replaceAll(">, r=", " ");
            line = line.replaceAll(">,", " ");
            line = line.replaceAll(",", " ");

            String[] split = line.split(" ");
            nanobots.add(new Day23.Point(Long.parseLong(split[0]), Long.parseLong(split[1]),
                    Long.parseLong(split[2]), Long.parseLong(split[3])));
        }

        return nanobots;
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        Path path = Paths.get(getClass().getResource("Day23.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        // when
        int result = testObj.solvePartA(convertToPoints(lines));

        // then
        Assert.assertEquals(713, result);
        // wrong: 380416 too high, 959 too high, 515 too low
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        Path path = Paths.get(getClass().getResource("Day23.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        // when
        int result = testObj.solvePartB(convertToPoints(lines));

        // then
        Assert.assertEquals(104501042, result);
    }

    @Test
    public void shouldSolvePartASample() throws Exception {
        // given
        Path path = Paths.get(getClass().getResource("Day23-sample.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        // when
        int result = testObj.solvePartA(convertToPoints(lines));

        // then
        Assert.assertEquals(7, result);
    }
}
