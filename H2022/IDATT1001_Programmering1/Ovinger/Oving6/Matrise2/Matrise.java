package Oving_6_F.Matrise2;

import java.util.Random;

public class Matrise {

    // Random klassen
    Random random = new Random();

    // Objektvariabler
    private final double[][] matriseA;
    private final double[][] matriseB;
    private double[][] matriseC;

    int raderA;
    int kolonnerA;
    int raderB;
    int kolonnerB;

    // Matrise konstruktør
    public Matrise(int raderA, int kolonnerA, int raderB, int kolonnerB) {

        this.raderA = raderA;
        this.kolonnerA = kolonnerA;
        this.raderB = raderB;
        this.kolonnerB = kolonnerB;

        // Lager en tilfeldig Matrise A
        matriseA = new double[raderA][kolonnerA];
        for (int rad = 0; rad < raderA; rad++) {

            for (int kolonne = 0; kolonne < kolonnerA; kolonne++) {
                matriseA[rad][kolonne] = random.nextInt(1, 10);
            } // for

        } //for

        // Lager en tilfeldig Matrise B
        matriseB = new double[raderB][kolonnerB];
        for (int rad = 0; rad < raderB; rad++) {

            for (int kolonne = 0; kolonne < kolonnerB; kolonne++) {
                matriseB[rad][kolonne] = random.nextInt(1, 10);
            } // for

        } //for

    } // Matrise-konstruktør


    // Metode for å addere matrisene
    public double[][] adderMatrisene() {
        if (raderA != raderB || kolonnerA != kolonnerB) {
            return null;
        }

        matriseC = new double[raderA][kolonnerA];

        // Lager en Matrise C som et resultat av (Matrise A + Matrise B)
        for (int rad = 0; rad < raderA; rad++) {

            for (int kolonne = 0; kolonne < kolonnerA; kolonne++) {
                matriseC[rad][kolonne] = matriseA[rad][kolonne] + matriseB[rad][kolonne];
            } // for

        } // for matriseC

        return matriseC;

    } // adderMatrisene


    // Metode for å multiplisere matrisene
    public double[][] multipliserMatrisene() {
        if (kolonnerA == raderB) {
            int raderC = raderA;
            int kolonnerC = kolonnerB;
            matriseC = new double[raderC][kolonnerC];

            // Lager en Matrise C som et resultat av (Matrise A * Matrise B)
            for (int rad = 0; rad < raderC; rad++) {

                for (int kolonne = 0; kolonne < kolonnerC; kolonne++) {

                    matriseC[rad][kolonne] = 0;

                    for (int i = 0; i < raderC; i++) {
                        matriseC[rad][kolonne] += (matriseA[rad][i] * matriseB[i][kolonne]);
                    } // for enkelt element

                } // for

            } // for matriseC

            return matriseC;

        } // if multiplikasjon er mulig

        else {
            throw new NullPointerException("Matrisene kan ikke multipliseres.");
        } // else

    } // multipliserMatrisene


    // Metode for å transponere Matrise A
    public double[][] transponerMatriseA() {
        matriseC = new double[kolonnerA][raderA];

        // Lager en Matrise C som et resultat av en transponering av Matrise A
        for (int rad = 0; rad < kolonnerA; rad++) {

            for (int kolonne = 0; kolonne < raderA; kolonne++) {
                matriseC[rad][kolonne] = matriseA[kolonne][rad];
            } // for

        } // for matriseC
        return matriseC;
    } // transponerMatriseA


    // Ber om å kunne lese Matrise A
    public double[][] getMatriseA() {
        return matriseA;
    }


    // Ber om å kunne lese Matrise B
    public double[][] getMatriseB() {
        return matriseB;
    }

    public String toString() {
        return "";
    }

} // klasse Matrise
