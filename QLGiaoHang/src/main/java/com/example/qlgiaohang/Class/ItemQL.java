package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemQL {
    public String getSoHD() {
        return SoHD;
    }

    public void setSoHD(String soHD) {
        SoHD = soHD;
    }

    public Date getNgayNhapKho() {
        return NgayNhapKho;
    }

    public void setNgayNhapKho(Date ngayNhapKho) {
        NgayNhapKho = ngayNhapKho;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long tongTien) {
        TongTien = tongTien;
    }

    public String getMaLVC() {
        return MaLVC;
    }

    public void setMaLVC(String maLVC) {
        MaLVC = maLVC;
    }

    public String getMaKhoDen() {
        return MaKhoDen;
    }

    public void setMaKhoDen(String maKhoDen) {
        MaKhoDen = maKhoDen;
    }

    public ItemQL(String soHD, Date ngayNhapKho, long tongTien, String maLVC, String maKhoDen, String maCTVC) {
        SoHD = soHD;
        NgayNhapKho = ngayNhapKho;
        TongTien = tongTien;
        MaLVC = maLVC;
        MaKhoDen = maKhoDen;
        MaCTVC = maCTVC;
    }
    public static List<ItemQL> AddList(String MaNK) throws SQLException {
        ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowItemQL "+MaNK);
        List<ItemQL> lstHD = new ArrayList<ItemQL>();
        while (rs.next()) {
            lstHD.add(new ItemQL(rs.getString(1).replaceAll(" ",""),rs.getDate(2),rs.getLong(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        }
        return lstHD;
    }
    private String SoHD;
    private Date NgayNhapKho;
    private long TongTien;
    private String MaLVC;
    private String MaKhoDen;

    public String getMaCTVC() {
        return MaCTVC;
    }

    public void setMaCTVC(String maCTVC) {
        MaCTVC = maCTVC;
    }

    private String MaCTVC;
}
