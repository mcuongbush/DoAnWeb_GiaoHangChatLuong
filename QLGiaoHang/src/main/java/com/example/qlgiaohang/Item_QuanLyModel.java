package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.ItemQL;
import com.example.qlgiaohang.Class.NhaKho;
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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class Item_QuanLyModel implements Initializable {
    @FXML
    private ImageView LVC;
    @FXML
    private Label MaCTVC;
    @FXML
    private Label NgayNhap;
    @FXML
    private Label NhaKho;
    @FXML
    private Label SoHD;
    @FXML
    private Label TongTien;

    public static String sohd;
    public static String ngaynhap;
    public static long tongtien;
    public static String lvc;
    public static String nhakho;
    public static String mactvc;
    public void XuatKho(MouseEvent mouseEvent) throws SQLException, ParseException, IOException {
        String qr = String.format("UPDATE dbo.CTVanChuyen SET NgayXuatKho = GETDATE() WHERE MaCTVC = '%s'",MaCTVC.getText());
        Dataprovider.EditData(qr);
        QuanLyModel.Instance.LoadData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
      SoHD.setText(sohd);
      NgayNhap.setText(ngaynhap);
      TongTien.setText(formatter.format(tongtien));
      NhaKho.setText(nhakho);
      String sourceimg = "icon/"+lvc+".png";
      LVC.setImage(new Image(getClass().getResourceAsStream(sourceimg)));
      MaCTVC.setText(mactvc);
    }
    public static void Add_Item(ItemQL ql) throws SQLException {
        sohd = ql.getSoHD();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ngaynhap =df.format(ql.getNgayNhapKho());
        tongtien = ql.getTongTien();
        lvc = ql.getMaLVC().replaceAll(" ","");
        nhakho =(new NhaKho(ql.getMaKhoDen()).getTenNK());
        mactvc = ql.getMaCTVC();
    }

    public void Click_Item(MouseEvent mouseEvent) {
        Stage primaryStage = new Stage();
        Parent root = null;
        ItemQL ql = null;
        for (ItemQL temp:QuanLyModel.Instance.lst
             ) {
            if(temp.getSoHD().equals(SoHD.getText()))ql = temp;
        }
        if(ql != null){
            TheoDoiModel.lvc = ql.getMaLVC().replaceAll(" ", "");
            TheoDoiModel.SoHD = SoHD.getText();
            try {
                root = FXMLLoader.load(getClass().getResource("View/TheoDoiWinDow.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
            Scene screen = new Scene(root);
            primaryStage.setScene(screen);
            TheoDoiModel.stage = primaryStage;
            LoginViewModel.MakeDragable(root, primaryStage);
            primaryStage.show();
        }

    }
}
