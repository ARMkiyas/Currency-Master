package com.group05.currencymaster.currencymaster;


import com.fasterxml.jackson.databind.JsonNode;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryView implements Initializable {


    public BorderPane titleBar;
    public JFXTextField currencytextfield;
    public Label convertedmoney;
    public JFXComboBox convertfrom, convertto;
    public Label symbollable;
    private Stage stage;
    private String amount = "1";
    private String basecurrency = "USD";
    private String targetcurrency = "LKR";

    private DataHandler dataHandler;
    private String result;

    private double xOffset = 0;
    private double yOffset = 0;
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
                if (!newValue.matches("[+-]?([0-9]*[.])?[0-9]+")) {
                    currencytextfield.setText(newValue.replaceAll("[^\\d(\\.\\d)*$]", ""));
                }
                amount = currencytextfield.getText();
                setAmount();
            }
        });


    }

    public void initialize(Stage stage, DataHandler dataHandler) {

        this.stage = stage;
        this.dataHandler = dataHandler;
        JsonNode datalist = dataHandler.getCurrencyList();
;

        datalist.get("rates").fields().forEachRemaining(jsonNode -> {
            convertfrom.getItems().add(jsonNode.getKey());
            convertto.getItems().add(jsonNode.getKey());
        });

        convertfrom.getSelectionModel().select("USD");
        convertto.getSelectionModel().select("LKR");

        setAmount();

//        stage.getScene().widthProperty().addListener( ( observable, oldWidth, newWidth ) ->
//        {
//            final double spacerMargin = newWidth.doubleValue()
//                    -  stage.getScene().getRoot().getChildrenUnmodifiable().stream().mapToDouble( node -> node.getLayoutBounds().getWidth() ).sum();
//            FlowPane.clearConstraints( convertto );
//            FlowPane.setMargin( convertto, new Insets( 0, 0, 0, spacerMargin ) );
//
//        } );
    }


    public void closeBtn() {
        stage.close();
    }

    public void minimizeBtn() {
        stage.setIconified(true);
    }

    public void swapBtn(ActionEvent e) {
        currencytextfield.setText(result);
        String temp = convertfrom.getSelectionModel().getSelectedItem().toString();
        convertfrom.getSelectionModel().select(convertto.getSelectionModel().getSelectedItem().toString());
        convertto.getSelectionModel().select(temp);
        setAmount();
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
        stage.setX(e.getScreenX() + xOffset);
        stage.setY(e.getScreenY() + yOffset);

    }


    public void currencyFromChange(ActionEvent actionEvent) {
        basecurrency = convertfrom.getSelectionModel().getSelectedItem().toString();
        String sybol = dataHandler.getCurrencySymbol(basecurrency);
        symbollable.setText(sybol);
        setAmount();

    }

    public void currencyToChange(ActionEvent actionEvent) {
        targetcurrency = convertto.getSelectionModel().getSelectedItem().toString();

        setAmount();

    }

    private void setAmount() {
        if (!amount.isEmpty() && amount.matches("[+-]?([0-9]*[.])?[0-9]+") ) {
             result = String.valueOf(Math.round(dataHandler.convertCurrency(basecurrency, targetcurrency, Double.parseDouble(amount)) * 100.0) / 100.0);
            convertedmoney.setText(dataHandler.getCurrencySymbol(targetcurrency)+" "+result);
        }

    }


    public void titlepressed(MouseEvent mouseEvent) {
        xOffset = stage.getX() - mouseEvent.getScreenX();
        yOffset = stage.getY() - mouseEvent.getScreenY();
    }
}
