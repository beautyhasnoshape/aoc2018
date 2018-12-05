package com.beautyhasnoshape.aoc2018;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

/**
 * --- Day 5: Alchemical Reduction ---
 * You've managed to sneak in to the prototype suit manufacturing lab. The Elves are making decent progress,
 * but are still struggling with the suit's size reduction capabilities.
 *
 * While the very latest in 1518 alchemical technology might have solved their problem eventually, you can do better.
 * You scan the chemical composition of the suit's material and discover that it is formed by extremely long polymers
 * (one of which is available as your puzzle input).
 *
 * The polymer is formed by smaller units which, when triggered, react with each other such that two adjacent units
 * of the same type and opposite polarity are destroyed. Units' types are represented by letters; units' polarity
 * is represented by capitalization. For instance, r and R are units with the same type but opposite polarity,
 * whereas r and s are entirely different types and do not react.
 *
 * For example:
 *
 * In aA, a and A react, leaving nothing behind.
 * In abBA, bB destroys itself, leaving aA. As above, this then destroys itself, leaving nothing.
 * In abAB, no two adjacent units are of the same type, and so nothing happens.
 * In aabAAB, even though aa and AA are of the same type, their polarities match, and so nothing happens.
 * Now, consider a larger example, dabAcCaCBAcCcaDA:
 *
 * dabAcCaCBAcCcaDA  The first 'cC' is removed.
 * dabAaCBAcCcaDA    This creates 'Aa', which is removed.
 * dabCBAcCcaDA      Either 'cC' or 'Cc' are removed (the result is the same).
 * dabCBAcaDA        No further actions can be taken.
 * After all possible reactions, the resulting polymer contains 10 units.
 *
 * How many units remain after fully reacting the polymer you scanned? (Note: in this puzzle and others,
 * the input is large; if you copy/paste your input, make sure you get the whole thing.)
 *
 * Your puzzle answer was 10180.
 *
 * --- Part Two ---
 * Time to improve the polymer.
 *
 * One of the unit types is causing problems; it's preventing the polymer from collapsing as much as it should.
 * Your goal is to figure out which unit type is causing the most problems, remove all instances of it
 * (regardless of polarity), fully react the remaining polymer, and measure its length.
 *
 * For example, again using the polymer dabAcCaCBAcCcaDA from above:
 *
 * Removing all A/a units produces dbcCCBcCcD. Fully reacting this polymer produces dbCBcD, which has length 6.
 * Removing all B/b units produces daAcCaCAcCcaDA. Fully reacting this polymer produces daCAcaDA, which has length 8.
 * Removing all C/c units produces dabAaBAaDA. Fully reacting this polymer produces daDA, which has length 4.
 * Removing all D/d units produces abAcCaCBAcCcaA. Fully reacting this polymer produces abCBAc, which has length 6.
 * In this example, removing all C/c units was best, producing the answer 4.
 *
 * What is the length of the shortest polymer you can produce by removing all units of exactly one type
 * and fully reacting the result?
 *
 * Your puzzle answer was 5668.
 */
public class Day05 {

    public int solvePartA(String input) {
        byte[] bytes = input.getBytes();
        boolean hasChanged;

        do {
            hasChanged = false;
            int prevPos = -1;
            for (int i = 0; i < bytes.length - 1; i++) {
                if (bytes[i] == 0) {
                    continue;
                } else if (prevPos == -1) {
                    prevPos = i;
                } else if (Math.abs(bytes[prevPos] - bytes[i]) == 32) {
                    bytes[prevPos] = 0;
                    bytes[i] = 0;

                    prevPos = -1;
                    hasChanged = true;
                } else {
                    prevPos = i;
                }
            }
        } while (hasChanged);

        return countNonZero(bytes);
    }

    private int countNonZero(byte[] input) {
        int count = 0;
        for (byte b : input) {
            if (b != 0) {
                ++count;
            }
        }

        return count;
    }

    public String solvePartA1(String input) {
        byte[] bytes = input.getBytes();
        boolean hasChanged;

        do {
            hasChanged = false;
            for (int i = 0; i < bytes.length - 1; i++) {
                if (Math.abs(bytes[i] - bytes[i + 1]) == 32) {
                    byte[] tmp = new byte[bytes.length - 2];
                    System.arraycopy(bytes, 0, tmp, 0, i);
                    System.arraycopy(bytes, i+2, tmp, i, bytes.length-i-2);

                    bytes = tmp;
                    hasChanged = true;

                    break;
                }
            }
        } while (hasChanged);

        return new String(bytes);
    }

    private Set<Byte> getUpperChars(byte[] input) {
        Set<Byte> chars = new HashSet<>(26);
        for (byte aByte : input) {
            if (aByte >= 'A' && aByte <= 'Z') {
                chars.add(aByte);
            }
        }

        return chars;
    }

    public int solvePartB(String input) {
        int bestCount = Integer.MAX_VALUE;
        Set<Byte> chars = getUpperChars(input.getBytes());

        Iterator<Byte> charIterator = chars.iterator();
        while (charIterator.hasNext()) {
            byte b = charIterator.next();
            byte[] bytes = replace(input.getBytes(), b, (byte) 0);
            int length = solvePartA(new String(bytes));
            if (length < bestCount) {
                bestCount = length;
            }
        }

        return bestCount;
    }

    public int solvePartB1(String input) {
        int bestCount = Integer.MAX_VALUE;
        for (byte b = 'A'; b <= 'Z'; b++) {
            byte[] bytes = replace(input.getBytes(), b, (byte) 0);
            int length = solvePartA(new String(bytes));
            if (length < bestCount) {
                bestCount = length;
            }
        }

        return bestCount;
    }

    public int solvePartB2(String input) {
        byte[] bytes = input.getBytes();

        int bestCount = Integer.MAX_VALUE;
            for (byte b = 'A'; b <= 'Z'; b++) {
                byte[] output = ArrayUtils.removeAllOccurences(bytes,  b);
                output = ArrayUtils.removeAllOccurences(output,  (byte) (b + 32));
                int length = solvePartA(new String(output));
                if (length < bestCount) {
                    bestCount = length;
                }
            }

        return bestCount;
    }

    private byte[] replace(byte[] input, byte oldValue, byte newValue) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == oldValue || input[i] == (oldValue + 32)) {
                input[i] = newValue;
            }
        }

        return input;
    }
}
