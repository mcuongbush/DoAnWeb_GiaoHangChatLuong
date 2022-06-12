
using Common1;
using GiaoHangTietKiem.Controllers.Model;
using GiaoHangTietKiem.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace GiaoHangTietKiem.Controllers
{
    public class GiaoHangController : BaseController
    {
        // GET: GiaoHang
        public ActionResult Index()
        {
            List<LoaiVanChuyen> lstLVC = Dataprovider.Instance.DB.LoaiVanChuyens.ToList();
            ViewBag.TenLVC = new SelectList(lstLVC, "TenLVC", "TenLVC");
            return View();

        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Index(BaoGiaModel model, FormCollection form)
        {
            string content = System.IO.File.ReadAllText(Server.MapPath("~/template/BaoGia.html"));
            content = content.Replace("{{TenLVC}}", form["TenLVC"].ToString());
            content = content.Replace("{{Gia}}", "1000");
            content = content.Replace("{{TenKH}}", model.TenKH);
            Random ramdom = new Random();
            new MailHelper().SendMail(model.Email, "Đăng ký mới từ web", content);
            return View(model);
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
            ShowPrice model = new ShowPrice();
            return View(model);
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Contact(ShowPrice model)
        {
            int gia = 10000;
            string km = model.KM;
            //km = km.Replace(" km", "");
            //double dodai = double.Parse(km);
            Session["TongTien"] = model.KM;
            return View(model);

        }
        public ActionResult Create()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
    }
}