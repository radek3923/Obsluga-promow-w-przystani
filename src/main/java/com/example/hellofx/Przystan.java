package com.example.hellofx;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class Przystan extends Thread{
    public int numerPromu;
    public String elem;
    public String nazwaPrzystani;
    Buffer buf;
    Random rand = new Random();
    int minimalnyCzasProdukcjiSamochodu;
    int maksymalnyCzasProdukcjiSamochodu;
    double szerokoscSamochodu;
    double wysokoscSamochodu;
    Rectangle kladkaWjazdu;
    Rectangle droga;

    public Przystan(int numerPromu, Buffer buf, int minimalnyCzasProdukcjiSamochodu, int maksymalnyCzasProdukcjiSamochodu, double szerokoscSamochodu, double wysokoscSamochodu, Rectangle kladkaWjazdu, Rectangle droga) {
        this.numerPromu = numerPromu;
        this.buf = buf;
        this.minimalnyCzasProdukcjiSamochodu = minimalnyCzasProdukcjiSamochodu;
        this.maksymalnyCzasProdukcjiSamochodu = maksymalnyCzasProdukcjiSamochodu;
        this.szerokoscSamochodu = szerokoscSamochodu;
        this.kladkaWjazdu = kladkaWjazdu;
        this.droga = droga;
        this.wysokoscSamochodu = wysokoscSamochodu;
        if (numerPromu%2==0){
            nazwaPrzystani = "'A'";
        }
        else{
            nazwaPrzystani = "'B'";
        }
    }

    public void run(){
        for(int i=0; i>-1; i++){
            long czasTrwaniaAnimacjiSamochodu = rand.nextInt(maksymalnyCzasProdukcjiSamochodu-minimalnyCzasProdukcjiSamochodu) + minimalnyCzasProdukcjiSamochodu;
            elem= i+"";
            buf.wstaw(elem, nazwaPrzystani, szerokoscSamochodu, wysokoscSamochodu, kladkaWjazdu, droga, czasTrwaniaAnimacjiSamochodu);

            try {
                Thread.sleep(czasTrwaniaAnimacjiSamochodu);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
