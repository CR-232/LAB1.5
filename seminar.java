package seminar;/*
sunt date 2 fire de execuție care citesc date din aceeași listă cu 50 de numere aleatoare între 0 și 100.
Primul fir citește lista de la început și sumează numerele câte 2 mai mici decât 50, afișând suma.
Al doilea fir citește lista de la sfârșit și face același lucru.
*/

public class seminar {
    public static void main(String[] args) {
        int tab[] = new int[50];


        System.out.println("Lista de numere:");
        for (int i = 0; i < 50; i++) {
            tab[i] = (int)(Math.random() * 100-1)+1;
            System.out.print(tab[i] + " ");
        }
        System.out.println("\n--------------------------------");


        Thread1 fir = new Thread1(0, 49, tab,1);
        Thread1 sec = new Thread1(49, 0, tab,-1);

        fir.start();
        sec.start();
    }
}
class Thread1 extends Thread {
    int from;
    int to;
    int[] tab;
    int pasul;

    public Thread1(int from, int to, int[] tab, int pasul) {
        this.from = from;
        this.to = to;
        this.tab = tab;
        this.pasul = pasul;
    }

    public void run() {
        int S = 0;
        int C = 0;

        for (int i = from; i != to; i=i+pasul) {
            if (tab[i] < 50) {
                S += tab[i];
                C++;
            }
            if (C == 2) {
                System.out.println(getName() + " Suma: " + S);
                C = 0;
                S = 0;
            }
            Thread.yield();
        }
    }


}

/*
class Thread2 extends Thread {
    int from;
    int to;
    int[] tab;

    public Thread2(int from, int to, int[] tab) {
        this.from = from;
        this.to = to;
        this.tab = tab;
    }

    public void run() {
        int S = 0;
        int C = 0;
        for (int i = from; i >= to; i--) {
            if (tab[i] < 50) {
                S += tab[i];
                C++;
            }
            if (C == 2) {
                System.out.println(getName() + " (de la sfârșit) -> Suma: " + S);
                C = 0;
                S = 0;
            }
        }
    }
}
*/