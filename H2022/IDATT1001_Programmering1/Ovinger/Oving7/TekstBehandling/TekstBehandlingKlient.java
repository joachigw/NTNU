package Oving_7_F.TekstBehandling;

import java.util.Scanner;

/**
 * Klasse som representerer en klient for klassen TekstBehandling
 */
public class TekstBehandlingKlient {

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            String tekstInp = "he: hei; hei, hei. hei! hei? hei: he";

            TekstBehandling tekst = new TekstBehandling(tekstInp);

            System.out.println("""
                    Velg én av tjenestene under:
                    [1] Antall ord
                    [2] Gjennomsnittlig ordlengde
                    [3] Gjennomstnittlig antall ord per periode
                    [4] Erstatt et ord med et annet
                    [5] Vis original tekst
                    [6] Vis original tekst med store bokstaver
                    [7] Avslutt""");
            int valgtTjeneste = in.nextInt();

            switch (valgtTjeneste) {
                case 1 -> System.out.println("Det er " + tekst.antallOrd() + " ord i teksten.");
                case 2 -> System.out.println("Gjennomsnittlig ordlengde: " +
                        String.format("%.2f", tekst.gjOrdLengde()) + " bokstaver.");
                case 3 -> System.out.println("Gjennomsnittlig antall ord per periode: " +
                        String.format("%.2f", tekst.gjAntOrdPerPeriode()) + " ord.");
                case 4 -> {
                    System.out.println("Hvilket ord skal erstattes?");
                    String gammel = in.next();
                    System.out.println("Hvilket ord skal " + gammel + " erstattes med?");
                    String ny = in.next();
                    System.out.println(tekst.erstatt(gammel, ny));
                }
                case 5 -> System.out.println(tekst.getTekst());
                case 6 -> System.out.println(tekst.getTekstStorForbokstav());
                case 7 -> {
                    System.out.println("Takk for denne gang.");
                    System.exit(0);
                }
                default -> System.out.println("Du må velge en av tallene ovenfor. Prøv på nytt.");
            }

        } catch (Exception e) {
            System.out.println("Unntak: " + e);
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
    }
}
