package com.example.hellofx;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class SamochodAnimacja extends Thread{
    public double szerokosc;
    public double wysokosc;
    Rectangle kladka;
    Rectangle droga;
    String nrSamochodu; //ktory numer samochodu
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    double tymczasowyLayoutX;
    double tymczasowyLayoutY;
    int naKtoreMiejsceSieUstawic;
    int rozmiarParkingu;
    AnchorPane parkingSamochodowAnchor;
    double czasTrwaniaAnimacji1;
    double czasTrwaniaAnimacji2;
    long czasTrwaniaAnimacjiSamochodu;
    Label licznikSamochodowWBuforze;
    public boolean czyWjazd;

    SamochodAnimacja(double szerokosc, double wysokosc, Rectangle kladka, Rectangle droga, String nrSamochodu, int naKtoreMiejsceSieUstawic, AnchorPane parkingSamochodowAnchor, long czasTrwaniaAnimacjiSamochodu, Label licznikSamochodowWBuforze, int rozmiarParkingu, boolean czyWjazd){
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.kladka = kladka;
        this.droga = droga;
        this.nrSamochodu = nrSamochodu;
        this.naKtoreMiejsceSieUstawic = naKtoreMiejsceSieUstawic;
        this.parkingSamochodowAnchor = parkingSamochodowAnchor;
        this.czasTrwaniaAnimacjiSamochodu = czasTrwaniaAnimacjiSamochodu;
        this.licznikSamochodowWBuforze = licznikSamochodowWBuforze;
        this.rozmiarParkingu = rozmiarParkingu;
        this.czyWjazd = czyWjazd;
        czasTrwaniaAnimacji1 = 0.7*czasTrwaniaAnimacjiSamochodu;
        czasTrwaniaAnimacji2 = 0.3*czasTrwaniaAnimacjiSamochodu;
    }
    SamochodAnimacja(double szerokosc, double wysokosc, Rectangle kladka,boolean czyWjazd,long czasTrwaniaAnimacjiSamochodu){
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.kladka = kladka;
        this.czyWjazd = czyWjazd;
        this.czasTrwaniaAnimacjiSamochodu = czasTrwaniaAnimacjiSamochodu;
    }

    void wjazd(Rectangle samochod){
        double XSamochoduPrzyWjezdzie = droga.getLayoutX()- samochod.getWidth();
        double YSamochoduPrzyWjezdzie = droga.getLayoutY()+(((3*droga.getHeight())/2-samochod.getHeight())/2);

        samochod.setLayoutX(XSamochoduPrzyWjezdzie); //ustawiam polozenie x
        samochod.setLayoutY(YSamochoduPrzyWjezdzie); //ustawiam polozenie y

        //pojawienie sie samochodu na ekranie
        Platform.runLater(() -> {
            HelloApplication.root.getChildren().add(samochod);
        });

        //przemieszczenie sie samochodu z punktu Produkcji, do kladki
        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        LineTo lineTo = new LineTo();

        //skad, wspolrzedne lokalnie??
        moveTo.setX(samochod.getWidth()/2);
        moveTo.setY(samochod.getHeight()/2);

        //dokad
        lineTo.setX(kladka.getLayoutX());
        lineTo.setY(samochod.getHeight()/2); //bez zmian, bo to ma byc poziomy ruch

        path.getElements().addAll(moveTo, lineTo);
        PathTransition pathTransition = new PathTransition(Duration.millis(czasTrwaniaAnimacji1),path, samochod);

        tymczasowyLayoutX = kladka.getLayoutX();
        tymczasowyLayoutY = samochod.getHeight()/2;

        pathTransition.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            pathTransition.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //pojawienie sie kladki
        kladka.setVisible(true);

//        //przemieszczenie sie z konca drogi na kladke
        Path path2 = new Path();
        MoveTo moveTo2 = new MoveTo();
        LineTo lineTo2 = new LineTo();

        //skad
        moveTo2.setX(tymczasowyLayoutX);
        moveTo2.setY(tymczasowyLayoutY);

        //dokad
        lineTo2.setX(kladka.getLayoutX()+kladka.getWidth()+((kladka.getWidth()-samochod.getWidth())/2));
        lineTo2.setY(samochod.getHeight()/2); //bez zmian, bo to poziomy ruch

        path2.getElements().addAll(moveTo2, lineTo2);
        PathTransition pathTransition2 = new PathTransition(Duration.millis(czasTrwaniaAnimacji2),path2, samochod);

        tymczasowyLayoutX = kladka.getLayoutX()+kladka.getWidth()+((kladka.getWidth()-samochod.getWidth())/2);
        tymczasowyLayoutY = samochod.getHeight()/2;


        pathTransition.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            pathTransition2.play();
        });


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Buffer.liczbaAutNaParkingu++;
        System.out.print("Samochod o nr: "+nrSamochodu);
        System.out.println(". Mam miejsce: "+naKtoreMiejsceSieUstawic);

        Platform.runLater(() -> {
            licznikSamochodowWBuforze.setText("["+Buffer.liczbaAutNaParkingu+"/"+rozmiarParkingu+"]");
        });

        samochod.setVisible(false);
        if (naKtoreMiejsceSieUstawic<20) {
            parkingSamochodowAnchor.getChildren().get(naKtoreMiejsceSieUstawic+1).setVisible(true);
        }
    }
    void wyjazd(Rectangle samochod){
        double XSamochoduPrzyWyjezdzie = (kladka.getWidth()-samochod.getWidth())/2+kladka.getLayoutX();
        double YSamochoduPrzyWyjezdzie = (kladka.getHeight()-samochod.getHeight())/2+kladka.getLayoutY();

        samochod.setLayoutX(XSamochoduPrzyWyjezdzie); //ustawiam polozenie x
        samochod.setLayoutY(YSamochoduPrzyWyjezdzie); //ustawiam polozenie y
        samochod.setRotate(90);
        kladka.setVisible(true);
        //pojawienie sie samochodu na ekranie
        Platform.runLater(() -> {
            HelloApplication.root.getChildren().add(samochod);
        });
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(10*czasTrwaniaAnimacjiSamochodu));
        transition.setNode(samochod);
        transition.setByY(-450);

        transition.setOnFinished(e -> {
            samochod.setVisible(false);
        });
        Platform.runLater(() -> {
            transition.play();
        });
    }

    public void run(){

        Rectangle samochod = new Rectangle(szerokosc,wysokosc); //szerokosc, wysokosc,
        samochod.setFill(Color.RED);
        samochod.setStroke(Color.BLACK);
        samochod.setStrokeWidth(1);

        if (czyWjazd){
            wjazd(samochod);;
        }
        else{
            wyjazd(samochod);
        }
    }
}
