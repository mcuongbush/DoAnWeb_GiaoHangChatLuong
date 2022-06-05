package com.example.qlgiaohang.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHang {
    public String makh;
    public String tenkh;
    public String gioitinh;
    public String SDT;
    public String diachi;

    public KhachHang(){}
    public KhachHang(String maKH, String Name, String sdt, String DiaChi, String GioiTinh) {
        makh= maKH;
        tenkh = Name;
        gioitinh = GioiTinh;
        SDT = sdt;
        diachi = DiaChi;
    }
    public KhachHang(KhachHang kh)
    {
        tenkh=kh.tenkh;
        gioitinh=kh.gioitinh;
        makh=kh.makh;
        SDT=kh.getSDT();
        diachi=kh.diachi;
    }

    public static List<KhachHang> AddList(ResultSet rs) throws SQLException {
        List<KhachHang> lstKH= new ArrayList<KhachHang>();
        while(rs.next()){
            String GioiTinh = "Nam";
            if(rs.getBoolean(5)==true)GioiTinh="Nữ";
            lstKH.add(new KhachHang(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),GioiTinh));
        }
        return lstKH;
    }



    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getName() {
        return tenkh;
    }

    public void setName(String name) {
        this.tenkh = name;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
