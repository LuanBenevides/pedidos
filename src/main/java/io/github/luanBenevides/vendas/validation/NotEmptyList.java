package io.github.luanBenevides.vendas.validation;

import io.github.luanBenevides.vendas.validation.ConstraintValidation.NotEmptyListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
    String message() default "A lista não pode ser vazia.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
