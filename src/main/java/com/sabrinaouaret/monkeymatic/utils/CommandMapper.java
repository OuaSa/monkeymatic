package com.sabrinaouaret.monkeymatic.utils;

import com.sabrinaouaret.monkeymatic.dto.OutputPosition;
import com.sabrinaouaret.monkeymatic.model.Command;
import com.sabrinaouaret.monkeymatic.model.Position;
import com.sabrinaouaret.monkeymatic.model.Tractor;

import java.util.Arrays;
import java.util.List;

public class CommandMapper {

    public static final String PARENTHESIS_REGEX = "[()]";
    public static final String COMMA_REGEX = ",";
    public static final String WHITE_SPACES_REGEX = "\\s+";

    public static Tractor mapToTractor(String input) {
        String[] initialPosition = Arrays.stream(input.replaceAll(PARENTHESIS_REGEX, "")
                        .split(COMMA_REGEX))
                .map(String::trim)
                .toArray(String[]::new);

        Position position = mapToPosition(initialPosition);
        return new Tractor(position);
    }

    public static Position mapToPosition(String[] input) {
        int x = Integer.parseInt(input[0]);
        int y = Integer.parseInt(input[1]);
        Direction direction = Direction.valueOf(input[2]);
        return new Position(x, y, direction);
    }

    public static Command mapToCommand(String input) {
        List<String> commandList = Arrays.stream(input.trim()
                .replaceAll(WHITE_SPACES_REGEX, "")
                .toUpperCase()
                .split("")).toList();

        return new Command(commandList);
    }

    public static OutputPosition maptToOutputPosition(Position position, String comment) {
        return new OutputPosition(position.toString(), comment);
    }
}
