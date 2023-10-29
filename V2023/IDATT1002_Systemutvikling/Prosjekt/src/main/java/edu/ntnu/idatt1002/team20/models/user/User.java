package edu.ntnu.idatt1002.team20.models.user;

/**
 * Represents an account registered by the user.
 */
public class User {

    private final String username;
    private final String password;
    private final String expensesFilePath;
    private final String incomesFilePath;
    private final String debtsFilePath;
    private final String goalsFilePath;
    private final String billsFilePath;
    private final String upcomingBillsFilePath;


    /**
     * Constructor for the user class.
     *
     * @param username The users username
     * @param password The users password
     * @param expensesFilePath The path to the users expense file
     * @param incomesFilePath The path to the users income file
     * @param debtsFilePath The path to the users debt file
     * @param goalsFilePath The path to the users goal file
     * @param billsFilePath The path to the users bill file
     * @param upcomingBillsFilePath The path to the users upcoming bills file.
     */
    public User(String username, String password, String expensesFilePath, String incomesFilePath,
                String debtsFilePath, String goalsFilePath, String billsFilePath, String upcomingBillsFilePath) {
        this.username = username;
        this.password = password;
        this.expensesFilePath = expensesFilePath;
        this.incomesFilePath = incomesFilePath;
        this.debtsFilePath = debtsFilePath;
        this.goalsFilePath = goalsFilePath;
        this.billsFilePath = billsFilePath;
        this.upcomingBillsFilePath = upcomingBillsFilePath;
    }

    /**
     * Gets the username of the user.
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }


    /**
     * Gets the hashed password of the user.
     * @return the hashed password of the user
     */
    public String getHashedPassword() {
        return password;
    }


    /**
     * Gets the file path of the expenses data-file.
     * @return the file path of the expenses data-file
     */
    public String getExpensesFilePath() {
        return expensesFilePath;
    }


    /**
     * Gets the file path of the incomes data-file.
     * @return the file path of the incomes data-file
     */
    public String getIncomesFilePath() {
        return incomesFilePath;
    }


    /**
     * Gets the file path of the debts data-file.
     * @return the file path of the debts data-file
     */
    public String getDebtsFilePath() {
        return debtsFilePath;
    }


    /**
     * Gets the file path of the goals data-file.
     * @return the file path of the goals data-file
     */
    public String getGoalsFilePath() {
        return goalsFilePath;
    }


    /**
     * Gets the file path of the bills data-file.
     * @return the file path of the bills data-file
     */
    public String getBillsFilePath() {
        return billsFilePath;
    }


    /**
     * Gets the file path of the upcoming bills data-file.
     * @return the file path of the upcoming bills data-file
     */
    public String getUpcomingBillsFilePath() {
        return upcomingBillsFilePath;
    }
}
