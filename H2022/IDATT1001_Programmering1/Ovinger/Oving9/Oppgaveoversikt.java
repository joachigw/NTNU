package Oving_9_F;

import java.util.ArrayList;

/**
 * Klasse som holder en oversikt over antall studenter, kan registrere en ny
 * student og øke antall løste oppgaver.
 */
public class Oppgaveoversikt {

    private ArrayList<Student> studenter;

    /**
     * Klassekonstruktør.
     * Initierer arraylisten som studentene skal legges i.
     */
    public Oppgaveoversikt() {
        studenter = new ArrayList<>();
    }

    /**
     * Metode for å få tak i antall registrerte studenter.
     *
     * @return int av antallet studenter.
     */
    public int getAntStud() {
        return studenter.size();
    }

    /**
     * Metode for å registrere en ny student på bakgrunn av parameterne.
     *
     * @param navn    Studentens navn.
     * @param antOppg Antall løste oppgaver.
     */
    public void regNyStud(String navn, int antOppg) {

        boolean navnEksisterer = false;

        for (Student student : studenter) {
            if (student.getNavn().equalsIgnoreCase(navn)) {
                navnEksisterer = true;
                break;
            }
        }

        if (navnEksisterer) {
            System.out.println("Dette navnet finnes fra før av!");
        } else {
            Student nyStud = new Student(navn, antOppg);
            studenter.add(nyStud);
            System.out.println(nyStud.getNavn() + " ble registrert.");
        }
    }

    /**
     * Metode for å finne indeksen til en valgt student i arraylisten.
     *
     * @param studenterIndeks Studentens indeks i arraylisten.
     * @return Spesifisert objekt av klassen Student.
     */
    public Student getStudNavnIArray(int studenterIndeks) {
        return studenter.get(studenterIndeks);
    }

    /**
     * Metode for å skrive ut klasseattributtene.
     *
     * @return String av klasseattributtene.
     */
    @Override
    public String toString() {
        String print = "";
        for (int i = 0; i < studenter.size(); i++) {
            print += "[" + (i + 1) + "] " + studenter.get(i).getNavn() + "\n";
        }
        return print;
    }

}
