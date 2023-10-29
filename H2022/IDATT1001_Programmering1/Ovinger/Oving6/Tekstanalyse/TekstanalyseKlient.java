package Oving_6_F.Tekstanalyse;

import java.util.Scanner;

public class TekstanalyseKlient {
    public static void main(String[] args) {

        /* Try-catch for å fange eventuelle feilmeldinger ved gal input o.l. */
        try {
            while (true) {

                Scanner in = new Scanner(System.in);

                System.out.println("\nSkriv inn en tekst her: ");
                String tekst = in.nextLine();

                Tekstanalyse utfTab = new Tekstanalyse(tekst);

                /* Skriver ut resultatene av metodene */
                System.out.println("Antall ulike bokstaver/tegn: " + utfTab.getAntallUlike());
                System.out.println("Antall bokstaver totalt: " + utfTab.getAntallBokstaver());
                System.out.println("Antall prosent annet: " + String.format("%.2f", utfTab.getProsentAnnet()) + "%");
                System.out.println("Denne bokstaven forekommer oftest: " + utfTab.getStorstFrekvens());

                System.out.println("\nVil du finne frekvens av en bestemt bokstav?" +
                        "\n[1] Ja" +
                        "\n[2] Nei");
                int frekvensBokstavValg = in.nextInt();

                /* Hvis ja til spørsmål ovenfor: ber klienten velge en bokstav */
                if (frekvensBokstavValg == 1) {
                    System.out.println("Velg en bokstav her: ");
                    char valgtBokstav = in.next().charAt(0);
                    System.out.println("'" + valgtBokstav + "' forekommer " + utfTab.getAntallValgtBokstav(valgtBokstav)
                            + " ganger");
                } else if (frekvensBokstavValg == 2) {
                    System.out.println("Takk for denne gang.");
                    System.exit(0);
                } else {
                    throw new IllegalArgumentException("Du må velge én av valgene ovenfor!");
                }

            } // while
        } // try

        // Catch for å fange og vise eventuelle feilmeldinger
        catch (Exception e) {
            System.out.println("Du må skrive inn en tekst. Feilkode " + e + ", linje: ");
            System.out.println(e.getStackTrace()[0].getLineNumber());
        } // catch

    } // main
}
// class
