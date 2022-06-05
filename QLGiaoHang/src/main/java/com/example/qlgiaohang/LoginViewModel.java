package com.example.qlgiaohang;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.gluonhq.charm.glisten.control.Icon;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginViewModel extends Application implements Initializable {

    static Stage stage = null;
    @FXML
    private Pane content_area;
    @FXML
    private AnchorPane parent;
    private static double x =0;
    private static double y=0;
    @FXML
    private PasswordField MatKhau;
    @FXML
    private TextField TenTK;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
         Dataprovider.ConnecSQL();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View/LoginWindow.fxml"));
            primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
            Scene screen = new Scene(root);
            MakeDragable(root,primaryStage);
            primaryStage.setScene(screen);
            stage = primaryStage;
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void MakeDragable(Parent parent,Stage stage1){
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getSceneX();
                y = mouseEvent.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage1.setX(mouseEvent.getScreenX()-x);
                stage1.setY(mouseEvent.getScreenY()-y);
            }
        });
    }
    public void close_app(javafx.scene.input.MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void open_app(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("View/Registration.fxml"));
            content_area.getChildren().removeAll();
            content_area.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void open_main(ActionEvent actionEvent) throws IOException, SQLException {
        if(TenTK.getText().isEmpty() || MatKhau.getText().isEmpty()){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi nhập liệu");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin tài khoản");
            alert.showAndWait();
        }
        else {
            String qr = String.format("SELECT MaNV FROM dbo.TaiKhoan WHERE TenTK = '%s' AND MatKhau = '%s'",TenTK.getText(),MatKhau.getText());
            ResultSet rs = Dataprovider.DataTable(qr);
            String MaNV=null;
            while (rs.next())MaNV = rs.getString(1);
            if(MaNV == null){
                javafx.scene.control.Alert alert = new javafx.scene.control.Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi Đăng Nhập");
                alert.setHeaderText(null);
                alert.setContentText("Sai Thông Tin Đăng Nhập");
                alert.showAndWait();
            }
            else {
                ResultSet rs1 = Dataprovider.DataTable("EXEC USP_MaNV "+TenTK.getText());
                String mank = null;
                while (rs1.next()) mank = rs1.getString(1);
                MainViewModel.MaNK = mank;
                String s = String.format("SELECT TenNV FROM dbo.TaiKhoan,dbo.NhanVien WHERE NhanVien.MaNV = TaiKhoan.MaNV AND TenTK = '%s'",TenTK.getText());
                ResultSet ten = Dataprovider.DataTable(s);
                String TenNV = null;
                while (ten.next())  TenNV = ten.getString(1);
                MainViewModel.Tennv = TenNV;
                Parent root = FXMLLoader.load(getClass().getResource("View/MainWindow.fxml"));
                stage.close();
                Scene screen = new Scene(root);
                stage.setScene(screen);
                MakeDragable(root,stage);
                MainViewModel.stage = stage;
                stage.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TenTK.setText("dinhthuan");
        MatKhau.setText("1234");
    }
}
