package com.nnk.poseidon.security.passwordvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * ValidPassword annotation indicates that the annotated field must respect.
 * different validation rules.
 *
 * @author Yahia CHERIFI
 */

@Documented
@Constraint(validatedBy = CustomPasswordValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    /**
     * A message that indicates what caused the password validation.
     *
     * @return customized messages
     */
    String message() default "Invalid Password";

    /**
     * Defines constraint password validation rules.
     *
     * @return the different constraints
     */
    Class<?>[] groups() default {
    };

    /**
     * Specifies the payload with which the constraint declaration is associated.
     * @return an object that extends Payload
     */
    Class<? extends Payload>[] payload() default {
    };
}
