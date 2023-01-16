
public class Abstand1 extends Datenverarbeitung{
    // berechnet den Abstand zweier Arrays
    @Override
    public double berechneAbstand(double[] d1, double[] d2) {
        double summe = 0;
        for (int i = 0; i < d1.length; i++) {
            summe += Math.pow((d1[i]-d2[i]), 2);
        }
        // returnt den Abstand1
        return Math.sqrt(summe);
    }
}
