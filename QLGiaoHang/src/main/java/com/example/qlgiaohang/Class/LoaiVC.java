package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LoaiVC {
    public LoaiVC(String maLVC, String tenLVC, int gia) {
        MaLVC = maLVC;
        TenLVC = tenLVC;
        Gia = gia;
    }

    public LoaiVC() {
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

    public int getGia() {
        return Gia;
    }

    public void setGia(int gia) {
        Gia = gia;
    }
    public static List<LoaiVC> AddList() throws SQLException, ParseException {
        ResultSet rs = Dataprovider.DataTable("SELECT * FROM dbo.LoaiVanChuyen");
        List<LoaiVC> lstHH = new ArrayList<LoaiVC>();
        while (rs.next()) {
            lstHH.add(new LoaiVC(rs.getString(1),rs.getString(2),rs.getInt(3)));
        }
        return lstHH;
    }
    @Override
    public String toString() {
        return this.getTenLVC();
    }
    private String MaLVC;
    private String TenLVC;
    private int Gia;
}
