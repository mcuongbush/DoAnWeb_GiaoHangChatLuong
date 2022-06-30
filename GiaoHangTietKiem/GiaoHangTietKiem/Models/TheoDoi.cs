using GiaoHangTietKiem.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Controllers.Model
{
    public class TheoDoi
    {
        [BindProperty]
        public string TenKH { set; get; }
        [BindProperty]
        public string SDTKH { set; get; }
        [BindProperty]
        public string DiaChiKH { set; get; }
        [BindProperty]
        public string TenKN { set; get; }
        [BindProperty]
        public string SDTKN { set; get; }
        [BindProperty]
        public string DiaChiKN { set; get; }
        [BindProperty]
        public string MaKH { set; get; }
        [BindProperty]
        public long TongTien { set; get; }
        [BindProperty]
        public bool COD { set; get; }
        [BindProperty]
        public bool TrangThai { set; get; }
        GiaoHangChatLuongContext data = new GiaoHangChatLuongContext();
        public TheoDoi() { }
        public TheoDoi(HoaDonVanChuyen HD)
        {

            PhieuGuiHang PGH = data.PhieuGuiHangs.FirstOrDefault(n => n.SoPGH.Equals(HD.SoPGH));
            KhachHang KH = data.KhachHangs.SingleOrDefault(n => n.MaKH.Equals(PGH.MaKH));
            KhachNhan KN = data.KhachNhans.SingleOrDefault(n => n.MaKN.Equals(PGH.MaKN));
            TenKH = KH.TenKH;
            SDTKH = KH.SDT;
            DiaChiKH = KH.DiaChi;
            TenKN = KN.TenKN;
            SDTKN = KN.SDT;
            DiaChiKN = KN.DiaChi;
            COD = (bool)PGH.COD;
            TongTien = (long)HD.TongTien;
            TrangThai = HD.TrangThai;
            MaKH = KH.MaKH;
        }
    }
}