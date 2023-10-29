package Oving_11;

import java.util.Scanner;


/**
 * Client class.
 * Represents a user.
 */
public class Client {

    static PropertyRegister propReg = new PropertyRegister();
    static Scanner in;


    /**
     * The main method of the class Client.
     *
     * @param args Given arguments.
     */
    public static void main(String[] args) {

        addDefaultProperties();

        try {
            boolean programFinished = false;

            while (!programFinished) {
                in = new Scanner(System.in);

                System.out.println("""

                        Welcome! The following services are available:

                        [1] Register new property
                        [2] Show all registered properties
                        [3] Search for a property (municipal, lot and section number)
                        [4] Calculate the properties' mean area (m^2)
                        [5] Exit""");
                int chosenService = in.nextInt();
                in.nextLine();

                switch (chosenService) {
                    case 1 -> registerNewProperty();
                    case 2 -> showAllProperties();
                    case 3 -> System.out.println(searchForProperty());
                    case 4 -> calculateMeanArea();
                    case 5 -> {
                        programFinished = true;
                        System.out.println("Have a nice day!");
                    }
                    default -> System.out.println("You must choose one of the available services (number between" +
                            " 1 and 5)");
                }

            }
        } catch (Exception e) {
            System.out.println("Exception: " + e + ". Please enter a valid input.");
            main(null);
        }
    }


    /**
     * Method used to register a new property by taking inputs from the user.
     */
    public static void registerNewProperty() {

        // Initialization of the attributes needed to create a Property
        Scanner inNumb = new Scanner(System.in);
        Scanner inStr = new Scanner(System.in);
        String municipalityNameInput;
        int municipalityNumberInput;
        int lotNumberInput;
        int sectionNumberInput;
        String sectionNameInput;
        double areaInput;
        String nameOfOwner;

        System.out.println("Municipality name:");
        municipalityNameInput = inStr.nextLine();
        if (municipalityNameInput == null || municipalityNameInput.isBlank() ||
                !municipalityNameInput.matches("[æøåÆØÅa-zA-z]+")) {
            System.out.println("The municipality name cannot be empty, and must consist of latin characters only " +
                    "(including æøå)! Please try again.");
            registerNewProperty();
        }

        System.out.println("Municipality number:");
        municipalityNumberInput = inNumb.nextInt();
        if (municipalityNumberInput < 101 || municipalityNumberInput > 5054) {
            System.out.println("The municipality number must be a number, and be between 101 and 5054! " +
                    "Please try again.");
            registerNewProperty();
        }

        System.out.println("Lot number:");
        lotNumberInput = inNumb.nextInt();
        if (lotNumberInput <= 0) {
            System.out.println("The lot number must be a number, an integer, and above 0! " +
                    "Please try again.");
            registerNewProperty();
        }

        System.out.println("Section number:");
        sectionNumberInput = inNumb.nextInt();
        if (sectionNumberInput <= 0) {
            System.out.println("The municipality number must be a number, and be between 101 and 5054! " +
                    "Please try again.");
            registerNewProperty();
        }

        System.out.println("Section name:");
        sectionNameInput = inStr.nextLine();
        if (sectionNameInput == null || sectionNameInput.isBlank() ||
                !sectionNameInput.matches("[æøåÆØÅa-zA-z]+")) {
            System.out.println("The section name cannot be empty, and must consist of latin characters only " +
                    "(including æøå)! Please try again.");
            registerNewProperty();
        }

        System.out.println("Area (m^2):");
        areaInput = inNumb.nextDouble();
        if (areaInput <= 0) {
            System.out.println("The area must be a number, and above 0! Please try again.");
            registerNewProperty();
        }

        System.out.println("Name of owner:");
        nameOfOwner = inStr.nextLine();
        if (nameOfOwner == null || nameOfOwner.isBlank() ||
                !nameOfOwner.matches("[æøåÆØÅa-zA-z]+")) {
            System.out.println("The owner's name cannot be empty, and must consist of latin characters only " +
                    "(including æøå)! Please try again.");
            registerNewProperty();
        }

        // Checks whether the user has given the property a section name or not, and
        // calls the correct constructor
        if (sectionNameInput == null || sectionNameInput.isBlank()) {
            propReg.addProperty(new Property(municipalityNameInput, municipalityNumberInput, lotNumberInput,
                    sectionNumberInput, areaInput, nameOfOwner));
        } else {
            propReg.addProperty(new Property(municipalityNameInput, municipalityNumberInput, lotNumberInput,
                    sectionNumberInput, sectionNameInput, areaInput, nameOfOwner));
        }

    }


    /**
     * Method used to show all the registered properties.
     */
    public static void showAllProperties() {
        if (propReg.toString() != null) {
            System.out.println("The following properties are registered:\n" + propReg);
        } else {
            System.out.println("No properties have been added to the register yet!");
        }
    }


    /**
     * Method used to take user inputs, and use the PropertyRegister class to search
     * for a property with the
     * specified municipality number, lot number and section number.
     *
     * @return String of the search result.
     */
    public static String searchForProperty() {
        Scanner in = new Scanner(System.in);

        System.out.print("Municipal number: ");
        boolean muniNumbInt = in.hasNextInt();
        int searchedMunicipalityNumber = in.nextInt();
        if (!muniNumbInt || searchedMunicipalityNumber <= 0) {
            System.out.println("The municipal number must be an integer, and above 0! Please try again.");
            searchForProperty();
        }

        System.out.print("Lot number: ");
        boolean lotNumberInt = in.hasNextInt();
        int searchedLotNumb = in.nextInt();
        if (!lotNumberInt || searchedLotNumb <= 0) {
            System.out.println("The lot number must be an integer, and above 0! Please try again.");
            searchForProperty();
        }

        System.out.print("Section number: ");
        boolean sectNumbInt = in.hasNextInt();
        int searchedSectionNumber = in.nextInt();
        if (!sectNumbInt || searchedSectionNumber <= 0) {
            System.out.println("The section number must be an integer, and above 0! Please try again.");
            searchForProperty();
        }

        Property searchResults = propReg.getPropertyFromSearch(searchedMunicipalityNumber,
                searchedLotNumb, searchedSectionNumber);

        if (searchResults == null) {
            return "The search yielded no results!";
        }
        return "The following property was found:\n" + searchResults;
    }


    /**
     * Method used to show the mean area of the registered properties, using the
     * PropertyRegister class
     * to retrieve the mean area.
     **/
    public static void calculateMeanArea() {
        System.out.println("The mean area of all registered properties: " + propReg.getMeanArea() + " m^2.");
    }


    /**
     * Method used to add default/placeholder properties to the property register.
     */
    public static void addDefaultProperties() {
        propReg.addProperty(new Property("Gloppen", 1445, 77, 631, 1017.6, "Jens Olsen"));
        propReg.addProperty(new Property("Gloppen", 1445, 77, 131, "Syningom", 661.3,
                "Nicolay Madsen"));
        propReg.addProperty(new Property("Gloppen", 1445, 75, 19, "Fugletun", 650.6,
                "Evilyn Jensen"));
        propReg.addProperty(new Property("Gloppen", 1445, 74, 188, 1457.2, "Karl Ove Bråten"));
        propReg.addProperty(new Property("Gloppen", 1445, 69, 47, "Høiberg", 1339.4,
                "Elsa Indregård"));
    }
}
