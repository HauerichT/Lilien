import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public abstract class Datenverarbeitung {

    // Arrays für die Cluster
    protected double[][] cluster1;
    protected double[][] cluster2;
    protected double[][] cluster3;


    // Methode führt die einzelnen Schritte des Programms aus
    public final void ladenVearbeitenSpeichern(String s) {
        // lädt und verarbeitet die Daten
        ladeDaten(s);

        // ermittelt die Mittelwerte der Cluster
        double[] mittelwert1 = berechneMittelwert(cluster1);
        double[] mittelwert2 = berechneMittelwert(cluster2);
        double[] mittelwert3 = berechneMittelwert(cluster3);

        // berechnet den Abstand 1
        Abstand1 abstand1 = new Abstand1();
        double a11 = abstand1.berechneAbstand(mittelwert1, mittelwert2);
        double a12 = abstand1.berechneAbstand(mittelwert1, mittelwert3);
        double a13 = abstand1.berechneAbstand(mittelwert2, mittelwert3);

        // berechnet den Abstand 2
        Abstand2 abstand2 = new Abstand2();
        double a21 = abstand2.berechneAbstand(mittelwert1, mittelwert2);
        double a22 = abstand2.berechneAbstand(mittelwert1, mittelwert3);
        double a23 = abstand2.berechneAbstand(mittelwert2, mittelwert3);

        // gibt die Abstände der Cluster aus
        ausgabe(a11, a12, a13);
        ausgabe(a21, a22, a23);
    }

    // Abstrakte-Methode zur Berechnung der Abstände
    public abstract double berechneAbstand(double[] d1, double[] d2);

    // Methode zur Ermittlung des Mittelwertes
    public double[] berechneMittelwert(double[][] d) {
        double[] temp = new double[d.length];

        for (double[] doubles : d) {
            temp[0] += doubles[0];
            temp[1] += doubles[1];
            temp[2] += doubles[2];
            temp[3] += doubles[3];
        }

        temp[0] = temp[0]/d.length;
        temp[1] = temp[1]/d.length;
        temp[2] = temp[2]/d.length;
        temp[3] = temp[3]/d.length;
        return temp;
    }

    // lädt und verarbeitet Daten aus Datei
    public void ladeDaten(String datei) {
        try {
            // liest die Daten Zeile für Zeile ein
            List<String> list = Files.readAllLines(Path.of(datei));

            // ermittelt die Anzahl der Linien pro Art
            int countCluster1 = 0;
            int countCluster2 = 0;
            int countCluster3 = 0;

            for (String s : list) {
                if (s.contains("Iris-setosa")) {
                    countCluster1++;
                }
                if (s.contains("Iris-versicolor")) {
                    countCluster2++;
                }
                if (s.contains("Iris-virginica")) {
                    countCluster3++;
                }
            }

            // instanziiert die Cluster-Arrays
            this.cluster1 = new double[countCluster1][4];
            this.cluster2 = new double[countCluster2][4];
            this.cluster3 = new double[countCluster3][4];

            // temporäre Array-Variablen
            String[] tempStringArray;
            double[] tempDoubleArray;

            // Counter zur Ermittlung des Array-Indexes
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;

            // fügt die Daten in das jeweilige Cluster hinzu
            for (int i = 0; i < list.size(); i++) {
                tempStringArray = list.get(i).split(",");
                tempDoubleArray = new double[4];

                if (list.get(i).contains("Iris-setosa")) {
                    for (int j = 0; j < 4; j++) {
                        tempDoubleArray[j] = Double.parseDouble(tempStringArray[j]);
                    }
                    cluster1[count1] = tempDoubleArray;
                    count1++;
                }
                else if (list.get(i).contains("Iris-versicolor")) {
                    for (int j = 0; j < 4; j++) {
                        tempDoubleArray[j] = Double.parseDouble(tempStringArray[j]);
                    }
                    cluster2[count2] = tempDoubleArray;
                    count2++;                }
                else if (list.get(i).contains("Iris-virginica")) {
                    for (int j = 0; j < 4; j++) {
                        tempDoubleArray[j] = Double.parseDouble(tempStringArray[j]);
                    }
                    cluster3[count3] = tempDoubleArray;
                    count3++;
                }
                else {
                    System.out.println("Zeile " + i + "enthält keinen Namen der Linienart oder die Linienart ist nicht bekannt!");
                }
            }
        // wirft Fehler, wenn Datei nicht gelesen werden kann
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // gibt die Abstände auf der Konsole aus
    public void ausgabe(double a1, double a2, double a3) {
        System.out.printf("d(cluster1, cluster2) = %.2f\n", a1);
        System.out.printf("d(cluster1, cluster3) = %.2f\n", a2);
        System.out.printf("d(cluster2, cluster3) = %.2f\n", a3);
        System.out.println();
    }

}
