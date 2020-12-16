package by.matusevich.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {

        if (phoneNo == null) {
            return false;
        }
        //validate phone numbers of format "123456789"
        if (phoneNo.matches("\\d{9}")) return true;
            //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{2}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else //return false if nothing matches the input
            if (phoneNo.matches("\\d{2}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else return phoneNo.matches("\\(\\d{2}\\)-\\d{3}-\\d{4}");
    }

}
