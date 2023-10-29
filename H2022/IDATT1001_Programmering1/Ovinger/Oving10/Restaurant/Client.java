package Oving_10_F.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Client class.
 * Acts as a user-interface for testing the classes Dish, Menu and MenuRegister.
 */
public class Client {

    static boolean hasRun = false;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        MenuRegister reg = new MenuRegister();


        try {
            while (true) {
                System.out.println("""
                                                
                        Welcome!
                        Choose one of the following services:
                        [0] Add default dishes
                        [1] Register a new dish
                        [2] Find dish by name
                        [3] Find dish(es) by type
                        [4] Register a new menu
                        [5] Find menu(s) within a given price-interval
                        [6] All menus
                        [7] Exit
                        """);
                int chosenService = in.nextInt();

                switch (chosenService) {
                    case 0 -> createDefaultMenus(reg);
                    case 1 -> registerNewDish(reg);
                    case 2 -> findDishByName(reg);
                    case 3 -> findDishByType(reg);
                    case 4 -> registerNewMenu(reg);
                    case 5 -> findMenuPriceInterval(reg);

                    case 6 -> System.out.println(reg);
                    case 7 -> {
                        System.out.println("Hope to see you again soon.");
                        System.exit(0);
                    }
                    default -> System.out.println("You must choose a number between 0 and 8!");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e + ". Please restart the program.");
        }
    }

    /**
     * Method for creating default menus, which then are added to the MenuRegister ArrayList.
     *
     * @param reg The object "reg" of the class MenuRegister, for accessing its class-methods.
     */
    public static void createDefaultMenus(MenuRegister reg) {

        if (!hasRun) {
            Dish defaultStarter1 = new Dish("Starter1", "Starter", 99.9);
            Dish defaultMain1 = new Dish("Main1", "Main", 149.5);
            Dish defaultDessert1 = new Dish("Dessert1", "Dessert", 179.5);

            Menu defaultMenu1 = new Menu(defaultStarter1, defaultMain1, defaultDessert1);
            reg.addNewMenu(defaultMenu1);

            Dish defaultStarter2 = new Dish("Starter2", "Starter", 199.9);
            Dish defaultMain2 = new Dish("Main2", "Main", 249.5);
            Dish defaultDessert2 = new Dish("Dessert2", "Dessert", 279.5);

            Menu defaultMenu2 = new Menu(defaultStarter2, defaultMain2, defaultDessert2);
            reg.addNewMenu(defaultMenu2);

            Dish defaultStarter3 = new Dish("Starter3", "Starter", 299.9);
            Dish defaultMain3 = new Dish("Main3", "Main", 349.5);
            Dish defaultDessert3 = new Dish("Dessert3", "Dessert", 379.5);

            Menu defaultMenu3 = new Menu(defaultStarter3, defaultMain3, defaultDessert3);
            reg.addNewMenu(defaultMenu3);

            System.out.println("Default menus added!");
            hasRun = true;
        } else {
            System.out.println("You have already added the default dishes.");
        }

    }

    /**
     * Method used to receive input from the user.
     * Calls a method in MenuRegister used to register the new dish.
     *
     * @param reg The object "reg" of the class MenuRegister, for accessing its class-methods.
     */
    public static void registerNewDish(MenuRegister reg) {

        Scanner in = new Scanner(System.in);

        System.out.println("Name of dish:");
        String newDishName = in.nextLine();
        System.out.println("Type of dish (starter/main/dessert):");
        String newDishType = in.nextLine();
        System.out.println("Price of dish:");
        double newDishPrice = in.nextDouble();

        reg.newDish(newDishName, newDishType, newDishPrice);

    }

    /**
     * Method used to receive an input from the user.
     * Calls a method in MenuRegister used to search for dishes with the input as its name.
     *
     * @param reg The object "reg" of the class MenuRegister, for accessing its class-methods.
     */
    public static void findDishByName(MenuRegister reg) {

        if (reg.getAmountOfMenus() <= 0) {
            System.out.println("There are no dishes registered yet!");
            return;
        }

        Scanner in = new Scanner(System.in);

        System.out.println("Name of dish to find:");
        String searchedName = in.nextLine();

        reg.findDishByName(searchedName);
    }

    /**
     * Method used to receive an input from the user.
     * Calls a method in MenuRegister used to search for dishes with the input as its type.
     *
     * @param reg The object "reg" of the class MenuRegister, for accessing its class-methods.
     */
    public static void findDishByType(MenuRegister reg) {

        if (reg.getAmountOfMenus() <= 0) {
            System.out.println("There are no dishes registered yet!");
            return;
        }

        Scanner in = new Scanner(System.in);

        System.out.println("Type of dish to find (starter/main/dessert):");
        String searchedType = in.next();

        reg.findDishesByType(searchedType);

    }

    /**
     * Method used to register a new menu.
     * The user can choose between using pre-defined dishes, or creating new ones.
     *
     * @param reg The object "reg" of the class MenuRegister, for accessing its class-methods.
     */
    public static void registerNewMenu(MenuRegister reg) {

        Scanner in = new Scanner(System.in);

        System.out.println("""
                Create a new menu either by using default dishes, or create new ones:
                [1] Create from set of default dishes
                [2] Create new dishes""");

        int chosenAction = in.nextInt();

        if (chosenAction == 1) {

            Dish dishExample1 = new Dish("Example1", "Starter", 40);
            Dish dishExample2 = new Dish("Example2", "Dessert", 20);
            Dish dishExample3 = new Dish("Example3", "Main", 10);
            Dish dishExample4 = new Dish("Example4", "Starter", 70);
            Dish dishExample5 = new Dish("Example5", "Dessert", 90);
            Dish dishExample6 = new Dish("Example6", "Main", 50);

            ArrayList<Dish> dishesList = new ArrayList<>();

            dishesList.add(dishExample1);
            dishesList.add(dishExample2);
            dishesList.add(dishExample3);
            dishesList.add(dishExample4);
            dishesList.add(dishExample5);
            dishesList.add(dishExample6);

            System.out.println("[1]" + dishExample1);
            System.out.println("[2]" + dishExample2);
            System.out.println("[3]" + dishExample3);
            System.out.println("[4]" + dishExample4);
            System.out.println("[5]" + dishExample5);
            System.out.println("[6]" + dishExample6);

            System.out.println("Dish no. 1:");
            int dish1 = in.nextInt() - 1;
            System.out.println("Dish no. 2:");
            int dish2 = in.nextInt() - 1;
            System.out.println("Dish no. 3:");
            int dish3 = in.nextInt() - 1;

            Menu newMenu = new Menu(dishesList.get(dish1), dishesList.get(dish2), dishesList.get(dish3));
            reg.addNewMenu(newMenu);
            System.out.println(newMenu);

        } else if (chosenAction == 2) {
            in.nextLine();
            System.out.println("Dish 1:\nName:");
            String name1 = in.nextLine();
            System.out.println("Type:");
            String type1 = in.nextLine();
            System.out.println("Price:");
            double price1 = in.nextDouble();

            in.nextLine();
            System.out.println("Dish 2:\nName:");
            String name2 = in.nextLine();
            System.out.println("Type:");
            String type2 = in.nextLine();
            System.out.println("Price:");
            double price2 = in.nextDouble();

            in.nextLine();
            System.out.println("Dish 3:\nName:");
            String name3 = in.nextLine();
            System.out.println("Type:");
            String type3 = in.nextLine();
            System.out.println("Price:");
            double price3 = in.nextDouble();

            Dish dish1 = new Dish(name1, type1, price1);
            Dish dish2 = new Dish(name2, type2, price2);
            Dish dish3 = new Dish(name3, type3, price3);

            Menu newMenu = new Menu(dish1, dish2, dish3);

            reg.addNewMenu(newMenu);

            System.out.println(newMenu);

        } else {
            System.out.println("You must choose either 1 or 2! Please try again.");
        }
    }

    /**
     * Method used to show menus with a total price within the specified interval
     *
     * @param reg The object "reg" of the class MenuRegister, for accessing its class-methods.
     */
    public static void findMenuPriceInterval(MenuRegister reg) {

        if (reg.getAmountOfMenus() <= 0) {
            System.out.println("There are no dishes registered yet!");
            return;
        }

        Scanner in = new Scanner(System.in);

        System.out.println("Interval start:");
        double intervalStart = in.nextDouble();
        System.out.println("Interval end:");
        double intervalEnd = in.nextDouble();

        reg.findMenusByPrice(intervalStart, intervalEnd);

    }
}

