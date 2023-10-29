package Oving_6_F.Matrise2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MatriseKlient {
    public static void main(String[] args) {
        try {

            // Klargjør Scanner og definerer klassen Random med aliaset 'random'
            Scanner in = new Scanner(System.in);
            Random random = new Random();


            // Initialiserer variablene til matrisene
            int raderA;
            int kolonnerA;
            int raderB = 0;
            int kolonnerB = 0;


            // Ber om dimensjonene til Matrise A
            System.out.println("MATRISE A\nAntall rader: ");
            raderA = in.nextInt();
            System.out.println("Antall kolonner:");
            kolonnerA = in.nextInt();


            // Klienten velger ønsket operasjon/tjeneste som skal utføres
            System.out.println("Velg én:" +
                    "\n[1] Addisjon med annen matrise" +
                    "\n[2] Multiplikasjon med annen matrise" +
                    "\n[3] Transponering av Matrise A" +
                    "\n[4] Avslutt");
            int operasjonsValg = in.nextInt();


            // Oppretter Matrise B hvis det skal adderes eller multipliseres
            if (operasjonsValg == 1 || operasjonsValg == 2) {

                System.out.println("MATRISE B\nAntall rader:");
                raderB = in.nextInt();
                System.out.println("Antall kolonner:");
                kolonnerB = in.nextInt();

            } // if addisjon eller multiplikasjon


            // Avslutter programmet
            else if (operasjonsValg == 4) {

                System.out.println("Takk for denne gang.");
                System.exit(1);

            } // else if avslutt


            // Oppretter objektet matrise
            Matrise matrise = new Matrise(raderA, kolonnerA, raderB, kolonnerB);


            // Addisjon
            if (operasjonsValg == 1) {


                matrise.getMatriseB();

                System.out.println(Arrays.deepToString(matrise.getMatriseA()) + " + " +
                        Arrays.deepToString(matrise.getMatriseB()) + "\n=");
                System.out.println(Arrays.deepToString(matrise.adderMatrisene()));

            } // if addisjon


            // Multiplikasjon
            else if (operasjonsValg == 2) {
                System.out.println(Arrays.deepToString(matrise.getMatriseA()) + " x " +
                        Arrays.deepToString(matrise.getMatriseB()) + "\n=");
                System.out.println(Arrays.deepToString(matrise.multipliserMatrisene()));
            } // else if multiplikasjon


            //Transponering
            else if (operasjonsValg == 3) {
                System.out.println("A: " + Arrays.deepToString(matrise.getMatriseA())
                        + "\nA(T): " + Arrays.deepToString(matrise.transponerMatriseA()));
            } // else if transponering

        } // try
        catch (Exception e) {
            System.out.println("Unntak, vennligst start på nytt: " + e);
            System.out.println(e.getStackTrace()[0].getLineNumber());
        } // catch
    }
}
