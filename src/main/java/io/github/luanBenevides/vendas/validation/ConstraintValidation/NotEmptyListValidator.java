package io.github.luanBenevides.vendas.validation.ConstraintValidation;

import io.github.luanBenevides.vendas.validation.NotEmptyList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyListValidator
        implements ConstraintValidator<NotEmptyList, List> {
    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }
}
