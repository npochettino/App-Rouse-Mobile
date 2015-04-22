package sempait.rouss.Utils;

import java.util.regex.Pattern;

/**
 * Created by martin on 17/04/15.
 */
public class RoussUtils {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean validEmail(String email) {

        return Pattern.matches(EMAIL_REGEX, email);
    }

}
