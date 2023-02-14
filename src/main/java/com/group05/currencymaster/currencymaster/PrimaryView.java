package com.group05.currencymaster.currencymaster;


import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryView {


    private Stage stage;


    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void closeBtn(){
        stage.close();
    }

    public void minimizeBtn(){
        stage.setIconified(true);
    }
}
