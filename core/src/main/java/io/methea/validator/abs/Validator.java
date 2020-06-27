package io.methea.validator.abs;

import io.methea.domain.common.binder.abs.AbstractMetheaBinder;

import java.util.Map;

public interface Validator<B extends AbstractMetheaBinder<B>> {
    void validate(B binder, Map<String, String> errors);
}
