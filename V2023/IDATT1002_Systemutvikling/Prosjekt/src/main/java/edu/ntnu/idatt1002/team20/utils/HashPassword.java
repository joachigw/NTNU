package edu.ntnu.idatt1002.team20.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The utility class for hashing passwords using the SHA-512 algorithm.
 * <p>
 * This class provides a single public static method to hash a password using the SHA-512 algorithm.
 * The method takes a string password as input and returns the hashed password as a string.
 * The class uses the MessageDigest class from the Java security package to perform the hashing.
 */
public class HashPassword {


  /**
   * Hashes the given password using the SHA-512 algorithm.
   *
   * @param passwordToHash the password to hash
   * @return the hashed password
   */
  public static String hash(String passwordToHash) {

    String generatedPassword = null;

    try {
      // Create a MessageDigest object using the SHA-512 algorithm
      MessageDigest md = MessageDigest.getInstance("SHA-512");

      // Convert the password to a byte array and compute the hash
      byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();

      // Convert the hash to a hexadecimal string
      for (byte aByte : bytes) {
        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
      }

      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      // If the SHA-512 algorithm is not supported, print the stack trace
      e.printStackTrace();
    }

    // Return the hashed password
    return generatedPassword;
  }
}
