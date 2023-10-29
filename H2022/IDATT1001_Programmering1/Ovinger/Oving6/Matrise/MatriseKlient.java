package Oving_6_F.Matrise;

import java.util.Scanner;

public class MatriseKlient {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            // Initialiserer variablene i matrise B
            double b11 = 0;
            double b12 = 0;
            double b21 = 0;
            double b22 = 0;

            //Matrise A
            System.out.println("MATRISE A");
            System.out.println("Gi a\u2081\u2081 en verdi: ");
            double a11 = in.nextDouble();
            System.out.println("Gi a\u2081\u2082 en verdi: ");
            double a12 = in.nextDouble();
            System.out.println("Gi a\u2082\u2081 en verdi: ");
            double a21 = in.nextDouble();
            System.out.println("Gi a\u2082\u2082 en verdi: ");
            double a22 = in.nextDouble();

            // Ber om at en operasjonene velges
            System.out.println("Velg en av operasjonene:");
            System.out.println("[1] Addere med ny matrise\n[2] Multiplisere med ny matrise\n[3] Transponer matrisen");
            int operasjonsValg = in.nextInt();

            // Ber om input til matrise B dersom nødvendig
            if (operasjonsValg == 1 || operasjonsValg == 2) {
                // Matrise B
                System.out.println("MATRISE B");
                System.out.println("Gi b\u2081\u2081 en verdi: ");
                b11 = in.nextDouble();
                System.out.println("Gi b\u2081\u2082 en verdi: ");
                b12 = in.nextDouble();
                System.out.println("Gi b\u2082\u2081 en verdi: ");
                b21 = in.nextDouble();
                System.out.println("Gi b\u2082\u2082 en verdi: ");
                b22 = in.nextDouble();
            } else if (operasjonsValg != 3) {
                throw new IllegalArgumentException("Du må velge en av tallene ovenfor.");
            }

            // Oppretter objektet matrise
            Matrise matrise = new Matrise(a11, a12, a21, a22, b11, b12, b21, b22);

            // Utfører tilhørende metoder avhengig av operasjonsvalget
            if (operasjonsValg == 1) {
                System.out.println("Matrisene adder: " + matrise.adderMatrisene());
            } else if (operasjonsValg == 2) {
                System.out.println("Matrisene multiplisert: " + matrise.multipliserMatrisene());
            } else if (operasjonsValg == 3) {
                System.out.println("Matrise A transponert: " + matrise.transponerMatrisen());
            } else {
                throw new IllegalArgumentException("Du må velge en av tallene ovenfor.");
            }
        } // try

        catch (Exception e) {
            System.out.println("Unntak: " + e);
        } // catch

    } // main
} // MatriseKlient
