package seminar;

import java.util.Random;

public class Main {

    public static Thread t1, t2,t3,t4;

    public static void main(String[] args) throws InterruptedException {

        int[] mas = new int[1101];
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int)(Math.random() * 2000) + 100;
        }


        System.out.println("Start fire...\n");

        t1 = new Th1(mas); t1.setName("Th1");
        t2 = new Th2(mas); t2.setName("Th2");
        t3 = new Th3(mas); t3.setName("Th3");
        t4 = new Th4(mas); t4.setName("Th4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();


        System.out.println("\n=== Program finalizat ===");
    }
}

// FIR 1 – interval 567 → 1002 (de la început)

class Th1 extends Thread {
    int[] m;

    Th1(int[] a) { m = a; }


    public void run() {

        System.out.println(getName() + " START (567–1002, început)");

        for (int i = 567; i < 1002; i += 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" + m[i+1] + "=" + (m[i]*m[i+1]));
            Thread.yield();
        }

        System.out.println(getName() + " DONE\n");


        try {
            while (Main.t4.isAlive()) {
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


// FIR 2 – interval 1002 → 567 (de la sfârșit)

class Th2 extends Thread {
    int[] m;

    Th2(int[] a) { m = a; }


    public void run() {

        System.out.println(getName() + " START (1002–567, sfârșit)");

        for (int i = 1002; i > 567; i -= 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" + m[i-1] + "=" + (m[i]*m[i-1]));
        }

        System.out.println(getName() + " DONE\n");

        try {
            // PRIMUL mesaj final – se execută fără așteptări
            String t = "Th2 -> Nume student: Rusanovschi, Covalciuc\n";
            for (char c : t.toCharArray()) {
                System.out.print(c);
                Thread.sleep(100);
            }
        } catch (Exception ignored) {}
    }
}

// FIR 3 – 567 → 1100 (de la început)

class Th3 extends Thread {
    int[] m;

    Th3(int[] a) { m = a; }


    public void run() {

        System.out.println(getName() + " START (567–1100, început)");

        for (int i = 567; i < 1100; i += 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" + m[i+1] + "=" + (m[i]*m[i+1]));
        }

        System.out.println(getName() + " DONE\n");


        try {
            while (Main.t1.isAlive()) {
                Thread.sleep(50);
            }

            Thread.sleep(200);

            String t = "Th3 -> Disciplina: Programarea concurentă și distribuită\n";
            for (char c : t.toCharArray()) {
                System.out.print(c);
                Thread.sleep(100);
            }

        } catch (Exception ignored) {}
    }
}

//────────────────────────────────────────────
// FIR 4 – interval 1100 → 567 (de la sfârșit)
//────────────────────────────────────────────
class Th4 extends Thread {
    int[] m;

    Th4(int[] a) { m = a; }


    public void run() {

        System.out.println(getName() + " START (1100–567, sfârșit)");

        for (int i = 1100; i > 567; i -= 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" +m[i-1] + "=" + (m[i]*m[i-1]));
        }

        System.out.println(getName() + " DONE\n");

        // Th4 așteaptă Th2
        try {
            while (Main.t2.isAlive()) {
                Thread.sleep(50);
            }

            String t = "Th4 -> Grupa: CR-232\n";
            for (char c : t.toCharArray()) {
                System.out.print(c);
                Thread.sleep(100);
            }

        } catch (Exception ignored) {}
    }
}

