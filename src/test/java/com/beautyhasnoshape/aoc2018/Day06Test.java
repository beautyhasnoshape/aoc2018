package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        int result = testObj.solvePartA(lines);

        // then
        Assert.assertEquals(-1, result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day06Test.class.getResource("Day06.txt").toURI()));

        // when
        int result = testObj.solvePartB(lines);

        // then
        Assert.assertEquals(-1, result);
    }

    @Test
    public void shouldSolvePartASample() throws Exception {
        // given
        List<String> input = new ArrayList<>();

        // when
        int result = testObj.solvePartA(input);

        // then
        Assert.assertEquals(-1, result);
    }

    @Test
    public void shouldSolvePartBSample() throws Exception {
        // given
        List<String> input = new ArrayList<>();

        // when
        int result = testObj.solvePartB(input);

        // then
        Assert.assertEquals(-1, result);
    }
}
