package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day11Test {
    private Day11 testObj;

    @Before
    public void setUp() {
        testObj = new Day11();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given

        // when
        String result = testObj.solvePartA(4172);

        // then
        Assert.assertEquals("243,43", result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given

        // when
        String result = testObj.solvePartB(4172);

        // then
        Assert.assertEquals("236,151,15", result);
    }

    @Test
    public void shouldSolvePartASamples() throws Exception {
        // given

        // when
        String result = testObj.solvePartA(42);

        // then
        Assert.assertEquals("21,61", result);
    }

    @Test
    public void shouldSolvePartBSamples() throws Exception {
        // given

        // when
        String result = testObj.solvePartB(18);

        // then
        Assert.assertEquals("90,269,16", result);
    }
}
