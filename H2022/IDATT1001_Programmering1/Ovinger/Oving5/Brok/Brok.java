package Oving_5_F.Brok;

import java.util.*;

public class Brok {

  public static void main(String[] args) {
    try (Scanner in = new Scanner(System.in)) {
      while (true) {
        System.out.println("\nBrøk 1:");
        System.out.print("Teller: ");
        double teller = in.nextInt();
        System.out.print("Nevner: ");
        double nevner = in.nextInt();

        BrokRegning brok = new BrokRegning(teller, nevner);

        System.out.print("\nBrøk 2:\nTeller: ");
        double teller2 = in.nextInt();
        System.out.print("Nevner: ");
        double nevner2 = in.nextInt();

        System.out.println(
            "\nVelg en regneoperasjon for brøkene (" +
                brok.teller +
                " / " +
                brok.nevner +
                ") og (" +
                teller2 +
                " / " +
                nevner2 +
                ")");
        System.out.println(
            "[1] Addisjon\n[2] Subtraksjon\n[3] Multiplikasjon\n[4] Divisjon");
        int operator = in.nextInt();

        if (operator == 1) {
          brok.getSum(teller2, nevner2);
          System.out.println(
              "(" +
                  teller +
                  "/" +
                  nevner +
                  ") + (" +
                  teller2 +
                  "/" +
                  nevner2 +
                  ") = " +
                  brok.result);
        } else if (operator == 2) {
          brok.getSub(teller2, nevner2);
          System.out.println(
              "(" +
                  teller +
                  "/" +
                  nevner +
                  ") - (" +
                  teller2 +
                  "/" +
                  nevner2 +
                  ") = " +
                  brok.result);
        } else if (operator == 3) {
          brok.getMulti(teller2, nevner2);
          System.out.println(
              "(" +
                  teller +
                  "/" +
                  nevner +
                  ") * (" +
                  teller2 +
                  "/" +
                  nevner2 +
                  ") = " +
                  brok.result);
        } else if (operator == 4) {
          brok.getDiv(teller2, nevner2);
          System.out.println(
              "(" +
                  teller +
                  "/" +
                  nevner +
                  ") : (" +
                  teller2 +
                  "/" +
                  nevner2 +
                  ") = " +
                  brok.result);
        } else {
          System.out.println(
              "Du må velge én av operatorene. Kjør programmet på nytt.");
        }
      } // while
      // catch (Exception e) {
      // System.out.println("Du skrev mest sannsynlig inn noe feil. Error: " + e);
      // } // catch
    } // try
  } // main
}
// class
