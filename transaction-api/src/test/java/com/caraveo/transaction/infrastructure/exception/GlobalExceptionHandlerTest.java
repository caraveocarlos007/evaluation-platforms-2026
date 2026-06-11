package com.caraveo.transaction.infrastructure.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldReturnValidationErrors() throws Exception {

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(
                        new Object(),
                        "request");

        bindingResult.addError(
                new FieldError(
                        "request",
                        "operacion",
                        "Operacion solo permite letras"));

        bindingResult.addError(
                new FieldError(
                        "request",
                        "cliente",
                        "Cliente solo permite letras"));

        MethodParameter parameter =
                new MethodParameter(
                        DummyController.class.getMethod(
                                "dummyMethod",
                                String.class),
                        0);

        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(
                        parameter,
                        bindingResult);

        Map<String, String> errors =
                handler.handleValidation(exception);

        assertFalse(errors.isEmpty());

        assertEquals(
                "Operacion solo permite letras",
                errors.get("operacion"));

        assertEquals(
                "Cliente solo permite letras",
                errors.get("cliente"));
    }

    static class DummyController {

        public void dummyMethod(String value) {
        }
    }
}