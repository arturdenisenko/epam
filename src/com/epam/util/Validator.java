/*
 * @Denisenko Artur
 */

package com.epam.util;

public class Validator {

    private static final String filledRegex = "^[а-яА-ЯёЁa-zA-Z][а-яА-ЯёЁa-zA-Z0-9-_\\.]{1,20}$";
    private static final String emailRegex = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String digitsRegex = "^[0-9]+$";
    private static final String dateValidating = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
    private static final String imageFormatRegex = "^.+\\.(([pP][nN][gG])|([jJ][pP][gG]))$";
    private static final String fullNameRegex = "([A-Za-zА-Яа-яёЁ. ]+)";

    public static boolean isFilled(String... values) {
        for (String value : values) {
            if (value.matches(filledRegex)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCorrectEmail(String email) {
        return email.matches(emailRegex);
    }

    public static boolean isDigitsInput(String input) {
        return input.matches(digitsRegex);
    }

    public static boolean isCorrectDate(String date) {
        return date.matches(dateValidating);
    }

    public static boolean isCorrectImage(String image) {
        return image.matches(imageFormatRegex);
    }

    public static boolean isCorrectName(String... input) {
        for (String s : input) {
            if (!s.matches(fullNameRegex)) {
                return false;
            }
        }
        return true;
    }
}

