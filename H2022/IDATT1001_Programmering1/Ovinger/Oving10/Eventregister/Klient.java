package Oving_10_F.Eventregister;

import java.util.*;

/**
 * Client class.
 * Acts as the user-interface for the class Event and EventRegister.
 */
public class Klient {

    /**
     * main method of the class Klient.
     *
     * @param args Included arguments.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try {
            EventRegister reg = new EventRegister();

            reg.newEvent("Weekend", "Oslo", "orgOSLO", "Music Festival", 201910100000L);
            reg.newEvent("Tons of Rock", "Trondheim", "orgTRD", "Music Festival", 202210102100L);
            reg.newEvent("Festivalen", "Oslo", "orgOSLO", "Theater", 202210082100L);
            reg.newEvent("Bergensfestivalen", "Trondheim", "orgTRD", "Theater", 202210082100L);
            reg.newEvent("Den etter bergensfestivalen", "Trondheim", "orgTRD", "Theater", 202210082000L);
            reg.newEvent("Stavern", "Bergen", "orgBERG", "Theater", 202210082100L);
            reg.newEvent("Den etter stavern", "Bergen", "orgBERG", "Artigfest", 202210082100L);
            reg.newEvent("Hallelujah", "Ålesund", "orgÅL", "Theater", 202210082100L);

            while (true) {
                System.out.println("""
                                            
                        Welcome. Please choose one of the following services:
                                        
                        [1] Register a new event
                        [2] Search for events by place
                        [3] Search for events by date
                        [4] Get a list of events in a given interval
                        [5] Get a list of all registered events (sorted by place - type - date and time)
                        [6] Print all events
                        [7] Exit this program""");
                int chosenService = in.nextInt();

                switch (chosenService) {
                    case 1 -> registerNewEvent(reg);
                    case 2 -> searchEventPlace(reg);
                    case 3 -> searchEventDateTime(reg);
                    case 4 -> eventsInterval(reg);
                    case 5 -> eventsSortedList(reg);
                    case 6 -> System.out.println(reg);
                    case 7 -> {
                        System.out.println("Hope to see you again soon.");
                        System.exit(0);
                    }
                    default -> System.out.println("You must choose one of the available services. Try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e + ". Please restart the program.");
        }
    }

    /**
     * Method for registering a new Event.
     *
     * @param reg The object "reg" of the class EventRegister, for accessing its class-methods.
     */
    public static void registerNewEvent(EventRegister reg) {
        Scanner in = new Scanner(System.in);
        ArrayList<Event> events = reg.getEvents();

        int idNumber = reg.getAmountOfEvents();
        System.out.println("Name of the event:");
        String name = in.next();
        System.out.println("Where the event will take place:");
        String place = in.next();
        System.out.println("Name of organizer:");
        String organizer = in.next();
        System.out.println("Type of event:");
        String type = in.next();
        System.out.println("Date and time of the event [YYYYMMDDHHMM]-format:");
        long dateTime = in.nextLong();

        reg.newEvent(name, place, organizer, type, dateTime);
        // System.out.println(newEvent);
    }

    /**
     * Method for showing the currently registered datetimes.
     * Does not show duplicates.
     *
     * @param reg The object "reg" of the class EventRegister, for accessing its class-methods.
     */
    public static void searchEventDateTime(EventRegister reg) {
        Scanner in = new Scanner(System.in);

        System.out.println("Search for a date-time here: (YYYYMMDDHHMM)");
        long searchedDateTime = in.nextLong();

        boolean searchHadResults = false;
        for (Event event : reg.getEvents()) {
            if (searchedDateTime == event.getDateTime()) {
                System.out.println(event);
                searchHadResults = true;
            }
        }

        if (!searchHadResults) {
            System.out.println("No results were found for the date-time '" + searchedDateTime + "'.");
        }

    }

    /**
     * Method for showing the currently registered places.
     * Does not show duplicates.
     *
     * @param reg The object "reg" of the class EventRegister, for accessing its class-methods.
     */
    public static void searchEventPlace(EventRegister reg) {
        Scanner in = new Scanner(System.in);

        System.out.println("Search for a place here:");
        String searchedPlace = in.nextLine();

        boolean searchHadResults = false;

        for (Event event : reg.getEvents()) {
            if (searchedPlace.equalsIgnoreCase(event.getPlace())) {
                System.out.println(event);
                searchHadResults = true;
            }
        }

        if (!searchHadResults) {
            System.out.println("No results were found for the place '" + searchedPlace + "'.");
        }

    }

    /**
     * Method for getting an interval from user input, and printing the events in the specified interval.
     *
     * @param reg The object "reg" of the class EventRegister, for accessing its class-methods.
     */
    public static void eventsInterval(EventRegister reg) {
        Scanner in = new Scanner(System.in);
        ArrayList<Event> events = reg.getEvents();

        System.out.println("Interval start (YYYYMMDDHHMM):");
        long intervalStart = in.nextLong();

        System.out.println("Interval end (YYYYMMDDHHMM):");
        long intervalEnd = in.nextLong();

        ArrayList<Event> eventsInInterval = new ArrayList<>();

        for (int i = 0; i < reg.getAmountOfEvents(); i++) {
            if (events.get(i).getDateTime() > intervalStart && events.get(i).getDateTime() < intervalEnd) {
                eventsInInterval.add(events.get(i));
            }
        }
        System.out.println(eventsInInterval);
    }

    /**
     * Method for sorting the ArrayList in EventRegister.
     * Sorted by place, type and then date-time.
     *
     * @param reg The object "reg" of the class EventRegister, for accessing its class-methods.
     */
    public static void eventsSortedList(EventRegister reg) {
        System.out.println("The following events are registered (sorted by place -> type -> date-time)");
        ArrayList<Event> events = reg.getEvents();
        events.sort(Comparator
                .comparing(Event::getPlace, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Event::getType, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Event::getDateTime));

        System.out.println(reg);
    }
}
