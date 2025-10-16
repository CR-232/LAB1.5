package seminar;

public class seminar {
    public static void main(String[] args) {
        int[] mas = new int[100];


        System.out.println("Tabloul de numere generate aleatoriu:");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int)(Math.random() * 100) + 1;
            System.out.print(mas[i] + " ");
        }
        System.out.println("\n--------------------------------\n");


        Th1 fir1 = new Th1(mas);
        Th2 fir2 = new Th2(mas);
        Th3 fir3 = new Th3(mas);
        Th4 fir4 = new Th4(mas);

        try {
            fir1.start();
            fir1.join();

            fir2.start();
            fir2.join();

            fir3.start();
            fir3.join();

            fir4.start();
            fir4.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("\nLucrarea de laborator realizată de: RV și VC.");
    }
}

class Th1 extends Thread {
    int[] mas;
    public Th1(int[] mas) { this.mas = mas; }

    public void run() {
        System.out.println(getName() + " -> Condiția 1: început, poziții pare (metodă clasică)");
        int suma = 0;
        for (int i = 0; i < mas.length - 2; i += 2) {
            int produs = mas[i] * mas[i + 2];
            System.out.println("[" + i + "," + (i + 2) + "] " + mas[i] + " * " + mas[i + 2] + " = " + produs);
            suma += produs;
        }
        System.out.println("→ Suma totală (de la început) = " + suma + "\n");
    }
}

class Th2 extends Thread {
    int[] mas;
    public Th2(int[] mas) { this.mas = mas; }

    public void run() {
        System.out.println(getName() + " -> Condiția 2: sfârșit, poziții pare (metodă clasică)");
        int suma = 0;
        for (int i = mas.length - 2; i >= 2; i -= 2) {
            int produs = mas[i] * mas[i - 2];
            System.out.println("[" + i + "," + (i - 2) + "] " + mas[i] + " * " + mas[i - 2] + " = " + produs);
            suma += produs;
        }
        System.out.println("→ Suma totală (de la sfârșit) = " + suma + "\n");
        //test
    }
}

class Th3 extends Thread {
    int[] mas;
    public Th3(int[] mas) { this.mas = mas; }

    public void run() {
        System.out.println(getName() + " -> Condiția 1: început, poziții pare (folosind WHILE)");
        int i = 0;
        int suma = 0;
        while (i < mas.length - 2) {
            int produs = mas[i] * mas[i + 2];
            System.out.printf("[%d,%d] %d * %d = %d%n", i, i + 2, mas[i], mas[i + 2], produs);
            suma += produs;
            i += 2;
        }
        System.out.println("→ Suma totală (de la început, WHILE) = " + suma + "\n");
    }
}

// ======================== FIR 4 ============================

class Th4 extends Thread {
    int[] mas;
    public Th4(int[] mas) { this.mas = mas; }

    public void run() {
        System.out.println(getName() + " -> Condiția 2: sfârșit, poziții pare (alt stil de cod - for invers)");
        int suma = 0;
        for (int i = mas.length; i >= 4; i -= 2) {
            int a = mas[i - 2];
            int b = mas[i - 4];
            int produs = a * b;
            System.out.printf("[%d,%d] %d * %d =%d%n", i - 2, i - 4, a, b, produs);
            suma += produs;
        }
        System.out.println("→ Suma totală (de la sfârșit, alt stil) = " + suma + "\n");
    }
}

