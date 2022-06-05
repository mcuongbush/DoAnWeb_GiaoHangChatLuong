package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.CT_HD;
import com.example.qlgiaohang.Class.CT_HH_HD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Add_ItemCTHDModel implements Initializable {
    static String Sourceimg;
    static String tenhh;
    static int sl;
    static int kg;
    static String tenloai;
    static int giatien;
    @FXML
    private Label GiaTien;

    @FXML
    private Label KG;

    @FXML
    private Label SL;

    @FXML
    private Label TenHH;

    @FXML
    private Label TenLoai;

    @FXML
    private ImageView img;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img.setImage(new Image(Sourceimg));
        TenHH.setText(tenhh);
        SL.setText(String.valueOf(sl));
        KG.setText(String.valueOf(kg));
        TenLoai.setText(tenloai);
        GiaTien.setText(String.valueOf(giatien));
    }
    public static void Add_ItemCTHD(CT_HH_HD cthd){
        Sourceimg = cthd.getImg();
        tenhh = cthd.getTenHH();
        sl = cthd.getSL();
        kg =cthd.getKG();
        tenloai =cthd.getTenLHH();
        giatien = cthd.getGiaTien();
    }

    public void Remove_Item(MouseEvent mouseEvent) {
        Add_PhieuGuiModel.Instance.lstCTHD.removeIf(n->(n.getTenHH().equals(TenHH.getText())));
        Add_PhieuGuiModel.Instance.Reset_ItemHH();
        Add_PhieuGuiModel.Instance.ClearHH();
    }
}
