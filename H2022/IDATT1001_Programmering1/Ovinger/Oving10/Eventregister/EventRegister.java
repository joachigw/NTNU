package Oving_10_F.Eventregister;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * EventRegister class.
 * Used to store objects of the class Event in an ArrayList.
 * Includes methods for registering a new event, getting amount of events, getting Event-attributes and
 * the ArrayList itself.
 */
public class EventRegister {

    private final ArrayList<Event> events;

    /**
     * Class constructor.
     * Creates an empty ArrayList used to store objects of the class Event.
     */
    public EventRegister() {
        events = new ArrayList<>();
    }

    /**
     * Method used to register new events.
     *
     * @param name      Name of event.
     * @param place     Place of event.
     * @param organizer Event's organizer.
     * @param type      Type of event.
     * @param dateTime  Date and time of event (YYYYMMDDHHMM-format).
     */
    public void newEvent(String name, String place, String organizer, String type, long dateTime) {
        int idNumber = events.size();
        Event newEvent = new Event(idNumber, name, place, organizer, type, dateTime);
        events.add(newEvent);
    }

    /**
     * Method used to get the amount of registered events in the ArrayList "events".
     *
     * @return int of the size of the ArrayList "events".
     */
    public int getAmountOfEvents() {
        return events.size();
    }

    /**
     * Method used to get all the registered date-times.
     *
     * @return String of (line-separated) date-times.
     */
    public String getDateTime() {

        HashSet<String> dateTimes = new HashSet<>();

        for (Event event : events) {
            dateTimes.add(event.getDateTime() + "\n");
        }

        return String.join("", dateTimes);
    }

    /**
     * Method used to show all the events' registered places.
     *
     * @return String of (line-separated) places.
     */
    public String getPlaces() {

        HashSet<String> places = new HashSet<>();

        for (Event event : events) {
            places.add(event.getPlace() + "\n");
        }

        return String.join("", places);

    }

    /**
     * Method used to show all registered events (including their attributes).
     *
     * @return ArrayList of objects of the class Event.
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Custom toString()-method.
     * Used to show the ArrayList "events" from this class.
     *
     * @return String of the ArrayList "events".
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Event event : events) {
            result.append(event).append("\n");
        }
        return result.toString();
    }
}
