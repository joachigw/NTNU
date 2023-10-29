package Oving_10_F.Restaurant;

/**
 * Dish class.
 * Defines a dish given by the specified parameters.
 */
public class Dish {

    private final String name;
    private final String type;
    private double price;

    /**
     * Class constructor.
     * Creates a new dish using the specified name, type and price.
     * Add
     *
     * @param name  Name of dish.
     * @param type  Type of dish (starter, main, dessert).
     * @param price Price of dish.
     */
    public Dish(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    /**
     * Method used to access the dish's name.
     *
     * @return String of the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to access the dish's type.
     *
     * @return String of the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method used to access the dish's price.
     *
     * @return double of the price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Method used to change the dish's price.
     *
     * @param price New price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Custom toString()-method.
     * Used to print each object of the class Dish in the ArrayList "dishes".
     *
     * @return String of line-separated objects of the class Dish.
     */
    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
