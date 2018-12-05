package beauty.aoc2018;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class Day03Test {
    private Day03 testObj;

    @Before
    public void setUp() throws Exception {
        testObj = new Day03();
    }

    @Test
    public void shouldSolvePartA() throws Exception {
        // given
        Path path = Paths.get(getClass().getResource("/Day03.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        // when
        int result = testObj.solvePartA(lines);

        // then
        assertEquals(111326, result);
    }

    @Test
    public void shouldSolvePartB() throws Exception {
        // given
        Path path = Paths.get(getClass().getResource("/Day03.txt").toURI());
        List<String> lines = Files.readAllLines(path);

        // when
        int result = testObj.solvePartB(lines);

        // then
        assertEquals(1019, result);
    }
}