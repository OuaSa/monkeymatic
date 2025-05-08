package com.sabrinaouaret.monkeymatic.controller;

import com.sabrinaouaret.monkeymatic.dto.OutputPosition;
import com.sabrinaouaret.monkeymatic.service.MotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tractors")
@AllArgsConstructor
public class TractorController {

    private final MotionService motionService;

    @PostMapping("/calculate-position")
    public ResponseEntity calculatePosition(@RequestBody @Valid String input) {
        OutputPosition output = motionService.calculateNewPosition(input);
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }

}
