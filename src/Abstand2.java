public class Abstand2 extends Datenverarbeitung{
    @Override
    public double berechneAbstand(double[] d1, double[] d2) {
        double summe = 0;
        for (int i = 0; i < d1.length; i++) {
            double temp = d1[i]-d2[i];
            if (temp < 0) {
                summe = summe + (temp*-1);
            }
            else {
                summe = summe + temp;
            }
        }
        return summe;
    }
}
