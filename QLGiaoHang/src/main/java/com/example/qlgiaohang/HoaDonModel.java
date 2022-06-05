package com.example.qlgiaohang;

import com.example.qlgiaohang.Class.HoaDon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.xml.transform.Result;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HoaDonModel implements Initializable {
    @FXML
    public VBox paneitems;
    @FXML
    private Label DangGiao;
    @FXML
    private Label HoanThanh;
    @FXML
    private Label TongDon;
    @FXML
    private Label TongTien;
    public Integer tongdon;
    public Integer danggiao;
    public Integer hoanthanh;
    public Integer tongtien;
    public static HoaDonModel getInstance(){
        if (instance == null) instance = new HoaDonModel();
        return instance;
    }

    public static void setInstance(HoaDonModel instance) {
        HoaDonModel.instance = instance;
    }
    private static HoaDonModel instance;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LoadData();
            instance = this;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void LoadData() throws InvocationTargetException{
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        paneitems.getChildren().clear();
        List<HoaDon> lstHD = null;
        hoanthanh =0;
        try {
            lstHD = new ArrayList<HoaDon>(HoaDon.AddList(Dataprovider.DataTable("select SoHD,CONVERT(VARCHAR(10),NgayLapHD,103),TongTien,SoPGH,MaNV,TrangThai from HoaDonVanChuyen")));
            List<HoaDon> list = new ArrayList<>();
            for (HoaDon hd: lstHD
                 ) {
                if(hd.TrangThai == false)list.add(hd);
                else hoanthanh++;
            }
            Node[] node = new Node[list.size()];
            tongdon = danggiao = tongtien =0;
            for (int i=0;i<node.length;i++){
                    HoaDon hd = list.get(i);
                    Item_HDModel.Add_ItemHD(hd);
                    tongdon ++;
                    if(hd.TrangThai == true) hoanthanh++;
                    else danggiao++;
                    tongtien +=Integer.parseInt(hd.Tien);
                    node[i] = (Node)FXMLLoader.load(getClass().getResource("View/item_hoadon.fxml"));
                    paneitems.getChildren().add(node[i]);
            }
            TongDon.setText(String.valueOf(tongdon));
            DangGiao.setText(String.valueOf(danggiao));
            HoanThanh.setText(String.valueOf(hoanthanh));
            TongTien.setText(formatter.format(tongtien)+"VNĐ");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

}
