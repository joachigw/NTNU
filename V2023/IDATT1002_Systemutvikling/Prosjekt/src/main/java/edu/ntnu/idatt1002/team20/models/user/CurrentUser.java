package edu.ntnu.idatt1002.team20.models.user;

/**
 * The singleton class for managing the current user of the application.
 * <p>
 * This class is responsible for managing the current user of the application.
 * It provides methods to log in, log out, and get the current user.
 * The class is implemented as a singleton to ensure that there is only one instance
 * of the current user object throughout the lifetime of the application.
 */
public class CurrentUser {

  // The singleton instance of the CurrentUser class
  private static CurrentUser instance;

  // The current user object
  private User user;


  /**
   * Private constructor to prevent instantiation from outside the class.
   */
  private CurrentUser() {
  }


  /**
   * Returns the singleton instance of the CurrentUser class.
   * If the instance does not exist, it is created.
   *
   * @return the singleton instance of the CurrentUser class
   */
  public static CurrentUser getInstance() {
    if (instance == null) {
      instance = new CurrentUser();
    }
    return instance;
  }


  /**
   * Sets the current user to the given user.
   *
   * @param user the user to set as the current user
   */
  public void login(User user) {
    this.user = user;
  }


  /**
   * Sets the current user to null, effectively logging out the user.
   */
  public void logout() {
    this.user = null;
  }

  /**
   * Returns the current user object.
   *
   * @return the current user object
   */
  public User getUser() {
    return user;
  }
}
