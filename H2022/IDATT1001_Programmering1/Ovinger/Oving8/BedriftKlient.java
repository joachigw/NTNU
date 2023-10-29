package Oving_8_F;

import java.util.Scanner;

/**
 * Klasse som representerer en klient med tilgang til å lese alle, og endre enkelte attributter
 */
public class BedriftKlient {

    public static void main(String[] args) {

        try {

            Scanner in = new Scanner(System.in);

            System.out.println("Fornavn:");
            String fornavn = in.nextLine();
            System.out.println("Etternavn:");
            String etternavn = in.nextLine();
            System.out.println("Fødselsår:");
            int fødselsår = in.nextInt();
            System.out.println("Ansettelsesår?");
            int ansettelsesår = in.nextInt();
            System.out.println("Månedslønn?");
            double månedslønn = in.nextDouble();
            System.out.println("Skatteprosent?");
            double skatteprosent = (in.nextDouble());
            System.out.println("Jobbet lengre enn X år?");
            int årTilSammenligning = in.nextInt();
            System.out.println("Hvor mange er allerede ansatt i bedriften?");
            int arbtakernr = (in.nextInt() + 1);

            Person joachim = new Person(fornavn, etternavn, fødselsår);
            ArbTaker arbTaker = new ArbTaker(joachim, arbtakernr, ansettelsesår, månedslønn, skatteprosent);

            System.out.println("Arbtakernr: " + arbTaker.getArbtakernr());
            System.out.println("Ansettelsesår: " + arbTaker.getAnsettelsesår());
            System.out.println("Månedslønn: " + arbTaker.getMånedslønn() + " kr");
            System.out.println("Skatteprosent: " + arbTaker.avrund(arbTaker.getSkatteprosent() * 100));
            System.out.println("Skatt per mnd: " + arbTaker.avrund(arbTaker.mndSkatt()) + " kr");
            System.out.println("Årlig bruttolønn: " + arbTaker.avrund(arbTaker.bruttoPerÅr()) + " kr");
            System.out.println("Årlig skatt: " + arbTaker.avrund(arbTaker.skattPerÅr()));
            System.out.println("Fullt navn: " + arbTaker.getFulltNavn());
            System.out.println("Alder: " + arbTaker.getAlder() + " år");
            System.out.println("Antall år i bedriften: " + arbTaker.antallÅrIBedrift());

            String ansattLengreEnnResultat = "";
            if (arbTaker.ansattLengreEnn(årTilSammenligning)) {
                ansattLengreEnnResultat = "Ja.";
            } else {
                ansattLengreEnnResultat = "Nei.";
            }
            System.out.println("Ansatt lengre enn " + årTilSammenligning + " år? " + ansattLengreEnnResultat);

            while (true) {
                System.out.println("""
                                                
                        Foreta endringer:
                        [1] Endre månedslønn
                        [2] Endre skatteprosent
                        [3] Avslutt""");
                int menyValg = in.nextInt();

                switch (menyValg) {
                    case 1 -> {
                        System.out.println("Nåværende månedslønn: " + arbTaker.getMånedslønn() +
                                " kr\nHva er den nye månedslønnen?");
                        double nyMndLønn = in.nextDouble();
                        if (nyMndLønn < 0) {
                            System.out.println("Lønna må være høyere enn 0 kr.");
                            continue;
                        }
                        arbTaker.setMånedslønn(nyMndLønn);
                        System.out.println("Ny månedslønn er: " + arbTaker.getMånedslønn() + " kr.");
                    }
                    case 2 -> {
                        System.out.println("Nåværende skatteprosent: " + (arbTaker.getSkatteprosent() * 100) +
                                "\nHva er den nye skatteprosenten?");
                        double nySkatteprosent = in.nextDouble();
                        if (nySkatteprosent <= 0 || nySkatteprosent >= 100) {
                            System.out.println("Skatteprosenten må være et tall mellom 0 og 100.");
                            continue;
                        }
                        arbTaker.setSkatteprosent(nySkatteprosent);
                        System.out.println("Ny skatteprosent er: " + arbTaker.getSkatteprosent() + "%.");
                    }
                    case 3 -> {
                        System.out.println("Takk for denne gang.");
                        System.exit(0);
                    }
                    default -> System.out.println("Du må velge en av tallene ovenfor!");
                }
            }
        } catch (Exception e) {
            System.out.println("Du fikk følgende unntaksmelding: " + e +
                    ", vennligst kjør programmet på nytt.");
        }
    }
}
