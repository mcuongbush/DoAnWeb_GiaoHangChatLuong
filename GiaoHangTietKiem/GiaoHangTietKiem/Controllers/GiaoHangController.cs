
using Common1;
using GiaoHangTietKiem.Controllers.Model;
using GiaoHangTietKiem.Models;
using GiaoHangTietKiem.VNPAY.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace GiaoHangTietKiem.Controllers
{
    public class GiaoHangController : BaseController
    {
        // GET: GiaoHang
        GiaoHangChatLuongContext data = new GiaoHangChatLuongContext();
        public ActionResult Index()
        {
            List<LoaiVanChuyen> lstLVC = data.LoaiVanChuyens.ToList();
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
        [HttpPost]
        public ActionResult TheoDoi(string SoHD)
        {
            HoaDonVanChuyen HD = data.HoaDonVanChuyens.FirstOrDefault(t => t.SoHD.Equals(SoHD));
            if (HD != null)
            {
                TheoDoi td = new TheoDoi(HD);
                Session["TheoDoi"] = td;
                IEnumerable<CTVanChuyen> model = data.CTVanChuyens.Where(t => t.SoHD.Equals(SoHD));
                List<HanhTrinh> lst = new List<HanhTrinh>();
                foreach (var item in model)
                {
                    NhaKho nk = new NhaKho(item.MaNK);
                    lst.Add(new HanhTrinh(item.NgayNhapKho, nk.TenNK, true));
                    if (item.NgayXuatKho != null)
                    {
                        lst.Add(new HanhTrinh(item.NgayXuatKho, nk.TenNK, false));
                    }
                }
                Session["QuaTrinh"] = lst;
                return RedirectToAction("Index");
            }
            else
            {
                Session["TheoDoi"] = null;
                Session["ErrorTheoDoi"] = "Số hóa đơn không có trong danh sách. Vui lòng nhập số hóa đơn khác!";
                return RedirectToAction("Index");
            }
        }

        public ActionResult Payment(string SoTien)
        {
            string url = ConfigurationManager.AppSettings["Url"];
            string returnUrl = ConfigurationManager.AppSettings["ReturnUrl"];
            string tmnCode = ConfigurationManager.AppSettings["TmnCode"];
            string hashSecret = ConfigurationManager.AppSettings["HashSecret"];

            VnPayLibrary pay = new VnPayLibrary();

            pay.AddRequestData("vnp_Version", "2.1.0"); //Phiên bản api mà merchant kết nối. Phiên bản hiện tại là 2.0.0
            pay.AddRequestData("vnp_Command", "pay"); //Mã API sử dụng, mã cho giao dịch thanh toán là 'pay'
            pay.AddRequestData("vnp_TmnCode", tmnCode); //Mã website của merchant trên hệ thống của VNPAY (khi đăng ký tài khoản sẽ có trong mail VNPAY gửi về)
            pay.AddRequestData("vnp_Amount", SoTien + "00"); //số tiền cần thanh toán, công thức: số tiền * 100 - ví dụ 10.000 (mười nghìn đồng) --> 1000000
            pay.AddRequestData("vnp_BankCode", ""); //Mã Ngân hàng thanh toán (tham khảo: https://sandbox.vnpayment.vn/apis/danh-sach-ngan-hang/), có thể để trống, người dùng có thể chọn trên cổng thanh toán VNPAY
            pay.AddRequestData("vnp_CreateDate", DateTime.Now.ToString("yyyyMMddHHmmss")); //ngày thanh toán theo định dạng yyyyMMddHHmmss
            pay.AddRequestData("vnp_CurrCode", "VND"); //Đơn vị tiền tệ sử dụng thanh toán. Hiện tại chỉ hỗ trợ VND
            pay.AddRequestData("vnp_IpAddr", Utils.GetIpAddress()); //Địa chỉ IP của khách hàng thực hiện giao dịch
            pay.AddRequestData("vnp_Locale", "vn"); //Ngôn ngữ giao diện hiển thị - Tiếng Việt (vn), Tiếng Anh (en)
            pay.AddRequestData("vnp_OrderInfo", "Thanh toan don hang"); //Thông tin mô tả nội dung thanh toán
            pay.AddRequestData("vnp_OrderType", "other"); //topup: Nạp tiền điện thoại - billpayment: Thanh toán hóa đơn - fashion: Thời trang - other: Thanh toán trực tuyến
            pay.AddRequestData("vnp_ReturnUrl", returnUrl); //URL thông báo kết quả giao dịch khi Khách hàng kết thúc thanh toán
            pay.AddRequestData("vnp_TxnRef", DateTime.Now.Ticks.ToString()); //mã hóa đơn

            string paymentUrl = pay.CreateRequestUrl(url, hashSecret);

            return Redirect(paymentUrl);
        }

        public ActionResult PaymentConfirm()
        {
            if (Request.QueryString.Count > 0)
            {
                string hashSecret = ConfigurationManager.AppSettings["HashSecret"]; //Chuỗi bí mật
                var vnpayData = Request.QueryString;
                VnPayLibrary pay = new VnPayLibrary();

                //lấy toàn bộ dữ liệu được trả về
                foreach (string s in vnpayData)
                {
                    if (!string.IsNullOrEmpty(s) && s.StartsWith("vnp_"))
                    {
                        pay.AddResponseData(s, vnpayData[s]);
                    }
                }

                long orderId = Convert.ToInt64(pay.GetResponseData("vnp_TxnRef")); //mã hóa đơn
                long vnpayTranId = Convert.ToInt64(pay.GetResponseData("vnp_TransactionNo")); //mã giao dịch tại hệ thống VNPAY
                string vnp_ResponseCode = pay.GetResponseData("vnp_ResponseCode"); //response code: 00 - thành công, khác 00 - xem thêm https://sandbox.vnpayment.vn/apis/docs/bang-ma-loi/
                string vnp_SecureHash = Request.QueryString["vnp_SecureHash"]; //hash của dữ liệu trả về

                bool checkSignature = pay.ValidateSignature(vnp_SecureHash, hashSecret); //check chữ ký đúng hay không?

                if (checkSignature)
                {
                    if (vnp_ResponseCode == "00")
                    {
                        //Thanh toán thành công
                        ViewBag.Message = "Thanh toán thành công hóa đơn " + orderId + " | Mã giao dịch: " + vnpayTranId;

                    }
                    else
                    {
                        //Thanh toán không thành công. Mã lỗi: vnp_ResponseCode
                        ViewBag.Message = "Có lỗi xảy ra trong quá trình xử lý hóa đơn " + orderId + " | Mã giao dịch: " + vnpayTranId + " | Mã lỗi: " + vnp_ResponseCode;
                    }
                }
                else
                {
                    ViewBag.Message = "Có lỗi xảy ra trong quá trình xử lý";
                }
            }

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