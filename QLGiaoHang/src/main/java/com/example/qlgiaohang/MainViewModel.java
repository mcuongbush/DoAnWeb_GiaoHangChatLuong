package com.example.qlgiaohang;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.qlgiaohang.LoginViewModel.MakeDragable;

public class MainViewModel implements Initializable {
    @FXML
    private Pane swap_layout;
    @FXML
    private Label TenNV;
    @FXML
    private Label Name_Page;
    public static String MaNK;
    public static String Tennv;
    public static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        TenNV.setText(Tennv);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View/TongQuanWindow.fxml"));
            swap_layout.getChildren().removeAll();
            swap_layout.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close_main(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void open_HoaDon(MouseEvent mouseEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("View/HoaDonWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Hóa Đơn");
    }
    public void open_TongQuan(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/TongQuanWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Tổng Quan");
    }

    public void open_giaodich(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/GiaoDichWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Giao Dịch");
    }

    public void open_KhoBai(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/KhoBaiWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Kho Bãi");
    }

    public void Open_BaoCao(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/BaoCaoWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Báo Cáo");
    }

    public void open_KhachHang(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/CustomerWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Khách Hàng");
    }

    public void open_DoiTac(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/DoiTacVanChuyenWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Đối Tác");
    }

    public void open_NhanVien(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("View/StaffWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Nhân Viên");
    }

    public void open_QuanLy(MouseEvent mouseEvent) throws IOException {
        QuanLyModel.MaNK = MaNK;
        Parent root = FXMLLoader.load(getClass().getResource("View/quanLyWindow.fxml"));
        swap_layout.getChildren().removeAll();
        swap_layout.getChildren().setAll(root);
        Name_Page.setText("Quản Lý " +MaNK);
    }

    public void DangXuat(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View/LoginWindow.fxml"));
            Scene screen = new Scene(root);
            MakeDragable(root,stage);
            stage.setScene(screen);
            LoginViewModel.stage = stage;
            stage.close();
            LoginViewModel.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
