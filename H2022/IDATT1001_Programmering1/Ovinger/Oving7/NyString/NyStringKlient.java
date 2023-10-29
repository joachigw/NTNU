package Oving_7_F.NyString;

import java.util.Scanner;

/**
 * Klasse som fungerer som en klient for klassen NyString
 */
public class NyStringKlient {

    public static void main(String[] args) {

        try {
            Scanner in = new Scanner(System.in);

            System.out.println("Skriv inn en tekst:");
            String tekstInp = in.nextLine();

            NyString tekst = new NyString(tekstInp);

            System.out.println("""
                    Velg én av følgende tjenester:
                    [1] Vis første bokstav fra hvert ord
                    [2] Fjern et tegn eller en bokstav
                    [3] Avslutt""");
            int valgtTjeneste = in.nextInt();

            switch (valgtTjeneste) {
                case 1 -> System.out.println(tekst.forkort());
                case 2 -> {
                    System.out.println("Hvilke bokstav(er) skal fjernes?");
                    String onsketFjernet = String.valueOf(in.next().charAt(0));
                    System.out.println(tekst.fjern(onsketFjernet));
                }
                case 3 -> {
                    System.out.println("Takk for denne gang.");
                    System.exit(0);
                }
                default -> System.out.println("Du må velge en av tallene ovenfor!");
            }

        } catch (Exception e) {
            System.out.println("Unntak: " + e);
            System.out.println(e.getStackTrace()[0].getLineNumber());
        }
    }
}
