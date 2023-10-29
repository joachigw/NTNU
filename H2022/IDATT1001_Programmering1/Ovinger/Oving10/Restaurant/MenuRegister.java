package Oving_10_F.Restaurant;

import java.util.ArrayList;

/**
 * MenuRegister class.
 * Creates a list of all the restaurant's current menus.
 */
public class MenuRegister {

    private final ArrayList<Menu> menuRegister;
    private final ArrayList<Dish> dishes = new ArrayList<>();

    /**
     * Class constructor.
     * Used to initialize an empty ArrayList containing different objects of the class Menu (a list of menus).
     */
    public MenuRegister() {
        menuRegister = new ArrayList<>();
    }

    /**
     * Method used to get the current amount of registered menus.
     *
     * @return int of the amount of menus.
     */
    public int getAmountOfMenus() {
        return menuRegister.size();
    }

    /**
     * Method used to register an object of the class Menu to "menuregister".
     *
     * @param menu An object of the class Menu.
     */
    public void addNewMenu(Menu menu) {
        menuRegister.add(menu);
    }

    /**
     * Method used to register a new dish.
     *
     * @param name  Name of the dish.
     * @param type  Type of dish (starter, main, dessert).
     * @param price Price of dish.
     */
    public void newDish(String name, String type, double price) {
        dishes.add(new Dish(name, type, price));
    }

    /**
     * Method used to find dishes with the same name as the specified String "name".
     *
     * @param name Name of dish.
     */
    public void findDishByName(String name) {

        outerLoop:
        for (int i = 0; i < menuRegister.size(); i++) {

            for (int j = 0; j < menuRegister.get(i).getMenuArray().size(); j++) {

                if (menuRegister.get(i).getMenuArray().get(j).getName().equalsIgnoreCase(name)) {
                    System.out.println(menuRegister.get(i).getMenuArray().get(j));
                    break outerLoop;
                }
            }
            if (i == menuRegister.size()) {
                System.out.println("No dishes with the name " + name + " were found.");
            }
        }

    }

    /**
     * Method used to find dishes with the same type as the specified String "type".
     *
     * @param type Type of dish (starter, main, dessert).
     */
    public void findDishesByType(String type) {

        for (int i = 0; i < menuRegister.size(); i++) {

            for (int j = 0; j < menuRegister.get(i).getMenuArray().size(); j++) {

                if (menuRegister.get(i).getMenuArray().get(j).getType().equalsIgnoreCase(type)) {
                    System.out.println(menuRegister.get(i).getMenuArray().get(j));
                }
            }
            if (i == menuRegister.size()) {
                System.out.println("No dishes with type " + type + " were found.");
            }
        }
    }

    /**
     * Method used to search for menus with total price within the specified price-range.
     *
     * @param intervalStart Start of price-range.
     * @param intervalEnd   End of price-range.
     */
    public void findMenusByPrice(double intervalStart, double intervalEnd) {

        for (Menu menu : menuRegister) {
            double menuTotal = 0;
            for (int j = 0; j < menu.getMenuArray().size(); j++) {
                menuTotal += menu.getMenuArray().get(j).getPrice();
            }
            if (menuTotal > intervalStart && menuTotal < intervalEnd) {
                System.out.println("Total: " + menuTotal + ", " + menu);
            }
        }
    }

    /**
     * Custom toString()-method.
     * Used to print out each object of the class Menu in the menuregister.
     *
     * @return String of line-separated objects of the type Menu.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Menu menu : menuRegister) {
            result.append(menu.toString()).append("\n");
        }

        return result.toString();
    }
}
