package com.group05.currencymaster.currencymaster;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryView implements Initializable{


    public BorderPane titleBar;
    public JFXTextField currencytextfield;
    public Label convertedmoney;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.currencytextfield.requestFocus();

    }
    public void initialize(Stage stage) {

        this.stage = stage;


    }

    public void closeBtn(){
        stage.close();
    }

    public void minimizeBtn(){
        stage.setIconified(true);
    }

    public void swapBtn(ActionEvent e) {

    }

    public void numBtnEvent(ActionEvent e) {

    }

    public void numBackSpace(ActionEvent e) {

    }


}
