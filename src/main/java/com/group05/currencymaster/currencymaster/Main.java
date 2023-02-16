package com.group05.currencymaster.currencymaster;


import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Main extends Application {
    private DataHandler dataHandler;
    @Override
    public void start(Stage stage) throws IOException {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Currency Master");
        stage.getIcons().add(new Image("file:src/main/resources/com/group05/currencymaster/currencymaster/assets/images/logo.png"));
        FXMLLoader splashscreen = new FXMLLoader(Main.class.getResource("Splash.fxml"));
        Scene splashscene = new Scene(splashscreen.load());
        stage.setScene(splashscene);

        Splash splashController = splashscreen.getController();
        splashController.initialize(stage);
        stage.show();


        //create a task to load data from api

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {


                try {

                    dataHandler = new DataHandler();

                } catch (Exception e) {
                    System.out.println("Task failed with exception: " + e.getMessage());
                    throw new Exception("Task failed with exception: " + e.getMessage(), e);
                }
                Thread.sleep(3000);
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            try {
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Primary-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);
                PrimaryView primaryViewController = fxmlLoader.getController();
                primaryViewController.initialize(stage, dataHandler);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        task.setOnFailed(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                splashController.showdialog();
                System.out.println("Task failed");

            }


       });

        new Thread(task).start();



    }

    public static void main(String[] args) {
        launch();
    }
}