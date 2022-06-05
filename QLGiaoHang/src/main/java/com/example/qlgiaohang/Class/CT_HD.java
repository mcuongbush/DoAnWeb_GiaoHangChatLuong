package com.example.qlgiaohang.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CT_HD {
    public  CT_HD(){}
    public CT_HD(String img, String tenHH, String maCTHD, int SL, Float KG, String tenLHH, int giaTien) {
        this.img = img;
        TenHH = tenHH;
        MaCTHD = maCTHD;
        this.SL = SL;
        this.KG = KG;
        TenLHH = tenLHH;
        GiaTien = giaTien;
    }
    public static List<CT_HD> AddList(ResultSet rs) throws SQLException {
        List<CT_HD> lstHD = new ArrayList<CT_HD>();
        while (rs.next()) {
            String img = "image/"+rs.getString(7)+".png";
            img = img.replaceAll(" ","");
            lstHD.add(new CT_HD(img,rs.getString(1),rs.getString(2),rs.getInt(3),rs.getFloat(4),rs.getString(5), rs.getInt(6)));
        }
        return lstHD;
    }
    public String img;
    public String TenHH;
    public String MaCTHD;
    public int SL;
    public Float KG;
    public String TenLHH;
    public int GiaTien;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTenHH() {
        return TenHH;
    }

    public void setTenHH(String tenHH) {
        TenHH = tenHH;
    }

    public String getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(String maCTHD) {
        MaCTHD = maCTHD;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }

    public Float getKG() {
        return KG;
    }

    public void setKG(Float KG) {
        this.KG = KG;
    }

    public String getTenLHH() {
        return TenLHH;
    }

    public void setTenLHH(String tenLHH) {
        TenLHH = tenLHH;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int giaTien) {
        GiaTien = giaTien;
    }


}
