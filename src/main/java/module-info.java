module com.group05.currencymaster.currencymaster {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.group05.currencymaster.currencymaster to javafx.fxml;
    exports com.group05.currencymaster.currencymaster;
}