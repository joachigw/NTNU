package Oving_5_F.Random;

public class MinRandom {

    // Objektvariabel randomTall som genererer et tilfeldig tall innenfor et
    // brukerbestemt område
    java.util.Random randomTall = new java.util.Random();

    // Genererer ett tilfeldig heltall mellom et gitt intervall
    public int nesteHeltall(int nedre, int ovre) {
        return randomTall.nextInt(nedre, ovre + 1);
    }

    // Genererer ett tilfeldig desimaltall mellom et gitt intervall
    public double nesteDesimaltall(double nedre, double ovre) {
        return randomTall.nextDouble(nedre, ovre);
    }
}
