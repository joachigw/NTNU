package Oving_11;

import java.util.ArrayList;

/**
 * PropertyRegister class.
 * Represents a register capable of storing and manipulating multiple
 * properties.
 * <p>
 * The class has methods for adding properties to the register, removing
 * properties from the register,
 * returning the amount of properties currently registered, returning a copy of
 * the current register,
 * searching through the register for a specific property and returning the mean
 * area of the registered properties.
 */
public class PropertyRegister {

    private final ArrayList<Property> properties;

    /**
     * Class constructor.
     * Initializes the register for storing objects created from the class Property.
     */
    public PropertyRegister() {
        properties = new ArrayList<>();
    }

    /**
     * Method used to add a property to the register.
     *
     * @param property Object of the type Property.
     */
    public void addProperty(Property property) {
        properties.add(property);
    }

    /**
     * Method used to remove a property from the register.
     *
     * @param property Object of the type Property.
     */
    public void removeProperty(Property property) {
        properties.remove(property);
    }

    /**
     * Method used to return the amount of registered properties.
     *
     * @return int of the amount of registered properties.
     */
    public int getAmountOfProperties() {
        return properties.size();
    }

    /**
     * Method used to return a copy of the current register.
     *
     * @return ArrayList<Property> of the current register.
     */
    public ArrayList<Property> getProperties() {
        return new ArrayList<>(properties);
    }

    /**
     * Method used to search through the register for a specific property using the
     * municipality number,
     * lot number and section number. All these are used to search at the same time,
     * meaning they all have
     * to refer to the same property to be able to retrieve the property.
     * <p>
     * Returns null if no matching property was found.
     *
     * @param muniNumb Municipality number.
     * @param lotNumb  Lot number.
     * @param sectNumb Section number.
     * @return ArrayList<Property> of the
     */
    public Property getPropertyFromSearch(int muniNumb, int lotNumb, int sectNumb) {

        for (Property property : properties) {

            if (property.getMunicipalityNumber() == muniNumb &&
                    property.getLotNumber() == lotNumb &&
                    property.getSectionNumber() == sectNumb) {
                return property;
            }
        }
        return null;
    }

    /**
     * Method used to return an ArrayList with properties with the specified lot
     * number.
     *
     * @param lotNumb Lot number.
     * @return ArrayList containing the found properties, if any.
     */
    public ArrayList<Property> getPropertiesFromLotNumberSearch(int lotNumb) {
        ArrayList<Property> foundProperties = new ArrayList<>();

        for (Property property : properties) {
            if (property.getLotNumber() == lotNumb) {
                foundProperties.add(property);
            }
        }
        return foundProperties;
    }

    /**
     * Method used to return the mean area of all registered properties.
     *
     * @return double of the mean area.
     */
    public double getMeanArea() {
        double totalArea = 0;

        for (Property property : properties) {
            totalArea += property.getAreaSquared();
        }
        return (totalArea / properties.size());
    }

    /**
     * Method used to return a String of the register, with one property per line.
     *
     * @return String of the registered properties.
     */
    @Override
    public String toString() {
        if (!properties.isEmpty()) {
            StringBuilder formattedList = new StringBuilder();
            for (Property property : properties) {
                formattedList.append(property).append("\n");
            }
            return formattedList.toString();
        } else {
            return null;
        }
    }
}
