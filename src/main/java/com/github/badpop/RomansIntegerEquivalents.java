package com.github.badpop;

import io.vavr.API;
import io.vavr.collection.Map;

public interface RomansIntegerEquivalents {
    Map<Character, Integer> ROMAN_INTEGER_EQUIVALENTS = API.Map(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);
}
