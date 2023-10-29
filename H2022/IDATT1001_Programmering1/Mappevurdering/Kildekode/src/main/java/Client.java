import java.util.Scanner;

//TODO Test for unexpected exceptions. Ensure that everything works.
/**
 * Client class for testing purposes.
 * Specifically designed for testing the ItemRegister class and its ArrayList.
 *
 */
public class Client
{
    static ItemRegister register = new ItemRegister(); // Initializes the item register
    static Scanner in;
    static boolean finished = false; // While-loop condition

    /**
     * main method for the class Client.
     *
     * @param args Included arguments.
     */
    public static void main(String[] args)
    {
        addDefaultItems(); // Adds a set of four pre-defined items
        System.out.println("Welcome to Smarthus AS!");

        while (!finished) // Loops the program as long as the user does not enter the "exit-number"
        {
            try
            {
                mainMenu();

            }  catch (Exception e)
            {
                System.out.println("The entered input was not valid. Please try again. " + e + ".");
            }

        }
    }

    /**
     * The Client class' main menu.
     * Presents the menu where the user can choose between and
     * use the available services.
     */
    public static void mainMenu()
    {
        in = new Scanner(System.in);
        System.out.println("""
                    
                    The following actions are available:
                    [1] Show all available items
                    [2] Search for an item (by item number and/or description)
                    [3] Register a new item
                    [4] Increase an item's in stock amount
                    [5] Decrease an item's in stock amount
                    [6] Delete an item from the storage
                    [7] Change discount, price and/or an item's description
                    [8] Exit""");

        int chosenAction = Integer.parseInt(in.nextLine());

        switch (chosenAction)
        {
            case 1 -> showAllItems();
            case 2 -> searchForAnItem();
            case 3 -> registerNewItemMenu();
            case 4 -> increaseInStock();
            case 5 -> decreaseInStock();
            case 6 -> removeItemFromRegister();
            case 7 -> editItemMenu();
            case 8 ->
            {
                System.out.println("Hope to see you again soon.");
                finished = true;
            }
            default -> System.out.println("You must choose a number between 1 and 8.");
        }

    }

    /**
     * Method for printing all the stored items in the "items"-register.
     */
    public static void showAllItems()
    {
        try
        {
            System.out.println("The following items are registered:");
            if (register.getAmountOfItems() <= 0)
            {
                System.out.println("""
                        There are no registered items. Please register a new one...
                        [1] Register a new item
                        [2] Exit""");

                int chosenAction = Integer.parseInt(in.nextLine());

                switch (chosenAction)
                {
                    case 1 ->
                    {
                        registerNewItemMenu();
                        mainMenu();
                    }
                    case 2 ->
                    {
                        System.out.println("Hope to see you again soon.");
                        finished = true;
                        System.exit(0);
                    }
                    default ->
                    {
                        System.out.println("You must choose either 1 or 2. Please try again.");
                        showAllItems();
                    }
                }

            } else
            {
                System.out.println(register);
            }
        } catch (Exception e)
        {
            System.out.println("The input was not valid. Please try again. " + e);
            showAllItems();
        }
    }

    /**
     * Prints a menu used to search for an item based on its item number or description.
     */
    public static void searchForAnItem()
    {
        System.out.println("""
                What parameter do you want to search by?
                [1] Item number
                [2] Item description
                [3] Back to the main menu
                """);
        int chosenSearchMethod = Integer.parseInt(in.nextLine());

        switch (chosenSearchMethod)
        {
            case 1 ->
            {
                System.out.println("Enter the item number here:");
                String foundItems = register.searchForItemByNumber(in.nextLine());

                if (foundItems != null)
                {
                    System.out.println("The following item/items was/were found:\n" + foundItems);
                } else
                {
                    System.out.println("The search yielded no results!");
                }
            }
            case 2 ->
            {
                System.out.println("Enter parts of the description here:");
                String foundItems = register.searchForItemByDescription(in.nextLine());

                if (foundItems != null)
                {
                    System.out.println("The following item/items was/were found:\n" + foundItems);
                } else
                {
                    System.out.println("The search yielded no results!");
                }

            }
            case 3 -> System.out.println("Taking you back to the main menu...");
            default ->
            {
                System.out.println("You must choose either 1 or 2. Please try again.");
                searchForAnItem();
            }
        }
    }

    /**
     * Method for printing an input-menu for registering a new item.
     */
    public static void registerNewItemMenu()
    {
        try
        {
            System.out.println("Item number (combination of 6 letters/numbers, "
                    + "eg.: ABC123, 123ABC, 1A2B3C...)");
            final String number = in.nextLine();

            System.out.println("Item description:");
            final String description = in.nextLine();

            System.out.println("Price (NOK):");
            final int price = Integer.parseInt(in.nextLine());

            System.out.println("Brand name:");
            final String brand = in.nextLine();

            System.out.println("Weight (in kilograms):");
            final double weight = Double.parseDouble(in.nextLine());

            System.out.println("Length (in centimeters):");
            final double length = Double.parseDouble(in.nextLine());

            System.out.println("Height (in centimeters):");
            final double height = Double.parseDouble(in.nextLine());

            System.out.println("Color:");
            final String color = in.nextLine();

            System.out.println("Amount in stock:");
            final int inStock = Integer.parseInt(in.nextLine());

            // StringBuilder used to store all item categories given in enum "ItemCategory"
            StringBuilder categories = new StringBuilder(); // String of all available categories
            String comma = "";
            for (ItemCategory category : ItemCategory.values())
            {
                // Ordinal is the index of the constant's placement in "ItemCategory"
                categories.append(comma)
                        .append(" ")
                        .append(category)
                        .append(" (")
                        // Ordinal is the index of the constant's placement in "ItemCategory"
                        .append(category.ordinal() + 1)
                        .append(")");
                comma = ",";
            }

            System.out.println("Category (" + categories + " ):");
            final int category = Integer.parseInt(in.nextLine());

            register.addNewItem(number, description, price, brand, weight, length,
                    height, color, inStock, category);
            System.out.println("The item " + number + " was successfully registered!");

        } catch (Exception e)
        {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * Method for increasing an item's in stock amount.
     */
    public static void increaseInStock()
    {
        try
        {
            System.out.println("Which item would you like to increase the in stock of?");
            showAllItems();

            int chosenItemIndex = Integer.parseInt(in.nextLine()) - 1;
            Item chosenItem = register.getItemFromIndex(chosenItemIndex);

            System.out.println("How many new items shall be added to the stock?");

            int amountToBeAdded = Integer.parseInt(in.nextLine());
            chosenItem.increaseInStock(amountToBeAdded);

            System.out.println("The stock amount of item " + chosenItem.getItemNumb() + " is now "
                    + chosenItem.getInStock() + ".");

        } catch (IllegalArgumentException e)
        {
            System.out.println("The amount to be added was either zero, less than zero, "
                    + "or not a valid and whole number. (Decimals are not allowed).");

        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("An item with this index does not exist. "
                                + "Only whole numbers are allowed.");
        }
    }

    /**
     * Method for decreasing an item's in stock amount.
     * The method will handle an IllegalArgumentException and tell the user what the program can
     * accept as an input.
     * The method will also handle an IndexOutOfBoundException and tell the user that the there
     * exists no items in the "items"-register with the specified index.
     */
    public static void decreaseInStock()
    {
        try
        {
            System.out.println("Which item would you like to decrease the in stock of?");
            showAllItems();

            int chosenItemIndex = Integer.parseInt(in.nextLine()) - 1;
            Item chosenItem = register.getItemFromIndex(chosenItemIndex);

            System.out.println("How many items shall be removed from the stock?");
            int amountToBeRemoved = Integer.parseInt(in.nextLine());

            chosenItem.decreaseInStock(amountToBeRemoved);

            System.out.println("The stock amount of item " + chosenItem.getItemNumb() + " is now "
                    + chosenItem.getInStock() + ".");

        } catch (IllegalArgumentException e)
        {
            System.out.println("The amount to be removed can not be larger than the "
                    + "current stock amount. The amount must also be a valid, positive "
                    + "and whole number. (Decimals are not allowed).");

        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("An item with this index does not exist. "
                                + "Only whole numbers are allowed.");
        }
    }

    /**
     * Method for removing an item from the "item"-register.
     */
    public static void removeItemFromRegister()
    {
        try
        {
            System.out.println("Which item would you like to delete?");
            showAllItems();
            int chosenItemIndex = Integer.parseInt(in.nextLine()) - 1;

            register.removeItem(register.getItemFromIndex(chosenItemIndex));

        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("An item with this index does not exist. "
                    + "Only whole numbers are allowed.");
        }
    }

    /**
     * Shows a menu for editing the item's discount, price or description.
     */
    public static void editItemMenu()
    {
        try
        {
            System.out.println("\nWhich item would you like to edit?");
            showAllItems();
            int chosenItemIndex = Integer.parseInt(in.nextLine()) - 1;
            Item chosenItem = register.getItemFromIndex(chosenItemIndex);

            String itemEditMenu = """
                                                    
                    What do you want to do with the item "$item"?
                    [1] Add/change/remove discount (in percent)
                    [2] Set new price (in NOK)
                    [3] Edit the item's description
                    [4] Back to the main menu
                    """.replace("$item", chosenItem.getItemNumb());
            System.out.println(itemEditMenu);
            int chosenItemEdit = Integer.parseInt(in.nextLine());

            // Executes a method based on the user's last input
            switch (chosenItemEdit)
            {
                case 1 -> editItemDiscount(chosenItem);
                case 2 -> editItemPrice(chosenItem);
                case 3 -> editItemDescription(chosenItem);
                case 4 -> System.out.println("Taking you back to the main menu...");
                default ->
                {
                    System.out.println("You must choose a number between 1 and 3.");
                    editItemMenu();
                }
            }
        } catch (IndexOutOfBoundsException e)
        {
            System.out.println("An item with this index does not exist. "
                    + "Only whole numbers are allowed.");
        }
    }

    /**
     * Method that adds a set of default "placeholder" items.
     */
    public static void addDefaultItems()
    {
        register.addNewItem("AAA000", "This item is useful.", 11,
                            "Moelven", 11, 22, 33, "Yellow", 10, 1);
        register.addNewItem("BBB111", "Wonderful mix of colors.", 100,
                            "Roteneng", 22, 412, 333, "BLue", 20, 2);
        register.addNewItem("CCC222", "Fantastic design.", 1321,
                            "Branaus", 22, 311, 381, "Green", 30, 3);
        register.addNewItem("DDD333", "Unbelievably sturdy.", 435,
                            "Helona", 41, 831, 837, "White", 40, 4);
        register.addNewItem("ABC123", "Unbelievably sturdy.", 546,
                "Kertok", 24, 81, 83, "Black", 200, 1);
    }

    /**
     * Method that prints a menu for editing an item's discount.
     *
     * @param chosenItem The chosen item that shall be edited.
     */
    public static void editItemDiscount(Item chosenItem)
    {
        try
        {
            System.out.println("""
                                        
                    [1] Add/change discount
                    [2] Remove discount
                    [3] Back to the main menu
                    """);
            int chosenDiscountAction = Integer.parseInt(in.nextLine());

            switch (chosenDiscountAction)
            {
                case 1 ->
                {
                    System.out.println("What shall the discount be set to?");
                    int newDiscount = Integer.parseInt(in.nextLine());
                    chosenItem.addDiscount(newDiscount);
                    System.out.println("The discount was successfully added.");
                }
                case 2 ->
                {
                    if (chosenItem.removeDiscount())
                    {
                        System.out.println("The discount was successfully removed.");
                    } else
                    {
                        System.out.println("The item does not currently have a discount.");
                        editItemDiscount(chosenItem);
                    }
                }
                case 3 -> System.out.println("Taking you back to the main menu...");
                default -> System.out.println("You must choose a number between 1 and 3.");
            }
        } catch (IllegalArgumentException e)
        {
            System.out.println("Exception: " + e);
            editItemDiscount(chosenItem);
        }
    }

    /**
     * Method for editing a specified item's price in NOK.
     *
     * @param chosenItem The item which price shall be changed.
     */
    public static void editItemPrice(Item chosenItem)
    {
        try
        {
            System.out.println("\nWhat shall the new price be? (in NOK)");

            int newPrice = Integer.parseInt(in.nextLine());
            chosenItem.setPrice(newPrice);

            System.out.println(chosenItem.getItemNumb() + " now costs "
                                + chosenItem.getPrice() + " NOK.");
        } catch (IllegalArgumentException e)
        {
            System.out.println("The new price you entered was either zero or lower, "
                    + "or not an integer. " + "Please try again.");
            editItemPrice(chosenItem);
        }

    }

    /**
     * Method for editing the specified item's description.
     *
     * @param chosenItem The item which description shall be edited.
     */
    public static void editItemDescription(Item chosenItem)
    {
        System.out.println("Current item description for " + chosenItem.getItemNumb() + ":\n'"
                + chosenItem.getDescription() + "'");

        System.out.println("What shall the new description say?");
        String newDescription = in.nextLine();
        chosenItem.setDescription(newDescription);

        System.out.println("The new description was successfully added.");
    }
}
