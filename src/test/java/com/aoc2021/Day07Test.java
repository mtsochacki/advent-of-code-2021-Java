package com.aoc2021;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {
    Day underTest = new Day07();

    @Test
    @DisplayName("Should return correct value for part1")
    void test1() {
        //when
        String receivedAnswer = underTest.part1("src/test/resources/day7test.txt");
        //then
        assertEquals("37", receivedAnswer);
    }

    @Test
    @DisplayName("Should return correct value for part2")
    void test2() {
        //when
        String receivedAnswer = underTest.part2("src/test/resources/day7test.txt");
        //then
        assertEquals("168", receivedAnswer);
    }

}