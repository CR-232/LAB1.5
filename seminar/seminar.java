package seminar;

import java.util.ArrayList;

public class seminar {
    public static void main(String[] args) throws InterruptedException {


        int X = 2;
        int Y = 5;
        int Z = 3;
        int D = 12;

        Store store = new Store(D);


        Producer[] producatori = new Producer[X];
        for (int i = 0; i < X; i++) {
            producatori[i] = new Producer(store);
            producatori[i].setName("Producator #" + (i + 1));
            producatori[i].setDaemon(true);
        }

        Consumer[] consumatori = new Consumer[Y];
        for (int i = 0; i < Y; i++) {
            consumatori[i] = new Consumer(store, Z);
            consumatori[i].setName("Consumator #" + (i + 1));
        }



        for (Producer p : producatori) p.start();
        for (Consumer c : consumatori) c.start();


        for (Consumer c : consumatori) c.join();

        System.out.println("\n=== Toți consumatorii au fost îndestulați! ===");
    }
}


// -------------------- Depozitul  --------------------
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
            try { wait(); } catch (InterruptedException e) {}
        }

        System.out.println(nume + " a produs: " + a + ", " + b);

        buffer.add(a);
        buffer.add(b);

        afiseazaDepozit();
        notifyAll();
    }

    // -------------------- CONSUMATOR --------------------
    public synchronized int get(String nume) {

        while (buffer.isEmpty()) {
            System.out.println(">>> Depozitul este GOL. " + nume + " așteaptă...");
            try { wait(); } catch (InterruptedException e) {}
        }

        int val = buffer.remove(buffer.size() - 1);
        System.out.println(nume + " a consumat: " + val);

        afiseazaDepozit();
        notifyAll();
        return val;
    }

    // -------------------- Afișarea stării depozitului --------------------
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


// -------------------- Producător --------------------
class Producer extends Thread {

    Store store;


    int[] pare = {2,4,6,8,10,12,14,16,18,20};

    public Producer(Store store) {
        this.store = store;
    }

///////////
    public void run() {
        while (true) {
            int a = pare[(int)(Math.random() * pare.length)];
            int b = pare[(int)(Math.random() * pare.length)];
            store.put(getName(), a, b);

            try { Thread.sleep(300); } catch (InterruptedException e) {}
        }
    }
}


// -------------------- Consumator --------------------//git
class Consumer extends Thread {

    Store store;
    int need;

    public Consumer(Store store, int need) {
        this.store = store;
        this.need = need;
    }


    public void run() {
        int count = 0;

        while (count < need) {
            store.get(getName());
            count++;

            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
        System.out.println(getName() + " a consumat " + need + " obiecte și a finalizat.");
    }
}