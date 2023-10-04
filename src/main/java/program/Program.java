package program;

import java.util.Scanner;
import model.Tacka;
import model.Poligon;

public class Program {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Poligon poligon = new Poligon();

        int brojTemena;

        while (true) {
            System.out.println("Unesite broj temena mnogougla (minimum 3): ");
            if (sc.hasNextInt()) {
                brojTemena = sc.nextInt();
                if (brojTemena >= 3) {
                    break;
                } else {
                    System.out.println("Molimo vas unesite ispravan broj temena (minimum 3).");
                }
            } else {
                System.out.println("Nevažeći unos. Molimo unesite ispravan broj.");
                sc.next();
            }
        }
        System.out.println("Temena unosite redom u bilo kom smeru ");
        System.out.println("Koordinate unosite u formatu X,X ili kao cele brojeve");
        System.out.println("--------------------------------");

        double[] xCoordinates = new double[brojTemena];
        double[] yCoordinates = new double[brojTemena];

        for (int i = 0; i < brojTemena; i++) {
            while (true) {
                System.out.println("Unesite X koordinatu temena " + (i + 1) + ":");
                if (sc.hasNextDouble()) {
                    xCoordinates[i] = sc.nextDouble();
                    break;
                } else {
                    System.out.println("Nevažeći unos. Molimo unesite ispravne koordinate.");
                    sc.next();
                }
            }

            while (true) {
                System.out.println("Unesite Y koordinatu temena " + (i + 1) + ":");
                if (sc.hasNextDouble()) {
                    yCoordinates[i] = sc.nextDouble();
                    break;
                } else {
                    System.out.println("Nevažeći unos. Molimo unesite ispravne koordinate.");
                    sc.next();
                }
            }
        }
       
        for (int i = 0; i < xCoordinates.length && i < yCoordinates.length; i++) {
            Tacka tacka = new Tacka(xCoordinates[i], yCoordinates[i]);
            poligon.dodajTacku(tacka);
        }

        System.out.println("TACKA ZA PROVERU ");
        System.out.println("--------------------------------");
        double XProvera, YProvera;

        while (true) {
            System.out.println("Unesite X koordinate tacke za proveru: ");
            if (sc.hasNextDouble()) {
                XProvera = sc.nextDouble();
                break;
            } else {
                System.out.println("Nevažeći unos. Molimo unesite ispravne koordinate.");
                sc.next();
            }
        }

        while (true) {
            System.out.println("Unesite Y koordinate tacke za proveru:");
            if (sc.hasNextDouble()) {
                YProvera = sc.nextDouble();
                break;
            } else {
                System.out.println("Nevažeći unos. Molimo unesite ispravne koordinate.");
                sc.next();
            }
        }

        Tacka provera = new Tacka(XProvera, YProvera);

        boolean teme = poligon.daLiJeTeme(provera);
        if (teme) {
            System.out.println("Uneli ste teme poligona");
            sc.close();
            return;
        }

        boolean unutra = poligon.proveriTacku(provera);

        if (unutra) {
            System.out.println("RESENJE: Tacka je unutar poligona.");
        } else {
            System.out.println("RESENJE: Tacka je van poligona.");
        }

        sc.close();
    }
}
