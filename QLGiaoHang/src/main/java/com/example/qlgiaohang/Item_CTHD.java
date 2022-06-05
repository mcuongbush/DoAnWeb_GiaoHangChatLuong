package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.CT_HD;
import com.example.qlgiaohang.Class.HoaDon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Item_CTHD implements Initializable {
    static String Sourceimg;
    static String tenhh;
    static String macthd;
    static int sl;
    static Float kg;
    static String tenloai;
    static int giatien;
    @FXML
    private ImageView img;
    @FXML
    private Label TenHH;
    @FXML
    private Label MaCTHD;
    @FXML
    private Label SL;
    @FXML
    private Label KG;
    @FXML
    private Label TenLoai;
    @FXML
    private Label GiaTien;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img.setImage(new Image(getClass().getResourceAsStream(Sourceimg)));
        TenHH.setText(tenhh);
        MaCTHD.setText(macthd);
        SL.setText(String.valueOf(sl));
        KG.setText(String.valueOf(kg));
        TenLoai.setText(tenloai);
        GiaTien.setText(String.valueOf(giatien));
    }
    public static void Add_ItemCTHD(CT_HD cthd){
        Sourceimg = cthd.img;
        tenhh = cthd.TenHH;
        macthd = cthd.MaCTHD;
        sl = cthd.SL;
        kg =cthd.KG;
        tenloai =cthd.TenLHH;
        giatien = cthd.GiaTien;
    }
}
