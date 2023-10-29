package edu.ntnu.idatt1002.team20.models.user;

import edu.ntnu.idatt1002.team20.utils.HashPassword;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Class for the user register.
 * The user register contains a list of all the users.
 * Includes methods for adding and getting users by username.
 */
public class UserRegister {

    private final List<User> userList;


    /**
     * Constructor for the user register.
     * Creates a new user register with an empty list of users.
     */
    public UserRegister() {
        userList = new ArrayList<>();
    }


    /**
     * Adds a user to the user register.
     * @param username the username of the user
     * @param password the password of the user
     * @param expenseFilePath the file path of the expense data-file
     * @param incomeFilePath the file path of the income data-file
     * @param debtFilePath the file path of the debt data-file
     * @param goalFilePath the file path of the goal data-file
     * @param billsFilePath the file path of the bills data-file
     * @param upcomingBillsFilePath the file path of the upcoming bills data-file
     */
    public void addUser(String username, String password, String expenseFilePath,
                           String incomeFilePath, String debtFilePath, String goalFilePath,
                           String billsFilePath, String upcomingBillsFilePath) {
        if (userList.stream().anyMatch(user -> user.getUsername().equals(username))) {
            throw new IllegalArgumentException("This username is already in use.");
        }
        userList.add(new User(username, password, expenseFilePath, incomeFilePath, debtFilePath, goalFilePath,
                billsFilePath, upcomingBillsFilePath));
    }


    /**
     * Adds a list of users to the user register.
     * @param users the list of users to add
     */
    public void addUsers(List<User> users) {
        userList.addAll(users);
    }


    /**
     * Gets the user with the given username.
     * @param username the username of the user
     * @return the user with the given username
     */
    public User getUserByUsername(String username) {
        try {
            return userList.stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException("There exists no user with this username.");
        }
    }


    /**
     * Checks if the user register contains a user with the given username.
     * @param username the username of the user
     * @return true if the user register contains a user with the given username, false otherwise
     */
    public boolean hasUsername(String username)
    {
        try {
            return userList.stream()
                    .anyMatch(user -> user.getUsername().equals(username));
        } catch (NoSuchElementException noSuchElementException) {
            throw new NoSuchElementException("There exists no user with this username.");
        }
    }


    /**
     * Checks if the user register contains a user with the given username and password.
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the user register contains a user with the given username and password, false otherwise
     */
    public boolean hasUser(String username, String password) {
        String hashedPassword = HashPassword.hash(password);

        return userList.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getHashedPassword().equals(hashedPassword));
    }

}
