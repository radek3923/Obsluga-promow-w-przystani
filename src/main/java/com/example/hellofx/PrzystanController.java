package com.example.hellofx;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import static java.lang.System.exit;

public class PrzystanController {
    @FXML
    public Rectangle rzeka;
    @FXML
    public Rectangle wyspa;
    @FXML
    public Rectangle droga;
    @FXML
    public Rectangle kladkaWjazdu;
    @FXML
    public Rectangle kladkaWyjazdu;
    @FXML
    public Label licznikSamochodowWBuforze;
    @FXML
    public AnchorPane parkingSamochodowAnchor;
    @FXML
    public Rectangle plac;
    @FXML
    public Rectangle p0;
    @FXML
    public Rectangle p1;
    @FXML
    public Rectangle p2;
    @FXML
    public Rectangle p3;
    @FXML
    public Rectangle p4;
    @FXML
    public Rectangle p5;
    @FXML
    public Rectangle p6;
    @FXML
    public Rectangle p7;
    @FXML
    public Rectangle p8;
    @FXML
    public Rectangle p9;
    @FXML
    public Rectangle p10;
    @FXML
    public Rectangle p11;
    @FXML
    public Rectangle p12;
    @FXML
    public Rectangle p13;
    @FXML
    public Rectangle p14;
    @FXML
    public Rectangle p15;
    @FXML
    public Rectangle p16;
    @FXML
    public Rectangle p17;
    @FXML
    public Rectangle p18;
    @FXML
    public Rectangle p19;
    @FXML
    public Circle swiatloProdukcji;
    @FXML
    public TextField liczbaStatkowMenu;
    @FXML
    public TextField pojemnoscStatkowMenu;
    @FXML
    public TextField rozmiarParkinguMenu;
    @FXML
    public TextField maksymalnyCzasPostojuMenu;
    @FXML
    public TextField minCzasProdukcjiMenu;
    @FXML
    public TextField maxCzasProdukcjiMenu;
    @FXML
    public TextField minCzasKonsumpcjiMenu;
    @FXML
    public TextField maxCzasKonsumpcjiMenu;
    @FXML
    public TextField czasWyladunkuSamochoduMenu;
    @FXML
    public TextField czasPrzeplywuMenu;
    @FXML
    public Button zapiszMenu;
    @FXML
    public Button domyslneMenu;
    @FXML
    public Button wyczyscMenu;
    @FXML
    public Button rozpocznijMenu;
    @FXML
    public Button zakonczMenu;
    @FXML
    public Label zegar;

    //pusty konstruktor
    public PrzystanController(){

    }

    //ta metoda jest wywolywana zaraz po konstruktorze naszego kontrolera,
    //czyli jesli nasz kontroler zostanie powolany do zycia to wykona sie najpierw jego konstruktor, a nastepnie tam metoda
    //to nam gwarantuje ze wszystkie elementy fxml, ktore zostały wstrzykniete, zostaly zainicjalizowane
    //bo przeciez w konstruktorze nie musimy sie odwolywac do wszystkich elementow
    @FXML
    void initialize() throws FileNotFoundException {

        //blokada mozliwosci wcisniecia przyciskow
        rozpocznijMenu.setDisable(true);

        //ustawienia kladki
        kladkaWjazdu.setLayoutY(droga.getLayoutY()+(((3*droga.getHeight())/2-kladkaWjazdu.getHeight())/2));
        kladkaWyjazdu.setLayoutY(droga.getLayoutY()+((droga.getHeight()/2-kladkaWyjazdu.getHeight())/2));
        kladkaWjazdu.setVisible(false);
        kladkaWyjazdu.setVisible(false);
        swiatloProdukcji.setVisible(false);

        p0.setVisible(false);
        p1.setVisible(false);
        p2.setVisible(false);
        p3.setVisible(false);
        p4.setVisible(false);
        p5.setVisible(false);
        p6.setVisible(false);
        p7.setVisible(false);
        p8.setVisible(false);
        p9.setVisible(false);
        p10.setVisible(false);
        p11.setVisible(false);
        p12.setVisible(false);
        p13.setVisible(false);
        p14.setVisible(false);
        p15.setVisible(false);
        p16.setVisible(false);
        p17.setVisible(false);
        p18.setVisible(false);
        p19.setVisible(false);

        double szerokoscPromu = 67;
        double wysokoscPromu = 120;
        double szerokoscSamochodu = 35;
        double wysokoscSamochodu = 20;
        final int[] rozmiarParkingu = {5}; //ustaw tez w buforze
        int liczbaPrzystani = 1;
        final int[] liczbaPromow = {5};
        final int[] pojemnosPromow = {9};
        final long[] maksymalnyCzasPostoju = {9000}; // w mili sekundach

        final int[] minimalnyCzasProdukcjiSamochodu = {500}; // w milisekundach
        final int[] maksymalnyCzasProdukcjiSamochodu = {5000}; // w milisekundach

        final int[] minimalnyCzasPoboruSamochodu = {500}; // w milisekundach
        final int[] maksymalnyCzasPoboruSamochodu = {1500}; // w milisekundach
        final int[] czasWyładunkuSamochodu = {100}; // w milisekundach
        final long[] czasPrzeplywu = {5000}; // w milisekundach

        File file = new File("parametry.txt");
        Scanner in = new Scanner(file);

        liczbaPromow[0] = Integer.parseInt(in.nextLine()) ;
        pojemnosPromow[0] = Integer.parseInt(in.nextLine()) ;
        maksymalnyCzasPostoju[0] = Integer.parseInt(in.nextLine()) ;
        czasWyładunkuSamochodu[0] = Integer.parseInt(in.nextLine()) ;
        czasPrzeplywu[0] = Integer.parseInt(in.nextLine()) ;
        rozmiarParkingu[0] = Integer.parseInt(in.nextLine()) ;
        minimalnyCzasProdukcjiSamochodu[0] = Integer.parseInt(in.nextLine()) ;
        maksymalnyCzasProdukcjiSamochodu[0] = Integer.parseInt(in.nextLine()) ;
        minimalnyCzasPoboruSamochodu[0] = Integer.parseInt(in.nextLine()) ;
        maksymalnyCzasPoboruSamochodu[0] = Integer.parseInt(in.nextLine()) ;

        liczbaStatkowMenu.setText(Integer.toString(liczbaPromow[0]));
        pojemnoscStatkowMenu.setText(Integer.toString(pojemnosPromow[0]));
        maksymalnyCzasPostojuMenu.setText(Long.toString(maksymalnyCzasPostoju[0]));
        czasWyladunkuSamochoduMenu.setText(Integer.toString(czasWyładunkuSamochodu[0]));
        czasPrzeplywuMenu.setText(Long.toString(czasPrzeplywu[0]));
        rozmiarParkinguMenu.setText(Integer.toString(rozmiarParkingu[0]));
        minCzasProdukcjiMenu.setText(Integer.toString(minimalnyCzasProdukcjiSamochodu[0]));
        maxCzasProdukcjiMenu.setText(Integer.toString(maksymalnyCzasProdukcjiSamochodu[0]));
        minCzasKonsumpcjiMenu.setText(Integer.toString(minimalnyCzasPoboruSamochodu[0]));
        maxCzasKonsumpcjiMenu.setText(Integer.toString(maksymalnyCzasPoboruSamochodu[0]));

        zapiszMenu.setOnAction(e ->{
            boolean czyMogeRozpoczac = true;
            if (liczbaStatkowMenu.getText()=="") czyMogeRozpoczac = false;
            if (pojemnoscStatkowMenu.getText()=="") czyMogeRozpoczac = false;
            if (maksymalnyCzasPostojuMenu.getText()=="") czyMogeRozpoczac = false;
            if (rozmiarParkinguMenu.getText()=="") czyMogeRozpoczac = false;
            if (minCzasProdukcjiMenu.getText()=="") czyMogeRozpoczac = false;
            if (maxCzasProdukcjiMenu.getText()=="") czyMogeRozpoczac = false;
            if (minCzasKonsumpcjiMenu.getText()=="") czyMogeRozpoczac = false;
            if (maxCzasKonsumpcjiMenu.getText()=="") czyMogeRozpoczac = false;
            if (czasWyladunkuSamochoduMenu.getText()=="") czyMogeRozpoczac = false;
            if (czasPrzeplywuMenu.getText()=="") czyMogeRozpoczac = false;
            if(czyMogeRozpoczac) {
                liczbaPromow[0] = Integer.parseInt(liczbaStatkowMenu.getText()) ;
                pojemnosPromow[0] = Integer.parseInt(pojemnoscStatkowMenu.getText()) ;
                maksymalnyCzasPostoju[0] = Integer.parseInt(maksymalnyCzasPostojuMenu.getText()) ;
                rozmiarParkingu[0] = Integer.parseInt(rozmiarParkinguMenu.getText()) ;
                minimalnyCzasProdukcjiSamochodu[0] = Integer.parseInt(minCzasProdukcjiMenu.getText()) ;
                maksymalnyCzasProdukcjiSamochodu[0] = Integer.parseInt(maxCzasProdukcjiMenu.getText()) ;
                minimalnyCzasPoboruSamochodu[0] = Integer.parseInt(minCzasKonsumpcjiMenu.getText()) ;
                maksymalnyCzasPoboruSamochodu[0] = Integer.parseInt(maxCzasKonsumpcjiMenu.getText()) ;
                czasWyładunkuSamochodu[0]= Integer.parseInt(czasWyladunkuSamochoduMenu.getText()) ;
                czasPrzeplywu[0]= Integer.parseInt(czasPrzeplywuMenu.getText()) ;

                rozpocznijMenu.setDisable(false);
                zakonczMenu.setDisable(false);
                if (liczbaPromow[0]>7){
                    liczbaPromow[0] = 7;
                    liczbaStatkowMenu.setText("7");
                }
                PrintWriter zapis = null;
                try {
                    zapis = new PrintWriter("parametry.txt");
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                PrintWriter finalZapis = zapis;

                finalZapis.println(liczbaPromow[0]);
                finalZapis.println(pojemnosPromow[0]);
                finalZapis.println(maksymalnyCzasPostoju[0]);
                finalZapis.println(czasWyładunkuSamochodu[0]);
                finalZapis.println(czasPrzeplywu[0]);
                finalZapis.println(rozmiarParkingu[0]);
                finalZapis.println(minimalnyCzasProdukcjiSamochodu[0]);
                finalZapis.println(maksymalnyCzasProdukcjiSamochodu[0]);
                finalZapis.println(minimalnyCzasPoboruSamochodu[0]);
                finalZapis.println(maksymalnyCzasPoboruSamochodu[0]);
                finalZapis.close();
            }
            else{
                rozpocznijMenu.setDisable(true);
            }
        });

        domyslneMenu.setOnAction(e ->{
            rozpocznijMenu.setDisable(true);
            liczbaStatkowMenu.setText("3");
            pojemnoscStatkowMenu.setText("9");
            maksymalnyCzasPostojuMenu.setText("9000");
            rozmiarParkinguMenu.setText("10");
            minCzasProdukcjiMenu.setText("500");
            maxCzasProdukcjiMenu.setText("2000");
            minCzasKonsumpcjiMenu.setText("500");
            maxCzasKonsumpcjiMenu.setText("1500");
            czasWyladunkuSamochoduMenu.setText("100");
            czasPrzeplywuMenu.setText("5000");
        });

        wyczyscMenu.setOnAction(e ->{
            rozpocznijMenu.setDisable(true);
            liczbaStatkowMenu.setText("");
            pojemnoscStatkowMenu.setText("");
            maksymalnyCzasPostojuMenu.setText("");
            rozmiarParkinguMenu.setText("");
            minCzasProdukcjiMenu.setText("");
            maxCzasProdukcjiMenu.setText("");
            minCzasKonsumpcjiMenu.setText("");
            maxCzasKonsumpcjiMenu.setText("");
            czasWyladunkuSamochoduMenu.setText("");
            czasPrzeplywuMenu.setText("");
        });
        zakonczMenu.setOnAction(e ->{
            //zamyka scene
            Stage stage = (Stage) zakonczMenu.getScene().getWindow();
            stage.close();

            //zamyka program
            System.out.println("\nKoniec programu...");
            exit(0);
        });

        rozpocznijMenu.setOnAction(e ->{
            domyslneMenu.setDisable(true);
            wyczyscMenu.setDisable(true);
            zapiszMenu.setDisable(true);
            rozpocznijMenu.setDisable(true);
            swiatloProdukcji.setFill(Color.GREEN);
            swiatloProdukcji.setVisible(true);

            licznikSamochodowWBuforze.setText("[0/"+ rozmiarParkingu[0] +"]");

            String[] pula = new String[rozmiarParkingu[0]];
            Buffer bufor = new Buffer(rozmiarParkingu[0], liczbaPromow[0], parkingSamochodowAnchor, licznikSamochodowWBuforze,pula, czasWyładunkuSamochodu[0], swiatloProdukcji);
            Przystan[] przystanie = new Przystan[liczbaPrzystani];
            Prom[] promy = new Prom[liczbaPromow[0]];

            for(int numerPrzystani=0; numerPrzystani<liczbaPrzystani; numerPrzystani++){
                Przystan producent = new Przystan(numerPrzystani, bufor, minimalnyCzasProdukcjiSamochodu[0], maksymalnyCzasProdukcjiSamochodu[0], szerokoscSamochodu, wysokoscSamochodu, kladkaWjazdu, droga);
                przystanie[numerPrzystani] = producent;
            }

            for(int numerPromu = 0; numerPromu< liczbaPromow[0]; numerPromu++){
                Prom konsument = new Prom(numerPromu, bufor, pojemnosPromow[0], maksymalnyCzasPostoju[0], minimalnyCzasPoboruSamochodu[0], maksymalnyCzasPoboruSamochodu[0], szerokoscPromu, wysokoscPromu, szerokoscSamochodu, wysokoscSamochodu, rzeka, kladkaWyjazdu, czasPrzeplywu[0]);
                promy[numerPromu] = konsument;
            }

            for (int i = 0; i < liczbaPrzystani; i++) {
                przystanie[i].start();
            }

            for (int i = 0; i < liczbaPromow[0]; i++) {
                promy[i].start();
            }

            final long[] liczbaMilisekund = {0};
            int period =100;

            new Timer().schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            liczbaMilisekund[0] = liczbaMilisekund[0] + period;

                            String txt = String.format("%02d:%02d:%02d",
                                    TimeUnit.MILLISECONDS.toHours(liczbaMilisekund[0]),
                                    TimeUnit.MILLISECONDS.toMinutes(liczbaMilisekund[0]) -
                                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(liczbaMilisekund[0])),
                                    TimeUnit.MILLISECONDS.toSeconds(liczbaMilisekund[0]) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(liczbaMilisekund[0])));

                            Platform.runLater(() -> {
                                zegar.setText(txt);
                            });
                        }
                    }, 0, period);
        });
    }
}