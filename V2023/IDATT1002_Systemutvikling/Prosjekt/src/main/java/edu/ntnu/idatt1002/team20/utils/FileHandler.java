package edu.ntnu.idatt1002.team20.utils;

import edu.ntnu.idatt1002.team20.models.bill.Bill;
import edu.ntnu.idatt1002.team20.models.debt.Debt;
import edu.ntnu.idatt1002.team20.models.expense.Expense;
import edu.ntnu.idatt1002.team20.models.goal.Goal;
import edu.ntnu.idatt1002.team20.models.goal.GoalRegister;
import edu.ntnu.idatt1002.team20.models.income.Income;
import edu.ntnu.idatt1002.team20.models.user.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for handling files.
 * Includes methods for reading and writing to the different storage files.
 */
public class FileHandler {


    /**
     * Reads the users from the file with the given filename.
     * @return the list of users read from the file.
     */
    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/edu/ntnu/idatt1002/team20/data/users.txt"));
            while (scanner.hasNextLine()) {
                String username = scanner.nextLine();
                String password = scanner.nextLine();
                String expenseFilePath = scanner.nextLine();
                String incomeFilePath = scanner.nextLine();
                String debtFilePath = scanner.nextLine();
                String goalFilePath = scanner.nextLine();
                String billsFilePath = scanner.nextLine();
                String upcomingBillsFilePath = scanner.nextLine();
                users.add(new User(username, password, expenseFilePath, incomeFilePath, debtFilePath, goalFilePath,
                        billsFilePath, upcomingBillsFilePath));
                scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            SceneSwitcher.showAlert("The file could not be found.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found.");
        }
        return users;
    }


    /**
     * Writes the goals to the file with the given filename.
     * @param filename the name of the file to be written to.
     * @param expenses the list of expenses to be written to the file.
     */
    public static void writeExpenses(String filename, List<Expense> expenses) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            for (Expense expense : expenses) {
                out.write(String.valueOf(expense.getExpenseDate()));
                out.newLine();
                out.write(expense.getExpenseSource());
                out.newLine();
                out.write(String.valueOf(expense.getExpenseAmount()));
                out.newLine();
                out.write(String.valueOf(expense.getDebt()));
                out.newLine();
                out.write(String.valueOf(expense.getGoal()));
                out.newLine();
                out.write("\n");
            }
            out.close();
        } catch (IOException e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }
    }


    /**
     * Reads the expenses from the file with the given filename.
     * @param filename the name of the file to be read from.
     */
    public static ObservableList<Expense> readExpenses (String filename) {
        ObservableList<Expense> observableExpenses = FXCollections.observableArrayList();

        Expense expense;
        Scanner scan;
        try {
            scan = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scan.hasNext()) {
            LocalDateTime dateTime = LocalDateTime.parse(scan.nextLine());
            String source = scan.nextLine();
            double amount = Double.parseDouble(scan.nextLine());
            String debt = scan.nextLine();
            String goal = scan.nextLine();

            expense = new Expense.Builder(dateTime, source, amount)
                    .debt(debt.equals("null") ? null : Debt.parse(debt))
                    .goal(goal.equals("null") ? null : Goal.parse(goal))
                    .build();

            scan.nextLine();
            observableExpenses.add(expense);
        }
        return observableExpenses;
    }


    /**
     * Writes the incomes to the file with the given filename.
     * @param filename the name of the file to be written to.
     * @param incomes the list of incomes to be written to the file.
     */
    public static void writeIncomes (String filename, List<Income> incomes) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename));
            for (Income income : incomes) {
                out.write(String.valueOf(income.getIncomeDate()));
                out.newLine();
                out.write(income.getIncomeSource());
                out.newLine();
                out.write(String.valueOf(income.getIncomeAmount()));
                out.newLine();
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }

    }


    /**
     * Reads the incomes from the file with the given filename.
     * @param filename the name of the file to be read from.
     */
    public static ObservableList<Income> readIncomes (String filename) {
        ObservableList<Income> observableIncomes = FXCollections.observableArrayList();

        Scanner scan;
        try {
            scan = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scan.hasNext()) {
            LocalDateTime date = LocalDateTime.parse(scan.nextLine());
            String source = scan.nextLine();
            double amount = Double.parseDouble(scan.nextLine());
            scan.nextLine();

            Income income = new Income(date, source, amount);
            observableIncomes.add(income);
        }
        return observableIncomes;
    }


    /**
     * Writes the debts to the file with the given filename.
     * @param filename the name of the file to be written to.
     * @param debts the list of debts to be written to the file.
     */
    public static void writeDebts(String filename, List<Debt> debts) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            for (Debt debt : debts) {
                out.write(debt.getDebtType());
                out.newLine();
                out.write(String.valueOf(debt.getTotalAmount()));
                out.newLine();
                out.write(String.valueOf(debt.getAmountPaid()));
                out.newLine();
                out.write(String.valueOf(debt.getInterestRate()));
                out.newLine();
                out.write("\n");
            }
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }
    }

    /**
     * Appends the given debt to the file with the given filename.
     * @param filename the name of the file to be read from.
     */
    public static void writeAddNewDebt(String filename, Debt debt){
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename, true))) {
            out.write(debt.getDebtType());
            out.newLine();
            out.write(String.valueOf(debt.getTotalAmount()));
            out.newLine();
            out.write(String.valueOf(debt.getAmountPaid()));
            out.newLine();
            out.write(String.valueOf(debt.getInterestRate()));
            out.newLine();
            out.write("\n");
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }
    }


    /**
     * Reads the debts from the file with the given filename.
     * @param filename the name of the file to be read from.
     * @return a list of debts read from the file.
     */
    public static List<Debt> readDebts(String filename) {
        List<Debt> debts = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String debtType;
            double totalAmount;
            double amountPaid;
            double interestRate;
            while ((debtType = in.readLine()) != null) {
                totalAmount = Double.parseDouble(in.readLine());
                amountPaid = Double.parseDouble(in.readLine());
                interestRate = Double.parseDouble(in.readLine());
                debts.add(new Debt(debtType, totalAmount, amountPaid, interestRate));
                in.readLine();
            }
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }

        return debts;
    }


    /**
     * Writes the given list of goals to the file with the given filename.
     * @param filename the name of the file to be written to.
     * @param goals the list of goals to be written to the file.
     */
    public static void writeGoals(String filename, List<Goal> goals) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            for (Goal goal : goals) {
                out.write(goal.getGoalType());
                out.newLine();
                out.write(String.valueOf(goal.getGoalAmount()));
                out.newLine();
                out.write(String.valueOf(goal.getAmountSpent()));
                out.newLine();
                out.write("\n");
            }
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }
    }


    /**
     * Reads all goals from specified file.
     *
     * @param filename The file the goals are stored in
     * @return Register with the goals.
     */
    public static GoalRegister readGoals(String filename) {
        GoalRegister goalRegister = new GoalRegister();

        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNext()) {
                String goalType = sc.nextLine();
                double goalAmount = Double.parseDouble(sc.nextLine());
                double amountSpent = Double.parseDouble(sc.nextLine());
                sc.nextLine();
                goalRegister.addGoal(new Goal(goalType, goalAmount, amountSpent));
            }
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }

        return goalRegister;
    }


    /**
     * Reads the bills from the file with the given filename.
     * @param filename the name of the file to be read from.
     * @return a list of bills read from the file.
     */
    public static List<Bill> readBills(String filename) {
        List<Bill> bills = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String category;
            int id;
            LocalDate dueDate;
            double amount;
            boolean isPaid;
            while ((category = in.readLine()) != null) {
                id = Integer.parseInt(in.readLine());
                dueDate = LocalDate.parse(in.readLine());
                amount = Double.parseDouble(in.readLine());
                isPaid = Boolean.parseBoolean(in.readLine());
                in.readLine();
                Bill bill = new Bill(dueDate, category, id, amount);
                bill.setPaid(isPaid);
                bills.add(bill);
            }
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }

        return bills;
    }


    /**
     * Writes the given list of bills to the file with the given filename.
     * @param filename the name of the file to be written to.
     * @param bills the list of bills to be written to the file.
     */
    public static void writeBills(String filename, List<Bill> bills) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            for (Bill bill : bills) {
                out.write(bill.getCategory());
                out.newLine();
                out.write(String.valueOf(bill.getBillId()));
                out.newLine();
                out.write(String.valueOf(bill.getDueDate()));
                out.newLine();
                out.write(String.valueOf(bill.getAmount()));
                out.newLine();
                out.write(String.valueOf(bill.getIsPaid()));
                out.newLine();
                out.write("\n");
            }
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }
    }


    /**
     * Appends the given list of bills to the file with the given.
     * Also writes the bill to the upcoming bills file if it is due in the future.
     * @param billStorage the name of the file to be read from.
     * @param upcomingBillStorage the name of the file to be written to.
     */
    public static void writeAddNewBill(String billStorage, String upcomingBillStorage, Bill bill) {

        List<Bill> bills = readBills(billStorage);

        try (BufferedWriter out = new BufferedWriter(new FileWriter(upcomingBillStorage, true))) {
            out.write(bill.getCategory());
            out.newLine();
            out.write(String.valueOf(bills.size() + 1));
            out.newLine();
            out.write(String.valueOf(bill.getDueDate()));
            out.newLine();
            out.write(String.valueOf(bill.getAmount()));
            out.newLine();
            out.write(String.valueOf(false));
            out.newLine();
            out.write("\n");
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }


        // Write the bill to the bills file
        try (BufferedWriter out = new BufferedWriter(new FileWriter(billStorage, true))) {
            out.write(bill.getCategory());
            out.newLine();
            out.write(String.valueOf(bills.size() + 1));
            out.newLine();
            out.write(String.valueOf(bill.getDueDate()));
            out.newLine();
            out.write(String.valueOf(bill.getAmount()));
            out.newLine();
            out.write(String.valueOf(bill.getIsPaid()));
            out.newLine();
            out.write("\n");
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            throw new RuntimeException("The file could not be found or written to.");
        }
    }

    /**
     * Saves the upcoming bills to the file with the given filename.
     * @param filename the name of the file to be written to.
     * @param upcomingBills the list of upcoming bills to be written to the file.
     */
    public static void writeUpcomingBills(String filename, List<Bill> upcomingBills) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            for(Bill bill : upcomingBills) {
                if(!bill.getIsPaid()) {
                    out.write(bill.getCategory());
                    out.newLine();
                    out.write(String.valueOf(bill.getBillId()));
                    out.newLine();
                    out.write(String.valueOf(bill.getDueDate()));
                    out.newLine();
                    out.write(String.valueOf(bill.getAmount()));
                    out.newLine();
                    out.write(String.valueOf(bill.getIsPaid()));
                    out.newLine();
                    out.write("\n");
                }
            }
        } catch (Exception e) {
            SceneSwitcher.showAlert("The file could not be found or written to.");
            e.printStackTrace();
            throw new RuntimeException("The file could not be found or written to.");
        }
    }
}