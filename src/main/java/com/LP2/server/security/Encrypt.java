package com.LP2.server.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Arrays;

import com.LP2.app.ErrorCodes;

public class Encrypt {

  static private final SecureRandom random = new SecureRandom();
  static private final int ITERATIONS = 65536;
  static private final int KEY_LENGTH = 512;
  static private final String ALGORITHM = "PBKDF2WithHmacSHA512";

  static public String getSalt(final int length) {
    byte[] salt = null;

    if (length <= 0) {
      System.out.println("ERROR: " + ErrorCodes.CREATIONERROR());
      System.out.println("Given Length Must be longer than 0!");
      return null;
    }
    salt = new byte[length];
    random.nextBytes(salt);

    return Base64.getEncoder().encodeToString(salt);
  }

  static public String encryptPBKDF(String password, String salt) {
    char[] chars = password.toCharArray();
    byte[] bytes = salt.getBytes();
    PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

    Arrays.fill(chars, Character.MIN_VALUE);

    try {
      SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
      byte[] encryPass = fac.generateSecret(spec).getEncoded();

      return Base64.getEncoder().encodeToString(encryPass);
    } catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
      return null;
    } finally {
      spec.clearPassword();
    }
  }

  static public boolean validatePBKDF(String pass, String key, String salt) {
    String encryPass = encryptPBKDF(pass, salt);

    return encryPass == null ? false : encryPass.equals(key);
  }

  static public String encryptSHA512(String text) {
    SecureRandom sr = new SecureRandom();
    byte[] salt = new byte[16];
    byte[] hashedPassword = null;
    MessageDigest md = null;

    sr.nextBytes(salt);
    try {
      md = MessageDigest.getInstance("SHA-512");
      md.update(salt);
      hashedPassword = md.digest(text.getBytes(StandardCharsets.UTF_8));

      return hashedPassword.toString();
    } catch(NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}