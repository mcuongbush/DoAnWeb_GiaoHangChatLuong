package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;
import com.example.qlgiaohang.DoiTacVanChuyenModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DoiTacVanChuyen {
    public String MaDTVC;
    public String TenDTVC;
    public String DiaChi;
    public String SoDienThoai;
    public LocalDate NgayHopTac;
    public int HoaHong;
    public String MaLVC;

    public DoiTacVanChuyen(){};
    public DoiTacVanChuyen(DoiTacVanChuyen dtvc)
    {
        MaDTVC=dtvc.MaDTVC;
        TenDTVC=dtvc.TenDTVC;
        DiaChi=dtvc.DiaChi;
        SoDienThoai=dtvc.SoDienThoai;
        NgayHopTac=dtvc.NgayHopTac;
        HoaHong=dtvc.HoaHong;
        MaLVC=dtvc.MaLVC;
    }
    public DoiTacVanChuyen(String maDTVC, String tenDTVC, String diaChi, String soDienThoai, LocalDate ngayHopTac, int hoaHong, String maLVC) {
        MaDTVC = maDTVC;
        TenDTVC = tenDTVC;
        DiaChi = diaChi;
        SoDienThoai = soDienThoai;
        NgayHopTac = ngayHopTac;
        HoaHong = hoaHong;
        MaLVC = maLVC;
    }

    public static List<DoiTacVanChuyen> AddList(ResultSet rs) throws SQLException, ParseException {
        List<DoiTacVanChuyen> lstDTVC = new ArrayList<DoiTacVanChuyen>();
        while (rs.next())
        {
            lstDTVC.add(new DoiTacVanChuyen(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),LocalDate.parse(rs.getString(5)),rs.getInt(6),rs.getString(7)));
        }
        return lstDTVC;
    }

    public String getMaDTVC() {
        return MaDTVC;
    }

    public void setMaDTVC(String maDTVC) {
        MaDTVC = maDTVC;
    }

    public String getTenDTVC() {
        return TenDTVC;
    }

    public void setTenDTVC(String tenDTVC) {
        TenDTVC = tenDTVC;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public LocalDate getNgayHopTac() {
        return NgayHopTac;
    }

    public void setNgayHopTac(LocalDate ngayHopTac) {
        NgayHopTac = ngayHopTac;
    }

    public int getHoaHong() {
        return HoaHong;
    }

    public void setHoaHong(int hoaHong) {
        HoaHong = hoaHong;
    }

    public String getMaLVC() {
        return MaLVC;
    }

    public void setMaLVC(String maLVC) {
        MaLVC = maLVC;
    }
}
