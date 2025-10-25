package seminar;

public class seminar {
    public static void main(String[] args) {
        int[] mas = new int[100];

        System.out.println("Tabloul de numere generate aleatoriu:");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int)(Math.random() * 100 - 1) + 1;
            System.out.print(mas[i] + " ");
        }
        System.out.println("\n-------------------------------------\n");
        Thread fir1 = new Thread(new Th1(mas), "Fir-1");
        Thread fir2 = new Thread(new Th2(mas), "Fir-2");
        Thread fir3 = new Thread(new Th3(mas), "Fir-3");
        Thread fir4 = new Thread(new Th4(mas), "Fir-4");

        try {
            fir1.start();
            fir1.join();

            fir2.start();
            fir2.join();

            fir3.start();
            fir3.join();

            fir4.start();
            fir4.join();






//
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String studenti = "RV și VC grupa CR-232";
        for (int i = 0; i < studenti.length(); i++) {
            System.out.print(studenti.charAt(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}

//------------------- TH1 -------------------
class Th1 implements Runnable {
    int[] mas;
    public Th1(int[] mas) { this.mas = mas; }


    public void run() {
        System.out.println(Thread.currentThread().getName() + " -> Condiția 1: început, poziții pare (metodă clasică)");
        int suma = 0;
        for (int i = 0; i < mas.length - 2; i += 2) {
            int produs = mas[i] * mas[i + 2];
            System.out.println("[" + i + "," + (i + 2) + "] " + mas[i] + " * " + mas[i + 2] + " = " + produs);
            suma += produs;
        }
        System.out.println("→ Suma totală (de la început) = " + suma + "\n");
    }
}

//------------------- TH2 -------------------
class Th2 implements Runnable {
    int[] mas;
    public Th2(int[] mas) { this.mas = mas; }


    public void run() {
        System.out.println(Thread.currentThread().getName() + " -> Condiția 2: sfârșit, poziții pare (metodă clasică)");
        int suma = 0;
        for (int i = mas.length - 2; i >= 2; i -= 2) {
            int produs = mas[i] * mas[i - 2];
            System.out.println("[" + i + "," + (i - 2) + "] " + mas[i] + " * " + mas[i - 2] + " = " + produs);
            suma += produs;
        }
        System.out.println("→ Suma totală (de la sfârșit) = " + suma + "\n");
    }
}

//------------------- TH3 -------------------
class Th3 implements Runnable {
    int[] mas;
    public Th3(int[] mas) { this.mas = mas; }


    public void run() {
        System.out.println(Thread.currentThread().getName() + " -> Condiția 1: început, poziții pare (stil alternativ de afișare)");
        int i = 0;
        int suma = 0;
        while (i < mas.length - 2) {
            int produs = mas[i] * mas[i + 2];
            System.out.printf("   ► Indici: (%02d , %02d)  |  Valori: (%3d , %3d)  |  Produs = %5d%n",
                    i, i + 2, mas[i], mas[i + 2], produs);
            suma += produs;
            i += 2;
        }
        System.out.println("────────────────────────────────────────────");
        System.out.println("★ Rezultatul final (începând de la început): " + suma + "\n");
    }
}

//------------------- TH4 -------------------
class Th4 implements Runnable {
    int[] mas;
    public Th4(int[] mas) { this.mas = mas; }


    public void run() {
        System.out.println(Thread.currentThread().getName() + " -> Condiția 2: sfârșit, poziții pare (afișare tip tabel compact)");
        int suma = 0;

        System.out.println("┌────┬────┬───────┬───────┬────────┐");
        System.out.println("│ i1 │ i2 │ val1  │ val2  │ produs │");
        System.out.println("├────┼────┼───────┼───────┼────────┤");

        for (int i = mas.length - 2; i >= 2; i -= 2) {
            int produs = mas[i] * mas[i - 2];
            System.out.printf("│ %2d │ %2d │ %5d │ %5d │ %6d │%n", i, i - 2, mas[i], mas[i - 2], produs);
            suma += produs;
        }

        System.out.println("└────┴────┴───────┴───────┴────────┘");
        System.out.println("→ Suma totală (de la sfârșit, tabel) = " + suma + "\n");
    }
}

