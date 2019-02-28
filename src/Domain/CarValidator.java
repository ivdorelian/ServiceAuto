package Domain;

public class CarValidator {

    /**
     * Validates a car.
     * @param car the car to validate.
     * Raises RuntimeException if there are validation errors.
     */
    public void validate(Car car) {

        String number = car.getLicenseNumber();
        String errors = "";
        if (number.length() != 7) {
            errors += "The license plate number must be 7 characters long!\n";
        }
        if (number.charAt(0) < 'A' || number.charAt(0) > 'Z') {
            errors += "The license plate number must start with a capital letter!\n";
        }
        if (number.charAt(4) < 'A' || number.charAt(4) > 'Z' ||
            number.charAt(5) < 'A' || number.charAt(5) > 'Z' ||
            number.charAt(6) < 'A' || number.charAt(6) > 'Z') {
            errors += "The license plate number must end with 3 capital letters!";
        }

        if (!errors.equals("")) {
            throw new RuntimeException(errors);
        }
    }
}
