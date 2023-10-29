package Oving_7_F.NyString;

import java.util.Scanner;

/**
 * Klasse som fungerer som en klient for klassen NyString
 */
public class Test {

    public static void main(String[] args) {

        boolean loop = true;

        while (loop) {
            Scanner in = new Scanner(System.in);

            System.out.println("Skriv inn en tekst:");
            String tekstInp = in.nextLine();

            System.out.println(tekstInp);
        }
    }
}
