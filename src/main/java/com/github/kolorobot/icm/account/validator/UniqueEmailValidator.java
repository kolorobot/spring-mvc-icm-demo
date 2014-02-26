package com.github.kolorobot.icm.account.validator;

import com.github.kolorobot.icm.account.AccountForm;
import com.github.kolorobot.icm.account.AccountRepository;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, AccountForm> {

    @Inject
    private AccountRepository accountRepository;
    private UniqueEmail constraintAnnotation;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(AccountForm value, ConstraintValidatorContext context) {
        if (value == null) {
            throw new IllegalArgumentException("value must not be null");
        }

        if (value.getEmail() == null) {
            return true;
        }

        if (accountRepository.hasEmail(value.getEmail())) {
            context.buildConstraintViolationWithTemplate(constraintAnnotation.message())
                    .addNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        return true;
    }
}