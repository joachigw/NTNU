package Oving_7_F.TekstBehandling;

import java.util.Arrays;
import java.util.Objects;

/**
 * Klasse som behandler en spesifisert String
 */
public class TekstBehandling {

    private final String tekst;

    /**
     * Oppretter en tekst lik den spesifiserte teksten
     *
     * @param tekst Den spesifiserte teksten
     */
    public TekstBehandling(String tekst) {
        this.tekst = tekst;
    }

    /**
     * Metode for å finne antall ord i teksten
     *
     * @return En int av lengden til teksten delt opp i ord
     */
    public int antallOrd() {
        if (tekst == null || tekst.isEmpty()) {
            return 0;
        }
        return tekst.split("\\s+").length;
    }

    /**
     * Metode for å finne gjennomsnittlig lengde på ordene i teksten
     *
     * @return En double av gjennomsnittlig lengde på ordene
     */
    public double gjOrdLengde() {

        String[] ordArray = tekst.replaceAll("\\p{Punct}", "").split("\\s+");
        double antallOrd = 0;

        for (String ord : ordArray) {
            antallOrd += ord.length();
        }

        return (antallOrd / ordArray.length);
    }

    /**
     * Metode for å finne gjennomsnittlig antall ord per 'periode'
     * Periode defineres som teksten splittet ved '.', '!', '?' og ':'
     *
     * @return double av gjennomsnittlig antall ord per periode
     */
    public double gjAntOrdPerPeriode() {

        String[] periodeArray = tekst.split("[.!?:]");
        String[] ordArray = tekst.split("\\s");

        double ordLengde = ordArray.length;
        double periodeLengde = periodeArray.length;

        return ordLengde / periodeLengde;
    }

    /**
     * Metode for å erstatte ett spesifisert ord, med et annet spesifisert ord
     *
     * @param gammel Ordet som skal erstattes
     * @param ny     Det nye ordet som skal erstatte det gamle
     * @return String av teksten etter erstatningen(e)
     */
    public String erstatt(String gammel, String ny) {

        String[] ordArray = tekst.split("(?<=\\b|[^\\p{L}])", 0);
        System.out.println(Arrays.deepToString(ordArray));

        gammel = gammel.toLowerCase();
        ny = ny.toLowerCase();

        StringBuilder nyTekst = new StringBuilder();

        for (int ord = 0; ord < ordArray.length; ord++) {
            if (Objects.equals(ordArray[ord], gammel)) {
                ordArray[ord] = ny;
                nyTekst.append(ny);
            } else {
                nyTekst.append(ordArray[ord]);
            }
        }
        System.out.println(Arrays.deepToString(ordArray));
        return nyTekst.toString();
    }

    /**
     * Metode for å få teksten
     *
     * @return En String av den spesifiserte teksten
     */
    public String getTekst() {
        return tekst;
    }

    /**
     * Metode for å få teksten med store bokstaver
     *
     * @return En String av den spesifiserte teksten med store bokstaver
     */
    public String getTekstStorForbokstav() {
        return tekst.toUpperCase();
    }

}
