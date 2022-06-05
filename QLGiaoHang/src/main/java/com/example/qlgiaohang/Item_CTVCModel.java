package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.HoaDon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Item_CTVCModel implements Initializable {
    @FXML
    private Label SoHD;
    public static String sohd;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         SoHD.setText(sohd);
    }
    public static void Add_item(HoaDon hd){
        sohd = hd.SoHD;
    }

    public void Add_Item(MouseEvent mouseEvent) throws SQLException, IOException {
        Add_CTVCModel.LstAdd.add(new HoaDon(SoHD.getText()));
        Add_CTVCModel.Instance.Reset_AddItem();
        Add_CTVCModel.Instance.Reset_Item();
    }


}
