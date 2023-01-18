public class DatenverarbeitungTest {
    public static void main(String[] args) {
        Abstand1 abstand1 = new Abstand1();
        abstand1.ladenVearbeitenSpeichern("src/iris-2.data.txt");
        Abstand2 abstand2 = new Abstand2();
        abstand2.ladenVearbeitenSpeichern("src/iris-2.data.txt");
    }
}
