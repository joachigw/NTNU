package Oving_4_F.Terningspillet;

public class terningSpilletMod {

  public static void main(String[] args) {
    // Oppretter spillerne som to objekter
    SpillerSim spiller1 = new SpillerSim(1, 0);
    SpillerSim spiller2 = new SpillerSim(2, 0);

    // Løkke som kjører til den breakes når en av spillerne får 100 eller mer
    while (true) {
      // Hver av spillerne kaster terningen én gang
      int spiller1Kast = spiller1.kastTerning();
      int spiller2Kast = spiller2.kastTerning();

      // Hver av spillerne får poeng basert på kastet sitt
      spiller1.leggTilKast(spiller1Kast);
      spiller2.leggTilKast(spiller2Kast);

      // Henter inn antall runder
      int antallRunder = spiller1.getAntallRunder();

      // Nullstiller poengene til spillerne dersom de kaster 1
      if (spiller1Kast == 1) {
        spiller1.sumPoeng = 0;
      } // if
      else if (spiller2Kast == 1) {
        spiller2.sumPoeng = 0;
      } // else if
      else if (spiller1.sumPoeng > 100) {
        spiller1.sumPoeng -= spiller1Kast * 2;
      } // else if
      else if (spiller2.sumPoeng > 100) {
        spiller2.sumPoeng -= spiller2Kast * 2;
      } // else if

      // Printer ut runde, spillernes kast og deres poeng
      System.out.println("\nRUNDE " + antallRunder);
      System.out.println(
        "Kast spiller1: " + spiller1Kast + ", poengsum: " + spiller1.sumPoeng
      );
      System.out.println(
        "Kast spiller2: " + spiller2Kast + ", poengsum: " + spiller2.sumPoeng
      );

      // Avslutter løkken dersom en av spillerne får 100 eller mer poeng
      if (spiller1.sumPoeng == 100) {
        System.out.println(spiller1.erFerdig(1));
        break;
      } // if
      else if (spiller2.sumPoeng == 100) {
        System.out.println(spiller2.erFerdig(2));
        break;
      } // else if
    } // for
  } // main
} // class
