package seminar;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        int X = 2;   
        int Y = 5;
        int Z = 3;
        int D = 12;

        Store store = new Store(D);


        ExecutorService executor = Executors.newFixedThreadPool(X + Y);
//

        for (int i = 0; i < X; i++) {
            executor.execute(new Producer("Producator #" + (i + 1), store));
        }


        for (int i = 0; i < Y; i++) {
            executor.execute(new Consumer("Consumator #" + (i + 1), store, Z));
        }


        executor.shutdown();
    }
}

// ======================= DEPOZIT =======================
class Store {

    private final int capacity;
    private final ArrayList<Integer> buffer = new ArrayList<>();

    public Store(int capacity) {
        this.capacity = capacity;
    }

    // -------------------- PRODUCĂTOR --------------------
    public synchronized void put(String nume, int a, int b) {

        while (buffer.size() >= capacity) {
            System.out.println(">>> Depozitul este PLIN. " + nume + " așteaptă...");
            try { wait(); } catch (InterruptedException ignored) {}
        }

        System.out.println(nume + " a produs: " + a + ", " + b);

        if (buffer.size() == capacity - 1) {
            buffer.add(a);
        } else {
            buffer.add(a);
            buffer.add(b);
        }

        afiseazaDepozit();
        notifyAll();
    }

    // -------------------- CONSUMATOR --------------------
    public synchronized int get(String nume) {

        while (buffer.isEmpty()) {
            System.out.println(">>> Depozitul este GOL. " + nume + " așteaptă...");
            try { wait(); } catch (InterruptedException ignored) {}
        }

        int val = buffer.remove(buffer.size() - 1);
        System.out.println(nume + " a consumat: " + val);

        afiseazaDepozit();
        notifyAll();
        return val;
    }

    // -------------------- Stare depozit --------------------
    private void afiseazaDepozit() {
        if (buffer.isEmpty()) {
            System.out.println("Depozitul este GOL.\n");
            return;
        }

        System.out.print("Depozit (" + buffer.size() + "): ");
        for (int x : buffer) System.out.print(x + " ");
        System.out.println("\n");
    }
}


// ======================= PRODUCĂTOR =======================
class Producer implements Runnable {

    private final Store store;
    private final String nume;

    int[] pare = {2,4,6,8,10,12,14,16,18,20};

    public Producer(String nume, Store store) {
        this.nume = nume;
        this.store = store;
    }

    public void run() {
        while (true) {
            int a = pare[(int)(Math.random() * pare.length)];
            int b = pare[(int)(Math.random() * pare.length)];
            store.put(nume, a, b);

            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }
    }
}


// ======================= CONSUMATOR =======================
class Consumer implements Runnable {

    private final Store store;
    private final String nume;
    private final int need;

    public Consumer(String nume, Store store, int need) {
        this.nume = nume;
        this.store = store;
        this.need = need;
    }

    public void run() {
        int count = 0;

        while (count < need) {
            store.get(nume);
            count++;

            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }

        System.out.println(nume + " a consumat " + need + " obiecte și a finalizat.");

        if (nume.equals("Consumator #5")) {
            System.out.println("\n=== Toți consumatorii au fost îndestulați! ===");
        }
    }
}
