package seminar;

public class LAB2 {
    public static void main(String[] args) {

        ThreadGroup mainGroup = new ThreadGroup("Main");


        ThreadGroup goGroup = new ThreadGroup(mainGroup, "GO");
        ThreadGroup gvGroup = new ThreadGroup(mainGroup, "GV");
        ThreadGroup gfGroup = new ThreadGroup(mainGroup, "GF");


        ThreadGroup gzGroup = new ThreadGroup(goGroup, "GZ");


        Thread tha = new MyThread(gzGroup, "Tha", 1);
        Thread thb = new MyThread(gzGroup, "Thb", 3);
        Thread thc = new MyThread(gzGroup, "Thc", 3);
        Thread thd = new MyThread(gzGroup, "Thd", 7);


        Thread thA = new MyThread(gvGroup, "ThA", 3);


        Thread th1_GF = new MyThread(gfGroup, "Th1", 5);
        Thread th2_GF = new MyThread(gfGroup, "Th2", 3);
        Thread th3_GF = new MyThread(gfGroup, "Th3", 9);


        Thread th1_main = new MyThread(mainGroup, "Th1", 3);
        Thread th2_main = new MyThread(mainGroup, "Th2", Thread.NORM_PRIORITY); // implicit 5


        tha.start();
        thb.start();
        thc.start();
        thd.start();
        thA.start();
        th1_GF.start();
        th2_GF.start();
        th3_GF.start();
        th1_main.start();
        th2_main.start();


        System.out.println("\n=== Structura grupurilor și firelor (prin list()) ===");
        mainGroup.list();
    }
}


class MyThread extends Thread {
    MyThread(ThreadGroup group, String name, int priority) {
        super(group, name);
        this.setPriority(priority);
    }

    @Override
    public void run() {
        System.out.println("Rulează: " + getName() +
                " | Grup: " + getThreadGroup().getName() +
                " | Prioritate: " + getPriority());
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}
    }
}

