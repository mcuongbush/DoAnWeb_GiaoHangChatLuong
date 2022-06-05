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

public class Item_AddCTVCModel implements Initializable {
    @FXML
    private Label SoHD;
    public static String sohd;
    public void Remove_Item(MouseEvent mouseEvent) throws IOException, SQLException {
      Add_CTVCModel.LstAdd.removeIf(t->t.SoHD.equals(SoHD.getText()));
      Add_CTVCModel.Instance.Reset_AddItem();
      Add_CTVCModel.Instance.Reset_Item();
    }
    public static void Add_item(HoaDon hd){
        sohd = hd.SoHD;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SoHD.setText(sohd);
    }
}
