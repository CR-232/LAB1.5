public class Main {
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


        try {
            fir1.start();
            fir1.join();

            fir2.start();
            fir2.join();


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
    }
}


