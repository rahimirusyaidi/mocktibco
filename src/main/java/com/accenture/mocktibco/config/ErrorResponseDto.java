package com.accenture.mocktibco.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorResponseDto {

    private final Date dateTime;

    private final String message;
}

