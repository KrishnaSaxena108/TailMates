/**
 * Utility class for validating user inputs
 */
public class InputValidator {

    /**
     * Validates a name (username or pet name)
     * Rules: Must not start with a digit, must contain only alphabets
     */
    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        // Check if first character is a digit
        if (Character.isDigit(name.charAt(0))) {
            return false;
        }

        // Check if all characters are alphabets
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates a phone number
     * Rules: Must be exactly 10 digits, must start with 6, 7, 8, or 9
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 10) {
            return false;
        }

        // Check if first digit is 6, 7, 8, or 9
        char firstDigit = phoneNumber.charAt(0);
        if (firstDigit != '6' && firstDigit != '7' && firstDigit != '8' && firstDigit != '9') {
            return false;
        }

        // Check if all characters are digits
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Validates pet gender
     * Rules: Must be either "Male" or "Female" (case insensitive)
     */
    public static boolean isValidGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return false;
        }

        String normalizedGender = gender.toLowerCase();
        return normalizedGender.equals("male") || normalizedGender.equals("female");
    }

    /**
     * Validates pet breed
     * Rules: Must not be a number, must not be empty or start with a digit
     */
    public static boolean isValidBreed(String breed) {
        if (breed == null || breed.isEmpty()) {
            return false;
        }

        // Check if first character is a digit
        if (Character.isDigit(breed.charAt(0))) {
            return false;
        }

        // Check if the string is not just a number
        try {
            Double.parseDouble(breed);
            return false; // If we can parse it as a number, it's invalid
        } catch (NumberFormatException e) {
            return true; // Not a number, so it's valid
        }
    }

    /**
     * Formats gender to proper case (first letter uppercase, rest lowercase)
     */
    public static String formatGender(String gender) {
        if (gender == null || gender.isEmpty()) {
            return "";
        }

        String lowerCase = gender.toLowerCase();
        return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
    }
}
