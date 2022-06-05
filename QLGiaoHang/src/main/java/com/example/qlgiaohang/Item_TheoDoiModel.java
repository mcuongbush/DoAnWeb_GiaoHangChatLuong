package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.NhaKho;
import com.example.qlgiaohang.Class.TheoDoi;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

public class Item_TheoDoiModel implements Initializable {
    @FXML
    private Label Message;
    @FXML
    private Label Time;

    public static Date time;
    public static String mess;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DateFormat df = new SimpleDateFormat("dd/MM HH:mm");
        Time.setText(df.format(time));
        Message.setText(mess);
    }
    public static void Add_Item(TheoDoi td) throws SQLException {
        time = td.getTime();
        if(!td.isSukien()){
            mess = "Đơn hàng đã đến kho "+(new NhaKho(td.getMaNK())).getTenNK();
        }
        else mess = "Đơn hàng đã xuất kho "+(new NhaKho(td.getMaNK())).getTenNK();
    }
}
