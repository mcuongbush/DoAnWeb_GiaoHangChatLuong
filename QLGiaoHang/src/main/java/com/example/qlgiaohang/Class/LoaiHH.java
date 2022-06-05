package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LoaiHH {
    public String getMaLHH() {
        return MaLHH;
    }

    public void setMaLHH(String maLHH) {
        MaLHH = maLHH;
    }

    public String getTenLHH() {
        return TenLHH;
    }

    public void setTenLHH(String tenLHH) {
        TenLHH = tenLHH;
    }

    public LoaiHH(String maLHH, String tenLHH) {
        MaLHH = maLHH;
        TenLHH = tenLHH;
    }
    public LoaiHH(String maLHH) throws SQLException {
        ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowLoaiHH_MaLHH "+maLHH);
        while (rs.next()) {
            TenLHH = rs.getString(2);
        }
        MaLHH = maLHH;
    }
    public LoaiHH(){}
    @Override
    public String toString() {
        return this.getTenLHH();
    }
    public static List<LoaiHH> AddList() throws SQLException, ParseException {
        ResultSet rs = Dataprovider.DataTable("SELECT * FROM dbo.LoaiHH");
        List<LoaiHH> lstHH = new ArrayList<LoaiHH>();
        while (rs.next()) {
            lstHH.add(new LoaiHH(rs.getString(1).replaceAll(" ",""),rs.getString(2)));
        }
        return lstHH;
    }
    private String MaLHH;
    private String TenLHH;
}
