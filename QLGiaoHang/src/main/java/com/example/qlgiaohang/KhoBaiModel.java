package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.NhaKho;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class KhoBaiModel implements Initializable {

    @FXML
    private Label SLNhaKho;
    @FXML
    private Label SLThanhPho;
    @FXML
    private Label SucChua;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            List<NhaKho> lst = NhaKho.AddList(Dataprovider.DataTable("SELECT * FROM dbo.NhaKho"));
            SLNhaKho.setText(String.valueOf(lst.size()));
            SLThanhPho.setText(String.valueOf(lst.size()));
            long sum = 0;
            for (NhaKho nk: lst
                 ) {
                sum += nk.getSucChua();
            }
            SucChua.setText(String.valueOf(sum));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void open_MienBac(MouseEvent mouseEvent) throws IOException {
        NhaKhoModel.MaKV = "KV0001";
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View/NhaKhoWindow.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        Scene screen = new Scene(root);
        primaryStage.setScene(screen);
        NhaKhoModel.Stage = primaryStage;
        LoginViewModel.MakeDragable(root,primaryStage);
        LoginViewModel.stage.hide();
        primaryStage.show();
    }

    public void open_MienTrung(MouseEvent mouseEvent) throws IOException {
        NhaKhoModel.MaKV = "KV0002";
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View/NhaKhoWindow.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        Scene screen = new Scene(root);
        primaryStage.setScene(screen);
        NhaKhoModel.Stage = primaryStage;
        LoginViewModel.MakeDragable(root,primaryStage);
        LoginViewModel.stage.hide();
        primaryStage.show();
    }

    public void open_MienNam(MouseEvent mouseEvent) throws IOException {
        NhaKhoModel.MaKV = "KV0003";
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View/NhaKhoWindow.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        Scene screen = new Scene(root);
        primaryStage.setScene(screen);
        NhaKhoModel.Stage = primaryStage;
        LoginViewModel.MakeDragable(root,primaryStage);
        LoginViewModel.stage.hide();
        primaryStage.show();
    }
}
