package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.PhieuGuiHang;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class Item_PhieuGui implements Initializable {
    @FXML
    private ImageView LoaiVC;
    @FXML
    private Label MaNV;
    @FXML
    private Label NgayGui;
    @FXML
    private Label SoPGH;
    public static String img;
    public static String manv;
    public static Date ngaygui;
    public static String sopgh;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String sourceimg = "icon/"+img+".png";
        sourceimg = sourceimg.replaceAll(" ","");
        LoaiVC.setImage(new Image(getClass().getResourceAsStream(sourceimg)));
        MaNV.setText(manv);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(ngaygui);
        NgayGui.setText(date);
        SoPGH.setText(sopgh);
    }
    public static void addItem(PhieuGuiHang pgh){
        img  = pgh.MaLVC;
        manv = pgh.MaNV;
        ngaygui = pgh.NgayLap;
        sopgh = pgh.SoPGH;
    }

    public void Remove(MouseEvent mouseEvent) throws SQLException {
        String qr = String.format("select SoHD from HoaDonVanChuyen where SoPGH like '%s'",SoPGH.getText());
        ResultSet rs = Dataprovider.DataTable(qr);
        String sohd =null;
        while (rs.next()) sohd = rs.getString(1);
        Dataprovider.EditData("EXEC USP_DeleteHD_SoHD "+sohd);
        qr = String.format("DELETE FROM PhieuGuiHang  WHERE SoPGH like '%s'",SoPGH.getText());
        Dataprovider.EditData(qr);
        GiaoDichModel.Intance.Reset_item();
    }

    public void Click_Item(MouseEvent mouseEvent) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            PhieuGuiHang Sopgh = null;
            Sopgh = GiaoDichModel.Intance.lstpgh.stream().filter(t->t.SoPGH.equals(SoPGH.getText())).findAny().orElse(null);
            if(Sopgh != null){
                PhieuGuiModel.SoPGH =Sopgh.SoPGH;
                root = FXMLLoader.load(getClass().getResource("View/PhieuGuiWindow.fxml"));
                primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
                Scene screen = new Scene(root);
                primaryStage.setScene(screen);
                LoginViewModel.MakeDragable(root,primaryStage);
                PhieuGuiModel.stage = primaryStage;
                LoginViewModel.stage.hide();
                primaryStage.show();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
