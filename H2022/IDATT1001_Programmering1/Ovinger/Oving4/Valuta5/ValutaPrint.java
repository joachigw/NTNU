package Oving_4_F.Valuta5;

import java.util.*;

class ValutaPrint {

  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      while (true) {
        String[] valutaNavn = { "USD", "EUR", "SEK" };
        double[] valutaValue = { 10, 10.5, 0.9 };
        String[] tilFra = { "til", "fra" };

        // Brukeren velger én av valutaene, eller å avslutte
        System.out.print(
            "\nVelg én av følgende valutaer:\n[1] USD\n[2] EUR\n[3] SEK\n[4] Avslutt\nSkriv valget ditt her: ");
        int nameInput = in.nextInt();

        // Inntreffer dersom brukeren ønsker å avslutte
        if (nameInput == 4) {
          System.out.println("\nTakk for at du tok i bruk valutakalkulatoren.");
          System.exit(0);
        }

        // Brukeren velger å enten gjøre om til eller fra NOK
        System.out.print(
            "\nVelg enten til eller fra NOK:\n[1] Til\n[2] Fra\nSkriv valget ditt her: ");
        int toFromInput = in.nextInt();

        // Brukeren skriver inn ønsket mengde som skal konverteres
        System.out.print("\nSkriv inn mengden som skal konverteres: ");
        double amountInput = in.nextDouble();

        // Oppretter et objekt på bakgrunn av valgene brukeren tar
        Valuta valgtValuta = new Valuta(
            valutaNavn[nameInput - 1],
            valutaValue[nameInput - 1],
            tilFra[toFromInput - 1],
            amountInput);

        // Kjører dersom det er valgt å gjøre om fra valutaen TIL NOK
        if (toFromInput == 1) {
          // Gir answer i objektet en ny verdi etter kalkulasjonen
          valgtValuta.calculateTo(amountInput);
          // Printer ut resultatet, med kvittering på valgene
          System.out.println(
              "Du valgte å konvertere " +
                  valgtValuta.amount +
                  " " +
                  valgtValuta.valutaName +
                  " til NOK.\n" +
                  valgtValuta.amount +
                  " " +
                  valgtValuta.valutaName +
                  " er det samme som " +
                  valgtValuta.answer +
                  " NOK.");
        } // if
        // Kjører dersom det er valgt å gjøres om til valutaen FRA NOK
        else if (toFromInput == 2) {
          // Gir answer i objektet en ny verdi etter kalkulasjonen
          valgtValuta.calculateFrom(amountInput);
          // Printer ut resultatet, med kvittering på valgene
          System.out.println(
              "Du valgte å konvertere " +
                  valgtValuta.amount +
                  " NOK til " +
                  valgtValuta.valutaName +
                  ".\n" +
                  valgtValuta.amount +
                  " NOK det samme som " +
                  valgtValuta.answer +
                  " " +
                  valgtValuta.valutaName +
                  ".");
        } // else if
      } // while
    } catch (Exception e) { // try
      System.out.println("Du må skrive inn et tall som hører til valgene!");
    } // catch
  } // main
} // class
