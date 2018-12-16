package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day16Test {
    private Day16 testObj;

    @Before
    public void setUp() {
        testObj = new Day16();
    }

    private int[] convert(String operation) {
        int[] result = new int[4];

        String[] split = operation.split(" ");
        result[0] = Integer.parseInt(split[0]);
        result[1] = Integer.parseInt(split[1]);
        result[2] = Integer.parseInt(split[2]);
        result[3] = Integer.parseInt(split[3]);

        return result;
    }

    private int[] convert(String before, String operation, String after) {
        int[] result = new int[12];

        String[] split = before.substring(9, 19).split(", ");
        result[0] = Integer.parseInt(split[0]);
        result[1] = Integer.parseInt(split[1]);
        result[2] = Integer.parseInt(split[2]);
        result[3] = Integer.parseInt(split[3]);

        split = operation.split(" ");
        result[4] = Integer.parseInt(split[0]);
        result[5] = Integer.parseInt(split[1]);
        result[6] = Integer.parseInt(split[2]);
        result[7] = Integer.parseInt(split[3]);

        split = after.substring(9, 19).split(", ");
        result[8] = Integer.parseInt(split[0]);
        result[9] = Integer.parseInt(split[1]);
        result[10] = Integer.parseInt(split[2]);
        result[11] = Integer.parseInt(split[3]);

        return result;
    }


    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day10Test.class.getResource("Day16.txt").toURI()));
        List<int[]> inputs = new ArrayList<>();

        String before = null, instruction = null;
        for (String line : lines) {
            if (line.startsWith("Before:")) {
                before = line;
            } else if (line.startsWith("After:")) {
                inputs.add(convert(before, instruction, line));
                before = null;
                instruction = null;
            } else if (before != null) {
                instruction = line;
            }
        }

        // when
        String result = testObj.solvePartA(inputs);

        // then
        Assert.assertEquals("642", result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day10Test.class.getResource("Day16.txt").toURI()));
        List<int[]> inputs = new ArrayList<>();
        List<int[]> data = new ArrayList<>();

        String before = null, instruction = null;
        for (String line : lines) {
            if (line.startsWith("Before:")) {
                before = line;
            } else if (line.startsWith("After:")) {
                inputs.add(convert(before, instruction, line));
                before = null;
                instruction = null;
            } else if (before != null) {
                instruction = line;
            } else if (!line.isEmpty()) {
                data.add(convert(line));
            }
        }

        // when
        String result = testObj.solvePartB(inputs, data);

        // then
        Assert.assertEquals("481", result);
    }
}
