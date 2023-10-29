package Oving_6_F.Tekstanalyse;

public class Tekstanalyse {

    /* Objektvariabler */
    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyzæøå_".toCharArray();
    private final int[] antallTegn;

    /* Konstruktør som fyller opp antallTegn-arrayet */
    public Tekstanalyse(String tekst) {
        antallTegn = new int[30];
        // Øker antallTegn sine elementer med 1 når teksten matcher alfabetet
        for (int i = 0; i < tekst.length(); i++) {
            boolean bokstav = Character.isLetter(tekst.charAt(i));

            if (bokstav) {

                for (int x = 0; x < alfabet.length; x++) {
                    if (Character.toLowerCase(tekst.charAt(i)) == alfabet[x]) {
                        antallTegn[x]++;
                    }
                }

            } // if bokstav

            // Kjører dersom elementet i stringen IKKE er i alfabetet
            else {
                antallTegn[29]++;
            } // else
        } // for tekst-lengde
    } // Konstruktør Tekstanalyse

    /**
     * Metode for å finne antall ulike bokstaver
     *
     * @return
     */
    public int getAntallUlike() {

        int antallUlike = 0;

        // Finner ut av hvor mange ulike bokstaver det er i teksten
        for (int j : antallTegn) {
            if (j != 0) {
                antallUlike++;
            } // if
        } // for

        return antallUlike;
    } // getAntallUlike

    /* Metode for å finne det totale antall bokstaver */
    public int getAntallBokstaver() {

        int antallBokstaver = 0;

        // Finner den totale mengden av bokstaver (ikke med symboler/tegn)
        for (int i = 0; i < (antallTegn.length - 1); i++) {
            antallBokstaver += antallTegn[i];
        } // for

        return antallBokstaver;
    } // getAntallBokstaver

    /*
     * Metode for å finne hvor mange prosent av teksten som ikke består av bokstaver
     */
    public double getProsentAnnet() {

        double antallTotalt = 0;

        // Finner prosentandelen av ikke-bokstaver
        for (int j : antallTegn) {
            antallTotalt += j;
        } // for

        return (antallTegn[29] / antallTotalt) * 100;
    } // getProsentAnnet

    /* Metode for å finne mengden av en valgt bokstav */
    public int getAntallValgtBokstav(char valgtBokstav) {

        int antallValgtBokstav = 0;

        // Finner ut av hvilken bokstav brukeren skriver inn og finner antall
        // forekomster av denne i antallTegn
        for (int i = 0; i < alfabet.length; i++) {
            if (valgtBokstav == alfabet[i]) {
                antallValgtBokstav = antallTegn[i];
            } // if
        } // for

        return antallValgtBokstav;
    } // getAntallValgtBokstav

    /* Metode for å finne bokstaven(e) som forekommer oftest */
    public String getStorstFrekvens() {

        int maksIndeks = 0;
        String hoyestAntallBokstaver = "";

        for (int i = 0; i < (antallTegn.length - 1); i++) {
            if (antallTegn[i] > antallTegn[maksIndeks]) {
                maksIndeks = i;
            } //if
        } //for

        // Gir Stringen verdien til den største indeksen
        hoyestAntallBokstaver = String.valueOf(alfabet[maksIndeks]);

        // Legger til andre bokstaver som forekommer like mange ganger i printen
        for (int x = 0; x < (antallTegn.length - 1); x++) {
            if (antallTegn[x] == antallTegn[maksIndeks] && x != maksIndeks) {
                hoyestAntallBokstaver += ", " + alfabet[x];
            } //if
        } //for

        return hoyestAntallBokstaver;
    } // getStorstFrekvens

} // class
