package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhaKho {
    public String getMaNK() {
        return MaNK;
    }

    public void setMaNK(String maNK) {
        MaNK = maNK;
    }

    public String getTenNK() {
        return TenNK;
    }

    public void setTenNK(String tenNK) {
        TenNK = tenNK;
    }

    public int getDienTich() {
        return DienTich;
    }

    public void setDienTich(int dienTich) {
        DienTich = dienTich;
    }

    public int getSucChua() {
        return SucChua;
    }

    public void setSucChua(int sucChua) {
        SucChua = sucChua;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getMaKv() {
        return MaKv;
    }

    public void setMaKv(String maKv) {
        MaKv = maKv;
    }

    public NhaKho(String maNK, String tenNK, int dienTich, int sucChua, String diaChi, String maKv) {
        MaNK = maNK;
        TenNK = tenNK;
        DienTich = dienTich;
        SucChua = sucChua;
        DiaChi = diaChi;
        MaKv = maKv;
    }
    public NhaKho(String maNK) throws SQLException {
        String s = String.format("SELECT * FROM dbo.NhaKho WHERE MaNK = '%s'",maNK);
        ResultSet rs= Dataprovider.DataTable(s);
        while (rs.next()){
            MaNK = maNK;
            TenNK = rs.getString(2);
            DienTich = rs.getInt(3);
            SucChua = rs.getInt(4);
            DiaChi = rs.getString(5);
            MaKv = rs.getString(6);
        }
    }
    public static List<NhaKho> AddList(ResultSet rs) throws SQLException {
        List<NhaKho> lstNK = new ArrayList<NhaKho>();
        while (rs.next()) {
            lstNK.add(new NhaKho(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5), rs.getString(6)));
        }
        return lstNK;
    }
    public static ObservableList<NhaKho> getPlanetList() throws SQLException {
        List<NhaKho> lstNK = NhaKho.AddList(Dataprovider.DataTable("EXEC USP_ShowNhaKho KV0003"));
        ObservableList<NhaKho> list //
                = FXCollections.observableArrayList(lstNK);
        return list;
    }
    @Override
    public String toString() {
        return this.getTenNK();
    }
    public NhaKho(){}
    private String MaNK;
    private String TenNK;
    private int DienTich;
    private int SucChua;
    private String DiaChi;
    private String MaKv;

}
