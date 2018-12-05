package com.beautyhasnoshape.aoc2018;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Day05Test {
    private Day05 testObj;

    @Before
    public void setUp() {
        testObj = new Day05();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day05Test.class.getResource("Day05.txt").toURI()));

        // when
        int result = testObj.solvePartA(lines.get(0));

        // then
        Assert.assertEquals(10180, result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day05Test.class.getResource("Day05.txt").toURI()));

        // when
        long startMillis = System.currentTimeMillis();
        int result = testObj.solvePartB(lines.get(0));
        long stopMillis = System.currentTimeMillis();
        printTime("Part B", startMillis, stopMillis);

        // then
        Assert.assertEquals(5668, result);
    }

    private void printTime(String text, long startMillis, long stopMillis) {
        System.out.println("Execution time of '" + text + "': " + (stopMillis - startMillis) + "ms = "
                           + ((stopMillis - startMillis) / 1000) + "s");
    }

    @Test
    public void shouldSolvePartASample() throws Exception {
        // given
        String input = "dabAcCaCBAcCcaDA";

        // when
        int result = testObj.solvePartA(input);

        // then
        Assert.assertEquals(10, result);
    }

    @Test
    public void shouldSolvePartBSample() throws Exception {
        // given
        String input = "dabAcCaCBAcCcaDA";

        // when
        int result = testObj.solvePartB(input);

        // then
        Assert.assertEquals(4, result);
    }
}
