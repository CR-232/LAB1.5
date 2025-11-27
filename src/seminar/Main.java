package seminar;

public class Main {

    public static Thread t1, t2, t3, t4;

    public static void main(String[] args) throws InterruptedException {

        int[] mas = new int[1101];
        for (int i = 0; i < mas.length; i++)
            mas[i] = (int)(Math.random() * 2000) + 100;

        t1 = new Th1(mas); t1.setName("Th1");
        t2 = new Th2(mas); t2.setName("Th2");
        t3 = new Th3(mas); t3.setName("Th3");
        t4 = new Th4(mas); t4.setName("Th4");

        t1.start();
        t3.start();
        t2.start();
        t4.start();

        t3.join();

        System.out.println("\n=== Program finalizat ===");
    }

    static void slowPrint(String s) throws InterruptedException {
        for (char c : s.toCharArray()) {
            System.out.print(c);
            Thread.sleep(100);
        }
    }
}



// FIR 1 – sleep()

class Th1 extends Thread {
    int[] m;
    Th1(int[] a) { m = a; }

    public void run() {

        for (int i = 567; i < 1002; i += 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" + m[i+1] + "=" + (m[i]*m[i+1]));

        }
        try {

            while (Main.t4.isAlive())
                Thread.sleep(50);

            Thread.sleep(200);

            Main.slowPrint("Th1 -> Prenume student: Vladimir, Veceslav\n");

        } catch (Exception ignored) {}
    }
}



// FIR 2 – yield()

class Th2 extends Thread {
    int[] m;
    Th2(int[] a) { m = a; }

    public void run() {


        for (int i = 1002; i > 567; i -= 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" + m[i-1] + "=" + (m[i]*m[i-1]));

        }
        Thread.yield();
        try {
            Main.slowPrint("Th2 -> Nume student: Rusanovschi, Covalciuc\n");
        } catch (Exception ignored) {}
    }
}



// FIR 3 – interrupt()

class Th3 extends Thread {
    int[] m;
    Th3(int[] a) { m = a; }

    public void run() {

        for (int i = 567; i < 1100; i += 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" + m[i+1] + "=" + (m[i]*m[i+1]));
        }
        Main.t4.interrupt();

        try {
            Main.t1.join();

            Main.slowPrint("Th3 -> Disciplina: Programarea concurentă și distribuită\n");

        } catch (Exception ignored) {}
    }
}



// FIR 4 – join()

class Th4 extends Thread {
    int[] m;
    Th4(int[] a) { m = a; }

    public void run() {

        for (int i = 1100; i > 567; i -= 2) {
            System.out.println(getName() + " FIR: " + m[i] + "*" +m[i-1] + "=" + (m[i]*m[i-1]));
        }
        try {

            while (!Thread.interrupted()) {}


            Main.t2.join();

            Main.slowPrint("Th4 -> Grupa: CR-232\n");

        } catch (Exception ignored) {}
    }
}
