package com.github.badpop.solution1;

import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import static com.github.badpop.RomansIntegerEquivalents.ROMAN_INTEGER_EQUIVALENTS;
import static io.vavr.API.Map;

public interface RomanToIntegerConverter1 {

    Map<String, String> ROMAN_SUBTRACTION_CHEATS = Map(
            "IV", "IIII",
            "IX", "VIIII",
            "XL", "XXXX",
            "XC", "LXXXX",
            "CD", "CCCC",
            "CM", "DCCCC");

    /**
     * <p>A simple method using a functional way to convert a roman number to its integer equivalent.
     *
     * <p>The main idea behind this approach is to remove the complexity imposed by the Roman numbers requiring subtraction
     * to obtain their equivalent in Arabic numbers.
     *
     * <p>This is done by replacing each of them with equivalents that do not require subtraction. For example: <b>IV</b> will become <b>IIII</b>.
     *
     * <p>The advantage of these choices is to have a simple and concise code.
     *
     * <p>Please note that the main concern with this way of doing things is that we end up with a longer string,
     * so we may have to execute more instructions than when calculating the subtractions. But the code is less complexe
     * than solution 2.
     *
     * @throws IllegalArgumentException if the given string is null, empty or contains a non-roman character
     */
    static int convertRomanToInteger(String roman) {
        if (roman == null || roman.isBlank()) {
            throw new IllegalArgumentException("Please provide a valid roman number");
        }

        final var romanWithoutSubtractions = replaceSubtractionsWithCheats(roman.toUpperCase());
        return List.ofAll(romanWithoutSubtractions.toCharArray())
                .map(ROMAN_INTEGER_EQUIVALENTS::get)
                .map(maybeEquivalent -> maybeEquivalent.getOrElseThrow(() ->
                        new IllegalArgumentException("The given string does not respect the roman notation")))
                .reduce(Integer::sum);
    }

    private static String replaceSubtractionsWithCheats(String roman) {
        for (Tuple2<String, String> cheat : ROMAN_SUBTRACTION_CHEATS) {
            roman = roman.replace(cheat._1, cheat._2);
        }
        return roman;
    }
}
