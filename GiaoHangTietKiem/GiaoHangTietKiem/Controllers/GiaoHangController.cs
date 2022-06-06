using GiaoHangTietKiem.App_Start;
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

        public ActionResult Create()
        {
            ViewBag.Message = "Your application description page.";
            return View();
        }
    }
}