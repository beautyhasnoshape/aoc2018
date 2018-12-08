package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day08Test {
    private Day08 testObj;

    @Before
    public void setUp() {
        testObj = new Day08();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day08Test.class.getResource("Day08.txt").toURI()));

        // when
        int result = testObj.solvePartA(convert(lines.get(0)));

        // then
        Assert.assertEquals(36307, result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day08Test.class.getResource("Day08.txt").toURI()));

        // when
        int result = testObj.solvePartB(convert(lines.get(0)));

        // then
        Assert.assertEquals(25154, result);
    }

    @Test
    public void shouldSolvePartASample() throws Exception {
        // given
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

        // when
        int result = testObj.solvePartA(convert(input));

        // then
        Assert.assertEquals(138, result);
    }

    @Test
    public void shouldSolvePartBSample() throws Exception {
        // given
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

        // when
        int result = testObj.solvePartB(convert(input));

        // then
        Assert.assertEquals(66, result);
    }

    private int[] convert(String input) {
        String[] split = input.split(" ");
        int[] data = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            data[i] = Integer.parseInt(split[i]);
        }
        return data;
    }
}
