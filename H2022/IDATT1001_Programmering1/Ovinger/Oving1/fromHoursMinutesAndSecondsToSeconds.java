public class fromHoursMinutesAndSecondsToSeconds {

  public static void main(String[] args) {
    double hours = 1.5;
    double minutes = 30.0;
    double seconds = 120.0;

    double hoursToSeconds = hours * 3600; // Gjør om timer til sekunder
    double minutesToSeconds = minutes * 60; // Gjør om minutter til sekunder
    double totalAmountOfSeconds = hoursToSeconds + minutesToSeconds + seconds; // Summerer de omgjorte timene og
    // minuttene med sekundene

    System.out.println("Totalt antall sekunder: " + totalAmountOfSeconds); // Printer ut totalt antall sekunder
  }
}
