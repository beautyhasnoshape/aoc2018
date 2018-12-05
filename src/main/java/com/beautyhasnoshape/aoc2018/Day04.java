package com.beautyhasnoshape.aoc2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 4: Repose Record ---
 * You've sneaked into another supply closet - this time, it's across from the prototype suit manufacturing lab.
 * You need to sneak inside and fix the issues with the suit, but there's a guard stationed outside the lab,
 * so this is as close as you can safely get.
 * As you search the closet for anything that might help, you discover that you're not the first person
 * to want to sneak in. Covering the walls, someone has spent an hour starting every midnight for the past
 * few months secretly observing this guard post! They've been writing down the ID of the one guard on duty
 * that night - the Elves seem to have decided that one guard was enough for the overnight shift - as well as when
 * they fall asleep or wake up while at their post (your puzzle input).
 * For example, consider the following records, which have already been organized into chronological order:
 *
 * [1518-11-01 00:00] Guard #10 begins shift
 * [1518-11-01 00:05] falls asleep
 * [1518-11-01 00:25] wakes up
 * [1518-11-01 00:30] falls asleep
 * [1518-11-01 00:55] wakes up
 * [1518-11-01 23:58] Guard #99 begins shift
 * [1518-11-02 00:40] falls asleep
 * [1518-11-02 00:50] wakes up
 * [1518-11-03 00:05] Guard #10 begins shift
 * [1518-11-03 00:24] falls asleep
 * [1518-11-03 00:29] wakes up
 * [1518-11-04 00:02] Guard #99 begins shift
 * [1518-11-04 00:36] falls asleep
 * [1518-11-04 00:46] wakes up
 * [1518-11-05 00:03] Guard #99 begins shift
 * [1518-11-05 00:45] falls asleep
 * [1518-11-05 00:55] wakes up
 * Timestamps are written using year-month-day hour:minute format. The guard falling asleep or waking up is always
 * the one whose shift most recently started. Because all asleep/awake times are during the midnight
 * hour (00:00 - 00:59), only the minute portion (00 - 59) is relevant for those events.
 * Visually, these records show that the guards are asleep at these times:
 *
 * Date   ID   Minute
 *             000000000011111111112222222222333333333344444444445555555555
 *             012345678901234567890123456789012345678901234567890123456789
 * 11-01  #10  .....####################.....#########################.....
 * 11-02  #99  ........................................##########..........
 * 11-03  #10  ........................#####...............................
 * 11-04  #99  ....................................##########..............
 * 11-05  #99  .............................................##########.....
 *
 * The columns are Date, which shows the month-day portion of the relevant day; ID, which shows the guard
 * on duty that day; and Minute, which shows the minutes during which the guard was asleep within the midnight hour.
 * (The Minute column's header shows the minute's ten's digit in the first row and the one's digit in the second row.)
 * Awake is shown as ., and asleep is shown as #.
 * Note that guards count as asleep on the minute they fall asleep, and they count as awake on the minute they wake up.
 * For example, because Guard #10 wakes up at 00:25 on 1518-11-01, minute 25 is marked as awake.
 * If you can figure out the guard most likely to be asleep at a specific time, you might be able to trick
 * that guard into working tonight so you can have the best chance of sneaking in. You have two strategies
 * for choosing the best guard/minute combination.
 *
 * Strategy 1: Find the guard that has the most minutes asleep. What minute does that guard spend asleep the most?
 *
 * In the example above, Guard #10 spent the most minutes asleep, a total of 50 minutes (20+25+5),
 * while Guard #99 only slept for a total of 30 minutes (10+10+10). Guard #10 was asleep most during minute 24
 * (on two days, whereas any other minute the guard was asleep was only seen on one day).
 * While this example listed the entries in chronological order, your entries are in the order you found them.
 * You'll need to organize them before they can be analyzed.
 * What is the ID of the guard you chose multiplied by the minute you chose? (In the above example,
 * the answer would be 10 * 24 = 240.)
 *
 * Your puzzle answer was 106710.
 *
 * --- Part Two ---
 * Strategy 2: Of all guards, which guard is most frequently asleep on the same minute?
 *
 * In the example above, Guard #99 spent minute 45 asleep more than any other guard or minute - three times in total.
 * (In all other cases, any guard spent any minute asleep at most twice.)
 * What is the ID of the guard you chose multiplied by the minute you chose? (In the above example,
 * the answer would be 99 * 45 = 4455.)
 *
 * Your puzzle answer was 10491.
 *
 * Both parts of this puzzle are complete! They provide two gold stars: **
 */
public class Day04 {
    private Map<Integer, List<int[]>> guardSleep = new HashMap<>();

    public int solvePartA(List<String> lines) {
        createGrid(lines);

        return findAnswerA();
    }

    /*
    [1518-11-19 00:04] Guard #239 begins shift
    [1518-08-03 00:43] wakes up
    [1518-05-20 00:16] falls asleep
     */
    private void createGrid(List<String> lines) {
        int guardNo = -1,
            start = -1;
        boolean isNewGuard = true;

        for (String line : lines) {
            String[] split = line.split(" ");
            if ("Guard".equals(split[2])) {
                isNewGuard = true;
                guardNo = Integer.parseInt(split[3].substring(1));
            } else if ("falls".equals(split[2])) {
                start = Integer.parseInt(split[1].substring(3, 5));
            } else {
                addSleep(guardNo, start, Integer.parseInt(split[1].substring(3, 5)), isNewGuard);
                isNewGuard = false;
            }
        }
    }

    private int findAnswerA() {
        // find the guard with the highest number of minutes sleeping
        int maxGuardNo = 0,
            maxGuardSleepCount = 0;

        for (Integer guardNo : guardSleep.keySet()) {
            int sleepCount = 0;
            List<int[]> sleeps = guardSleep.get(guardNo);
            for (int[] sleep : sleeps) {
                sleepCount += count(sleep, 1);
            }

            if (sleepCount > maxGuardSleepCount) {
                maxGuardSleepCount = sleepCount;
                maxGuardNo = guardNo;
            }
        }

        // find the minute the guard slept the most
        int maxMinute = 0,
            maxMinuteSum = 0;
        List<int[]> sleepTables = guardSleep.get(maxGuardNo);
        int[][] grid = convert(sleepTables);
        for (int i = 0; i < 60; i++) {
            int sum = 0;
            for (int j = 0; j < grid.length; j++) {
                sum += grid[j][i];
            }
            if (sum > maxMinuteSum) {
                maxMinuteSum = sum;
                maxMinute = i;
            }
        }

        return maxGuardNo * maxMinute;
    }

    public int solvePartB(List<String> lines) {
        createGrid(lines);

        return findAnswerB();
    }

    private int findAnswerB() {
        int maxSleepGuardNo = 0,
            maxCount = -1,
            maxMinute = -1;

        for (Integer guardNo : guardSleep.keySet()) {
            // grid: [minute][sleep]
            int[][] grid = convert(guardSleep.get(guardNo));
            for (int minute = 0; minute < 60; minute++) {
                int count = 0;
                for (int day = 0; day < grid.length; day++) {
                    count += grid[day][minute];
                }

                if (count > maxCount) {
                    maxSleepGuardNo = guardNo;
                    maxCount = count;
                    maxMinute = minute;
                }
            }
        }

        return maxSleepGuardNo * maxMinute;
    }

    // grid: [minute][sleep]
    private int[][] convert(List<int[]> list) {
        int[][] table = new int[list.size()][60];
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j <= 59; j++) {
                table[i][j] = list.get(i)[j];
            }
        }
        return table;
    }

    private int count(int[] values, int find) {
        int result = 0;
        for (int value : values) {
            if (value == find) {
                ++result;
            }
        }

        return result;
    }

    private void addSleep(int guardNo, int start, int stop, boolean isNewEntry) {
        List<int[]> sleeps = guardSleep.get(guardNo);

        if (sleeps == null) {
            guardSleep.put(guardNo, new ArrayList<>());
            sleeps = guardSleep.get(guardNo);
        }

        int[] sleep;

        if (isNewEntry || sleeps.isEmpty()) {
            sleep = new int[60];
            guardSleep.get(guardNo).add(sleep);
        } else {
            sleep = sleeps.get(sleeps.size() - 1);
        }

        for (int i = start; i < stop; i++) {
            sleep[i] = 1;
        }
    }
}
