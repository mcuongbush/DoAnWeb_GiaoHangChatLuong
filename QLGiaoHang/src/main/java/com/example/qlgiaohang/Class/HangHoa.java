package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class HangHoa {
    public HangHoa(){}
    public HangHoa(String maHH, String tenHH, String mota, String donVT, String maLHH, Long giaTien) {
        MaHH = maHH;
        TenHH = tenHH;
        Mota = mota;
        DonVT = donVT;
        MaLHH = maLHH;
        GiaTien = giaTien;
    }

    public String getMaHH() {
        return MaHH;
    }

    public void setMaHH(String maHH) {
        MaHH = maHH;
    }

    public String getTenHH() {
        return TenHH;
    }

    public void setTenHH(String tenHH) {
        TenHH = tenHH;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getDonVT() {
        return DonVT;
    }

    public void setDonVT(String donVT) {
        DonVT = donVT;
    }

    public String getMaLHH() {
        return MaLHH;
    }

    public void setMaLHH(String maLHH) {
        MaLHH = maLHH;
    }

    public Long getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(Long giaTien) {
        GiaTien = giaTien;
    }
    public static List<HangHoa> AddList(String s) throws SQLException, ParseException {
        ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowHH_TenHH "+s);
        List<HangHoa> lstHH = new ArrayList<HangHoa>();

        while (rs.next()) {
            lstHH.add(new HangHoa(rs.getString(1).replaceAll(" ",""),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5).replaceAll(" ",""),rs.getLong(6)));
        }
        return lstHH;
    }
    @Override
    public String toString() {
        return this.getTenHH();
    }
    private String MaHH;
    private String TenHH;
    private String Mota;
    private String DonVT;
    private String MaLHH;
    private Long GiaTien;
}
