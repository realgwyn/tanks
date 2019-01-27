package io.tanks.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import io.tanks.common.network.model.AdminCommand;
import io.tanks.server.database.DatabaseService;
import io.tanks.server.database.domain.User;

/**
 * Uses bCrypt
 * 
 * @author rafcio
 *
 */
@Component
public class AuthenticationService {

  @Autowired
  DatabaseService dbService;

  // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
  private static int workload = 12;

  public boolean authenticateCommand(AdminCommand cmd) {
    User user = dbService.getUserByUsername(cmd.getUsername());
    if (user != null) {
      return cmd.getHash().equals(cmd.getHash());
    }
    return false;
  }

  public static String hashPassword(String password_plaintext) {
    String salt = BCrypt.gensalt(workload);
    String hashed_password = BCrypt.hashpw(password_plaintext, salt);

    return (hashed_password);
  }

  public static boolean checkPassword(String password_plaintext, String stored_hash) {
    boolean password_verified = false;

    if (null == stored_hash || !stored_hash.startsWith("$2a$"))
      throw new IllegalArgumentException("Invalid hash provided for comparison");

    password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

    return (password_verified);
  }

  public static void main(String[] args) {
    String test_passwd = "abcdefghijklmnopqrstuvwxyz";
    String test_hash = "$2a$06$.rCVZVOThsIa97pEDOxvGuRRgzG64bvtJ0938xuqzv18d3ZpQhstC";

    System.out.println("Testing BCrypt Password hashing and verification");
    System.out.println("Test password: " + test_passwd);
    System.out.println("Test stored hash: " + test_hash);
    System.out.println("Hashing test password...");
    System.out.println();

    String computed_hash = hashPassword(test_passwd);
    System.out.println("Test computed hash: " + computed_hash);
    System.out.println();
    System.out.println("Verifying that hash and stored hash both match for the test password...");
    System.out.println();

    String compare_test = checkPassword(test_passwd, test_hash)
        ? "Passwords Match" : "Passwords do not match";
    String compare_computed = checkPassword(test_passwd, computed_hash)
        ? "Passwords Match" : "Passwords do not match";

    System.out.println("Verify against stored hash:   " + compare_test);
    System.out.println("Verify against computed hash: " + compare_computed);
  }

}
