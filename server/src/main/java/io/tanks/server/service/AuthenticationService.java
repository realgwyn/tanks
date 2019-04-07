package io.tanks.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import io.tanks.common.network.model.AdminCommand;
import io.tanks.server.database.DatabaseService;

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
    throw new UnsupportedOperationException("TODO: implement PlayerRepo get user by username");
    //TODO: implement PlayerRepo get user by username
//    Player user = dbService.getUserByUsername(cmd.getUsername());
//    if (user != null) {
//      return cmd.getHash().equals(cmd.getHash());
//    }
//    return false;
  }

  public static String createPasswordHash(String plaintextPassword) {
    String salt = BCrypt.gensalt(workload);
    return BCrypt.hashpw(plaintextPassword, salt);
  }

  public static boolean checkPassword(String plaintextPassword, String passwordHash) {
    if (null == passwordHash || !passwordHash.startsWith("$2a$"))
      throw new IllegalArgumentException("Invalid hash provided for comparison");

    return BCrypt.checkpw(plaintextPassword, passwordHash);
  }

}
