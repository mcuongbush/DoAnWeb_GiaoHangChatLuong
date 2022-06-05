package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CTVC {


    public CTVC(){}

    public String getMaCTVC() {
        return MaCTVC;
    }

    public void setMaCTVC(String maCTVC) {
        MaCTVC = maCTVC;
    }

    public String getSoHD() {
        return SoHD;
    }

    public void setSoHD(String soHD) {
        SoHD = soHD;
    }

    public String getMaNK() {
        return MaNK;
    }

    public void setMaNK(String maNK) {
        MaNK = maNK;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        NgayNhap = ngayNhap;
    }

    public Date getNgayXuat() {
        return NgayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        NgayXuat = ngayXuat;
    }

    public CTVC(String maCTVC, String soHD, String maNK, Date ngayNhap, Date ngayXuat) {
        MaCTVC = maCTVC;
        SoHD = soHD;
        MaNK = maNK;
        NgayNhap = ngayNhap;
        NgayXuat = ngayXuat;
    }

    private String MaCTVC;
    private String SoHD;
    private String MaNK;
    private Date NgayNhap;
    private Date NgayXuat;
    public static List<CTVC> AddList(String maNK) throws SQLException, ParseException {
        maNK= maNK.replaceAll(" ","");
        ResultSet rs = Dataprovider.DataTable("EXEC USP_ShowCTVC_MaNK "+maNK);
        List<CTVC> lstCTVC = new ArrayList<CTVC>();
        while (rs.next()) {
            lstCTVC.add(new CTVC(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getDate(5)));
        }
        return lstCTVC;
    }
}