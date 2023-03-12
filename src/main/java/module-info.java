module com.example.alquilervehiculosv1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.alquilervehiculosv1 to javafx.fxml;
    exports com.example.alquilervehiculosv1;
}