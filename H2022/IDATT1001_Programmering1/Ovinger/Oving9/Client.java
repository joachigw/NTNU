package Oving_9_F;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {

            Scanner in = new Scanner(System.in);

            Oppgaveoversikt array = new Oppgaveoversikt();

            while (true) {

                System.out.println("""  
                                     
                        Velg én av følgende tjenester:
                                                
                        [1] Finn antall registrerte studenter
                        [2] Finn antall løste oppgaver hos en bestemt student
                        [3] Registrer en ny student
                        [4] Øk antall oppgaver hos en bestemt student
                        [5] Se alle registrerte studenter
                        [6] Avslutt""");

                int valgtTjeneste = in.nextInt();

                switch (valgtTjeneste) {
                    case 1 -> System.out.println("Det er registrert " + array.getAntStud() + " student(er).");

                    case 2 -> {
                        if (array.getAntStud() <= 0) {
                            System.out.println("Det er ingen studenter registrert enda.");
                        } else {
                            try {
                                System.out.println(array);
                                System.out.println("Hvilken student vil du sjekke?");
                                int valgtStud = in.nextInt() - 1;

                                Student stud = array.getStudNavnIArray(valgtStud);

                                System.out.println(stud.getNavn() + " har løst " + stud.getAntOppg() + " oppgave(r).");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Det finnes ingen student med denne indeksen enda!");
                            }
                        }
                    }

                    case 3 -> {
                        System.out.println("Studentens navn?");
                        String nyStudNavn = in.next();

                        System.out.println("Antall løste oppgaver?");
                        int nyStudAntOppg = in.nextInt();

                        array.regNyStud(nyStudNavn, nyStudAntOppg);

                    }

                    case 4 -> {

                        if (array.getAntStud() <= 0) {
                            System.out.println("Det er ingen registrerte studenter enda.");
                        } else {
                            try {
                                System.out.println(array + "Hvilken student skal økes?");
                                int valgtStud = in.nextInt() - 1;
                                System.out.println("Hvor mye skal det økes med?");
                                int antØkning = in.nextInt();

                                Student stud = array.getStudNavnIArray(valgtStud);

                                if (stud.getAntOppg() + antØkning < 0) {
                                    System.out.println("Du kan ikke ha et negativt antall løste oppgaver!");
                                } else {
                                    stud.økAntOppg(antØkning);
                                    System.out.println(stud.getNavn() + " har nå løst " + stud.getAntOppg() + " oppgaver.");
                                }
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("Det finnes ingen student med denne indeksen enda!");
                            }
                        }
                    }

                    case 5 -> {
                        if (array.getAntStud() <= 0) {
                            System.out.println("Det er ingen registrerte studenter enda.");
                        } else {
                            System.out.println(array);
                        }
                    }

                    case 6 -> {
                        System.out.println("Takk for denne gang.");
                        System.exit(0);
                    }

                    default -> throw new IllegalArgumentException("Du må velge et tall i intervallet [1, 7].");

                }
            }
        } catch (Exception e) {
            System.out.println("Du fikk unntaket: " + e);
            main(null);
        }
    }
}
