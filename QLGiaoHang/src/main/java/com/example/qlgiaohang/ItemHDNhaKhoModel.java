package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.CTVC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ItemHDNhaKhoModel implements Initializable {
    @FXML
    private Label SoHD;
    @FXML
    private Label NgayNhap;
    @FXML
    private Label TongTien;
    @FXML
    private Label MaCTVC;
    public static String sohd;
    public static String mactvc;
    public static String ngaynhap;
    public static String tongtien;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SoHD.setText(sohd);
      NgayNhap.setText(ngaynhap);
      TongTien.setText(tongtien);
      MaCTVC.setText(mactvc);
    }
    public static void addItem(CTVC ctvc) throws SQLException {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        sohd = ctvc.getSoHD().replaceAll(" ","");
        mactvc = ctvc.getMaCTVC().replaceAll(" ","");
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       ngaynhap = df.format(ctvc.getNgayNhap());
       ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowTongTien_SoHD " + sohd );
        while(rs.next()){
            tongtien= formatter.format(rs.getInt(1));
        }
    }

//    public void XuatKho(MouseEvent mouseEvent) throws SQLException, ParseException {
//        String qr = String.format("UPDATE dbo.CTVanChuyen SET NgayXuatKho = GETDATE() WHERE MaCTVC = '%s'",MaCTVC.getText());
//      Dataprovider.EditData(qr);
//      NhaKhoModel.Instance.ResetItemCTVC(NhaKhoModel.mank);
//    }
}
