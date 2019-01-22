package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day10Test {
    private Day10 testObj;

    @Before
    public void setUp() {
        testObj = new Day10();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day10Test.class.getResource("Day10.txt").toURI()));

        // when
        String result = testObj.solvePartA(lines);

        // then
        Assert.assertEquals("", result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day10Test.class.getResource("Day10.txt").toURI()));

        // when
        String result = testObj.solvePartB(lines);

        // then
        Assert.assertEquals("", result);
    }

    @Test
    public void shouldSolvePartASample() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day10Test.class.getResource("Day10-sample.txt").toURI()));

        // when
        String result = testObj.solvePartASample(lines);

        // then
        Assert.assertEquals("", result);
    }

    @Test
    public void shouldSolvePartBSample() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day10Test.class.getResource("Day10-sample.txt").toURI()));

        // when
        String result = testObj.solvePartB(lines);

        // then
        Assert.assertEquals("", result);
    }
}
