package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.HoaDon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;



public class Item_HDModel implements Initializable{
    static String So_HD;
    static String img;
    static String Ngay_Lap;
    static String So_PGD;
    static String Ma_NV;
    static String Tien_t;
    @FXML
    private ImageView img_HD;
    @FXML
    private Label SoHD;
    @FXML
    private Label NgayLap;
    @FXML
    private Label SoPGD;
    @FXML
    private Label MaNV;
    @FXML
    private Label Tien;
    @FXML
    private Button open_CTHD;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        img_HD.setImage(new Image(getClass().getResourceAsStream(img)));
        SoHD.setText(So_HD);
        NgayLap.setText(Ngay_Lap);
        SoPGD.setText(So_PGD);
        MaNV.setText(Ma_NV);
        Tien.setText(formatter.format(Integer.parseInt(Tien_t)));
    }
    public static void Add_ItemHD(HoaDon hd){
      img = hd.img;
      So_HD = hd.SoHD;
      Ngay_Lap = hd.NgayLap;
      So_PGD = hd.SoPGD;
      Ma_NV =hd.MaNV;
      Tien_t =hd.Tien;
    }

    public void open_CT_HoaDon(ActionEvent actionEvent) throws IOException {
        CT_HoaDonModel.MaHD = SoHD.getText();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("View/CT_HoaDonWindow.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
        Scene screen = new Scene(root);
        primaryStage.setScene(screen);
        CT_HoaDonModel.stage = primaryStage;
        LoginViewModel.MakeDragable(root,primaryStage);
        LoginViewModel.stage.hide();
        primaryStage.show();
    }

    public void Remove_item(MouseEvent mouseEvent) throws SQLException, InvocationTargetException, IOException {
        Dataprovider.EditData("EXEC USP_DeleteHD_SoHD "+SoHD.getText());
        HoaDonModel.getInstance().LoadData();
    }
}
