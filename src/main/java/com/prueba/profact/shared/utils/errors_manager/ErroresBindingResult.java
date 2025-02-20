package com.prueba.profact.shared.utils.errors_manager;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;

public class ErroresBindingResult {

    public static List<String> recuperarErrores(final BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(
                        error -> error.getField().concat(" ").concat(error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

}
