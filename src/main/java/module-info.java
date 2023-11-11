module com.example.demo4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo4 to javafx.fxml;
    exports com.example.demo4;
    exports com.example.demo4.Controllers;
    exports com.example.demo4.Server;
    opens com.example.demo4.Controllers to javafx.fxml;
}
