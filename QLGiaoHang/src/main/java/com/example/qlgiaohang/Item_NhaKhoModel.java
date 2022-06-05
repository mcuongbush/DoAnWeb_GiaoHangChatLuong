package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.CTVC;
import com.example.qlgiaohang.Class.NhaKho;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class Item_NhaKhoModel implements Initializable {
    @FXML
    private Label TenNK;
    @FXML
    private Label DienTich;
    @FXML
    private Label SucChua;
    @FXML
    private Label DonHang;
    public static String tennk;
    public static String dientich;
    public static String succhua;
    public static String donhang;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TenNK.setText(tennk);
        DienTich.setText(dientich);
        SucChua.setText(succhua);
        DonHang.setText(donhang);
    }

    public static void Add_item(NhaKho nk) throws SQLException, ParseException {
        List<CTVC> lstCTVC = CTVC.AddList(nk.getMaNK());
        tennk = nk.getTenNK();
        dientich = String.valueOf(nk.getDienTich());
        succhua = String.valueOf(nk.getSucChua());
        donhang = String.valueOf(lstCTVC.size());
    }
}
