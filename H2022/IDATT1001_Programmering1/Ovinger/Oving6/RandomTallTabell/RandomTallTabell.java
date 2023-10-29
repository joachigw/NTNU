package Oving_6_F.RandomTallTabell;

import java.util.Scanner;

public class RandomTallTabell {

    public static void main(String[] args) {

        java.util.Random random = new java.util.Random();

        try (Scanner in = new Scanner(System.in)) {

            System.out.print("Hvor mange tilfeldige tall skal lagres?: ");
            int lengde = in.nextInt();

            if (lengde == 0) {
                System.out.println("Du må skrive et tall større enn 0");
            }

            int[] antall = new int[10];

            for (int i = 0; i < lengde; i++) {

                int tall = random.nextInt(10);

                antall[tall] += 1;

            }

            // Printer ut en oversikt over tallene i arrayet og frekvensen av hvert tall
            System.out.println("\nTabell med tall og deres frekvens:");
            for (int tall = 0; tall < 10; tall++) {
                System.out.println(
                        "[" + tall + "]: " + antall[tall] +
                                " (" + String.format("%.2f", ((double) antall[tall] / (double) lengde) * 100) + "%)");
            }

        } catch (Exception e) {
            System.out.println("Du fikk følgende feilmelding: " + e + ", vennligst start på nytt.");
        }

    }

}
