package by.daniil.epam.project.validator;

/**
 * class <code>ComplexValidator</code> is used to check some
 * specific places for validating data
 * @author Daniil
 */
public class ComplexValidator {

    public boolean isValidLoginOrName(String name) {
        return !name.matches("[<>]");
    }
 }
