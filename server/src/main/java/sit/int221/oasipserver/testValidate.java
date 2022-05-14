package sit.int221.oasipserver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testValidate {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^\\s*$",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(" ");
        System.out.println(matcher.find());

//        System.out.println(validate("user@domain.com"));
    }
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
