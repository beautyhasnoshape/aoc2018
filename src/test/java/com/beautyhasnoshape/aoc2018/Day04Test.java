package com.beautyhasnoshape.aoc2018;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Day04Test {
    private Day04 testObj;

    @Before
    public void setUp() {
        testObj = new Day04();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day04Test.class.getResource("Day04.txt").toURI()));

        // sort lines by date, format: [1518-11-19 00:04]
        Collections.sort(lines, (o1, o2) -> {
            LocalDateTime leftDate  = LocalDateTime.parse(o1.substring(1, 11) + "T" + o1.substring(12, 17));
            LocalDateTime rightDate = LocalDateTime.parse(o2.substring(1, 11) + "T" + o2.substring(12, 17));

            return leftDate.compareTo(rightDate);
        });

        // when
        int result = testObj.solvePartA(lines);

        // then
        Assert.assertEquals(106710, result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day04Test.class.getResource("Day04.txt").toURI()));

        // sort lines by date, format: [1518-11-19 00:04]
        Collections.sort(lines, (o1, o2) -> {
            LocalDateTime leftDate  = LocalDateTime.parse(o1.substring(1, 11) + "T" + o1.substring(12, 17));
            LocalDateTime rightDate = LocalDateTime.parse(o2.substring(1, 11) + "T" + o2.substring(12, 17));

            return leftDate.compareTo(rightDate);
        });

        // when
        int result = testObj.solvePartB(lines);

        // then
        Assert.assertEquals(10491, result);
    }

    @Test
    public void shouldSolvePartASample() throws Exception {
        // given
        List<String> lines = Files.readAllLines(Paths.get(Day04Test.class.getResource("Day04-sample.txt").toURI()));

        // when
        int result = testObj.solvePartA(lines);

        // then
        Assert.assertEquals(240, result);
    }



}
