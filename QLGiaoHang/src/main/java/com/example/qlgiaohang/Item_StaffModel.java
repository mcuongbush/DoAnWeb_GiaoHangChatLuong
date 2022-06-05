package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class Item_StaffModel implements Initializable {

    static String maNV;
    static String TenNV;
    static String GioiTinh;
    static String SoDienThoai;
    static String DiaChi;
    static String phongBan;
    static String ChucVu;
    static String BacLuong;
    static LocalDate NgaySinh;
    static String img;
    public PhongBan pb;
    @FXML
    private Label lbl_MaNV;
    @FXML
    private Label lbl_BacLuong;
    @FXML
    private Label lbl_PhongBan;
    @FXML
    private Label lbl_ChucVu;
    @FXML
    private Label lbl_TenNV;
    @FXML
    private Label lbl_DiaChi;
    @FXML
    private Label lbl_SoDienThoai;
    @FXML
    private Label lbl_NgaySinh;
    @FXML
    private ImageView img_avt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            pb = new PhongBan(phongBan);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lbl_MaNV.setText(maNV);
        lbl_TenNV.setText(TenNV);
        lbl_BacLuong.setText(BacLuong);
        lbl_ChucVu.setText(ChucVu);
        lbl_PhongBan.setText(pb.TenPB);
        lbl_SoDienThoai.setText(SoDienThoai);
        lbl_DiaChi.setText(DiaChi);
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lbl_NgaySinh.setText(NgaySinh.format(dt));
        img_avt.setImage(new Image(getClass().getResourceAsStream(img)));
    }
    public static void Add_itemNV(  NhanVien NV  ){
        maNV =NV.MaNV;
        TenNV= NV.TenNV;
        GioiTinh=NV.GioiTinh;
        SoDienThoai=NV.SoDienThoai;
        DiaChi=NV.DiaChi;
        phongBan=NV.PhongBan;
        NgaySinh=NV.NgaySinh;
        ChucVu=NV.ChucVu;
        BacLuong= String.valueOf( NV.BacLuong );
        if(NV.GioiTinh=="Nam") {
            img="icon/manAvt.png"  ;
        }
        else img="icon/womanAvt.png";
    }
    public void XoaNhanVien(MouseEvent mouseEvent) throws SQLException, InvocationTargetException {
        ButtonType result = Confirmation.ShowConfirmation("Thông báo!","Bạn muốn xóa nhân viên " +lbl_TenNV.getText());
        if(result==Confirmation.Co)
        {
            String query = "BEGIN \n"+
                    "DELETE NhanVien \n"+
                    "WHERE MaNV = '" +lbl_MaNV.getText()+"'\n"+
                    "END;";
            Dataprovider.Stmt.executeUpdate(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Xóa thành công nhân viên "+lbl_TenNV.getText());
            alert.showAndWait();
            DoiTacVanChuyenModel.getInstance().loadData();

        }
    }
    public void ClickItem (MouseEvent mouseEvent) throws InvocationTargetException, SQLException {
        StaffModel instance = StaffModel.getInstance();
        instance.txtF_HoTen .setText(lbl_TenNV.getText());
        instance.txtF_DiaChi.setText(lbl_DiaChi.getText());
        instance.txtF_SoDienThoai.setText(lbl_SoDienThoai.getText());
        instance.txtF_BacLuong.setText(lbl_BacLuong.getText());
        instance.dtP_NgaySinh.setValue(LocalDate.parse(lbl_NgaySinh.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        List<NhanVien> lstNV = instance.lstNV;
        for(NhanVien i : lstNV)
        {
            if(i.MaNV.equals(lbl_MaNV.getText()))
            {
                if(i.ChucVu.contains("Nhân Viên")) instance.rdB_NhanVien.setSelected(true);
                else instance.rdB_QuanLy.setSelected(true);
                if(i.MaNV.equals(lbl_MaNV.getText())) instance.cbx_PhongBan.setValue(instance.cbx_PhongBan.getItems().filtered(t -> t.MaPB.equals(i.PhongBan)).get(0));
                if(i.GioiTinh.equals("Nam")) instance.rdB_Nam.setSelected(true);
                else instance.rdB_Nu.setSelected(true);
                break;
            }
        }
        instance.MaNV=lbl_MaNV.getText();
        instance.btn_Sua.setDisable(false);
        instance.btn_Them.setDisable(true);
    }
}
