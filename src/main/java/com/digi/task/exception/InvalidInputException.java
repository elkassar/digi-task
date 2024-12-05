package com.digi.task.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class InvalidInputException extends RuntimeException {

    private final List<String> invalidInputProperties;
}
