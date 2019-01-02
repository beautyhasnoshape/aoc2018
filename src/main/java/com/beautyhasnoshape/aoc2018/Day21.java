package com.beautyhasnoshape.aoc2018;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 --- Day 21: Chronal Conversion ---
 You should have been watching where you were going, because as you wander the new North Pole base, you trip
 and fall into a very deep hole!

 Just kidding. You're falling through time again.

 If you keep up your current pace, you should have resolved all of the temporal anomalies by the next time
 the device activates. Since you have very little interest in browsing history in 500-year increments
 for the rest of your life, you need to find a way to get back to your present time.

 After a little research, you discover two important facts about the behavior of the device:

 First, you discover that the device is hard-wired to always send you back in time in 500-year increments.
 Changing this is probably not feasible.

 Second, you discover the activation system (your puzzle input) for the time travel module.
 Currently, it appears to run forever without halting.

 If you can cause the activation system to halt at a specific moment, maybe you can make the device send you
 so far back in time that you cause an integer underflow in time itself and wrap around back to your current time!

 The device executes the program as specified in manual section one and manual section two.

 Your goal is to figure out how the program works and cause it to halt. You can only control register 0;
 every other register begins at 0 as usual.

 Because time travel is a dangerous activity, the activation system begins with a few instructions which verify
 that bitwise AND (via bani) does a numeric operation and not an operation as if the inputs were interpreted as strings.
 If the test fails, it enters an infinite loop re-running the test instead of allowing the program to execute normally.
 If the test passes, the program continues, and assumes that all other bitwise operations (banr, bori, and borr)
 also interpret their inputs as numbers. (Clearly, the Elves who wrote this system were worried that someone might
 introduce a bug while trying to emulate this system with a scripting language.)

 What is the lowest non-negative integer value for register 0 that causes the program to halt after executing
 the fewest instructions? (Executing the same instruction multiple times counts as multiple instructions executed.)

 Your puzzle answer was 212115.

 --- Part Two ---
 In order to determine the timing window for your underflow exploit, you also need an upper bound:

 What is the lowest non-negative integer value for register 0 that causes the program to halt after executing
 the most instructions? (The program must actually halt; running forever does not count as halting.)

 Your puzzle answer was 9258470.
 */
public class Day21 {
    public Pair<Integer, Integer> solve() {
        final int pointerId = 5; // #ip 5
        int[] r = new int[] { 0, 0, 0, 0, 0, 0 };

        int solutionA = 0, solutionB = 0, lastSeen = 0;
        Set<Integer> seen = new HashSet<>();

        for(; solutionA == 0 || solutionB == 0;) {
            switch (r[pointerId]) {
                case 0: // seti 123 0 3
                    r[3] = 123;
                    break;
                case 1: // bani 3 456 3
                    r[3] &= 456;
                    break;
                case 2: // eqri 3 72 3
                    r[3] = (r[3] == 72 ? 1 : 0);
                    break;
                case 3: // addr 3 5 5
                    r[5] += r[3];
                    break;
                case 4: // seti 0 0 5
                    r[5] = 0;
                    break;
                case 5: // seti 0 5 3
                    r[3] = 0;
                    break;
                case 6: // bori 3 65536 2
                    r[2] = r[3] | 65536;
                    break;
                case 7: // seti 832312 1 3
                    r[3] = 832312;
                    break;
                case 8: // bani 2 255 1
                    r[1] = r[2] & 255;
                    break;
                case 9: // addr 3 1 3
                    r[3] += r[1];
                    break;
                case 10: // bani 3 16777215 3
                    r[3] &= 16777215;
                    break;
                case 11: // muli 3 65899 3
                    r[3] *= 65899;
                    break;
                case 12: // bani 3 16777215 3
                    r[3] &= 16777215;
                    break;
                case 13: // gtir 256 2 1
                    r[1] = (256 > r[2] ? 1 : 0);
                    break;
                case 14: // addr 1 5 5
                    r[5] += r[1];
                    break;
                case 15: // addi 5 1 5
                    r[5]++;
                    break;
                case 16: // seti 27 7 5
                    r[5] = 27;
                    break;
                case 17: // seti 0 2 1
                    r[1] = 0;
                    break;
                case 18: // addi 1 1 4
                    r[4] = r[1] + 1;
                    break;
                case 19: // muli 4 256 4
                    r[4] *= 256;
                    break;
                case 20: // gtrr 4 2 4
                    r[4] = (r[4] > r[2] ? 1 : 0);
                    break;
                case 21: // addr 4 5 5
                    r[5] += r[4];
                    break;
                case 22: // addi 5 1 5
                    r[5]++;
                    break;
                case 23: // seti 25 1 5
                    r[5] = 25;
                    break;
                case 24: // addi 1 1 1
                    r[1]++;
                    break;
                case 25: // seti 17 0 5
                    r[5] = 17;
                    break;
                case 26: // setr 1 7 2
                    r[2] = r[1];
                    break;
                case 27: // seti 7 2 5
                    r[5] = 7;
                    break;
                case 28: // eqrr 3 0 1
                    r[1] = (r[3] == r[0] ? 1 : 0);

                    if (seen.isEmpty()) {
                        solutionA = r[3];
                    }

                    if (!seen.contains(r[3])) {
                        seen.add(r[3]);
                        lastSeen = r[3];
                    } else {
                        solutionB = lastSeen;
                    }

                    break;
                case 29: // addr 1 5 5
                    r[5] += r[1];
                    break;
                case 30: // seti 5 5 5
                    r[5] = 5;
                    break;
            }
            ++r[5];
        }

        return Pair.of(solutionA, solutionB);
    }

    private void print(int... values) {
        System.out.println(Arrays.toString(values));
    }
}