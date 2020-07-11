package io.methea.validator.configuration.menu;

import io.methea.domain.configuration.menu.dto.MenuBinder;
import io.methea.repository.configuration.menu.MenuRepository;
import io.methea.validator.abs.AbstractMetheaValidator;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author : DKSilverX
 * Date : 11/07/2020
 */
@Component
public class MenuValidator extends AbstractMetheaValidator<MenuBinder> {
    public MenuValidator(MenuRepository repository){
        super.repository = repository;
    }

    @Override
    public void validate(MenuBinder binder, Map<String, String> errors) {

    }
}
