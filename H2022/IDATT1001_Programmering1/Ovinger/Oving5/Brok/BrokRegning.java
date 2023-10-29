package Oving_5_F.Brok;

//Klasse som utfører regneoperasjoner mellom to brøker, og forkorter svaret hvis mulig
public class BrokRegning {

  // Objektvariabler
  double teller;
  double nevner;
  double iterator;
  String result;

  // Konstruktør som tar både teller og nevner som argumenter
  public BrokRegning(double teller, double nevner) {
    if (nevner == 0) {
      throw new IllegalArgumentException("---Nevneren kan ikke være lik 0!---");
    } else {
      this.teller = teller;
      this.nevner = nevner;
    }
  }

  // Konstruktør som kun tar teller, og setter nevner lik 1
  public BrokRegning(double teller) {
    this.teller = teller;
    this.nevner = 1;
  }

  // METODE FOR ADDISJON
  public void getSum(double teller2, double nevner2) {
    // Plusser tellerne sammen dersom de har lik nevner
    if (nevner == nevner2) {
      teller = teller + teller2;
      nevner = nevner2;
    }
    // Lager en ny brøk på bakgrunn av felles nevner
    else {
      teller = (teller * nevner2) + (teller2 * nevner);
      nevner = nevner * nevner2;
    }
    // Sjekker om det er teller eller nevner som er størst, og bruker denne i
    // for-loop
    if (teller > nevner) {
      iterator = teller;
    } else {
      iterator = nevner;
    }

    // Forkorter brøken dersom det er mulig
    forkortBrok(teller, nevner, iterator);
  }

  // METODE FOR SUBTRAKSJON
  public void getSub(double teller2, double nevner2) {
    // Trekker teller fra teller2 dersom de har lik nevner
    if (nevner == nevner2) {
      teller = teller - teller2;
      nevner = nevner2;
    }
    // Lager en ny brøk på bakgrunn av felles nevner
    else {
      teller = (teller * nevner2) - (teller2 * nevner);
      nevner = nevner * nevner2;
    }
    // Sjekker om teller eller nevner som er størst, og bruker denne lengden til
    // å begrense for-loopens lengde
    if (teller > nevner) {
      iterator = teller;
    } else {
      iterator = nevner;
    }

    // Forkorter brøken dersom det er mulig
    forkortBrok(teller, nevner, iterator);
  }

  // METODE FOR MULTIPLIKASJON
  public void getMulti(double teller2, double nevner2) {
    // Multiplikasjon av teller og nevner hver for seg
    teller = teller * teller2;
    nevner = nevner * nevner2;

    // Sjekker om teller eller nevner som er størst, og bruker denne lengden til
    // å begrense for-loopens lengde
    if (teller > nevner) {
      iterator = teller;
    } else {
      iterator = nevner;
    }

    // Forkorter brøken dersom det er mulig
    forkortBrok(teller, nevner, iterator);
  }

  // METODE FOR DIVISJON
  public void getDiv(double teller2, double nevner2) {
    // Snur om siste brøken og multipliserer
    teller = teller * nevner2;
    nevner = nevner * teller2;
    // Sjekker om teller eller nevner som er størst, og bruker denne lengden til
    // å begrense for-loopens lengde
    if (teller > nevner) {
      iterator = teller;
    } else {
      iterator = nevner;
    }

    // Forkorter brøken dersom det er mulig
    forkortBrok(teller, nevner, iterator);
  }

  // METODE FOR Å FORKORTE RESULTATBRØKEN DERSOM DET ER MULIG
  public void forkortBrok(double teller, double nevner, double iterator) {
    // Forkorter basert på om telleren og nevneren kan deles opp til mindre heltall
    for (int i = 1; i <= iterator; i++) {
      if (teller % i == 0 && nevner % i == 0) {
        teller = teller / i;
        nevner = nevner / i;
      }
    }
    // Dersom nevneren blir 1, skriver vi ikke ut denne
    if (nevner == 1.0) {
      result = "" + teller;
    }
    // Skriver ut fullstendig resultat når nevner != 1
    else {
      result = teller + " / " + nevner;
    }
  }
}
