package org.geektimes.projects.user.validator.bean.validation;

import org.geektimes.projects.user.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lin Zehuan
 */
public class PhoneNumberAnnotationValidator implements ConstraintValidator<PhoneNumberValid, String> {

    @Override
    public void initialize(PhoneNumberValid phoneNumberValid) {

    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return isMobile(phoneNumber);
    }

    Pattern p = Pattern.compile("^((13[0-9])|(14[0|5|6|7|9])|(15[0-3])|(15[5-9])|(16[6|7])|(17[2|3|5|6|7|8])|(18[0-9])|(19[1|8|9]))\\d{8}$");

    public boolean isMobile(String mobiles) {
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
