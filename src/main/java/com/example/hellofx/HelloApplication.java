package com.example.hellofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    static SplitPane splitPane;
    static AnchorPane root;
    static AnchorPane rootMenu;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        splitPane = fxmlLoader.load();
        rootMenu = (AnchorPane)splitPane.getItems().get(0);
        root = (AnchorPane)splitPane.getItems().get(1);

        Scene scene = new Scene(splitPane, 1280, 800);
        stage.setResizable(false); //blokuje mozliwosc zmiany rozmiaru sceny
        stage.setTitle("Projekt obslugi promow");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}