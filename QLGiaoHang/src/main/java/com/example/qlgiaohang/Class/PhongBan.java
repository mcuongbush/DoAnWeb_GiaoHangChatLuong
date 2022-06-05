package com.example.qlgiaohang.Class;

import com.example.qlgiaohang.Dataprovider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongBan {
    public String MaPB;
    public String TenPB;

    public static List<PhongBan> lstPB = new ArrayList<PhongBan>();
    public PhongBan(){};
    public PhongBan(String maPB, String tenPB) {
        MaPB = maPB;
        TenPB = tenPB;
    }
    public PhongBan(PhongBan pb)
    {
        MaPB=pb.MaPB;
        TenPB=pb.TenPB;
    }
    public PhongBan(String MaPB) throws SQLException {
        String qr = String.format("select * FROM dbo.PhongBan WHERE MaPB = '%s'",MaPB);
        ResultSet rs = Dataprovider.DataTable(qr);
        while (rs.next()){
            this.MaPB = rs.getString(1);
            TenPB = rs.getString(2);
        }
    }

    public static ObservableList<PhongBan> AddObservable() throws SQLException{

        ResultSet rs = Dataprovider.DataTable("select MaPB,TenPB from PhongBan");
        while(rs.next()){
            lstPB.add(new PhongBan(rs.getString(1),rs.getString(2)));
        }
        ObservableList <PhongBan> lst = FXCollections.observableArrayList(lstPB);

        return lst;
    }


    public String getMaPB() {
        return MaPB;
    }

    public static List<PhongBan> AddList(ResultSet rs) throws SQLException{
        List<PhongBan> lstPB = new ArrayList<PhongBan>();
        while(rs.next()){
            lstPB.add(new PhongBan(rs.getString(1),rs.getString(2)));
        }
        return lstPB;
    }

    public String getValue(){
        return TenPB;
    }

    public String getId(){
        return MaPB;
    }
    public void setMaPB(String maPB) {
        MaPB = maPB;
    }

    @Override
    public String toString() {
        return TenPB;
    }

    public void setTenPB(String tenPB) {
        TenPB = tenPB;
    }
}
