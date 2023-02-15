module com.group05.currencymaster.currencymaster {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;


    opens com.group05.currencymaster.currencymaster to javafx.fxml;
    exports com.group05.currencymaster.currencymaster;
}