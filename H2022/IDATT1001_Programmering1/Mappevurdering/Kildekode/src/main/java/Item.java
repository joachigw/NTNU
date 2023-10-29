/**
 * Item class.
 * Creates different items based on the given parameters.
 */
public class Item
{
    private final String itemNumb;
    private String description;
    private int price;
    private final String brand;
    private final double weight;
    private final double length;
    private final double height;
    private final String color;
    private int inStock;
    private final int category;
    private final ItemCategory categoryName;
    private int originalPrice;
    private int discount;

    /**
     * Class constructor.
     * Creates an object "item" based on the parameters.
     *
     * @param itemNumb Unique identification number.
     * @param description Description.
     * @param price Price in NOK.
     * @param brand Name of brand.
     * @param weight Weight in kilograms.
     * @param length Length in cm.
     * @param height Height in cm.
     * @param color Name of color.
     * @param inStock Amount in stock.
     * @param category Category (1 (floor laminate), 2 (window), 3 (door) or 4 (lumber)).
     */
    public Item(String itemNumb, String description, int price, String brand, double weight,
                double length, double height, String color, int inStock, int category)
    {
        if (itemNumb.isBlank() || !itemNumb.matches("[æøåa-zA-Z0-9]+") || itemNumb.length() != 6)
        {
            throw new IllegalArgumentException("The item number must be a combination of "
                    + "6 letters/numbers.");

        } else if (description.isBlank() || !description.matches("[æøåa-zA-Z_.,!?:;\s]+"))
        {
            throw new IllegalArgumentException("The description cannot be blank, and must "
                    + "consist of latin characters only (æøå are also included).");

        } else if (price <= 0)
        {
            throw new IllegalArgumentException("The price can not be less than zero NOK.");

        } else if (!Integer.toString(price).matches("[0-9]*"))
        {
            throw new NumberFormatException("Hehe");
        } else if (brand.isBlank() || !brand.matches("[æøåa-zA-Z_.,!?:;\s]+"))
        {
            throw new IllegalArgumentException("The brand name cannot be blank, and must "
                    + "consist of latin characters only (æøå are also included).");

        } else if (weight <= 0)
        {
            throw new IllegalArgumentException("The weight can not be less than zero kg.");

        } else if (length <= 0)
        {
            throw new IllegalArgumentException("The length can not be less than zero cm.");

        } else if (height <= 0)
        {
            throw new IllegalArgumentException("The height can not be less than zero cm.");

        } else if (color.isBlank() || !color.matches("[æøåa-zA-Z.,!?:;\s]+"))
        {
            throw new IllegalArgumentException("The color cannot be blank, and must "
                    + "consist of latin characters only (æøå are also included).");

        } else if (inStock < 0)
        {
            throw new IllegalArgumentException("The in stock amount can not be lower than zero.");

        } else if (category < 1 || category > ItemCategory.values().length)
        {
            throw new IllegalArgumentException("The category must be a number between 1 and 4.");

        }

        this.itemNumb = itemNumb.toUpperCase();
        this.description = description;
        this.price = price;
        this.originalPrice = price;
        this.brand = brand;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.color = color;
        this.inStock = inStock;
        this.category = category;
        this.categoryName = ItemCategory.values()[category - 1];
    }

    /**
     * Method for returning the item's identification number.
     *
     * @return String of the number.
     */
    public String getItemNumb()
    {
        return itemNumb;
    }

    /**
     * Method for returning the item's description.
     *
     * @return String of the description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Method for returning the price.
     *
     * @return int of the price in NOK.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * Method for returning the item's brand.
     *
     * @return String of the brand.
     */
    public String getBrand()
    {
        return brand;
    }

    /**
     * Method for returning the item's weight.
     *
     * @return double of weight in kilograms.
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Method for returning the item's length.
     *
     * @return double of length in centimeters.
     */
    public double getLength()
    {
        return length;
    }

    /**
     * Method for returning the item's height.
     *
     * @return double of height in centimeters.
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Method for returning the item's color.
     *
     * @return String of the color.
     */
    public String getColor()
    {
        return color;
    }

    /**
     * Method for returning amount of item in stock.
     *
     * @return int of amount of item in stock.
     */
    public int getInStock()
    {
        return inStock;
    }

    /**
     * Method for returning category.
     *
     * @return int of category.
     */
    public int getCategory()
    {
        return category;
    }

    /**
     * Method for returning the name of the category.
     * @return String of the item's category name.
     */
    public String getCategoryName()
    {
        return categoryName.toString();
    }

    /**
     * Method for setting a new description for the item.
     *
     * @param newDescription New description.
     */
    public void setDescription(String newDescription)
    {
        if (newDescription.matches("[æøåa-zA-z0-9.,?:-_ ]+") && !newDescription.isBlank())
        {
            this.description = newDescription.trim();
        } else
        {
            throw new NumberFormatException();
        }
    }

    /**
     * Method for setting a new price for the item.
     *
     * @param price Price in NOK.
     *
     */
    public void setPrice(int price)
    {
        if (price > 0)
        {
            this.price = price;
            this.originalPrice = price;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method for adding a new amount of an item to the item's in stock.
     *
     * @param  amount in stock.
     *
     */
    public void increaseInStock(int amount)
    {
        if (amount > 0)
        {
            this.inStock = inStock + amount;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method for removing a specified amount from an item's stock.
     *
     * @param  amount in stock.
     *
     */
    public void decreaseInStock(int amount)
    {
        if (amount <= this.inStock && !(amount <= 0))
        {
            this.inStock = inStock - amount;
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method for adding a new discount to the item.
     * Always calculates the new price using the original price. This way,
     * the discounts cannot be stacked.
     *
     * @param discount New discount in percent.
     */
    public void addDiscount(int discount)
    {
        if (discount < 0 || discount > 100)
        {
            throw new IllegalArgumentException("The discount must be between 1 and 100 (precent)!");

        } else
        {
            this.price = (originalPrice - (originalPrice * discount / 100));

            this.discount = discount;
        }
    }

    /**
     * Method for removing existing discount.
     * Takes into account whether the original price was an odd or even number,
     * to handle rounding errors of integers.
     *
     * @return true if the discount was successfully removed, and false if it was not.
     */
    public boolean removeDiscount()
    {
        if (discount != 0)
        {
            this.price = this.originalPrice;
            this.discount = 0;
            return true;
        }
        return false;
    }

    /**
     * Custom toString() method for all attributes.
     *
     * @return String of all object attributes.
     */
    @Override
    public String toString()
    {
        return "Item{"
                +
                "number='" + itemNumb + '\''
                +
                ", description='" + description + '\''
                +
                ", price=" + price
                +
                ", brand='" + brand + '\''
                +
                ", weight=" + weight
                +
                ", length=" + length
                +
                ", height=" + height
                +
                ", color='" + color + '\''
                +
                ", inStock=" + inStock
                +
                ", category=" + category
                +
                ", category name=" + categoryName
                + '}';
    }
}