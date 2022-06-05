package com.example.qlgiaohang.Class;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Confirmation  {
    public static ButtonType Co = new ButtonType("Có", ButtonBar.ButtonData.YES);
    public static ButtonType Khong = new ButtonType("Không", ButtonBar.ButtonData.CANCEL_CLOSE);
    public static ButtonType ShowConfirmation(String HeaderTitle, String HeaderText)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(HeaderTitle);
        alert.setHeaderText(HeaderText);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(Co,Khong);
        Optional<ButtonType> option = alert.showAndWait();
        return option.get();
    }
}
