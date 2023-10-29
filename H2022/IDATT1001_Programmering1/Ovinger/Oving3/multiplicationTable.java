import java.util.*;

public class multiplicationTable {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.print("Skriv inn en startverdi:");
    int startVerdi = in.nextInt();
    System.out.print("Skriv inn en sluttverdi:");
    int sluttVerdi = in.nextInt();

    int i = 1;
    int x = 0;

    if (startVerdi >= 0 && sluttVerdi > 0) { // Skriver ut gangetabellen ifra 1-10 dersom tallet er // positivt
      // eller lik 0
      while (x <= (sluttVerdi - startVerdi)) {
        i = 1; // Gjør klar i variabelen for en ny runde i while-loopen
        System.out.println((startVerdi + x) + "-gangen:"); // Lager overskriften til printen
        while (i <= 10) { // While-loop som kjøres til i blir lik 10
          System.out.println(
            (startVerdi + x) +
            " x " +
            Integer.toString(i) +
            " = " +
            (startVerdi + x) *
            i
          );
          i++; // Øker verdien til i for å kunne begrense området til loopen
        }
        x++; // Øker x for å kunne begrense området til loopen
      }
    } else if (startVerdi == sluttVerdi) {
      System.out.println(
        "Vennligst velg en sluttverdi som er større enn startverdien."
      );
    } else { // Dersom tallet er mindre enn 0, så vil brukeren få beskjed om å skrive inn et
      // postivt tall
      System.out.println("Skriv inn et positivt tall! :-)");
    }
    System.out.println(
      "Du bestilte gangetabellen ifra " +
      startVerdi +
      " til " +
      sluttVerdi +
      "."
    );
    in.close();
  }
}
