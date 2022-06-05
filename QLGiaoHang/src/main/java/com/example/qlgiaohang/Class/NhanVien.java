package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;
import javafx.scene.control.DatePicker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NhanVien {
    public String MaNV;
    public String TenNV;
    public LocalDate NgaySinh;
    public String GioiTinh;
    public String PhongBan;
    public String ChucVu;
    public String SoDienThoai;
    public String DiaChi;
    public float BacLuong;

    public NhanVien(){};

    public NhanVien(String maNV, String tenNV, LocalDate ngaySinh, String diaChi, String chucVu, float bacLuong, String phongBan, String gioiTinh, String soDienThoai) {
        MaNV = maNV;
        TenNV = tenNV;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        PhongBan = phongBan;
        ChucVu = chucVu;
        SoDienThoai = soDienThoai;
        DiaChi = diaChi;
        BacLuong = bacLuong;
    }

    public NhanVien(NhanVien nv)
    {
        MaNV=nv.getMaNV();
        TenNV=nv.TenNV;
        NgaySinh=nv.NgaySinh;
        GioiTinh=nv.GioiTinh;
        PhongBan=nv.PhongBan;
        ChucVu=nv.ChucVu;
        SoDienThoai=nv.SoDienThoai;
        DiaChi=nv.DiaChi;
        BacLuong=nv.BacLuong;
    }

    public static List<NhanVien> AddList() throws SQLException, ParseException {
        ResultSet rs = Dataprovider.DataTable("select MaNV, TenNV, NgaySinh,Diachi,ChucVu,BacLuong,MaPB,GioiTinh,SDT from NhanVien");
        List<NhanVien> lstNV= new ArrayList<NhanVien>();
        while(rs.next()){
            String GioiTinh = "Nam";
            if(rs.getBoolean(8)==true)GioiTinh="Nữ";
            lstNV.add(new NhanVien(rs.getString(1), rs.getString(2),LocalDate.parse(rs.getString(3)), rs.getString(4), rs.getString(5), rs.getFloat(6),rs.getString(7) , GioiTinh, rs.getString(9)));
        }
        return lstNV;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public LocalDate getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String phongBan) {
        PhongBan = phongBan;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public float getBacLuong() {
        return BacLuong;
    }

    public void setBacLuong(float bacLuong) {
        BacLuong = bacLuong;
    }
}
