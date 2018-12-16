package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Day14Test {
    private Day14 testObj;

    @Before
    public void setUp() {
        testObj = new Day14();
    }

    @Test
    public void shouldSolvePartA() {
        // given

        // when
        String result = testObj.solvePartA("37", 768071);

        // then
        Assert.assertEquals("6548103910", result);
    }

    @Test
    public void shouldSolvePartB() {
        // given

        // when
        String result = testObj.solvePartB("37", new int[] { 7, 6, 8, 0, 7, 1 });

        // then
        Assert.assertEquals("20198090", result);
    }

    @Test
    public void shouldSolvePartASample1() {
        // given

        // when
        String result = testObj.solvePartA("37", 5);

        // then
        Assert.assertEquals("0124515891", result);
    }

    @Test
    public void shouldSolvePartASample2() {
        // given

        // when
        String result = testObj.solvePartA("37", 9);

        // then
        Assert.assertEquals("5158916779", result);
    }

    @Test
    public void shouldSolvePartASample3() {
        // given

        // when
        String result = testObj.solvePartA("37", 18);

        // then
        Assert.assertEquals("9251071085", result);
    }

    @Test
    public void shouldSolvePartASample4() {
        // given

        // when
        String result = testObj.solvePartA("37", 2018);

        // then
        Assert.assertEquals("5941429882", result);
    }


}
