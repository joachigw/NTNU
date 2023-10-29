package Oving_7_F.NyString;

/**
 * Immutabel klasse som manipulerer en spesifisert tekst
 */
public final class NyString {

    private final String tekst;

    /**
     * Oppretter en tekst lik den spesifiserte teksten
     *
     * @param tekst Den spesifiserte teksten
     */
    public NyString(String tekst) {
        this.tekst = tekst;
    }

    /**
     * Metode for å lage en String ut av den første bokstaven i hvert ord av teksten
     *
     * @return En String med første bokstav fra hvert ord
     */
    public String forkort() {
        String[] ordArray = tekst.trim().split("\\s+");
        StringBuilder forsteBokstav = new StringBuilder();

        for (String ord : ordArray) {
            forsteBokstav.append(ord.charAt(0));
        }

        return forsteBokstav.toString();
    }

    /**
     * Metode for å fjerne en spesifisert bokstav fra teksten
     *
     * @param bokstavFjernes Bokstaven som skal fjernes
     * @return String av teksten uten den spesifiserte bokstaven
     */
    public String fjern(String bokstavFjernes) {
        String[] ordArray = tekst.trim().split("\\s+");
        StringBuilder etterFjerning = new StringBuilder();

        for (String ord : ordArray) {
            etterFjerning.append(ord.replace(bokstavFjernes, "")).append(" ");
        }
        return etterFjerning.toString();
    }
}
