package Oving_11;

/**
 * Property class.
 * Represents a single property based on the specified parameters.
 * The property can exist without having a section name.
 * The class has get- and set-methods for all attributes, in case the property is created with no.
 */
public class Property {

    private String municipalityName, sectionName, nameOwner;
    private final int municipalityNumber, lotNumber, sectionNumber;
    private double areaSquared;


    /**
     * Class constructor for properties with all parameters given.
     *
     * @param municipalityName   Municipality's name.
     * @param municipalityNumber Municipality's number.
     * @param lotNumber          Lot number.
     * @param sectionNumber      Section number.
     * @param sectionName        Section name.
     * @param areaSquared        Area squared (m2).
     * @param nameOwner          Name of the owner.
     */
    public Property(String municipalityName, int municipalityNumber, int lotNumber, int sectionNumber,
                    String sectionName, double areaSquared, String nameOwner) {

        this.municipalityName = municipalityName;
        this.municipalityNumber = municipalityNumber;
        this.lotNumber = lotNumber;
        this.sectionNumber = sectionNumber;
        this.sectionName = sectionName;
        this.areaSquared = areaSquared;
        this.nameOwner = nameOwner;
    }


    /**
     * Class constructor for properties without a section name.
     *
     * @param municipalityName   Municipality's name.
     * @param municipalityNumber Municipality's number.
     * @param lotNumber          Lot number.
     * @param sectionNumber      Section number.
     * @param areaSquared        Area squared (m2).
     * @param nameOwner          Name of the owner.
     */
    public Property(String municipalityName, int municipalityNumber, int lotNumber, int sectionNumber,
                    double areaSquared, String nameOwner) {
        this.municipalityName = municipalityName;
        this.municipalityNumber = municipalityNumber;
        this.lotNumber = lotNumber;
        this.sectionNumber = sectionNumber;
        this.sectionName = "";
        this.areaSquared = areaSquared;
        this.nameOwner = nameOwner;
    }


    /**
     * Method for retrieving the property's municipality name.
     *
     * @return String of the municipality name.
     */
    public String getMunicipalityName() {
        return municipalityName;
    }


    /**
     * Method for retrieving the property's municipality number.
     *
     * @return int of the municipality number.
     */
    public int getMunicipalityNumber() {
        return municipalityNumber;
    }


    /**
     * Method for retrieving the property's lot number.
     *
     * @return int of the lot number.
     */
    public int getLotNumber() {
        return lotNumber;
    }


    /**
     * Method for retrieving the property's section number.
     *
     * @return int of the section number.
     */
    public int getSectionNumber() {
        return sectionNumber;
    }


    /**
     * Method for retrieving the property's section name.
     *
     * @return String of the section name.
     */
    public String getSectionName() {
        return sectionName;
    }


    /**
     * Method for retrieving the property's area squared (m2).
     *
     * @return double of the property's area.
     */
    public double getAreaSquared() {
        return areaSquared;
    }


    /**
     * Method for retrieving the name of the property's owner.
     *
     * @return String of the name of the owner.
     */
    public String getNameOwner() {
        return nameOwner;
    }


    /**
     * Method used to return a unique ID from the municipality number, lot number and section number.
     *
     * @return String of the numbers concatenated.
     */
    public String getPropertyID() {
        return this.municipalityName + this.lotNumber + this.sectionNumber;
    }


    /**
     * Method for replacing the property's municipality name (in case of a merger or split).
     *
     * @param municipalityName New municipality name.
     */
    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }


    /**
     * Method for replacing the property's section name.
     *
     * @param sectionName New section name.
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }


    /**
     * Method for replacing the property's area (in case the property shrinks or enlarges).
     *
     * @param areaSquared New area.
     */
    public void setAreaSquared(double areaSquared) {
        this.areaSquared = areaSquared;
    }


    /**
     * Method for replacing the name of the property's owner.
     *
     * @param nameOwner Name of the new owner.
     */
    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }


    /**
     * Method for returning the property and its attributes.
     *
     * @return String of the property's attributes.
     */
    @Override
    public String toString() {
        return "Property{" +
                "municipalityName='" + municipalityName + '\'' +
                ", municipalityNumber=" + municipalityNumber +
                ", lotNumber=" + lotNumber +
                ", sectionNumber=" + sectionNumber +
                ", sectionName='" + sectionName + '\'' +
                ", areaSquared=" + areaSquared +
                ", nameOwner='" + nameOwner + '\'' +
                "}\n";
    }
}
