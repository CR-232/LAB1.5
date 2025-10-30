package seminar;

class seminar {
    public static void main(String[] args) {

        // Grupul principal (implicit)
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        // Creăm grupurile G1 și G2
        ThreadGroup G1 = new ThreadGroup(mainGroup, "G1");
        ThreadGroup G2 = new ThreadGroup(mainGroup, "G2");

        // G3 este subgrup al G1
        ThreadGroup G3 = new ThreadGroup(G1, "G3");

        // Grupul G3 cu firele Tha(3), Thb(3), Thc(3), Thd(3)
        Thread Tha = new Fir("Tha", G3, 3);
        Thread Thb = new Fir("Thb", G3, 3);
        Thread Thc = new Fir("Thc", G3, 3);
        Thread Thd = new Fir("Thd", G3, 3);

        //Grupul G2 cu firele Th1(4), Th2(5), Th3(5)
        Thread Th1_G2 = new Fir("Th1_G2", G2, 4);
        Thread Th2_G2 = new Fir("Th2_G2", G2, 5);
        Thread Th3_G2 = new Fir("Th3_G2", G2, 5);

        // Grupul principal cu Th1(7), Th2(7), ThA(3)
        Thread Th1_Main = new Fir("Th1_Main", mainGroup, 7);
        Thread Th2_Main = new Fir("Th2_Main", mainGroup, 7);
        Thread ThA_Main = new Fir("ThA_Main", mainGroup, 3);

        // Pornim toate firele
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

        // Afișăm structura completă
        System.out.println("\n==========================================");
        System.out.println("=== STRUCTURA COMPLETĂ A FIRELOR ===");
        System.out.println("==========================================\n");
        afisareStructura(mainGroup, ""); // apelăm metoda recursivă
    }

    // Metodă recursivă pentru afișarea ierarhiei de grupuri și fire
    public static void afisareStructura(ThreadGroup group, String indent) {
        System.out.println(indent + "Grup: " + group.getName() + " (Prioritate maximă: " + group.getMaxPriority() + ")");
        int activeThreads = group.activeCount();
        Thread[] threads = new Thread[activeThreads];
        group.enumerate(threads, false);

        for (Thread t : threads) {
            if (t != null)
                System.out.println(indent + "   └─  Fir: " + t.getName() + " (Prioritate: " + t.getPriority() + ")");
        }

        int activeGroups = group.activeGroupCount();
        ThreadGroup[] subGroups = new ThreadGroup[activeGroups];
        group.enumerate(subGroups, false);

        for (ThreadGroup g : subGroups) {
            if (g != null)
                afisareStructura(g, indent + "   ");
        }
    }
}


class Fir extends Thread {

    public Fir(String name, ThreadGroup group, int priority) {
        super(group, name);
        setPriority(priority);
    }

    @Override
    public void run() {
        System.out.println("→ Firul pornit: " + getName() +
                " | Grup: " + getThreadGroup().getName() +
                " | Prioritate: " + getPriority());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
