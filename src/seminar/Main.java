package seminar;

import java.util.Random;

public class Main {

    public static Thread t1, t2;

    public static void main(String[] args) throws InterruptedException {

        int[] mas = new int[1101];
        Random r = new Random();

        System.out.println("Generare masiv...");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = r.nextInt(2000) + 100;
        }

        System.out.println("Start fire...\n");

        t1 = new Th1(mas); t1.setName("Th1");
        t2 = new Th2(mas); t2.setName("Th2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\n=== Program finalizat ===");
    }
}

//────────────────────────────────────────────
// FIR 1 – interval 567 → 1002 (de la început)
//────────────────────────────────────────────
class Th1 extends Thread {
    int[] m;

    Th1(int[] a) { m = a; }

    @Override
    public void run() {

        System.out.println(getName() + " START (567–1002, început)");

        for (int i = 567; i < 1002; i += 2) {
            System.out.println(getName() + " Produs: " + m[i] + "*" + m[i+1] + "=" + (m[i] * m[i+1]));
            Thread.yield();
        }

        System.out.println(getName() + " DONE\n");

        // Așteaptă Th2 pentru mesajul final
        try {
            while (Main.t2.isAlive()) {
                Thread.sleep(50);
            }

            String t = "Th1 -> Prenume student: Vladimir, Veceslav\n";
            for (char c : t.toCharArray()) {
                System.out.print(c);
                Thread.sleep(100);
            }

        } catch (Exception ignored) {}
    }
}

//────────────────────────────────────────────
// FIR 2 – interval 1002 → 567 (de la sfârșit)
//────────────────────────────────────────────
class Th2 extends Thread {
    int[] m;

    Th2(int[] a) { m = a; }

    @Override
    public void run() {

        System.out.println(getName() + " START (1002–567, sfârșit)");

        for (int i = 1002; i > 567; i -= 2) {
            System.out.println(getName() + " Produs: " + m[i] + "*" + m[i-1] + "=" + (m[i] * m[i-1]));
        }

        System.out.println(getName() + " DONE\n");

        try {
            String t = "Th2 -> Nume student: Rusanovschi, Covalciuc\n";
            for (char c : t.toCharArray()) {
                System.out.print(c);
                Thread.sleep(100);
            }

        } catch (Exception ignored) {}
    }
}
