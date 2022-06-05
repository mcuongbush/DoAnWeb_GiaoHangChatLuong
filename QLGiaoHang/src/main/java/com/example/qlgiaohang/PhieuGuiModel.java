package com.example.qlgiaohang;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PhieuGuiModel implements Initializable {
    @FXML
    public static Stage stage;
    public static String SoPGH = null;
    @FXML
    private Label KH_DiaChi;

    @FXML
    private ImageView KH_Gender;

    @FXML
    private Label KH_MaKH;

    @FXML
    private Label KH_SDT;

    @FXML
    private Label KH_TenKH;

    @FXML
    private Label KN_DiaChi;

    @FXML
    private ImageView KN_Gender;

    @FXML
    private Label KN_MaKN;

    @FXML
    private Label KN_SDT;

    @FXML
    private Label KN_TenKN;

    @FXML
    private ImageView LoaiVC;

    @FXML
    private Label MaHD;

    @FXML
    private Label TenNV;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowPhieuGui_SoPGH " + SoPGH);
            while (rs.next()){
                KH_MaKH.setText(rs.getString(1).replaceAll(" ",""));
                KH_TenKH.setText(rs.getString(2));
                KH_SDT.setText(rs.getString(3));
                KH_DiaChi.setText(rs.getString(5));
                if(rs.getInt(4) == 0) KH_Gender.setImage(new Image(getClass().getResourceAsStream("icon/Man.png")));
                else KH_Gender.setImage(new Image(getClass().getResourceAsStream("icon/female.png")));
                KN_MaKN.setText(rs.getString(6).replaceAll(" ",""));
                KN_TenKN.setText(rs.getString(7));
                KN_SDT.setText(rs.getString(8));
                KN_DiaChi.setText(rs.getString(10));
                if(rs.getInt(9) == 0) KN_Gender.setImage(new Image(getClass().getResourceAsStream("icon/Man.png")));
                else KN_Gender.setImage(new Image(getClass().getResourceAsStream("icon/female.png")));
                String sourceimg = "icon/"+rs.getString(11)+".png";
                sourceimg = sourceimg.replaceAll(" ","");
                LoaiVC.setImage(new Image(getClass().getResourceAsStream(sourceimg)));
                TenNV.setText(rs.getString(12));
                MaHD.setText(rs.getString(13).replaceAll(" ",""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Close_app(MouseEvent mouseEvent) {
        stage.close();
        LoginViewModel.stage.show();
    }

    public void Open_HoaDon(MouseEvent mouseEvent) throws IOException {
        CT_HoaDonModel.MaHD = MaHD.getText();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View/CT_HoaDonWindow.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        Scene screen = new Scene(root);
        primaryStage.setScene(screen);
        CT_HoaDonModel.stage = primaryStage;
        LoginViewModel.MakeDragable(root,primaryStage);
        stage.hide();
        primaryStage.show();
        CT_HoaDonModel.isTrue = true;
    }
}
