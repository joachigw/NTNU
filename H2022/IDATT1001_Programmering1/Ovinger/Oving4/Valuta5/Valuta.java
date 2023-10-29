package Oving_4_F.Valuta5;

public class Valuta {

  String valutaName;
  double valutaConv;
  String valutaToFrom;
  double amount;
  double answer;

  // Konstruktør som angir verdier for de ulike objektvariablene
  public Valuta(
    String valutaName,
    double valutaConv,
    String valutaToFrom,
    double amount
  ) {
    this.valutaName = valutaName;
    this.valutaConv = valutaConv;
    this.valutaToFrom = valutaToFrom;
    this.amount = amount;
  }

  // Kalkulerer ifra mengden i NOK til den valgte valutaen, med hensyn på den
  // valgte valutaen sitt forhold til NOK
  public void calculateTo(double amount) {
    answer = amount * valutaConv;
  }

  public void calculateFrom(double amount) {
    answer = amount / valutaConv;
  }
}
