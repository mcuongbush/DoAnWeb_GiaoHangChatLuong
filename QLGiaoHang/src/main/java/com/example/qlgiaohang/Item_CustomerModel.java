package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.Confirmation;
import com.example.qlgiaohang.Class.KhachHang;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Item_CustomerModel implements Initializable {

    static String maKH;
    static String TenKH;
    static String GioiTinh;
    static String SoDienThoai;
    static String DiaChi;
    static String img;

    @FXML
    private Label lbl_MaKH;
    @FXML
    private Label lbl_TenKH;
    @FXML
    private Label lbl_DiaChi;
    @FXML
    private Label lbl_SoDienThoai;
    @FXML
    private ImageView img_avt;

    ImageView image;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_MaKH.setText(maKH);
        lbl_TenKH.setText(TenKH);
        lbl_SoDienThoai.setText(SoDienThoai);
        lbl_DiaChi.setText(DiaChi);
        img_avt.setImage(new Image(getClass().getResourceAsStream(img)));
        image=new ImageView(new Image(getClass().getResourceAsStream("icon/manAvt.png")));
        //image.setImage(new Image(getClass().getResourceAsStream("icon/manAvt.png")));
    }


    public static void Add_itemKH(  KhachHang KH  ){
        maKH=KH.makh;
        TenKH=KH.tenkh;
        GioiTinh=KH.gioitinh;
        SoDienThoai=KH.SDT;
        DiaChi=KH.diachi;
        if(KH.gioitinh=="Nam") {
            img="icon/manAvt.png"  ;
        }
        else img="icon/womanAvt.png";
    }

    public void ClickItem (MouseEvent mouseEvent) throws InvocationTargetException {
        CustomerModel instance = CustomerModel.getInstance();
        instance.txtF_HoTen.setText(lbl_TenKH.getText());
        instance.txtF_DiaChi.setText(lbl_DiaChi.getText());
        instance.txtF_SoDienThoai.setText(lbl_SoDienThoai.getText());
        List<KhachHang> lkh = instance.lstKH;
        for (KhachHang i : lkh)
        {
            if(i.makh.equals(lbl_MaKH.getText()))
            {
                if(i.gioitinh.equals("Nam")) instance.rdB_Nam.setSelected(true);
                else instance.rdB_Nu.setSelected(true);
            }
        }
        instance.MaKH=lbl_MaKH.getText();
        instance.MaKH=lbl_MaKH.getText();
        instance.TenKH=lbl_TenKH.getText();
        instance.btn_Sua.setDisable(false);
        instance.btn_Them.setDisable(true);
    }

    public void XoaKhachHang (MouseEvent actionEvent) throws SQLException, InvocationTargetException, IOException {
        ButtonType result = Confirmation.ShowConfirmation("Thông báo!","Bạn muốn xóa khách hàng " +lbl_TenKH.getText());
        if(result==Confirmation.Co)
        {
            String query = "BEGIN \n"+
                            "DELETE KhachHang \n"+
                            "WHERE MaKH = '" +lbl_MaKH.getText()+"'\n"+
                            "END;";
            Dataprovider.Stmt.executeUpdate(query);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo!");
            alert.setHeaderText("Xóa thành công khách hàng "+lbl_TenKH.getText());
            alert.showAndWait();
            CustomerModel.getInstance().loadData();

        }
    }
}
