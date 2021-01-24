package by.daniil.epam.project.validator;

import by.daniil.epam.project.domain.Entity;

import javax.servlet.http.HttpServletRequest;

public interface Validator<Type extends Entity>  {
    Type validate(HttpServletRequest request);
}
