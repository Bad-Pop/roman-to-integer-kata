package com.github.badpop.solution2;

import static com.github.badpop.RomansIntegerEquivalents.ROMAN_INTEGER_EQUIVALENTS;

public interface RomanToIntegerConverter2 {

    /**
     * <p>A simple method using a more classical java programming style to convert a roman number to its integer equivalent
     *
     * <p>The main idea behind this approach is not to cheat with the subtractions involved in converting
     * from Roman to Arabic, unlike solution 1.
     *
     * <p>This solution has the advantage of preserving the size of the input string, and therefore potentially
     * executing fewer instructions.
     *
     * <p>But the main concern, however, is that this solution is less readable than the first one and a little more complexe.
     * It is also more difficult to implement in a functional way and need more code.
     *
     * @throws IllegalArgumentException if the given string is null, empty or contains a non-roman character
     */
    static int convertRomanToInteger(String roman) {
        if (roman == null || roman.isBlank()) {
            throw new IllegalArgumentException("Please provide a valid roman number");
        }

        var romanEquivalentTotal = 0;
        roman = roman.toUpperCase();

        for (int i = 0; i < roman.length(); i++) {
            final var currentIntEquivalent = getIntEquivalentFromRomanChar(roman.charAt(i));

            if (hasNextCharFromIndex(i, roman)) {
                final var nextIntEquivalent = getIntEquivalentFromRomanChar(roman.charAt(i + 1));
                romanEquivalentTotal = calculateTotalByMakingCareOfPotentialRomanSubtraction(
                        currentIntEquivalent, nextIntEquivalent, romanEquivalentTotal);
            } else {
                romanEquivalentTotal += currentIntEquivalent;
            }
        }

        return romanEquivalentTotal;
    }

    private static Integer getIntEquivalentFromRomanChar(char romanChar) {
        return ROMAN_INTEGER_EQUIVALENTS.get(romanChar)
                .getOrElseThrow(() -> new IllegalArgumentException("The given string does not respect the roman notation"));
    }

    private static boolean hasNextCharFromIndex(int index, String s) {
        return index + 1 < s.length();
    }

    private static int calculateTotalByMakingCareOfPotentialRomanSubtraction(int currentIntEquivalent, int nextIntEquivalent, int currentTotal) {
        return currentIntEquivalent >= nextIntEquivalent
                ? currentTotal + currentIntEquivalent
                : currentTotal - currentIntEquivalent;
    }
}
