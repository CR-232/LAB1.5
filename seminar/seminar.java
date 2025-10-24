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



        try {
            fir1.start();
            fir1.join();

            fir2.start();
            fir2.join();







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
