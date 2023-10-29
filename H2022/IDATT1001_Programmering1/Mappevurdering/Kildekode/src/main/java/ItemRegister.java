import java.util.ArrayList;

/**
 * Item register class.
 * Stores different objects created from the class "Item".
 */
public class ItemRegister
{
    private final ArrayList<Item> items;

    private final ItemCategory[] categories = ItemCategory.values(); //List of the categories


    /**
     * Class constructor.
     * Initializes the ArrayList containing objects of the class "Item".
     */
    public ItemRegister()
    {
        items = new ArrayList<>();
    } //Initializes the list used to store the registered items

    /**
     * Method used to access the length of the items ArrayList.
     * Returns the amount of items registered.
     *
     * @return int of the amount of items.
     */
    public int getAmountOfItems()
    {
        return items.size();
    }

    /**
     * Method for generating a random alphanumerical combination, used as an ID for an item.
     * It will repeat until it generates an ID which does not already exist.
     *
     * @param numb The initialized item number.
     * @return String of the alphanumerical combination.
     */
    public boolean checkItemId(String numb)
    {
        for (Item item : items)
        {
            if (item.getItemNumb().equals(numb))
            {
                return false;
            }
        }

        return true;
    }


    /**
     * Adds an item to the register, and checks whether the new item number already exists or not.
     * Returns true if the item number does not already exist.
     * Returns false if the item number already exists in an already registered item.

     * @param number Item number.
     * @param description Description.
     * @param price Price.
     * @param brand Brand.
     * @param weight Weight.
     * @param length Length.
     * @param height Height.
     * @param color Color.
     * @param inStock Amount in stock.
     * @param category Category (1 (floor laminate), 2 (window), 3 (door) or 4 (lumber)).
     */
    public void addNewItem(String number, String description, int price, String brand,
                           double weight, double length, double height, String color,
                           int inStock, int category)
    {
        if (!checkItemId(number))
        {
            throw new IllegalArgumentException("This item number already exists.");
        }

        items.add(new Item(number, description, price, brand, weight, length,
                height, color, inStock, category));
    }

    /**
     * Removes an item if the specified item exists in the "item"-register.
     *
     * @param itemToBeRemoved Item to be removed.
     */
    public void removeItem(Item itemToBeRemoved)
    {
        if (!items.contains(itemToBeRemoved))
        {
            throw new IndexOutOfBoundsException();
        }

        items.remove(itemToBeRemoved);
    }

    /**
     * Searches for an item with an item number mathing the specified item number.
     *
     * @param itemNumb Item number.
     * @return Item if the search was successful, and null if nothing was found.
     */
    public String searchForItemByNumber(String itemNumb)
    {

        StringBuilder foundItems = new StringBuilder();

        for (Item item : items)
        {
            if (item.getItemNumb().toUpperCase().contains(itemNumb.toUpperCase()))
            {
                foundItems.append(item).append("\n");
            }
        }

        if (foundItems.length() == 0)
        {
            return null;
        } else
        {
            return foundItems.toString();
        }
    }

    /**
     * Searches through the "items"-list and checks wether there exists an item containing
     * any of the specified text.
     *
     * @param itemDesc Item description.
     * @return Matching Item if the search was successful, and null if nothing was found.
     */
    public String searchForItemByDescription(String itemDesc)
    {

        StringBuilder foundItems = new StringBuilder();

        for (Item item : items)
        {
            if (item.getDescription().toUpperCase().contains(itemDesc.toUpperCase()))
            {
                foundItems.append(item).append("\n");
            }
        }

        if (foundItems.length() == 0)
        {
            return null;
        } else
        {
            return foundItems.toString();
        }
    }

    /**
     * Returns a copy of the current item register.
     *
     * @param index The item's index in the register list.
     * @return The item with the specified index.
     */
    public Item getItemFromIndex(int index)
    {
        return items.get(index);
    }

    /**
     * Custom toString()-method.
     * Used to print each item in the "items" ArrayList on separate lines.
     *
     * @return String of items.
     */
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < items.size(); i++)
        {
            result.append("[")
                    .append(i + 1)
                    .append("]")
                    .append("Item ")
                    .append(i + 1)
                    .append(": ")
                    .append(items.get(i))
                    .append("\n");
        }

        return result.toString();
    }

}
