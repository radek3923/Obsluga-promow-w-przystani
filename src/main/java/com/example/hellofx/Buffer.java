package com.example.hellofx;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    public int rozmiarParkingu;
    public static int liczbaAutNaParkingu;
    public static int liczbaAutNaParkinguTymczasowo;
    public int wej = 0;
    public int wyj = 0;
    public String[] pula;
    final Lock dostep = new ReentrantLock();
    final Condition pelny = dostep.newCondition();
    final Condition pusty = dostep.newCondition();
    public int liczbaSamochodowNaPokladzie;
    public long czasPostojuPromu = 0;
    public long startLiczeniaCzasu;
    public long koniecLiczeniaCzasu;
    public long czasWyladunkuSamochodu;
    public int liczbaPromow;
    Label licznikSamochodowWBuforze;
    AnchorPane parkingSamochodowAnchor;
    Circle swiatloProdukcji;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Buffer(int rozmiarParkingu, int liczbaPromow, AnchorPane parkingSamochodowAnchor, Label licznikSamochodowWBuforze, String[] pula, long czasWyladunkuSamochodu, Circle swiatloProdukcji) {
        this.rozmiarParkingu = rozmiarParkingu;
        this.liczbaPromow = liczbaPromow;
        this.parkingSamochodowAnchor = parkingSamochodowAnchor;
        this.licznikSamochodowWBuforze = licznikSamochodowWBuforze;
        this.pula = pula;
        this.czasWyladunkuSamochodu = czasWyladunkuSamochodu;
        this.swiatloProdukcji = swiatloProdukcji;
    }

    public int ileSamochodowNaPokladzie(String[] pokladPromu){
        int liczbaSamochodowNaPokladzie =0;
        for (int i=0; i<pokladPromu.length;i++) {
            if (pokladPromu[i] != null) {
                liczbaSamochodowNaPokladzie++;
            }
        }
        return liczbaSamochodowNaPokladzie;
    }
    public void wstaw(String nrSamochodu, String nazwaPrzystani, double szerokoscSamochodu, double wysokoscSamochodu, Rectangle kladkaWjazdu, Rectangle droga, long czasTrwaniaAnimacjiSamochodu) {
        dostep.lock();
        try {
            if (liczbaAutNaParkinguTymczasowo == rozmiarParkingu) {
                try {
                    System.out.println(ANSI_YELLOW + "Przystan " + nazwaPrzystani + " jest gotowa produkowac samochod, ale stan parkingu: [" + liczbaAutNaParkinguTymczasowo + "/" + rozmiarParkingu + "] wiec czeka, az zwolni sie miejsce" + ANSI_RESET);
                    swiatloProdukcji.setFill(Color.RED);
                    pelny.await();
                    swiatloProdukcji.setFill(Color.GREEN);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(ANSI_YELLOW + "Przystan " + nazwaPrzystani + " produkuje samochod o nr: " + nrSamochodu + ANSI_RESET);
            liczbaAutNaParkinguTymczasowo++;
            SamochodAnimacja s1 = new SamochodAnimacja(szerokoscSamochodu, wysokoscSamochodu, kladkaWjazdu, droga, nrSamochodu, wej, parkingSamochodowAnchor, czasTrwaniaAnimacjiSamochodu, licznikSamochodowWBuforze, rozmiarParkingu,true);
            s1.start();

            pula[wej] = nrSamochodu;
            wej = (wej + 1) % rozmiarParkingu;

            pusty.signal(); //powiadamia Prom, ze jesli czeka na samochod to niech go pobierze
        } finally {
            dostep.unlock();
        }
    }

    public String pobierz(String numerSamochodu, int numerPromu, String[] pokladPromu, long maksymalnyCzasPostoju, PromAnimacja promAnimowany, double szerokoscSamochodu, double wysokoscSamochodu, Rectangle kladkaWyjazdu, long czasPobraniaSamochodu) {
        dostep.lock();
        liczbaSamochodowNaPokladzie = ileSamochodowNaPokladzie(pokladPromu);
        if (promAnimowany.czyWjezdzaPierwszyRaz) {

            promAnimowany.czyWjezdzaPierwszyRaz = false;
            promAnimowany.czyMogePobracSamochody = true;
            czasPostojuPromu = 0; //zerowanie czasu postoju promu
            System.out.println("\nDo przystani przyplywa prom nr:"+numerPromu+", stan promu: ["+liczbaSamochodowNaPokladzie+"/"+pokladPromu.length+"]");
            if (liczbaSamochodowNaPokladzie >0){
                System.out.println("Wyjazd aut:");
                for (int i=0; i<pokladPromu.length;i++) {
                    if (pokladPromu[i] != null){
                        System.out.println(" -Wyjezdza samochod nr:"+pokladPromu[i]);
                        promAnimowany.schowajJedenSamochod();
                        SamochodAnimacja s1 = new SamochodAnimacja(szerokoscSamochodu, wysokoscSamochodu, kladkaWyjazdu,false, czasWyladunkuSamochodu);
                        s1.start();
                        pokladPromu[i] = null;
                        try {
                            Thread.sleep(czasWyladunkuSamochodu);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                liczbaSamochodowNaPokladzie =0; //bo juz wyjechaly
            }
        }
         //prom juz stoi w przystani
            startLiczeniaCzasu = System.nanoTime(); // początkowy czas w nano sekundach, liczony dopiero po wyładowaniu promow
            if ((czasPostojuPromu>maksymalnyCzasPostoju)&&(liczbaSamochodowNaPokladzie>0)){ //jesli prom przebywa juz zbyt dlugo w przystani, ale nie moze odjechac pusty
                System.out.println(ANSI_BLUE+"\n\nmaks czas: "+maksymalnyCzasPostoju+", a ja mam: "+czasPostojuPromu+ANSI_RESET);
              System.out.println("Prom o indeksie: "+numerPromu+" przebywa za dlugo przy przystani, wiec odjezdza, stan promu: ["+liczbaSamochodowNaPokladzie+"/"+pokladPromu.length+"]");
              Prom.ktoryPromMaPierwszenstwo = (Prom.ktoryPromMaPierwszenstwo+1)%liczbaPromow;
              promAnimowany.czyMogePobracSamochody = false;

              if (liczbaPromow == 1){ //jedyny taki wyjatek, gdy prom jest tylko jeden prom
                  Prom.ktoryPromMaPierwszenstwo = -1;
              }
            }
            else{ //prom nie przebywa zbyt dlugo, wiec moze pobrac samochod
                while (liczbaAutNaParkingu == 0) {
                    try {
                        pusty.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                numerSamochodu = pula[wyj];
                if (wyj<20){
                    parkingSamochodowAnchor.getChildren().get(wyj+1).setVisible(false); //schowa samochod z parkingu
                }
                promAnimowany.pokazJedenSamochod();

                wyj = (wyj + 1) % rozmiarParkingu;
                liczbaAutNaParkingu = liczbaAutNaParkingu - 1;
                liczbaAutNaParkinguTymczasowo = liczbaAutNaParkinguTymczasowo-1;
                pokladPromu[liczbaSamochodowNaPokladzie] = numerSamochodu+"";
                liczbaSamochodowNaPokladzie++;

                System.out.println("Prom nr:" + numerPromu + " pobiera samochod  nr:" + numerSamochodu + ", stan promu: ["+liczbaSamochodowNaPokladzie+"/"+pokladPromu.length+"]");
                System.out.println(ANSI_BLUE+"Prom: "+numerPromu+", maksymalny czas postoju: "+maksymalnyCzasPostoju+", a ja mam: "+czasPostojuPromu+ANSI_RESET);
                Platform.runLater(() -> {
                    licznikSamochodowWBuforze.setText("["+Buffer.liczbaAutNaParkingu+"/"+rozmiarParkingu+"]");
                });

                pelny.signal();

                if (liczbaSamochodowNaPokladzie == pokladPromu.length){
                    System.out.println("Prom o indeksie: "+numerPromu+" jest pelny, wiec odplywa.");
                    Prom.ktoryPromMaPierwszenstwo = (Prom.ktoryPromMaPierwszenstwo+1)%liczbaPromow;
                    promAnimowany.czyMogePobracSamochody = false;
                }
            }
        koniecLiczeniaCzasu = System.nanoTime() - startLiczeniaCzasu;
        czasPostojuPromu = czasPostojuPromu + czasPobraniaSamochodu + koniecLiczeniaCzasu/1000000; // czas wykonania programu w nano sekundach.
        dostep.unlock();
        return numerSamochodu;
    }
}



