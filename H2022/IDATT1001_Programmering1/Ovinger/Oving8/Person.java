package Oving_8_F;

/**
 * Klasse som representerer én person
 */
public final class Person {
    private final String fornavn;
    private final String etternavn;
    private final int fødselsår;

    /**
     * Klassekonstruktør
     * Oppretter en person på bakgrunn av parameterne
     *
     * @param fornavn   Fornavnet til personen
     * @param etternavn Etternavnet til personen
     * @param fødselsår Fødselsåret til personen
     */
    public Person(String fornavn, String etternavn, int fødselsår) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fødselsår = fødselsår;
    }

    /**
     * Metode som gir fornavnet
     *
     * @return String av fornavnet
     */
    public String getFornavn() {
        return fornavn;
    }

    /**
     * Metode som gir etternavnet
     *
     * @return String av etternavnet
     */
    public String getEtternavn() {
        return etternavn;
    }

    /**
     * Metode som gir fødselsåret
     *
     * @return int av fødselsåret
     */
    public int getFødselsår() {
        return fødselsår;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fornavn='" + fornavn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", fødselsår=" + fødselsår +
                '}';
    }
}
