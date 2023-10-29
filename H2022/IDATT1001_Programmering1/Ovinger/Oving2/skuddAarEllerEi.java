import javax.swing.JOptionPane;

public class skuddAarEllerEi {

  public static void main(String[] args) {
    String yearFromInput = JOptionPane.showInputDialog("Angi et årstall: ");
    int yearInt = Integer.parseInt(yearFromInput);
    String ikkeSkuddaar = " er IKKE et skuddår.";
    String erSkuddaar = " er et skuddår.";

    if ((yearInt % 100) == 0) { // Sjekker om tallet kan deles på 100
      if ((yearInt % 400) == 0) { // Sjekker om tallet også kan deles på 400
        System.out.println("År " + yearInt + erSkuddaar);
      } else {
        System.out.println("År " + yearInt + ikkeSkuddaar);
      }
    } else {
      if ((yearInt % 4) == 0) {
        System.out.println("År " + yearInt + erSkuddaar);
      } else {
        System.out.println("År " + yearInt + ikkeSkuddaar);
      }
    }
  }
}
