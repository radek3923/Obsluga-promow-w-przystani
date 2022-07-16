package com.example.hellofx;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class PromAnimacja extends Thread{
    Rectangle rzeka;
    int maksymalnaLiczbaSamochodowNaPokladzie;
    int naKtorejPozycjiJestem;
    boolean czyMogePobracSamochody = false;
    boolean czyWjezdzaPierwszyRaz = true;
    public double szerokoscPromu;
    public double wysokoscPromu;
    double szerokoscSamochodu;
    double wysokoscSamochodu;
    double czasobrotu;
    double czasPlyniecia;
    double czasPrzerwyPomiedzyAnimacjami;
    public int liczbaSamochodowNaPokladzie=0;
    long czasPrzeplywu;
    AnchorPane promZSamochodami = new AnchorPane();
    Label liczbaSamochodowNaPokladzieNapis = new Label();
    static boolean[] czyMiejsceJestZajete = new boolean[8];

    PromAnimacja(double szerokoscPromu, double wysokoscPromu,double szerokoscSamochodu, double wysokoscSamochodu, Rectangle rzeka, int maksymalnaLiczbaSamochodowNaPokladzie, int naKtorejPozycjiJestem, long czasPrzeplywu){
        this.szerokoscPromu = szerokoscPromu;
        this.wysokoscPromu = wysokoscPromu;
        this.szerokoscSamochodu = szerokoscSamochodu;
        this.wysokoscSamochodu = wysokoscSamochodu;
        this.rzeka = rzeka;
        this.maksymalnaLiczbaSamochodowNaPokladzie = maksymalnaLiczbaSamochodowNaPokladzie;
        this.naKtorejPozycjiJestem = naKtorejPozycjiJestem;
        this.czasPrzeplywu = czasPrzeplywu;
        czasobrotu = 1.5/4*czasPrzeplywu;
        czasPlyniecia = 1.5/4*czasPrzeplywu;
        czasPrzerwyPomiedzyAnimacjami = 1/4*czasPrzeplywu;
    }

    void z0do1(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByY(160);

        Platform.runLater(() -> {
            transition.play();
        });

        RotateTransition transition2 = new RotateTransition(Duration.millis(czasobrotu), promZSamochodami);
        transition2.setByAngle(-90);
        transition2.setDelay(Duration.millis(czasPrzerwyPomiedzyAnimacjami));

        transition2.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition2.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void z1do2(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByX(175);

        transition.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void z2do3(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByX(175);

        Platform.runLater(() -> {
            transition.play();
        });

        RotateTransition transition2 = new RotateTransition(Duration.millis(czasobrotu), promZSamochodami);
        transition2.setByAngle(-90);
        transition2.setDelay(Duration.millis(czasPrzerwyPomiedzyAnimacjami));


        transition2.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition2.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void z3do4(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByY(-160);

        transition.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void z4do5(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByY(-155);

        Platform.runLater(() -> {
            transition.play();
        });

        RotateTransition transition2 = new RotateTransition(Duration.millis(czasobrotu), promZSamochodami);
        transition2.setByAngle(-90);
        transition2.setDelay(Duration.millis(czasPrzerwyPomiedzyAnimacjami));


        transition2.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition2.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void z5do6(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByX(-175);

        transition.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void z6do7(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByX(-175);

        Platform.runLater(() -> {
            transition.play();
        });

        RotateTransition transition2 = new RotateTransition(Duration.millis(czasobrotu), promZSamochodami);
        transition2.setByAngle(-90);
        transition2.setDelay(Duration.millis(czasPrzerwyPomiedzyAnimacjami));


        transition2.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition2.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void z7do0(AnchorPane promZSamochodami){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(czasPlyniecia));
        transition.setNode(promZSamochodami);
        transition.setByY(155);

        transition.setOnFinished(e -> {
            synchronized (this){
                notify();
            }
        });
        Platform.runLater(() -> {
            transition.play();
        });
        synchronized (this) {
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void pokazJedenSamochod(){
        for (int i=1; i<=maksymalnaLiczbaSamochodowNaPokladzie;i++) {
            if(!promZSamochodami.getChildren().get(i).isVisible()){
                liczbaSamochodowNaPokladzie++;
                int finalI = i;
                Platform.runLater(() -> {
                promZSamochodami.getChildren().get(finalI).setVisible(true);
                    liczbaSamochodowNaPokladzieNapis.setText("["+liczbaSamochodowNaPokladzie+"/"+maksymalnaLiczbaSamochodowNaPokladzie+"]");
                });
                break;
            }
        }
    }
    void schowajJedenSamochod(){
        for (int i=1; i<=maksymalnaLiczbaSamochodowNaPokladzie;i++) {
            if(promZSamochodami.getChildren().get(i).isVisible()){
                promZSamochodami.getChildren().get(i).setVisible(false);
                liczbaSamochodowNaPokladzie--;
                Platform.runLater(() -> {
                    liczbaSamochodowNaPokladzieNapis.setText("["+liczbaSamochodowNaPokladzie+"/"+maksymalnaLiczbaSamochodowNaPokladzie+"]");
                });
                break;
            }
        }
    }

    public void run(){
        double odlegloscXPomiedzyPromami = (rzeka.getWidth()-3*szerokoscPromu)/12;
        double odlegloscYPomiedzyPromami = (rzeka.getHeight()-3*wysokoscPromu)/12;


        if (naKtorejPozycjiJestem == 7){//
            promZSamochodami.setLayoutX(odlegloscXPomiedzyPromami+rzeka.getLayoutX());
            promZSamochodami.setLayoutY(odlegloscYPomiedzyPromami+rzeka.getLayoutY());
            czyMiejsceJestZajete[7] = true;
        }
        else if (naKtorejPozycjiJestem == 6){//
            promZSamochodami.setLayoutX(6*odlegloscXPomiedzyPromami+szerokoscPromu+ rzeka.getLayoutX());
            promZSamochodami.setLayoutY(odlegloscYPomiedzyPromami+rzeka.getLayoutY());
            promZSamochodami.setRotate(-270);
            czyMiejsceJestZajete[6] = true;
        }
        else if (naKtorejPozycjiJestem == 5){//
            promZSamochodami.setLayoutX(11*odlegloscXPomiedzyPromami+2*szerokoscPromu+rzeka.getLayoutX());
            promZSamochodami.setLayoutY(odlegloscYPomiedzyPromami+rzeka.getLayoutY());
            promZSamochodami.setRotate(-270);
            czyMiejsceJestZajete[5] = true;
        }
        else if (naKtorejPozycjiJestem == 0){//
            promZSamochodami.setLayoutX(odlegloscXPomiedzyPromami+rzeka.getLayoutX());
            promZSamochodami.setLayoutY(6*odlegloscYPomiedzyPromami+wysokoscPromu+rzeka.getLayoutY());
            czyMogePobracSamochody = true;
            czyMiejsceJestZajete[0] = true;
        }
        else if (naKtorejPozycjiJestem == 4){//
            promZSamochodami.setLayoutX(11*odlegloscXPomiedzyPromami+2*szerokoscPromu+rzeka.getLayoutX());
            promZSamochodami.setLayoutY(6*odlegloscYPomiedzyPromami+wysokoscPromu+rzeka.getLayoutY());
            promZSamochodami.setRotate(-180);
            czyMiejsceJestZajete[4] = true;
        }
        else if (naKtorejPozycjiJestem == 1){//
            promZSamochodami.setLayoutX(odlegloscXPomiedzyPromami+rzeka.getLayoutX());
            promZSamochodami.setLayoutY(11*odlegloscYPomiedzyPromami+2*wysokoscPromu+rzeka.getLayoutY());
            promZSamochodami.setRotate(-90);
            czyMiejsceJestZajete[1] = true;
        }
        else if (naKtorejPozycjiJestem == 2){
            promZSamochodami.setLayoutX(6*odlegloscXPomiedzyPromami+szerokoscPromu+ rzeka.getLayoutX());
            promZSamochodami.setLayoutY(11*odlegloscYPomiedzyPromami+2*wysokoscPromu+rzeka.getLayoutY());
            promZSamochodami.setRotate(-90);
            czyMiejsceJestZajete[2] = true;
        }
        else if (naKtorejPozycjiJestem == 3){
            promZSamochodami.setLayoutX(11*odlegloscXPomiedzyPromami+2*szerokoscPromu+rzeka.getLayoutX());
            promZSamochodami.setLayoutY(11*odlegloscYPomiedzyPromami+2*wysokoscPromu+rzeka.getLayoutY());
            promZSamochodami.setRotate(-180);
            czyMiejsceJestZajete[3] = true;
        }

        Rectangle prom = new Rectangle(szerokoscPromu,wysokoscPromu);
        prom.setFill(Color.valueOf("#94975d"));
        prom.setStroke(Color.BLACK);
        prom.setStrokeWidth(1);
        promZSamochodami.getChildren().add(prom);

        Rectangle[] tablicaSamochodow = new Rectangle[maksymalnaLiczbaSamochodowNaPokladzie];

        double odlegloscXPomiedzySamochodami = (szerokoscPromu-3*szerokoscSamochodu)/4;
        double odlegloscYPomiedzySamochodami = (wysokoscPromu-3*wysokoscSamochodu)/4;
        for(int i = 0; i < maksymalnaLiczbaSamochodowNaPokladzie; i++)
        {
            Rectangle samochod = new Rectangle();
            samochod.setWidth(szerokoscSamochodu);
            samochod.setHeight(wysokoscSamochodu);
            samochod.setFill(Color.RED);
            samochod.setStroke(Color.BLACK);
            samochod.setStrokeWidth(1);
            samochod.setVisible(false);
            if(i == 0){
                samochod.setLayoutX(odlegloscXPomiedzySamochodami);
                samochod.setLayoutY(odlegloscYPomiedzySamochodami);
            }
            else if(i == 1){
                samochod.setLayoutX(2*odlegloscXPomiedzySamochodami+szerokoscSamochodu);
                samochod.setLayoutY(odlegloscYPomiedzySamochodami);
            }
            else if(i == 2){
                samochod.setLayoutX(3*odlegloscXPomiedzySamochodami+2*szerokoscSamochodu);
                samochod.setLayoutY(odlegloscYPomiedzySamochodami);
            }
            else if(i == 3){
                samochod.setLayoutX(odlegloscXPomiedzySamochodami);
                samochod.setLayoutY(2*odlegloscYPomiedzySamochodami+wysokoscSamochodu);
            }
            else if(i == 4){
                samochod.setLayoutX(2*odlegloscXPomiedzySamochodami+szerokoscSamochodu);
                samochod.setLayoutY(2*odlegloscYPomiedzySamochodami+wysokoscSamochodu);
            }
            else if(i == 5){
                samochod.setLayoutX(3*odlegloscXPomiedzySamochodami+2*szerokoscSamochodu);
                samochod.setLayoutY(2*odlegloscYPomiedzySamochodami+wysokoscSamochodu);
            }
            else if(i == 6){
                samochod.setLayoutX(odlegloscXPomiedzySamochodami);
                samochod.setLayoutY(3*odlegloscYPomiedzySamochodami+2*wysokoscSamochodu);
            }
            else if(i == 7){
                samochod.setLayoutX(2*odlegloscXPomiedzySamochodami+szerokoscSamochodu);
                samochod.setLayoutY(3*odlegloscYPomiedzySamochodami+2*wysokoscSamochodu);
            }
            else if(i == 8){
                samochod.setLayoutX(3*odlegloscXPomiedzySamochodami+2*szerokoscSamochodu);
                samochod.setLayoutY(3*odlegloscYPomiedzySamochodami+2*wysokoscSamochodu);
            }else if(i > 8){
                samochod.setLayoutX(3*odlegloscXPomiedzySamochodami+2*szerokoscSamochodu);
                samochod.setLayoutY(3*odlegloscYPomiedzySamochodami+2*wysokoscSamochodu);
            }
            tablicaSamochodow[i] = samochod;
            promZSamochodami.getChildren().add(tablicaSamochodow[i]);
        }
        liczbaSamochodowNaPokladzieNapis.setText("[0/"+maksymalnaLiczbaSamochodowNaPokladzie+"]");
        liczbaSamochodowNaPokladzieNapis.setFont(Font.font ("Verdana", 15));
        liczbaSamochodowNaPokladzieNapis.setLayoutX(15);
        liczbaSamochodowNaPokladzieNapis.setLayoutY(120);
        promZSamochodami.getChildren().add(liczbaSamochodowNaPokladzieNapis);

        Platform.runLater(() -> {
            HelloApplication.root.getChildren().add(promZSamochodami);
        });
        while(true){
            while (czyMogePobracSamochody){
                try {
                    Thread.sleep(10);
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (naKtorejPozycjiJestem ==0) {
                while(czyMiejsceJestZajete[1]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                    czyMiejsceJestZajete[1]=true;
                    czyMiejsceJestZajete[0]=false;
                    z0do1(promZSamochodami);
                    naKtorejPozycjiJestem++;
            }
            if (naKtorejPozycjiJestem ==1) {
                while(czyMiejsceJestZajete[2]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                czyMiejsceJestZajete[2]=true;
                czyMiejsceJestZajete[1]=false;
                z1do2(promZSamochodami);
                naKtorejPozycjiJestem++;
            }
            if (naKtorejPozycjiJestem ==2) {
                while(czyMiejsceJestZajete[3]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                czyMiejsceJestZajete[3]=true;
                czyMiejsceJestZajete[2]=false;
                z2do3(promZSamochodami);
                naKtorejPozycjiJestem++;
            }
            if (naKtorejPozycjiJestem ==3) {
                while(czyMiejsceJestZajete[4]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                czyMiejsceJestZajete[4]=true;
                czyMiejsceJestZajete[3]=false;
                z3do4(promZSamochodami);
                naKtorejPozycjiJestem++;
            }
            if (naKtorejPozycjiJestem ==4) {
                while(czyMiejsceJestZajete[5]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                czyMiejsceJestZajete[5]=true;
                czyMiejsceJestZajete[4]=false;
                z4do5(promZSamochodami);
                naKtorejPozycjiJestem++;
            }
            if (naKtorejPozycjiJestem ==5) {
                while(czyMiejsceJestZajete[6]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                czyMiejsceJestZajete[6]=true;
                czyMiejsceJestZajete[5]=false;
                z5do6(promZSamochodami);
                naKtorejPozycjiJestem++;
            }
            if (naKtorejPozycjiJestem ==6) {
                while(czyMiejsceJestZajete[7]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                czyMiejsceJestZajete[7]=true;
                czyMiejsceJestZajete[6]=false;
                z6do7(promZSamochodami);
                naKtorejPozycjiJestem++;
            }
            if (naKtorejPozycjiJestem ==7) {
                while(czyMiejsceJestZajete[0]) {
                    try {
                        Thread.sleep(10);
                    }catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                czyMiejsceJestZajete[0]=true;
                czyMiejsceJestZajete[7]=false;
                z7do0(promZSamochodami);
                naKtorejPozycjiJestem =0;
                czyWjezdzaPierwszyRaz = true;
                czyMogePobracSamochody = true;
            }
        }

    }
}
