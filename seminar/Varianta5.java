package seminar;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// ======================= DEPOZIT ===========================
class Depot {

    private final int CAPACITY = 12;   // D = 12
    private final int TARGET = 60;     // Z = 60

    private final Deque<Integer> buffer = new ArrayDeque<>(CAPACITY);

    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private int producedTotal = 0;
    private int consumedTotal = 0;

    private int nextEven = 2; // numere pare (2,4,6,...)

    public boolean isDone() {
        return consumedTotal >= TARGET;
    }

    // ============= PRODUCE =================
    public void produce(String name) throws InterruptedException {
        lock.lock();
        try {
            // dacă depozitul e plin → producătorul așteaptă
            while (buffer.size() == CAPACITY) {
                System.out.println(">>> Depozitul este PLIN. Producătorii așteaptă...");
                notFull.await();
            }

            if (producedTotal >= TARGET) return;

            int value = nextEven;
            nextEven += 2; // următorul număr par

            buffer.addLast(value);
            producedTotal++;

            System.out.printf("%s a produs %d | stoc=%d/%d | totalProd=%d%n",
                    name, value, buffer.size(), CAPACITY, producedTotal);

            // Dacă depozitul s-a umplut, consumatorii pot începe
            if (buffer.size() == CAPACITY) {
                System.out.println(">>> Depozitul este PLIN (12/12). Consumatorii pot începe.");
                notEmpty.signalAll();
            }

        } finally {
            lock.unlock();
        }
    }

    // ============= CONSUMĂ =================
    public void consume(String name) throws InterruptedException {
        lock.lock();
        try {

            while (buffer.isEmpty() || buffer.size() != CAPACITY && consumedTotal == 0) {
                notEmpty.await();
            }

            while (buffer.isEmpty()) {
                System.out.println("<<< Depozitul este GOL. Consumatorii așteaptă...");
                notEmpty.await();
            }

            if (consumedTotal >= TARGET) return;

            int value = buffer.removeFirst();
            consumedTotal++;

            System.out.printf("%s a consumat %d | stoc=%d/%d | totalCons=%d%n",
                    name, value, buffer.size(), CAPACITY, consumedTotal);


            if (buffer.isEmpty()) {
                System.out.println("<<< Depozitul este GOL (0/12). Producătorii pot începe.");
                notFull.signalAll();
            }

        } finally {
            lock.unlock();
        }
    }
}

// ======================= PRODUCĂTOR =========================
class Producer extends Thread {
    private final Depot depot;

    public Producer(Depot depot, String name) {
        super(name);
        this.depot = depot;
    }


    public void run() {
        try {
            while (!depot.isDone()) {
                depot.produce(getName());
                Thread.sleep(50); // simulare timp producție
            }
        } catch (InterruptedException ignored) {}
    }
}

// ======================= CONSUMATOR =========================
class Consumer extends Thread {
    private final Depot depot;

    public Consumer(Depot depot, String name) {
        super(name);
        this.depot = depot;
    }


    public void run() {
        try {
            while (!depot.isDone()) {
                depot.consume(getName());
                Thread.sleep(80); // simulare timp consum
            }
        } catch (InterruptedException ignored) {}
    }
}

// ======================= MAIN ===============================
public class Varianta5 {
    public static void main(String[] args) throws InterruptedException {

        Depot depot = new Depot();


        Producer[] producers = new Producer[2];
        for (int i = 0; i < 2; i++) {
            producers[i] = new Producer(depot, "Producator-" + (i + 1));
        }


        Consumer[] consumers = new Consumer[5];
        for (int i = 0; i < 5; i++) {
            consumers[i] = new Consumer(depot, "Consumator-" + (i + 1));
        }


        for (Producer p : producers) p.start();
        for (Consumer c : consumers) c.start();


        for (Producer p : producers) p.join();
        for (Consumer c : consumers) c.join();

        System.out.println("\n=== GATA! Au fost produse și consumate 60 de numere pare. ===");
    }
}
