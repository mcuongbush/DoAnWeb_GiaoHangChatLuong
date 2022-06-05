package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.NhaKho;
import com.example.qlgiaohang.Class.TheoDoi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TheoDoiModel implements Initializable {
    @FXML
    private VBox List_item;
    @FXML
    private Label KhoDen;
    @FXML
    private Label KhoDi;
    @FXML
    private ImageView LVC;
    public static Stage stage;
    public static String SoHD;
    public static String lvc;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String s = String.format("SELECT MaKhoBD,MaKhoKT FROM dbo.HoaDonVanChuyen,dbo.TuyenDuong WHERE HoaDonVanChuyen.MaTD = TuyenDuong.MaTD AND SoHD = '%s'",SoHD);
        try {
            ResultSet rs = Dataprovider.DataTable(s);
            String s1 = null;
            String s2 =null;
            while (rs.next()){
                s1 = rs.getString(1);
                s2 = rs.getString(2);
            }
            KhoDen.setText((new NhaKho(s2).getTenNK()));
            KhoDi.setText((new NhaKho(s1).getTenNK()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sourceimg = "icon/"+lvc+".png";
        LVC.setImage(new Image(getClass().getResourceAsStream(sourceimg)));
        try {
            LoadData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void LoadData() throws SQLException, ParseException {
        String s = String.format("SELECT NgayNhapKho,NgayXuatKho,MaNK FROM dbo.CTVanChuyen WHERE SoHD = '%s'",SoHD);
        ResultSet rs = Dataprovider.DataTable(s);
        List<TheoDoi> list = new ArrayList<TheoDoi>();
        while (rs.next()){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            list.add(new TheoDoi(df.parse(rs.getString(1)),false,rs.getString(3)));
            if(rs.getString(2)!= null)list.add(new TheoDoi(df.parse(rs.getString(2)),true,rs.getString(3)));
        }
        Node[] node = new Node[list.size()];
        for (int i = 0; i < node.length; i++) {
            try {
                Item_TheoDoiModel.Add_Item(list.get(i));
                node[i] = FXMLLoader.load(getClass().getResource("View/Item_TheoDoiWindow.fxml"));
                List_item.getChildren().add(node[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Close_app(MouseEvent mouseEvent) {
       stage.close();
    }
}
