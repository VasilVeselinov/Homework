package javaproject.testfinalproject.data_validators;

import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Component
public class UserValidator {


    private final int MIN_NUMBER_OF_SYMBOLS = 8;
    private final String SPECIAL_CHARACTER = "!#$%*@";

    public boolean usernameLengthCheck(String userName) {
        if (userName.length() < MIN_NUMBER_OF_SYMBOLS) {
            return false;
        }
        return true;
    }

    public boolean emailValidation(String userEmail) {
//        // alternative
//        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
//        userEmail.matches(regex);
        try {
            InternetAddress emailAddr = new InternetAddress(userEmail);
            emailAddr.validate();
        } catch (AddressException e) {
            return false;
        }
        return true;
    }

    public boolean passwordValidation(String userPassword) {
        if (userPassword.length() < MIN_NUMBER_OF_SYMBOLS) {
            return false;
        }

        int upperCaseCounter = 0;
        int lowerCaseCounter = 0;
        int digitCount = 0;
        int specialCharacterCounter = 0;
        for (int i = 0; i < userPassword.length(); i++) {

            char ch = userPassword.charAt(i);

            if (Character.isUpperCase(ch)){
                upperCaseCounter++;
            }
            if (Character.isLowerCase(ch)){
                lowerCaseCounter++;
            }
            if (Character.isDigit(ch)){
                digitCount++;
            }
            if (isSpecialCharacter(ch)){
                specialCharacterCounter++;
            }
        }
        return (upperCaseCounter >= 2 && lowerCaseCounter >= 2 && digitCount >= 2 &&  specialCharacterCounter >= 1);
    }

    private boolean isSpecialCharacter(char ch) {
        String chToString = String.valueOf(ch);
        if (SPECIAL_CHARACTER.contains(chToString)){
            return true;
        }
        return false;
    }
}
