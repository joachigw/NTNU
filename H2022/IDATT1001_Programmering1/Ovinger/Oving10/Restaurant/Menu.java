package Oving_10_F.Restaurant;

import java.util.ArrayList;

/**
 * Menu class.
 * Defines a menu containing multiple dishes of different types.
 */
public class Menu {

    private final ArrayList<Dish> menu;

    /**
     * Class constructor.
     * Creates a menu using the specified objects of the class Dish.
     *
     * @param dish1 Dish number 1.
     * @param dish2 Dish number 2.
     * @param dish3 Dish number 3.
     */
    public Menu(Dish dish1, Dish dish2, Dish dish3) {
        menu = new ArrayList<>();
        menu.add(dish1);
        menu.add(dish2);
        menu.add(dish3);
    }

    /**
     * Method used to get the name of a specified object of the class Dish.
     *
     * @param dish Object of the class Dish.
     * @return String of the name.
     */
    public String getName(Dish dish) {
        return dish.getName();
    }

    /**
     * Method used to get access to a menu in the form of an ArrayList.
     *
     * @return ArrayList of the menu.
     */
    public ArrayList<Dish> getMenuArray() {
        return menu;
    }

    /**
     * Custom toString()-method.
     * Used to print a menu and its attributes.
     *
     * @return String of the menu and its attributes.
     */
    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                '}';
    }
}
