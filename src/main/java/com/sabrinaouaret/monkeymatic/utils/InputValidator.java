package com.sabrinaouaret.monkeymatic.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    public static void validate(String input) {

        List<String> lines = Arrays.stream(input.split("\\R+"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        // The input contains more or less than 2 lines of text.
        if(lines.size() != 2) {
            throw new IllegalArgumentException("You put more or less than 2 lines of text. Try again little Monkey!");
        }

        // The first part of the input is not format like (int, int, direction).
        Pattern pattern = Pattern.compile("^\\(\\d,\\s*\\d,\\s*[NSEO]\\)$");
        if (!pattern.matcher(lines.getFirst()).matches()) {
            throw new IllegalArgumentException("The initial position and direction of the tractor is not quiet right. Try again with something like this (1, 1, N).");
        }

        // The second part of the input contains more that the letters A, G, D.
        if (!lines.getLast().matches("^[GDA]+$")) {
            throw new IllegalArgumentException("Heo! The list of command must only contains letters like A, G, D. Try again little Monkey!");
        }

    }
}
