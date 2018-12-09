package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Day09Test {
    private Day09 testObj;

    @Before
    public void setUp() {
        testObj = new Day09();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given

        // when
        long result = testObj.solvePartA(400, 71864);

        // then
        Assert.assertEquals(437654L, result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given

        // when
        long result = testObj.solvePartB(400, 71864 * 100);

        // then
        Assert.assertEquals(3689913905L, result);
    }

    @Test
    public void shouldSolvePartASample1() throws Exception {
        // given

        // when
        long result = testObj.solvePartA(9, 25);

        // then
        Assert.assertEquals(32, result);
    }

    @Test
    public void shouldSolvePartASample2() throws Exception {
        // given

        // when
        long result = testObj.solvePartA(10, 1618);

        // then
        Assert.assertEquals(8317, result);
    }

    @Test
    public void shouldSolvePartASample3() throws Exception {
        // given

        // when
        long result = testObj.solvePartA(13, 7999);

        // then
        Assert.assertEquals(146373, result);
    }

    @Test
    public void shouldSolvePartASample4() throws Exception {
        // given

        // when
        long result = testObj.solvePartA(17, 1104);

        // then
        Assert.assertEquals(2764, result);
    }

    @Test
    public void shouldSolvePartASample5() throws Exception {
        // given

        // when
        long result = testObj.solvePartA(21, 6111);

        // then
        Assert.assertEquals(54718, result);
    }

    @Test
    public void shouldSolvePartASample6() throws Exception {
        // given

        // when
        long result = testObj.solvePartA(30, 5807);

        // then
        Assert.assertEquals(37305, result);
    }
}
