package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.HoaDon;
import com.example.qlgiaohang.Class.KhachHang;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

public class TongQuanModel implements Initializable {
    @FXML
    private BarChart<String,Double> chart;
    @FXML
    private Label DonHang;
    @FXML
    private ProgressIndicator KhachHang;
    @FXML
    private ProgressIndicator LoiNhuan;
    @FXML
    private Label TongTien;
    @FXML
    private ProgressIndicator Vanchuyen;
    @FXML
    private Label LBKhachHang;
    @FXML
    private Label LBLoiNhan;
    @FXML
    private Label LBVanChuyen;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series<String ,Double> series_01 = new XYChart.Series<>();
        series_01.setName("Đơn Hàng");
        series_01.getData().add(new XYChart.Data("25/03/2022",30000));
        series_01.getData().add(new XYChart.Data("26/03/2022",20000));
        series_01.getData().add(new XYChart.Data("27/03/2022",40000));
        series_01.getData().add(new XYChart.Data("28/03/2022",45000));
        series_01.getData().add(new XYChart.Data("29/03/2022",50000));
        chart.getData().add(series_01);
        try {
            add();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void add() throws SQLException {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<HoaDon> lst = HoaDon.AddList(Dataprovider.DataTable("SELECT * FROM dbo.HoaDonVanChuyen"));
        DonHang.setText(String.valueOf(lst.size()));
        double tongdon = lst.size();
        Vanchuyen.setProgress(tongdon/100);
        LBVanChuyen.setText(String.valueOf((int)(tongdon))+"%");
        double tongtien = 0;
        for (HoaDon hd:lst
             ) {
            tongtien = Double.parseDouble(hd.Tien);
        }
        double tien = tongtien/100000000;
        LoiNhuan.setProgress(tien);
        LBLoiNhan.setText(String.valueOf((int)(tien*100))+"%");
        TongTien.setText(formatter.format(tongtien));
        List<com.example.qlgiaohang.Class.KhachHang> lstkh = com.example.qlgiaohang.Class.KhachHang.AddList(Dataprovider.DataTable("SELECT * FROM dbo.KhachHang"));
        double temp = lstkh.size();
        double kh = (temp/100);
        KhachHang.setProgress(kh);
        LBKhachHang.setText(String.valueOf((int)(kh*100))+"%");
    }
}
