package seminar;

// 🔹 Clasa principală
public class StructuraAvansata {
    public static void main(String[] args) {

        // Grupul principal (Main)
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();


        ThreadGroup G1 = new ThreadGroup(mainGroup, "G1");
        ThreadGroup G2 = new ThreadGroup(mainGroup, "G2");


        ThreadGroup G3 = new ThreadGroup(G1, "G3");


        Thread Tha = new Fir("Tha", G3, 3);
        Thread Thb = new Fir("Thb", G3, 3);
        Thread Thc = new Fir("Thc", G3, 3);
        Thread Thd = new Fir("Thd", G3, 3);

        Thread Th1_G2 = new Fir("Th1", G2, 4);
        Thread Th2_G2 = new Fir("Th2", G2, 5);
        Thread Th3_G2 = new Fir("Th3", G2, 5);


        Thread Th1_Main = new Fir("Th1", mainGroup, 7);
        Thread Th2_Main = new Fir("Th2", mainGroup, 7);
        Thread ThA_Main = new Fir("ThA", mainGroup, 3);


        Tha.start();
        Thb.start();
        Thc.start();
        Thd.start();

        Th1_G2.start();
        Th2_G2.start();
        Th3_G2.start();

        Th1_Main.start();
        Th2_Main.start();
        ThA_Main.start();

        System.out.println("\n=== Structura completă a firelor și grupurilor ===");
        mainGroup.list();
    }
}


class Fir extends Thread {

    public Fir(String name, ThreadGroup group, int priority) {
        super(group, name);
        setPriority(priority);
    }


    public void run() {
        System.out.println("Firul: " + getName() +
                " | Grup: " + getThreadGroup().getName() +
                " | Prioritate: " + getPriority());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
