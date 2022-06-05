using GiaoHangTietKiem.App_Start;
using GiaoHangTietKiem.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace GiaoHangTietKiem.Controllers
{
    public class GiaoHangController : Controller
    {
        // GET: GiaoHang
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
        public ActionResult Service()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
        public ActionResult Price()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
        public ActionResult Blog()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
        public ActionResult Single()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
        public ActionResult Contact()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }

        public ActionResult Login()
        {
            return View();
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(TaiKhoan model)
        {
            if (!string.IsNullOrEmpty(model.TenTK))
            {
                GiaoHangContext data = new GiaoHangContext();
                TaiKhoan tk = data.TaiKhoans.SingleOrDefault(p => p.TenTK.Equals(model.TenTK) && p.MatKhau.Equals(model.MatKhau));
                if (tk != null)
                {
                    return View("Index");
                }
                else
                {
                    ModelState.AddModelError("", "mk sai");
                }
            }
            return View(model);
        }
        public ActionResult Create()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
    }
}