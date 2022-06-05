package com.example.qlgiaohang.Class;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;

public class TheoDoi {
    public Date getTime() {
        return Time;
    }

    public void setTime(Date time) {
        Time = time;
    }

    public boolean isSukien() {
        return sukien;
    }

    public void setSukien(boolean sukien) {
        this.sukien = sukien;
    }

    public TheoDoi(Date time, boolean sukien, String maNK) {
        Time = time;
        this.sukien = sukien;
        MaNK = maNK;
    }

    private Date Time;
    private boolean sukien;

    public String getMaNK() {
        return MaNK;
    }

    public void setMaNK(String maNK) {
        MaNK = maNK;
    }

    private String MaNK;
}
