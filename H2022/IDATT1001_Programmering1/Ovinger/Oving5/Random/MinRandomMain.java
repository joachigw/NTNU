package Oving_5_F.Random;

import java.util.*;

public class MinRandomMain {

    public static void main(String[] args) {
        // Det tilfeldige tallet blir et objekt
        MinRandom randomTallGenerert = new MinRandom();

        // Try-catch for å kunne fange ugyldige inputs (som bokstaver eller tegn)
        try (Scanner in = new Scanner(System.in)) {
            while (true) {
                // Ber bruker velge mellom heltall og desimaltall i intervallet
                System.out.print(
                        "\nSkal intervallet være i...\n[1] Heltall\neller\n[2] Desimaltall\n");
                int valgType = in.nextInt();

                // Kjøres dersom bruker vil ha intervall i heltallR
                if (valgType == 1) {
                    // Bruker velger nedre grense
                    System.out.print("Velg nedre grense i intervallet: ");
                    int nedre = in.nextInt();

                    // Bruker velger øvre grense
                    System.out.print("Velg nedre grense i intervallet: ");
                    int ovre = in.nextInt();

                    for (int i = 0; i < 100; i++) {
                        System.out.println(randomTallGenerert.nesteHeltall(nedre, ovre));
                    } // for
                } // if
                // Kjøres dersom bruker vil ha intervall i desimaltall
                else if (valgType == 2) {
                    // Bruker velger nedre grense
                    System.out.print("Velg nedre grense i intervallet: ");
                    double nedre = in.nextDouble();

                    // Bruker velger øvre grense
                    System.out.print("Velg nedre grense i intervallet: ");
                    double ovre = in.nextDouble();

                    // Skriver ut 100 tall innenfor intervallet
                    for (int i = 0; i < 100; i++) {
                        System.out.println(
                                randomTallGenerert.nesteDesimaltall(nedre, ovre));
                    }
                    // for
                } // else if
                else {
                    System.out.println("Du må velge enten 1 eller 2.");
                    main(null);
                } // else

                Thread.sleep(1000);
            } // while
        } catch (Exception e) { // Fanger feilmeldinger fra f.eks. ugyldig input // try
            System.out.println(
                    "Du fikk følgende feilmelding: " + e + ", vennligst start på nytt.");
        } // catch
    } // main
}// class
