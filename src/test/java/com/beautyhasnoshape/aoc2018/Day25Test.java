//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.beautyhasnoshape.aoc2018;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day25Test {
    private Day25 testObj;

    public Day25Test() {
    }

    @Before
    public void setUp() {
        this.testObj = new Day25();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        Path path = Paths.get(this.getClass().getResource("Day25.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        // when
        int result = this.testObj.solvePartA(lines);

        // then
        Assert.assertEquals(420, result);
    }
}
