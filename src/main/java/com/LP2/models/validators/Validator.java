package com.LP2.models.validators;

import java.util.ArrayList;

import com.LP2.models.utils.Constants;

public class Validator {
  static public ValidationState email(String inEmail) {
    int i = 0;
    String tmp = "";
    String email = inEmail.toLowerCase();
    ArrayList<String> parts = new ArrayList<String>();
    boolean allowed = false;

    while (i < email.length() && email.charAt(i) != '@') {
      tmp += email.charAt(i);
      i += 1;
    }
    if (i == email.length())
      return new ValidationState(false, ValidationMessages.sEmailFormat(),
            ValidationMessages.cEmailFormat());

    parts.add(tmp);
    i += 1;
    tmp = "";
    while (i < email.length() && email.charAt(i) != '.') {
      tmp += email.charAt(i);
      i += 1;
    }
    if (i == email.length())
      return new ValidationState(false, ValidationMessages.sEmailFormat(),
        ValidationMessages.cEmailFormat());

    parts.add(tmp);
    i += 1;
    tmp = "";
    while (i < email.length()) {
      tmp += email.charAt(i);
      i += 1;
    }
    parts.add(tmp);
    allowed = (parts.get(0).length() > 0) &&
              (parts.get(1).length() > 0) &&
              (parts.get(2).length() > 0);

    if (allowed)
      return new ValidationState(true, ValidationMessages.sAccepted(),
        ValidationMessages.cAccepted());

    return new ValidationState(false, ValidationMessages.sEmailFormat(),
      ValidationMessages.cEmailFormat());
  }

  static private boolean isUppercase(char c) { return Character.isUpperCase(c); }

  static private boolean isLowercase(char c) { return Character.isLowerCase(c); }

  static private boolean isSymbol (char c) {
    return !(isNumber(c) || isUppercase(c) || isLowercase(c));
  }

  static private boolean isNumber(char c) { return c >= '0' && c <= '9'; }

  static public ValidationState password(String password) {
    boolean minimumLength = (password.length() >= Constants.getNoCodeMinPassLen());
    boolean uppercase = false;
    boolean symbol = false;
    boolean number = false;
    boolean allowed = false;
    char c;
    int i = 0;
    byte code = -1;
    String message = null;

    if (password.length() > Constants.getMaxPassLen())
      return new ValidationState(false, ValidationMessages.spassLong(),
            ValidationMessages.cpassLong());

    while (i < password.length()) {
      c = password.charAt(i);
      if (isUppercase(c))
        uppercase = true;
      if (isSymbol(c))
        symbol = true;
      if (isNumber(c))
        number = true;
      i += 1;
    }
    allowed = minimumLength && (symbol || number || uppercase);

    if ((password.length() == Constants.getMinPassLen()) || allowed)
      return new ValidationState(true, ValidationMessages.sAccepted(),
                  ValidationMessages.cAccepted());

    ValidationState vs = new ValidationState();
    if (!minimumLength) {
      code = ValidationMessages.cpassShort();
      message = ValidationMessages.spassShort();
      vs.addState(message, code);
    } if (!symbol) {
      code = ValidationMessages.cpassSymbol();
      message = ValidationMessages.spassSymbol();
      vs.addState(message, code);
    } if (!number) {
      code = ValidationMessages.cpassNumber();
      message = ValidationMessages.spassNumber();
      vs.addState(message, code);
    } if (!uppercase) {
      code = ValidationMessages.cpassUpper();
      message = ValidationMessages.spassUpper();
      vs.addState(message, code);
    }

    return vs;
  }

}