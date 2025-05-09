package com.sabrinaouaret.monkeymatic.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {

    public static final String RETURN_REGEX = "\\R+";
    public static final String INITIAL_POSITION_REGEX = "^\\(\\d,\\s*\\d,\\s*[NSEO]\\)$";
    public static final String COMMAND_LETTERS_REGEX = "(?i)^[gda]+$";
    public static final String INPUT_NUMBER_LINES_ERROR_MESSAGE = "You put more or less than 2 lines of text. Try again little Monkey!";
    public static final String INPUT_FORMAT_ERROR_MESSAGE = "The initial position and direction of the tractor is not quiet right. Try again with something like this (1, 1, N).";
    public static final String INPUT_COMMAND_LIST_ERROR_MESSAGE = "Heo! The list of command must only contains letters like A, G, D. Try again little Monkey!";

    public static void validate(String input) {

        List<String> lines = Arrays.stream(input.split(RETURN_REGEX))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        // The input contains more or less than 2 lines of text.
        if(lines.size() != 2) {
            throw new IllegalArgumentException(INPUT_NUMBER_LINES_ERROR_MESSAGE);
        }

        // The first part of the input is not format like (int, int, direction).
        Pattern pattern = Pattern.compile(INITIAL_POSITION_REGEX);
        if (!pattern.matcher(lines.getFirst()).matches()) {
            throw new IllegalArgumentException(INPUT_FORMAT_ERROR_MESSAGE);
        }

        // The second part of the input contains more that the letters A, G, D.
        if (!lines.getLast().matches(COMMAND_LETTERS_REGEX)) {
            throw new IllegalArgumentException(INPUT_COMMAND_LIST_ERROR_MESSAGE);
        }

    }
}
