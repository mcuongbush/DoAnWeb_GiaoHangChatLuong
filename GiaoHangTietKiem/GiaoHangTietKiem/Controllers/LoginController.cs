using Common1;
using GiaoHangTietKiem.App_Start;
using GiaoHangTietKiem.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace GiaoHangTietKiem.Controllers
{
    public class LoginController : Controller
    {
        public ActionResult Register()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Register(DangKy model)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }
            string content = System.IO.File.ReadAllText(Server.MapPath("~/template/newoder.html"));
            content = content.Replace("{{CustomerName}}", model.TenKH1);
            content = content.Replace("{{Phone}}", model.SDT1);
            content = content.Replace("{{Email}}", model.Email1);
            content = content.Replace("{{Address}}", model.DiaChi1);
            new MailHelper().SendMail(model.Email1, "Đăng ký mới từ web", content);
            KhachHang kh = new KhachHang(model.TenKH1, model.SDT1, model.DiaChi1, model.GioiTinh1.Equals("Nam") ? true : false);
            string s = string.Format("INSERT dbo.KhachHang( MaKH,TenKH, SDT, DiaChi, GioiTinh)VALUES( DEFAULT, N'{0}', '{1}', N'{2}', {3})", kh.TenKH, kh.SDT, kh.DiaChi, kh.GioiTinh == true ? 1 : 0);
            Dataprovider.Instance.DB.Database.ExecuteSqlCommand(s);
            SetAlert("Tạo tài khoản thành công", "success");
            return View(model);
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
    }

}