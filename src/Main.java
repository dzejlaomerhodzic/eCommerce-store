import com.company.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String, String> ime_kategorija = new HashMap<>();
        ArrayList<Kategorija> lista_kat = new ArrayList<>();
        HashMap<String, ArrayList<Purchase>> covjek_ajtemi = new HashMap<>();
        ArrayList<Inventory> listainv = new ArrayList<>();

        File fajl1 = new File("items.csv");
        Scanner sk = new Scanner(fajl1);
        HashMap<Double, Item> mapa1 = new HashMap<>();
        sk.nextLine();
        ArrayList<Item> lista1 = new ArrayList<>();

        while (sk.hasNextLine()) {
            String[] dijelovi = sk.nextLine().split(",");
            Inventory inv = new Inventory(dijelovi[0], dijelovi[2]);
            listainv.add(inv);
            Item a = new Item(dijelovi[0], dijelovi[1], dijelovi[2], dijelovi[3]);
            ime_kategorija.put(dijelovi[0], dijelovi[3]);

            lista1.add(a);

            mapa1.put(a.price, a);


        }

        Collections.sort(lista1);
        Collections.reverse(lista1);
        File fajl2 = new File("najprodavaniji.txt");
        FileWriter pisac1 = new FileWriter(fajl2);
        for (Item a : lista1) {
            pisac1.write(a.toString());
        }
        pisac1.close();

        File fajl3 = new File("purchases.csv");
        Scanner sk2 = new Scanner(fajl3);
        ArrayList<Purchase> lista2 = new ArrayList<>();
        sk2.nextLine();
        while (sk2.hasNextLine()) {
            String[] dijelovi = sk2.nextLine().split(",");
            Purchase a = new Purchase(dijelovi[0], dijelovi[1], dijelovi[2]);
            lista2.add(a);

            if (!covjek_ajtemi.containsKey(dijelovi[0])) covjek_ajtemi.put(dijelovi[0],new ArrayList<>());

            covjek_ajtemi.get(dijelovi[0]).add(a);


            for (Inventory inv : listainv) {
                if (a.item_name.equals(inv.name))
                    inv.Normalizuj(a.quantity);
            }
        }

        for (Purchase a : lista2) {
            for (Item b : lista1) {
                if (a.item_name.equals(b.name))
                    a.uradi(b.price);
            }
        }

        HashMap<String, ArrayList<Double>> mapa_za_duple = new HashMap<>();

        for (Purchase a : lista2) {
            if (!mapa_za_duple.containsKey(a.customer_name))
                mapa_za_duple.put(a.customer_name, new ArrayList<>());
        }

        for (Purchase a : lista2) {
            mapa_za_duple.get(a.customer_name).add(a.total_price);
        }

        for (String s : mapa_za_duple.keySet()) {
            Double g = 0.;
            for (Double d : mapa_za_duple.get(s)) {
                g += d;
            }
            mapa_za_duple.get(s).add(g);
        }

        File fajl4 = new File("najkupci.txt");
        FileWriter pisac5 = new FileWriter(fajl4);

        for (String s : mapa_za_duple.keySet()) {
            pisac5.write(s + "," + mapa_za_duple.get(s).get(mapa_za_duple.get(s).size() - 1) + '\n');
        }
        pisac5.close();

        HashMap<String, Integer> Item_kvantitet = new HashMap<>();
        for (Purchase a : lista2) {
            Item_kvantitet.put(a.item_name, a.quantity);
        }

        ArrayList<Kategorija> listastr = new ArrayList<>();

        for (String s : ime_kategorija.keySet()) {
            int f = 0;
            String L = "";
            for (String g : Item_kvantitet.keySet()) {
                if (s.equals(g)) {
                    f = Item_kvantitet.get(g);
                    L = ime_kategorija.get(s);
                }

            }
            if (f != 0) {
                Kategorija kat = new Kategorija(L, String.valueOf(f));
                listastr.add(kat);
            }

        }
        Collections.sort(listastr);

        File fajlPredzadnji = new File("najprodavanijeKategorije.txt");
        FileWriter predzadnji_pisac = new FileWriter(fajlPredzadnji);

        for (Kategorija k : listastr) predzadnji_pisac.write(k.toString());

        predzadnji_pisac.close();

        File fajl10 = new File("Invent  ory.txt");
        FileWriter zaInventory = new FileWriter(fajl10);

        for (Inventory i : listainv) {
            zaInventory.write(i.toString());
        }
        zaInventory.close();

        File trecidio = new File("trecidio.txt");
        FileWriter treci = new FileWriter(trecidio);
        String L="";

        for (String s: covjek_ajtemi.keySet()) {

            L="";

            Double total =0.;
            for (Purchase i: covjek_ajtemi.get(s)) {
                total+=i.total_price;
                L+=i.item_name +" " +i.quantity + " " + i.total_price + " ";}

            treci.write(s + " " + L + " he spent a total of: " + total + '\n');
        }
        treci.close();


        File fajlic = new File("najkupci.txt");
        Scanner skenerzadnji = new Scanner(fajlic);
        ArrayList<Kupac>listakupaca = new ArrayList<>();
        while (skenerzadnji.hasNextLine()){
            String []dijelovi2  = skenerzadnji.nextLine().split(",");

            Kupac kup = new Kupac(dijelovi2[0], dijelovi2[1]);
listakupaca.add(kup);
        }

        Collections.sort(listakupaca);
        FileWriter pisaccc = new FileWriter("najkupci.txt");
        for (Kupac k: listakupaca) {
            pisaccc.write(k.ime + " " + k.total + '\n');
        }
        pisaccc.close();

    }

}