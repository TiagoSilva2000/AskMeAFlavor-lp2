package com.LP2.models.validators;

import com.LP2.models.utils.Constants;

public class ValidationMessages {

  static public byte cAccepted() { return 0; }

  static public String sAccepted() { return "Accepted."; }

  static public byte cpassShort() { return 1; }

  static public String spassShort() {
    String tmp = "Senha muito curta. O tamanho mínimo é de " +
    String.valueOf(Constants.getNoCodeMinPassLen());

    return tmp;
  }

  static public byte cpassLong() { return 2; }

  static public String spassLong() {
    String tmp = "Senha muito longa. O tamanho máximo é de " +
      String.valueOf(Constants.getMaxPassLen());

    return tmp;
  }

  static public byte cpassUpper() { return 3; }

  static public String spassUpper() {
    return "Não apresenta nenhum caractere em maiúsculo.";
  }

  static public byte cpassSymbol() { return 4; }

  static public String spassSymbol() {
    return "Não apresenta nenhum caractere especial.";
  }

  static public byte cpassNumber() { return 5; }

  static public String spassNumber() {
    return "Não apresenta nenhum número.";
  }

  static public byte cpassAlmostShort () { return 6; }

  static public String spassAlmostShort() {
    return "Senha curta. Tente adicionar símbolos, números ou letras maiúsculas.";
  }

  static public byte cEmailFormat() { return 7; }

  static public String sEmailFormat() {
    return "Invalid e-mail format.";
  }
}