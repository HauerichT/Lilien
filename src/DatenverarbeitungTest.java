public class DatenverarbeitungTest {
    public static void main(String[] args) {
        Datenverarbeitung datenverarbeitung = new Datenverarbeitung() {
            @Override
            public double berechneAbstand(double[] d1, double[] d2) {
                return 0;
            }
        };
        datenverarbeitung.ladenVearbeitenSpeichern("src/iris-2.data.txt");
    }
}
