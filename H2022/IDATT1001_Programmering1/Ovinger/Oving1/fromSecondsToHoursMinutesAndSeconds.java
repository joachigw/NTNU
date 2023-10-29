import javax.swing.JOptionPane;

public class fromSecondsToHoursMinutesAndSeconds {

  public static void main(String[] args) {
    String secondsInput = JOptionPane.showInputDialog(
      "Angi et antall sekunder her: "
    );
    int secondsFromInput = Integer.parseInt(secondsInput); // Gjør om string til int
    if (secondsFromInput >= 0) {
      int hours = secondsFromInput / 3600; // antall timer blir input dividert med 3600
      secondsFromInput = secondsFromInput - (hours * 3600); // input mengden blir mindre mht antall timer
      int minutes = secondsFromInput / 60; // antall minutter blir resterende input etter timer dividert med 60
      secondsFromInput = secondsFromInput - (minutes * 60); // input mengden blir mindre mht antall timer+minutter
      // Skriver ut mengden sekunder i timer, minutter og sekunder dersom inputen er
      // over 0
      System.out.println(
        "Timer: " +
        hours +
        ", minutter: " +
        minutes +
        " og sekunder: " +
        secondsFromInput
      );
    } else {
      // Gir beskjed om at input må være et positivt tall
      System.out.println("Du må angi et positivt antall sekunder.");
    }
  }
}
