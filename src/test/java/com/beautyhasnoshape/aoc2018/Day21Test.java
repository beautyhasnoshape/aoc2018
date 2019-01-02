//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.beautyhasnoshape.aoc2018;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day21Test {
    private Day21 testObj;

    public Day21Test() {
    }

    @Before
    public void setUp() {
        this.testObj = new Day21();
    }

    @Test
    public void shouldSolveBothParts() {
        // given
        long start = System.currentTimeMillis();

        // when
        Pair<Integer, Integer> result = this.testObj.solve();
        long executionTimeMillis = System.currentTimeMillis() - start;

        // then
        System.out.println("Execution time [ms]: " + executionTimeMillis);
        Assert.assertEquals(212115,  (int) result.getLeft());
        Assert.assertEquals(9258470, (int) result.getRight());
    }
}
