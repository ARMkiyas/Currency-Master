package com.group05.currencymaster.currencymaster;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Primary-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Currency Master");
        PrimaryView primaryViewController=fxmlLoader.getController();
        primaryViewController.initialize(stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}