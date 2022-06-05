package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiVanChuyen {
    public String MaLVC;
    public String TenLVC;
    public float Gia;

    public static List<LoaiVanChuyen> lstLVC = new ArrayList<LoaiVanChuyen>();
    public LoaiVanChuyen(){};

    public static ObservableList<LoaiVanChuyen> AddObservable() throws SQLException {

        ResultSet rs = Dataprovider.DataTable("select MaLVC,TenLVC from LoaiVanChuyen");
        while(rs.next()){
            lstLVC.add(new LoaiVanChuyen(rs.getString(1),rs.getString(2)));
        }
        ObservableList <LoaiVanChuyen> lst = FXCollections.observableArrayList(lstLVC);

        return lst;
    }
    public LoaiVanChuyen(String MaLVC) throws SQLException {
        String qr = String.format("select * from LoaiVanChuyen WHERE MaLVC = '%s'",MaLVC);
        ResultSet rs = Dataprovider.DataTable(qr);
        while (rs.next()){
            this.MaLVC = MaLVC;
            TenLVC = rs.getString(2);
            Gia = rs.getFloat(3);
        }
    }

    public LoaiVanChuyen(String maLVC,String tenLVC)
    {
        MaLVC = maLVC;
        TenLVC=tenLVC;
    }
    public LoaiVanChuyen(String maLVC, String tenLVC, float gia) {
        MaLVC = maLVC;
        TenLVC = tenLVC;
        Gia = gia;
    }

    @Override
    public String toString()
    {
        return TenLVC;
    }
    public String getMaLVC() {
        return MaLVC;
    }

    public void setMaLVC(String maLVC) {
        MaLVC = maLVC;
    }

    public String getTenLVC() {
        return TenLVC;
    }

    public void setTenLVC(String tenLVC) {
        TenLVC = tenLVC;
    }

    public float getGia() {
        return Gia;
    }

    public void setGia(float gia) {
        Gia = gia;
    }



}
