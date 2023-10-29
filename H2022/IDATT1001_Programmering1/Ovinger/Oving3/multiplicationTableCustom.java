import javax.swing.JOptionPane;

public class multiplicationTableCustom {

  public static void main(String[] args) {
    String startVerdi = JOptionPane.showInputDialog("Startverdi:");
    String sluttVerdi = JOptionPane.showInputDialog("Sluttverdi:");
    String listeVerdi = JOptionPane.showInputDialog("Hvor lang liste?");
    int startVerdiInt = Integer.parseInt(startVerdi);
    int sluttVerdiInt = Integer.parseInt(sluttVerdi);
    int listeVerdiInt = Integer.parseInt(listeVerdi);
    // System.out.println(startVerdiInt);
    int i = 1;
    int x = 0;
    if (startVerdiInt >= 0 && sluttVerdiInt > 0) { // Skriver ut gangetabellen ifra 1-10 dersom tallet er positivt
      // eller lik 0
      while (x <= (sluttVerdiInt - startVerdiInt)) {
        i = 1; // Gjør klar i variabelen for en ny runde i while-loopen
        System.out.println((startVerdiInt + x) + "-gangen:"); // Lager overskriften til printen
        while (i <= listeVerdiInt) { // While-loop som kjøres til i blir lik 10
          System.out.println(
            (startVerdiInt + x) +
            " x " +
            Integer.toString(i) +
            " = " +
            (startVerdiInt + x) *
            i
          );
          i++; // Øker verdien til i for å kunne begrense området til loopen
        }
        x++; // Øker x for å kunne begrense området til loopen
      }
    } else {/*
     * Dersom tallet er mindre enn 0, så vil brukeren få beskjed om å skrive
     * innpositivt tall
     */
      System.out.println("Skriv inn et positivt tall! :-)");
    }
  }
}
