package Oving_10_F.Eventregister;

/**
 * Class for events.
 * Includes methods for getting class-object attributes, and setting places and date-times.
 */
public class Event {

    private final int idNumber;
    private final String name, organizer, type;
    private String place;
    private long dateTime;

    /**
     * Class constructor.
     * Creates an event based on the specified parameters.
     *
     * @param idNumber  Unique ID.
     * @param name      Name of event.
     * @param place     Place of event.
     * @param organizer Event's organizer.
     * @param type      Type of event.
     * @param dateTime  Date-time of event (YYYYMMDDHHMM).
     */
    public Event(int idNumber, String name, String place, String organizer, String type, long dateTime) {
        this.idNumber = idNumber;
        this.name = name;
        this.place = place;
        this.organizer = organizer;
        this.type = type;
        this.dateTime = dateTime;
    }

    /**
     * Method used to get the ID of the event.
     *
     * @return int of the ID.
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * Method used to get the name of the event.
     *
     * @return String of the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to get the place of the event.
     *
     * @return String of the place.
     */
    public String getPlace() {
        return place;
    }

    /**
     * Method used to get the name of the organizer of the event.
     *
     * @return String of the organizer's name.
     */
    public String getOrganizer() {
        return organizer;
    }

    /**
     * Method used to get the type of the event.
     *
     * @return String of the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method used to get the date-time of the event.
     *
     * @return long of the date-time.
     */
    public long getDateTime() {
        return dateTime;
    }

    /**
     * Method used to change the place of the event.
     *
     * @param place New place.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Method used to change the date-time of the event.
     *
     * @param dateTime New date-time.
     */
    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Custom toString()-method used to print/show the Event-object.
     *
     * @return String of the object and its attributes.
     */
    public String toString() {
        return "Event{" +
                "idNumber=" + idNumber +
                ", name='" + name + '\'' +
                ", organizer='" + organizer + '\'' +
                ", type='" + type + '\'' +
                ", place='" + place + '\'' +
                ", dateTime=" + dateTime +
                "}\n";
    }
}
