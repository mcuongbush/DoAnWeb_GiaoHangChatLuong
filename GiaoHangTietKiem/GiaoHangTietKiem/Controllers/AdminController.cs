using GiaoHangTietKiem.Controllers.Model;
using GiaoHangTietKiem.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace GiaoHangTietKiem.Controllers
{
    public class AdminController : Controller
    {
        public ActionResult IndexAdmin()
        {
            return View();
        }
        public ActionResult Login()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Login(UserAdmin model)
        {
            TaiKhoan tk = Dataprovider.Instance.DB.TaiKhoans.FirstOrDefault(p => p.TenTK.Equals(model.UserName) && p.MatKhau.Equals(MaHoaMD5(model.Password)));
            if (tk != null)
            {
                Session["TaiKhoan"] = tk.TenTK;
                return RedirectToAction("IndexAdmin");
            }
            else
            {
                ModelState.AddModelError("", "mk sai");
            }
            return View(model);
        }
        public ActionResult Register()
        {
            return View();
        }
        [HttpPost]
        public ActionResult Register(RegisterAdminModel model)
        {
            if (ModelState.IsValid)
            {
                var checktk = Dataprovider.Instance.DB.TaiKhoans.SingleOrDefault(t => t.TenTK.Equals(model.UserName));
                if (checktk != null)
                {
                    SetAlert("Tên đăng nhập đã có!", "warning");
                }
                else
                {
                    var manv = Dataprovider.Instance.DB.NhanViens.SingleOrDefault(n => n.SDT.Equals(model.SDT));
                    if (manv != null)
                    {
                        TaiKhoan tk = new TaiKhoan();
                        tk.TenTK = model.UserName;
                        tk.MatKhau = MaHoaMD5(model.Password);
                        tk.Email = model.Email;
                        tk.LoaiTK = false;
                        tk.MaNV = manv.MaNV;
                        Dataprovider.Instance.DB.TaiKhoans.Add(tk);
                        Dataprovider.Instance.DB.SaveChanges();
                        SetAlert("Tạo tài khoản thành công!", "success");
                    }
                    else
                    {
                        SetAlert("Số điện thoại không có trong danh sách nhân viên!", "warning");
                    }

                }
                return View(model);
            }
            return View();
        }
        protected void SetAlert(string mess, string type)
        {
            TempData["AlretMessage"] = mess;
            if (type == "success")
            {
                TempData["AlretType"] = "alret-success";
            }
            else if (type == "warning")
            {
                TempData["AlretType"] = "alret-warning";
            }
            else if (type == "error")
            {
                TempData["AlretType"] = "alret-danger";
            }
        }
        private string MaHoaMD5(string s)
        {
            //Tạo MD5 
            MD5 mh = MD5.Create();
            //Chuyển kiểu chuổi thành kiểu byte
            byte[] inputBytes = System.Text.Encoding.ASCII.GetBytes(s);
            //mã hóa chuỗi đã chuyển
            byte[] hash = mh.ComputeHash(inputBytes);
            //tạo đối tượng StringBuilder (làm việc với kiểu dữ liệu lớn)
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.Length; i++)
            {
                sb.Append(hash[i].ToString("X2"));
            }
            return sb.ToString();
        }
    }
}