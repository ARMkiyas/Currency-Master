package com.group05.currencymaster.currencymaster;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Splash {


    public StackPane dialogpane;
    private Stage stage;
    public void initialize(Stage stage) {
        this.stage = stage;
    }
    public void spCloseBtn(ActionEvent actionEvent) {
        stage.close();

    }

    public void showdialog(){
        JFXDialogLayout content = new JFXDialogLayout();
        Label label =new Label("Oops!, Error");
        label.setStyle("-fx-font-size: 18px");
        content.setHeading(label);
        content.setBody(new Label("Error Occur due to Internet connection, Please Check your Internet Connection and try again"));
        JFXButton button = new JFXButton("close");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        button.setStyle("-fx-background-color: #FF0032; -fx-text-fill: #ffffff;-fx-background-radius: 5px;-fx-font-size: 14px;-fx-padding: 5px 10px;-fx-font-weight: bold;");
        button.setRipplerFill(Paint.valueOf("#ffffff"));
        content.setActions(button);
        JFXDialog dialog =new JFXDialog(dialogpane, content, JFXDialog.DialogTransition.CENTER);
        dialog.setOverlayClose(false);
        dialog.show();
    }

}
