package com.example.hellofx;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Prom extends Thread{
    public int numerPromu;
    public String numerSamochodu;
    Buffer buf;
    Random rand = new Random();
    public int pojemnoscPromow;
    public long maksymalnyCzasPostoju;
    public long czasPrzeplywu;
    public static int ktoryPromMaPierwszenstwo = 0;
    int minimalnyCzasPoboruSamochodu;// w milisekundach
    int maksymalnyCzasPoboruSamochodu;// w milisekundach
    double szerokoscPromu;
    double wysokoscPromu;
    double szerokoscSamochodu;
    double wysokoscSamochodu;
    Rectangle rzeka;
    Rectangle kladkaWyjazdu;

    public Prom(int numerPromu,Buffer buf, int pojemnosPromow, long maksymalnyCzasPostoju, int minimalnyCzasPoboruSamochodu, int maksymalnyCzasPoboruSamochodu, double szerokoscPromu, double wysokoscPromu, double szerokoscSamochodu, double wysokoscSamochodu, Rectangle rzeka, Rectangle kladkaWyjazdu, long czasPrzeplywu) {
        this.numerPromu = numerPromu;
        this.buf = buf;
        this.pojemnoscPromow = pojemnosPromow;
        this.maksymalnyCzasPostoju = maksymalnyCzasPostoju;
        this.minimalnyCzasPoboruSamochodu = minimalnyCzasPoboruSamochodu;
        this.maksymalnyCzasPoboruSamochodu = maksymalnyCzasPoboruSamochodu;
        this.szerokoscPromu = szerokoscPromu;
        this.wysokoscPromu = wysokoscPromu;
        this.szerokoscSamochodu = szerokoscSamochodu;
        this.wysokoscSamochodu = wysokoscSamochodu;
        this.rzeka = rzeka;
        this.kladkaWyjazdu = kladkaWyjazdu;
        this.czasPrzeplywu = czasPrzeplywu;
    }

    public void run(){
        String[] pokladPromu = new String[pojemnoscPromow];

        PromAnimacja promAnimowany = new PromAnimacja(szerokoscPromu, wysokoscPromu, wysokoscSamochodu, szerokoscSamochodu,rzeka,pojemnoscPromow,numerPromu, czasPrzeplywu);
        promAnimowany.start();

        for(int i=0; i>-1; i++){
            long czasPobraniaSamochodu = rand.nextInt((maksymalnyCzasPoboruSamochodu-minimalnyCzasPoboruSamochodu) + minimalnyCzasPoboruSamochodu);
            if (promAnimowany.czyMogePobracSamochody) {
                numerSamochodu = buf.pobierz(numerSamochodu, numerPromu, pokladPromu, maksymalnyCzasPostoju, promAnimowany, szerokoscSamochodu, wysokoscSamochodu, kladkaWyjazdu, czasPobraniaSamochodu );
                try {
                    Thread.sleep(czasPobraniaSamochodu);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else { //jesli prom chcialby wjechac na przystan, ale inny prom ma pierwszenstwo
                try {
                    Thread.sleep((rand.nextInt(10) + 10));
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
