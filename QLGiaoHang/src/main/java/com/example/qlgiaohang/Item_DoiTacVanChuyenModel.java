package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.Confirmation;
import com.example.qlgiaohang.Class.DoiTacVanChuyen;
import com.example.qlgiaohang.Class.LoaiVanChuyen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class Item_DoiTacVanChuyenModel implements Initializable {

    static String MaDTVC;
    static String TenDTVC;
    static String DiaChi;
    static String LoaiVC;
    static LocalDate NgayHopTac;
    static int HoaHong;
    static String SoDienThoai;
   static LoaiVanChuyen lvc;
    @FXML
    private Label lbl_TenDTVC;
    @FXML
    private Label lbl_MaDTVC;
    @FXML
    private Label lbl_DiaChi;
    @FXML
    private Label lbl_HoaHong;
    @FXML
    private Label  lbl_NgayHopTac;
    @FXML
    private Label lbl_LoaiVC;
    @FXML
    private Label lbl_SoDienThoai;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lvc = new LoaiVanChuyen(LoaiVC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lbl_LoaiVC.setText(lvc.TenLVC);
        lbl_MaDTVC.setText(MaDTVC);
        lbl_SoDienThoai.setText(SoDienThoai);
        lbl_TenDTVC.setText(TenDTVC);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lbl_NgayHopTac.setText(NgayHopTac.format(dt));
        lbl_DiaChi.setText(DiaChi);
        lbl_HoaHong.setText(String.valueOf(HoaHong));
    }
    public static void Add_Item(DoiTacVanChuyen dtvc)
    {
        MaDTVC= dtvc.MaDTVC;
        TenDTVC= dtvc.TenDTVC;
        DiaChi= dtvc.DiaChi;
        SoDienThoai= dtvc.SoDienThoai;
        HoaHong= dtvc.HoaHong;
        NgayHopTac=dtvc.NgayHopTac;
        LoaiVC= dtvc.MaLVC;
    }
    public void btn_XoaDoiTac(MouseEvent mouseEvent) throws SQLException, InvocationTargetException {
        ButtonType result = Confirmation.ShowConfirmation("Thông báo!","Bạn muốn xóa đối tác " +lbl_TenDTVC.getText());
        if(result==Confirmation.Co)
        {
            String query = "BEGIN \n"+
                    "DELETE DoiTacVanChuyen \n"+
                    "WHERE MaDTVC = '" +lbl_MaDTVC.getText()+"'\n"+
                    "END;";
            Dataprovider.Stmt.executeUpdate(query);
            DoiTacVanChuyenModel.getInstance().loadData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Xóa thành công đối tác "+lbl_TenDTVC.getText());
            alert.showAndWait();


        }
    }
}
