package com.beautyhasnoshape.aoc2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * --- Day 16: Chronal Classification ---
 As you see the Elves defend their hot chocolate successfully, you go back to falling through time.
 This is going to become a problem.

 If you're ever going to return to your own time, you need to understand how this device on your wrist works.
 You have a little while before you reach your next destination, and with a bit of trial and error,
 you manage to pull up a programming manual on the device's tiny screen.

 According to the manual, the device has four registers (numbered 0 through 3) that can be manipulated
 by instructions containing one of 16 opcodes. The registers start with the value 0.

 Every instruction consists of four values: an opcode, two inputs (named A and B), and an output (named C),
 in that order. The opcode specifies the behavior of the instruction and how the inputs are interpreted.
 The output, C, is always treated as a register.

 In the opcode descriptions below, if something says "value A", it means to take the number given as A literally.
 (This is also called an "immediate" value.) If something says "register A", it means to use the number
 given as A to read from (or write to) the register with that number. So, if the opcode addi adds register A
 and value B, storing the result in register C, and the instruction addi 0 7 3 is encountered,
 it would add 7 to the value contained by register 0 and store the sum in register 3,
 never modifying registers 0, 1, or 2 in the process.

 Many opcodes are similar except for how they interpret their arguments.
 The opcodes fall into seven general categories:

 Addition:
 - addr (add register) stores into register C the result of adding register A and register B.
 - addi (add immediate) stores into register C the result of adding register A and value B.

 Multiplication:
 - mulr (multiply register) stores into register C the result of multiplying register A and register B.
 - muli (multiply immediate) stores into register C the result of multiplying register A and value B.

 Bitwise AND:
 - banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
 - bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.

 Bitwise OR:
 - borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
 - bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.

 Assignment:
 - setr (set register) copies the contents of register A into register C. (Input B is ignored.)
 - seti (set immediate) stores value A into register C. (Input B is ignored.)

 Greater-than testing:
 - gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B.
   Otherwise, register C is set to 0.
 - gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B.
   Otherwise, register C is set to 0.
 - gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B.
   Otherwise, register C is set to 0.

 Equality testing:
 - eqir (equal immediate/register) sets register C to 1 if value A is equal to register B.
   Otherwise, register C is set to 0.
 - eqri (equal register/immediate) sets register C to 1 if register A is equal to value B.
   Otherwise, register C is set to 0.
 - eqrr (equal register/register) sets register C to 1 if register A is equal to register B.
   Otherwise, register C is set to 0.

 Unfortunately, while the manual gives the name of each opcode, it doesn't seem to indicate the number.
 However, you can monitor the CPU to see the contents of the registers before and after instructions
 are executed to try to work them out. Each opcode has a number from 0 through 15, but the manual doesn't say
 which is which. For example, suppose you capture the following sample:

 Before: [3, 2, 1, 1]
 9 2 1 2
 After:  [3, 2, 2, 1]

 This sample shows the effect of the instruction 9 2 1 2 on the registers. Before the instruction is executed,
 register 0 has value 3, register 1 has value 2, and registers 2 and 3 have value 1.
 After the instruction is executed, register 2's value becomes 2.

 The instruction itself, 9 2 1 2, means that opcode 9 was executed with A=2, B=1, and C=2.

 Opcode 9 could be any of the 16 opcodes listed above, but only three of them behave in a way
 that would cause the result shown in the sample:
 - Opcode 9 could be mulr: register 2 (which has a value of 1) times register 1 (which has a value of 2) produces 2,
   which matches the value stored in the output register, register 2.
 - Opcode 9 could be addi: register 2 (which has a value of 1) plus value 1 produces 2,
   which matches the value stored in the output register, register 2.
 - Opcode 9 could be seti: value 2 matches the value stored in the output register, register 2;
   the number given for B is irrelevant.

 None of the other opcodes produce the result captured in the sample.
 Because of this, the sample above behaves like three opcodes.

 You collect many of these samples (the first section of your puzzle input).
 The manual also includes a small test program (the second section of your puzzle input) - you can ignore it for now.

 Ignoring the opcode numbers, how many samples in your puzzle input behave like three or more opcodes?

 Your puzzle answer was 642.

 --- Part Two ---
 Using the samples you collected, work out the number of each opcode and execute the test program
 (the second section of your puzzle input).

 What value is contained in register 0 after executing the test program?

 Your puzzle answer was 481.
 */
public class Day16 {

    public interface Instruction {
        void perform(int[] registers, int a, int b, int c);
    }

    private Map<String, Instruction> setupInstuctions() {
        Map<String, Instruction> map = new HashMap<>(16);
        map.put("addr", (regs, a, b, c) -> regs[c] = regs[a] + regs[b]);
        map.put("addi", (regs, a, b, c) -> regs[c] = regs[a] + b);

        map.put("mulr", (regs, a, b, c) -> regs[c] = regs[a] * regs[b]);
        map.put("muli", (regs, a, b, c) -> regs[c] = regs[a] * b);

        map.put("banr", (regs, a, b, c) -> regs[c] = regs[a] & regs[b]);
        map.put("bani", (regs, a, b, c) -> regs[c] = regs[a] & b);

        map.put("borr", (regs, a, b, c) -> regs[c] = regs[a] | regs[b]);
        map.put("bori", (regs, a, b, c) -> regs[c] = regs[a] | b);

        map.put("setr", (regs, a, b, c) -> regs[c] = regs[a]);
        map.put("seti", (regs, a, b, c) -> regs[c] = a);

        map.put("gtir", (regs, a, b, c) -> regs[c] = a > regs[b] ? 1 : 0);
        map.put("gtri", (regs, a, b, c) -> regs[c] = regs[a] > b ? 1 : 0);
        map.put("gtrr", (regs, a, b, c) -> regs[c] = regs[a] > regs[b] ? 1 : 0);

        map.put("eqir", (regs, a, b, c) -> regs[c] = a == regs[b] ? 1 : 0);
        map.put("eqri", (regs, a, b, c) -> regs[c] = regs[a] == b ? 1 : 0);
        map.put("eqrr", (regs, a, b, c) -> regs[c] = regs[a] == regs[b] ? 1 : 0);

        return map;
    }

    public String solvePartA(List<int[]> inputs) {
        Map<String, Instruction> instrs = setupInstuctions();
        Map<Integer, List<String>> map = new HashMap<>();

        int result = 0;
        for (int[] input : inputs) {
            int opcodeId = input[4], a = input[5], b = input[6], c = input[7];
            int[] reference = Arrays.copyOfRange(input, 8, 12);

            int opsCount = 0;
            for (String opcode : instrs.keySet()) {
                int[] registers = Arrays.copyOfRange(input, 0, 4);
                instrs.get(opcode).perform(registers, a, b, c);
                if (Arrays.equals(reference, registers)) {
                    ++opsCount;
                    if (!map.containsKey(opcodeId)) {
                        map.put(opcodeId, new ArrayList<>());
                    }
                    map.get(opcodeId).add(opcode);
                }
            }
            if (opsCount >= 3) {
                ++result;
            }
        }

        return "" + result;
    }


    public String solvePartB(List<int[]> inputs, List<int[]> data) {
        Map<String, Instruction> instrs = setupInstuctions();
        Map<Integer, Set<String>> map = new HashMap<>();

        for (int[] input : inputs) {
            int opcodeId = input[4], a = input[5], b = input[6], c = input[7];
            int[] reference = Arrays.copyOfRange(input, 8, 12);

            for (String opcode : instrs.keySet()) {
                int[] registers = Arrays.copyOfRange(input, 0, 4);
                instrs.get(opcode).perform(registers, a, b, c);
                if (Arrays.equals(reference, registers)) {
                    if (!map.containsKey(opcodeId)) {
                        map.put(opcodeId, new HashSet<>());
                    }
                    map.get(opcodeId).add(opcode);
                }
            }
        }

        Map<Integer, String> ops = resolveOps(map);

        int[] reg = new int[] { 0, 0, 0, 0 };
        for (int[] d : data) {
            int opcodeId = d[0], a = d[1], b = d[2], c = d[3];
            instrs.get(ops.get(opcodeId)).perform(reg, a, b, c);
        }

        return "" + reg[0];
    }

    private Map<Integer, String> resolveOps(Map<Integer, Set<String>> map) {
        Map<Integer, String> result = new HashMap<>();
        do {
            int shortestEntry = findShortestEntry(map);
            Set<String> ops = map.get(shortestEntry);
            if (ops.size() > 1) {
                throw new IllegalStateException("Found more ops for " + shortestEntry);
            }

            result.put(shortestEntry, ops.iterator().next());
            processOps(map, shortestEntry, ops.iterator().next());
        } while (result.size() < 16);

        return result;
    }

    private void processOps(Map<Integer, Set<String>> map, int opcodeId, String opcode) {
        map.remove(opcodeId);
        for (Set<String> opcodes : map.values()) {
            if (opcodes.contains(opcode)) {
                opcodes.remove(opcode);
            }
        }
    }

    private int findShortestEntry(Map<Integer, Set<String>> map) {
        int minSize = Integer.MAX_VALUE, result = -1;
        for (Integer key : map.keySet()) {
            if (map.get(key).size() < minSize) {
                minSize = map.get(key).size();
                result = key;
            }
        }
        return result;
    }
}
