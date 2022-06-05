package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.HoaDon;
import com.example.qlgiaohang.Class.KhachHang;
import com.example.qlgiaohang.Class.LoaiVC;
import com.example.qlgiaohang.Class.PhieuGuiHang;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

public class BaoCaoModel implements Initializable {
    @FXML
    private javafx.scene.chart.LineChart<?, ?> LineChart;
    @FXML
    private javafx.scene.chart.PieChart PieChart;
    @FXML
    private Label KhachHang;
    @FXML
    private Label KhoiLuong;
    @FXML
    private Label TongDon;
    @FXML
    private Label TongTien;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try {
            List<HoaDon> list = HoaDon.AddList(Dataprovider.DataTable("SELECT * FROM dbo.HoaDonVanChuyen"));
            List<com.example.qlgiaohang.Class.KhachHang> kh = com.example.qlgiaohang.Class.KhachHang.AddList(Dataprovider.DataTable("SELECT * FROM dbo.KhachHang"));
            TongDon.setText(String.valueOf(list.size()));
            KhachHang.setText(String.valueOf(kh.size()));
            long tongtien = 0;
            int kg = 0;
            for (HoaDon hd:list
                 ) {
              tongtien+=Integer.parseInt(hd.Tien);
              ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowKG "+hd.SoHD);
              while (rs.next()) kg+=rs.getInt(1);
            }
            TongTien.setText(String.valueOf(formatter.format(tongtien)));
            KhoiLuong.setText(String.valueOf(kg));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        iniLineChart();
        try {
            iniPieChart();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void iniLineChart(){
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Monday",15000000));
        series.getData().add(new XYChart.Data("Tuesday",23000000));
        series.getData().add(new XYChart.Data("Wednesday",18000000));
        series.getData().add(new XYChart.Data("Thursday",28000000));
        series.getData().add(new XYChart.Data("Friday",30000000));
        series.getData().add(new XYChart.Data("Staturday",25000000));
        series.getData().add(new XYChart.Data("Sunday",32000000));
        LineChart.getData().addAll(series);
        LineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        series.getNode().setStyle("-fx-stroke: #FFD6DC");
    }
    private void iniPieChart() throws SQLException, ParseException {
        List<PhieuGuiHang> list = PhieuGuiHang.AddList();
        float tong = list.size();
        float dgbo=0,dgthuy=0,dgsat=0,dgkhong=0;
        for (PhieuGuiHang pg: list
             ) {
            switch (pg.getMaLVC()){
                case "LVC01     ":
                    dgbo++;
                    break;
                case "LVC02     ":
                    dgthuy++;
                    break;
                case "LVC03     ":
                    dgkhong++;
                    break;
                case "LVC04     ":
                    dgsat++;
                    break;
            }
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Đường Bộ",(dgbo/tong)*100),
                new PieChart.Data("Đường Thủy",(dgthuy/tong)*100),
                new PieChart.Data("Đường Sắt",(dgsat/tong)*100),
                new PieChart.Data("Hàng Không",(dgkhong/tong)*100)
        );
        PieChart.setData(pieChartData);
    }
}
