package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhieuGuiHang {
    public PhieuGuiHang(String soPGH, Date ngayLap, boolean COD, String maKH, String maLVC, String maNV, String maKN) {
        SoPGH = soPGH;
        NgayLap = ngayLap;
        this.COD = COD;
        MaKH = maKH;
        MaLVC = maLVC;
        MaNV = maNV;
        MaKN = maKN;
    }
     public PhieuGuiHang(){}
    public String SoPGH;
    public Date NgayLap;

    public String getSoPGH() {
        return SoPGH;
    }

    public void setSoPGH(String soPGH) {
        SoPGH = soPGH;
    }

    public Date getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(Date ngayLap) {
        NgayLap = ngayLap;
    }

    public boolean isCOD() {
        return COD;
    }

    public void setCOD(boolean COD) {
        this.COD = COD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getMaLVC() {
        return MaLVC;
    }

    public void setMaLVC(String maLVC) {
        MaLVC = maLVC;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getMaKN() {
        return MaKN;
    }

    public void setMaKN(String maKN) {
        MaKN = maKN;
    }
    public static List<PhieuGuiHang> AddList() throws SQLException {
        ResultSet rs = Dataprovider.DataTable("Select * from PhieuGuiHang");
        List<PhieuGuiHang> lstHD = new ArrayList<PhieuGuiHang>();
        while (rs.next()) {
            lstHD.add(new PhieuGuiHang(rs.getString(1),rs.getDate(2),rs.getBoolean(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
        }
        return lstHD;
    }
    public boolean COD;
    public String MaKH;
    public String MaLVC;
    public String MaNV;
    public String MaKN;
}
