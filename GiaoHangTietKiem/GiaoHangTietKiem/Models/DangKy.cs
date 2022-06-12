using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Models
{
    public class DangKy
    {
        public DangKy() { }
        private string TenKH;

        private string SDT;

        private string DiaChi;

        private string GioiTinh;

        private string MatKhau;

        private string Email;

        private string MaKH;

        private string UserName;

        public DangKy(string tenKH, string sDT, string diaChi, string gioiTinh, string matKhau, string email, string maKH, string userName)
        {
            TenKH = tenKH;
            SDT = sDT;
            DiaChi = diaChi;
            GioiTinh = gioiTinh;
            MatKhau = matKhau;
            Email = email;
            MaKH = maKH;
            UserName1 = userName;
        }

        public string TenKH1 { get => TenKH; set => TenKH = value; }
        public string SDT1 { get => SDT; set => SDT = value; }
        public string DiaChi1 { get => DiaChi; set => DiaChi = value; }
        public string GioiTinh1 { get => GioiTinh; set => GioiTinh = value; }
        public string MatKhau1 { get => MatKhau; set => MatKhau = value; }
        public string Email1 { get => Email; set => Email = value; }
        public string MaKH1 { get => MaKH; set => MaKH = value; }
        public string UserName1 { get => UserName; set => UserName = value; }
    }
}