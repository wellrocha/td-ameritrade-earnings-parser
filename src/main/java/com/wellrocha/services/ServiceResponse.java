package com.wellrocha.services;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
final public class ServiceResponse {
    private final boolean success;

    private final String error;
}