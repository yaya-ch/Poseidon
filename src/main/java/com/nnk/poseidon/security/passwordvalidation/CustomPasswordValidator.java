package com.nnk.poseidon.security.passwordvalidation;

import com.nnk.poseidon.constants.ConstantNumbers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.passay.MessageResolver;
import org.passay.PropertiesMessageResolver;
import org.passay.PasswordValidator;
import org.passay.LengthRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.WhitespaceRule;
import org.passay.RuleResult;
import org.passay.PasswordData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * This class defines the different password validation rules.
 *
 * @author Yahia CHERIFI
 */

public class CustomPasswordValidator
        implements ConstraintValidator<ValidPassword, String> {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CustomPasswordValidator.class);

    /**
     * This method initializes the CustomPasswordValidator in preparation.
     * for the isValid calls
     * @param validPassword annotation constraint for password validation
     */
    @Override
    public void initialize(final ValidPassword validPassword) {
        //Initializes the CustomPasswordValidator in preparation
        // for isValid(String password, ConstraintValidatorContext) calls
    }

    /**
     * This method defines the password validation rules(logic).
     * @param password the password that will be validated
     * @param context provides customized data messages for password validation
     *                context in which the constraint is evaluated
     * @return {@code true} if the password matches the validation rules
     * or {@code false} if it does not matches the validation rules
     */
    @Override
    public boolean isValid(final String password,
                           final ConstraintValidatorContext context) {
        LOGGER.debug("Validating a new user password by the"
                + " CustomPasswordValidator");
        //Loading the customized messages Passay properties file
        URL fileUrl = this.getClass()
                .getClassLoader().getResource("passay.properties");
        Properties properties = new Properties();
        if (fileUrl != null) {
            try (InputStream inputStream = new FileInputStream(
                    fileUrl.getPath());
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream, StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader)) {
                properties.load(bufferedReader);
                LOGGER.info("Passay customized messages loaded successfully");
            } catch (IOException e) {
                LOGGER.error("Failed to load customized Passay messages");
            }
        }

        //Defining messages for the password validation failures
        MessageResolver resolver = new PropertiesMessageResolver(properties);
        //Defining the password validation rules
        PasswordValidator validator =
                new PasswordValidator(resolver, Arrays.asList(
                        //Define the min and max password length
                        new LengthRule(ConstantNumbers.EIGHT,
                                ConstantNumbers.ONE_HUNDRED_AND_TWENTY_FIVE),
                        //define the min number of upper case characters
                        new CharacterRule(EnglishCharacterData.UpperCase, 1),
                        //define the min number of lower case characters
                        new CharacterRule(EnglishCharacterData.LowerCase, 1),
                        //define the min number of digit characters
                        new CharacterRule(EnglishCharacterData.Digit, 1),
                        //define the min number of special characters
                        new CharacterRule(EnglishCharacterData.Special, 1),
                        //No white space is allowed
                        new WhitespaceRule()
        ));
        //Compare the provided password to the difined rules.
        RuleResult result = validator.validate(new PasswordData(password));
        //Validate the password when all rules are respected
        if (result.isValid()) {
            LOGGER.info("The user's password validated successfully");
            return true;
        }
        //
        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(" ", messages);
        //Display customized messages
        // when errors happen during the validation process
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        LOGGER.error("Failed to validate the user's password");
        return false;
    }
}
