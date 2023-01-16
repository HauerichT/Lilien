import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Datenverarbeitung {

    protected double[][] cluster1;
    protected double[][] cluster2;
    protected double[][] cluster3;


    public final void ladenVearbeitenSpeichern(String s) {
        ladeDaten(s);

        double[] mittelwert1 = berechneMittelwert(cluster1);
        double[] mittelwert2 = berechneMittelwert(cluster2);
        double[] mittelwert3 = berechneMittelwert(cluster3);

        Abstand1 abstand1 = new Abstand1();
        double a11 = abstand1.berechneAbstand(mittelwert1, mittelwert2);
        double a12 = abstand1.berechneAbstand(mittelwert1, mittelwert3);
        double a13 = abstand1.berechneAbstand(mittelwert2, mittelwert3);

        Abstand2 abstand2 = new Abstand2();
        double a21 = abstand2.berechneAbstand(mittelwert1, mittelwert2);
        double a22 = abstand2.berechneAbstand(mittelwert1, mittelwert3);
        double a23 = abstand2.berechneAbstand(mittelwert2, mittelwert3);

        ausgabe(a11, a12, a13);
        ausgabe(a21, a22, a23);

    }

    public abstract double berechneAbstand(double[] d1, double[] d2);

    public double[] berechneMittelwert(double[][] d) {
        double summe = 0;
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                summe = summe + d[i][j];
            }
        }
        return new double[]{summe / d.length};
    }

    public void ladeDaten(String datei) {
        try {
            // liest die Daten Zeile für Zeile ein
            List<String> list = Files.readAllLines(Path.of(datei));

            // ermittelt die Anzahl der Linien pro Art
            int countCluster1 = 0;
            int countCluster2 = 0;
            int countCluster3 = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).contains("Iris-setosa")) {
                    countCluster1++;
                }
                if (list.get(i).contains("Iris-versicolor")) {
                    countCluster2++;
                }
                if (list.get(i).contains("Iris-virginica")) {
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



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ausgabe(double a1, double a2, double a3) {
        System.out.printf("d(cluster1, cluster2) = " + a1 + " d(cluster1, cluster3) = " + a2 + " d(cluster2, cluster3) = " + a3);
        System.out.println();
    }

}
