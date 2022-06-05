package com.example.qlgiaohang;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class Registration {
    @FXML
    private AnchorPane pane;

    public void close_app(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void back_app(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/LoginWindow.fxml"));
        LoginViewModel.stage.getScene().setRoot(root);
    }
}
