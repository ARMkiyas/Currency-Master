package com.group05.currencymaster.currencymaster;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryView implements Initializable {


    public BorderPane titleBar;
    public JFXTextField currencytextfield;
    public Label convertedmoney;
    private Stage stage;
    private String amount = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //focus on textfield
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                currencytextfield.requestFocus();
            }
        });

        //Event handler of onchanged for textfield

        currencytextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String newValue) {
                if (!newValue.matches("\\d*")) {
                    currencytextfield.setText(newValue.replaceAll("[^\\d]", ""));
                }
                amount = currencytextfield.getText();
            }
        });



    }

    public void initialize(Stage stage) {

        this.stage = stage;


    }


    public void closeBtn() {
        stage.close();
    }

    public void minimizeBtn() {
        stage.setIconified(true);
    }

    public void swapBtn(ActionEvent e) {

    }

    @FXML
    public void numBtnEvent(ActionEvent e) {
        String value = ((Button) e.getSource()).getText();
        currencytextfield.setText(currencytextfield.getText() + value);
    }

    public void numBackSpace(ActionEvent e) {
        if (!amount.isEmpty()) {
            amount = amount.substring(0, amount.length() - 1);
            currencytextfield.setText(amount);
        }

    }


    public void draging(MouseEvent e) {


    }


}
