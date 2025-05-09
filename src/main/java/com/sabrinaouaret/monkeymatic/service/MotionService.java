package com.sabrinaouaret.monkeymatic.service;

import com.sabrinaouaret.monkeymatic.dto.OutputPosition;
import com.sabrinaouaret.monkeymatic.model.Command;
import com.sabrinaouaret.monkeymatic.model.Tractor;
import com.sabrinaouaret.monkeymatic.utils.CommandMapper;
import com.sabrinaouaret.monkeymatic.utils.InputValidator;
import org.springframework.stereotype.Service;


@Service
public class MotionService {

    public static final String RETURN_REGEX = "\\R+";

    public OutputPosition calculateNewPosition(String input) {
        InputValidator.validate(input);

        String[] lines = input.split(RETURN_REGEX);
        Tractor tractor = CommandMapper.mapToTractor(lines[0]);
        Command command = CommandMapper.mapToCommand(lines[1]);

        command.getCommandList().forEach(direction -> {
            switch (direction) {
                case "D" -> tractor.turnRight();
                case "G" -> tractor.turnLeft();
                case "A" -> tractor.moveForward();
            }
        });

        return CommandMapper.maptToOutputPosition(tractor.getPosition(), tractor.getAppreciationComment());

    }

}
