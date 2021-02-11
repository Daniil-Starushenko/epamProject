package by.daniil.epam.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class <code>ComplexValidator</code> is used to check some
 * specific places for validating data
 * @author Daniil
 */
public class ComplexValidator {
    private static final String SYMBOLS_TO_CHECK = "\\W";

    public boolean isValidLoginOrName(String parameter) {
        Pattern pattern = Pattern.compile(SYMBOLS_TO_CHECK);
        Matcher matcher = pattern.matcher(parameter);
        return !matcher.find();
    }
 }
