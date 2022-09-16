package com.github.badpop.solution1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class RomanToIntegerConverter1Test {

    @ParameterizedTest
    @MethodSource("provideValidRomansAndCorrespondingExpectations")
    void should_convert_roman_to_integer(String roman, int expectedInteger) {
        final var actual = RomanToIntegerConverter1.convertRomanToInteger(roman);
        assertThat(actual).isEqualTo(expectedInteger);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidRomansAndCorrespondingMessageExpectations")
    void should_fail_to_convert_roman_to_integer(String roman, String expectedMessage) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> RomanToIntegerConverter1.convertRomanToInteger(roman))
                .withMessage(expectedMessage);
    }

    private static Stream<Arguments> provideValidRomansAndCorrespondingExpectations() {
        return Stream.of(
                // NOMINAL ROMAN NUMBERS CASES
                Arguments.of("M", 1000),
                Arguments.of("D", 500),
                Arguments.of("C", 100),
                Arguments.of("L", 50),
                Arguments.of("X", 10),
                Arguments.of("V", 5),
                Arguments.of("I", 1),

                // ROMAN SUBTRACTIONS CASES
                Arguments.of("CM", 900),
                Arguments.of("CD", 400),
                Arguments.of("XC", 90),
                Arguments.of("XL", 40),
                Arguments.of("IX", 9),
                Arguments.of("IV", 4),

                // MORE COMPLEXE CASES
                Arguments.of("MDCLXVI", 1666),
                Arguments.of("MMCM", 2900),
                Arguments.of("XLII", 42),
                Arguments.of("MMMMMMMMDCCLIV", 8754),
                Arguments.of("MMMMDLXXVIII", 4578),
                Arguments.of(
                        "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMCMXCIX",
                        99999));
    }

    private static Stream<Arguments> provideInvalidRomansAndCorrespondingMessageExpectations() {
        return Stream.of(
                Arguments.of(null, "Please provide a valid roman number"),
                Arguments.of("", "Please provide a valid roman number"),
                Arguments.of("       ", "Please provide a valid roman number"),
                Arguments.of("A", "The given string does not respect the roman notation"),
                Arguments.of("AABBCDDIVSFD", "The given string does not respect the roman notation"));
    }
}