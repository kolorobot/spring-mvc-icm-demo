package com.github.kolorobot.icm.account.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniqueEmailValidator.class})
@Target({ TYPE})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface UniqueEmail {

    String message() default "{com.github.kolorobot.icm.UniqueEmail}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
