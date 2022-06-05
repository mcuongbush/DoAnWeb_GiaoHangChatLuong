package com.example.qlgiaohang.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CT_HH_HD {
    public CT_HH_HD(String img, String tenHH, String maCTHD, int SL, int KG, String tenLHH, int giaTien, String mota, String DVT) {
        this.img = img;
        TenHH = tenHH;
        MaCTHD = maCTHD;
        this.SL = SL;
        this.KG = KG;
        TenLHH = tenLHH;
        GiaTien = giaTien;
        Mota = mota;
        this.DVT = DVT;
    }
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

    public int getKG() {
        return KG;
    }

    public void setKG(int KG) {
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

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }


    private String img;
    private String TenHH;
    private String MaCTHD;
    private int SL;
    private int KG;
    private String TenLHH;
    private int GiaTien;
    private String Mota;
    private String DVT;

}
