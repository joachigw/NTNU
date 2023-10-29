package Oving_6_F.Matrise;

import java.util.Arrays;

public class Matrise {

    double[][] matriseA;
    double[][] matriseB;
    double[][] matriseC;


    //Matrise-konstruktør
    public Matrise(double a11, double a12, double a21, double a22, double b11, double b12, double b21, double b22) {
        matriseA = new double[][]{
                {a11, a12},
                {a21, a22}
        };
        matriseB = new double[][]{
                {b11, b12},
                {b21, b22}
        };
    } //Matrise-konstruktør


    /*
    public void print() {
        for (int i = 0; i < matrise.length; i++) {
            System.out.print("[ ");
            for (int j = 0; j < matrise[i].length; j++) {
                System.out.print(matrise[i][j] + " ");
                if (j == 0) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }
    */

    // Metode for å addere matrisene
    public String adderMatrisene() {
        matriseC = new double[][]{
                {matriseA[0][0] + matriseB[0][0], matriseA[0][1] + matriseB[0][1]},
                {matriseA[1][0] + matriseB[1][0], matriseA[1][1] + matriseB[1][1]}
        };
        return Arrays.deepToString(matriseA) + " + " + Arrays.deepToString(matriseB) +
                "=\n" + Arrays.deepToString(matriseC);

    } // adderMatrisene

    // Metode for å multiplisere matrisene
    public String multipliserMatrisene() {
        matriseC = new double[][]{
                {((matriseA[0][0] * matriseB[0][0]) + (matriseA[0][1] * matriseB[1][0])),
                        ((matriseA[0][0] * matriseB[0][1]) + (matriseA[0][1] * matriseB[1][1]))},
                {((matriseA[1][0] * matriseB[0][0]) + (matriseA[1][1] * matriseB[1][0])),
                        ((matriseA[1][0] * matriseB[0][1]) + (matriseA[1][1] * matriseB[1][1]))}
        };
        return Arrays.deepToString(matriseA) + " x " + Arrays.deepToString(matriseB) +
                "=\n" + Arrays.deepToString(matriseC);
    } // multipliserMatrisene

    // Metode for å transponere matrise A
    public String transponerMatrisen() {
        matriseC = new double[][]{
                {matriseA[0][0], matriseA[1][0]},
                {matriseA[0][1], matriseA[1][1]}
        };
        return Arrays.deepToString(matriseC);
    }
}
