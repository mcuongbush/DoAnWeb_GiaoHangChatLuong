package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDon {
    public String img;
    public HoaDon(){}
    public HoaDon(String img, String soHD, String ngayLap, String soPGD, String maNV, String tien, Boolean trangThai) {
        this.img = img;
        SoHD = soHD;
        NgayLap = ngayLap;
        SoPGD = soPGD;
        MaNV = maNV;
        Tien = tien;
        TrangThai = trangThai;
    }
    public HoaDon(String SoHD) throws SQLException {
        String s= String.format("SELECT * FROM dbo.HoaDonVanChuyen WHERE SoHD = '%s'",SoHD);
        ResultSet rs = Dataprovider.DataTable(s);
        while (rs.next()) {
            img = "icon/danggiao.png";
            if(rs.getInt(6) == 1) img = "icon/giaoxong.png";
            this.SoHD = rs.getString(1);
            NgayLap = rs.getString(2);
            SoPGD = rs.getString(4);
            MaNV = rs.getString(5);
            Tien = String.valueOf(rs.getInt(3));
        }
    }
    public static List<HoaDon> AddList(ResultSet rs) throws SQLException {
        List<HoaDon> lstHD = new ArrayList<HoaDon>();
        while (rs.next()) {
            String img = "icon/danggiao.png";
            if(rs.getInt(6) == 1) img = "icon/giaoxong.png";
            lstHD.add(new HoaDon(img,rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),String.valueOf(rs.getInt(3)),rs.getBoolean(6)));
        }
        return lstHD;
    }
    public static List<HoaDon> AddLists(String s,String nk) throws SQLException {
        List<HoaDon> lstHD = new ArrayList<HoaDon>();
        String qr = String.format("EXEC USP_ShowDonHang %s,%s" ,s,nk);
        ResultSet rs = Dataprovider.DataTable(qr);
        while (rs.next()) {
            String img = "icon/danggiao.png";
            if(rs.getInt(6) == 1) img = "icon/giaoxong.png";
            lstHD.add(new HoaDon(img,rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5),String.valueOf(rs.getInt(3)),rs.getBoolean(6)));
        }
        return lstHD;
    }
    public String SoHD;
    public String NgayLap;
    public String SoPGD;
    public String MaNV;
    public String Tien;

    public Boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        TrangThai = trangThai;
    }

    public Boolean TrangThai;
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSoHD() {
        return SoHD;
    }

    public void setSoHD(String soHD) {
        SoHD = soHD;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public String getSoPGD() {
        return SoPGD;
    }

    public void setSoPGD(String soPGD) {
        SoPGD = soPGD;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getTien() {
        return Tien;
    }

    public void setTien(String tien) {
        Tien = tien;
    }


}
