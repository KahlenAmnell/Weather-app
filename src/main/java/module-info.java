module Init{
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens pl.bernat;
    opens pl.bernat.controller;
}
