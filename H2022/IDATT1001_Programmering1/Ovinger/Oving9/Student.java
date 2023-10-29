package Oving_9_F;

/**
 * Klasse som representerer én student.
 * Klassen tilbyr metoder for å få navn og antall løste oppgaver, øke antall løste oppgaver og en toString().
 */
public class Student {

    private final String navn;
    private int antOppg;

    /**
     * Klassekonstruktør.
     * Oppretter en student på bakgrunn av parameterne.
     *
     * @param navn    Studentens navn.
     * @param antOppg Antall løste oppgaver.
     */
    public Student(String navn, int antOppg) {
        this.navn = navn;
        this.antOppg = antOppg;
    }

    /**
     * Metode for å få tak i navnet til studenten.
     *
     * @return String av navnet.
     */
    public String getNavn() {
        return this.navn;
    }

    /**
     * Metode for å få tak i studentens antall løste oppgaver.
     *
     * @return int av antall løste oppgaver.
     */
    public int getAntOppg() {
        return this.antOppg;
    }

    /**
     * Metode for å øke studentens antall løste oppgaver.
     *
     * @param økning Økningen av antallet løste oppgaver i heltall.
     */
    public void økAntOppg(int økning) {
        this.antOppg += økning;
    }

    /**
     * Metode for å skrive ut studentens tilhørende attributter.
     *
     * @return String av studentens attributter.
     */
    @Override
    public String toString() {
        return "Student{" +
                "navn='" + navn + '\'' +
                ", antOppg=" + antOppg +
                '}';
    }
}
