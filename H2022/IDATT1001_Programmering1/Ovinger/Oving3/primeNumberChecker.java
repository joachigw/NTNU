import java.util.*; //Brukes til å ta i bruk Scanner, som lar oss bruke input i terminalen

// import java.util.concurrent.TimeUnit; //Brukes til å lage en forsinkelse etter hver gjennomgang (bruker heller Thread.sleep her)

public class primeNumberChecker {

  public static void main(String[] args) throws InterruptedException {
    Scanner in = new Scanner(System.in); // Scanner som leser av raw input fra bruker
    while (true) { // While som alltid er true for å gjenta programmet
      try {
        System.out.print("Skriv inn et tall: ");
        long inputNumber = in.nextLong(); // Avlesning av input etter printen over
        if (inputNumber > 0) {
          if (inputNumber == 1 || inputNumber == 2) { // Kontrolleres fordi vi starter med i lik 2 lengre ned
            System.out.println(inputNumber + " er et primtall.");
          } else {
            for (int i = 2; i < inputNumber; i++) { // For-loop som kjører til i er 1 mindre enn inputNumber
              if ((inputNumber % i) == 0) { // Konkluderer med at tallet ikke er et primtall pga en faktor
                // i
                System.out.println(
                  inputNumber +
                  " er IKKE er primtall, fordi " +
                  i +
                  " er en faktor."
                );
                break; // Bryter for-loopen
              } else if (i == (inputNumber - 1)) { // Konkluderer med at tallet er et primtall
                System.out.println(inputNumber + " er et primtall.");
              }
            } // for
          }
        } else if (inputNumber == 0) { // Kontrollerer om input er lik 0
          System.out.println("0 er ikke et primtall...");
        } else if (inputNumber < 0) { // Kontrollerer om input er et negativt tall
          System.out.println("Vi betrakter ikke negative tall som primtall.");
        }
        System.out.println("\n"); // Lager et linjeskift
        Thread.sleep(250); // 1 sek pause før loopen starter på ny
      } catch (Exception e) { // try // Sier ifra dersom inputen ikke var et tall, og avslutten while-loopen
        System.out.println("Du må skrive inn et tall.");
        in.close();
        break;
      } // catch
    } // while
  } // class
} // main
