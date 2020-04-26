package io.methea.validator.abs;

import io.methea.domain.basebinder.abs.AbstractMetheaBinder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.inject.Inject;
import java.util.Map;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

/**
 * Author : DKSilverX
 * Date : 26/04/2020
 */
public abstract class AbstractMetheaValidator<B extends AbstractMetheaBinder<B>> implements Validator<B> {

    @Inject
    protected MessageSource messageSource;

    protected void rejectIfNotMatch(Map<String, String> errors, String code, String fieldValueA, String fieldValueB, String field, String fieldName) {
        if (StringUtils.isEmpty(fieldValueA)) {
            return;
        }
        if (StringUtils.isEmpty(fieldValueB)) {
            return;
        }
        if (!fieldValueA.equals(fieldValueB)) {
            String msg = messageSource.getMessage(code, new Object[]{},
                    LocaleContextHolder.getLocale());
            errors.put(field, msg);
        }
    }

    protected void rejectIfEmpty(Map<String, String> errors, String code, String fieldValue, String field, String fieldName) {
        if (StringUtils.isBlank(fieldValue)) {
            String msg = messageSource.getMessage(code, new Object[]{},
                    LocaleContextHolder.getLocale());
            errors.put(field, msg);
        }
    }
}
