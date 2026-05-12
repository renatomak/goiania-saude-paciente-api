package br.gov.goiania.saude.api.domain.exception;

import java.time.Instant;

public record ApiError(
        String message,
        Instant timestamp,
        String path
) {
}


