using GiaoHangTietKiem.Controllers.Model;
using GiaoHangTietKiem.Models;
using System;
using System.Collections.Generic;
using System.Linq;
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
            TaiKhoan tk = Dataprovider.Instance.DB.TaiKhoans.FirstOrDefault(p => p.TenTK.Equals(model.UserName) && p.MatKhau.Equals(model.Password));
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
    }
}