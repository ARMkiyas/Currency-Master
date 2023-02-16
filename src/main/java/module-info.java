module com.group05.currencymaster.currencymaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.web;
    requires javafx.media;
    requires javafx.swing;
    requires com.jfoenix;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;


    opens com.group05.currencymaster.currencymaster to javafx.fxml;
    exports com.group05.currencymaster.currencymaster;
}