public class whichIsCheaper {

  public static void main(String[] args) {
    double kjøttdeigA = 35.90;
    double kjøttdeigB = 39.50;
    int vektKjøttdeigA = 450;
    int vektKjøttdeigB = 500;

    double prisPerGramKjøttdeigA = kjøttdeigA / vektKjøttdeigA; // Finner pris per gram for kjøttdeig A
    double prisPerGramKjøttdeigB = kjøttdeigB / vektKjøttdeigB; // Finner pris per gram for kjøttdeig B

    if (prisPerGramKjøttdeigA > prisPerGramKjøttdeigB) { // Hvis kjøttdeig A har lavest pris per gram
      System.out.println(
        "Kjøttdeig B er billigst, siden den koster " +
        String.format("%.5f", prisPerGramKjøttdeigB) +
        "kr per gram, mens kjøttdeig A koster " +
        String.format("%.5f", prisPerGramKjøttdeigA) +
        "kr per gram."
      );
    } else if (prisPerGramKjøttdeigA < prisPerGramKjøttdeigB) {
      System.out.println(
        "Kjøttdeig A er billigst, siden den koster " +
        String.format("%.5f", prisPerGramKjøttdeigA) +
        "kr per gram, mens kjøttdeig B koster " +
        String.format("%.5f", prisPerGramKjøttdeigB) +
        "kr per gram."
      );
    } else {
      System.out.println("Kjøttdeigene er like dyre.");
    }
  }
}
