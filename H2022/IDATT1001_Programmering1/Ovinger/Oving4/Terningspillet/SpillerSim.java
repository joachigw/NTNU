package Oving_4_F.Terningspillet;

public class SpillerSim {

  int spillerId;
  int oyneTerning;
  int sumPoeng;
  int antallRunder;
  String ferdig;

  public SpillerSim(int spillerId, int sumPoeng) {
    this.spillerId = spillerId;
    this.sumPoeng = sumPoeng;
  }

  public int getSumPoeng() {
    return sumPoeng;
  }

  public int getAntallRunder() {
    antallRunder++;
    return antallRunder;
  }

  public int kastTerning() {
    java.util.Random terning = new java.util.Random();
    int oyneTerning = terning.nextInt(6) + 1;
    return oyneTerning;
  }

  public int leggTilKast(int kast) {
    sumPoeng += kast;
    return sumPoeng;
  }

  public String erFerdig(int spillerId) {
    if (sumPoeng >= 100) {
      ferdig =
        "Spiller " + spillerId + " er ferdig, med " + sumPoeng + " poeng.";
    }
    return ferdig;
  }
}
