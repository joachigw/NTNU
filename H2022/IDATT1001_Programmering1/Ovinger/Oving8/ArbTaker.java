package Oving_8_F;

import java.util.GregorianCalendar;

/**
 * Klasse som representerer en arbeidstaker
 * Klassen tilbyr metoder for å kunne få og endre elementene som inngår
 */
public class ArbTaker {

    GregorianCalendar kalender = new GregorianCalendar();
    int år = kalender.get(java.util.Calendar.YEAR);

    private final Person person;
    private final int arbtakernr;
    private final int ansettelsesår;
    private double månedslønn;
    private double skatteprosent;


    /**
     * Klassekonstruktør
     * Oppretter en arbeidstaker på bakgrunn av parameterne
     *
     * @param person        Fornavn, etternavn og fødselsår
     * @param arbtakernr    Individuelt identifikasjonsnummer
     * @param ansettelsesår Året ansettelsen tok sted
     * @param månedslønn    Arbeidstakerens månedslønn
     * @param skatteprosent Arbeidstakerens skatteprosent
     */
    public ArbTaker(Person person, int arbtakernr, int ansettelsesår, double månedslønn, double skatteprosent) {
        this.person = person;
        this.arbtakernr = arbtakernr;
        this.ansettelsesår = ansettelsesår;
        this.månedslønn = månedslønn;
        this.skatteprosent = skatteprosent / 100;
    }

    /**
     * Metode som viser arbeidstakernummer
     *
     * @return int av arbeidstakernummeret
     */
    public int getArbtakernr() {
        return arbtakernr;
    }

    /**
     * Metode som viser ansettelsesår
     *
     * @return int av ansettelsesåret
     */
    public int getAnsettelsesår() {
        return ansettelsesår;
    }

    /**
     * Metode som viser månedslønn
     *
     * @return double av månedslønnen
     */
    public double getMånedslønn() {
        return månedslønn;
    }

    /**
     * Metode som viser skatteprosent
     *
     * @return double av skatteprosenten
     */
    public double getSkatteprosent() {
        return skatteprosent;
    }

    /**
     * Metode som endrer objektets månedslønn
     *
     * @param nyMånedslønn Den nye månedslønnen
     */
    public void setMånedslønn(double nyMånedslønn) {
        this.månedslønn = nyMånedslønn;
    }

    /**
     * Metode som endrer objektets skatteprosent
     *
     * @param nySkatteprosent Den nye skatteprosenten
     */
    public void setSkatteprosent(double nySkatteprosent) {
        this.skatteprosent = nySkatteprosent;
    }

    /**
     * Metode som viser total mengde skatt for en måned vha. nåværende månedslønn og skatteprosent
     *
     * @return double av mengde skatt betalt i kroner
     */
    public double mndSkatt() {
        return this.månedslønn * this.skatteprosent;
    }

    /**
     * Metode som viser bruttolønn per år vha. nåværende månedslønn
     *
     * @return double av bruttolønn per år
     */
    public double bruttoPerÅr() {
        return (this.månedslønn * 12);
    }

    /**
     * Metode som viser total mengde skatt per år, hvorav juni er skattefri og desember kun har halv skatt
     *
     * @return double av mengden skatt for et helt år gitt i kroner
     */
    public double skattPerÅr() {
        return (this.mndSkatt() * 10.5);
    }

    /**
     * Metode for å vise fullt navn på formen (etternavn, fornavn)
     *
     * @return String av fullt navn på formen (etternavn, fornavn)
     */
    public String getFulltNavn() {
        return person.getEtternavn() + ", " + person.getFornavn();
    }

    /**
     * Metode for å vise alder
     *
     * @return int av alder
     */
    public int getAlder() {
        return (år - person.getFødselsår());
    }

    /**
     * Metode for å beregne hvor lengde arbeidstakeren har vært ansatt i bedriften
     *
     * @return int av antall år i bedriften
     */
    public int antallÅrIBedrift() {
        return (år - this.ansettelsesår);
    }

    /**
     * Metode for å beregne hvorvidt arbeidstakeren har vært ansatt lengre eller kortere enn et spesifisert antall år
     *
     * @return boolean om arbeidstakeren har vært ansatt lengre eller kortere
     */
    public boolean ansattLengreEnn(int årTilSammenligning) {
        return this.antallÅrIBedrift() > årTilSammenligning;
    }

    public String avrund(double tall) {
        return String.format("%.2f", tall);
    }

    @Override
    public String toString() {
        return "ArbTaker{" +
                "person=" + person +
                ", arbtakernr=" + arbtakernr +
                ", ansettelsesår=" + ansettelsesår +
                ", månedslønn=" + månedslønn +
                ", skatteprosent=" + skatteprosent +
                '}';
    }
}

