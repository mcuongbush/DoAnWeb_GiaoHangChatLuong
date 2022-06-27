using GiaoHangTietKiem.Controllers.Model;
using GiaoHangTietKiem.Models;
using GiaoHangTietKiem.Views.Admin.Models;
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
        QuanLyGiaoHangEntities data = new QuanLyGiaoHangEntities();
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
            string mk = MaHoaMD5(model.Password);
            TaiKhoan tk = data.TaiKhoans.FirstOrDefault(p => p.TenTK.Equals(model.UserName) && p.MatKhau.Equals(mk));
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
                var checktk = data.TaiKhoans.SingleOrDefault(t => t.TenTK.Equals(model.UserName));
                if (checktk != null)
                {
                    SetAlert("Tên đăng nhập đã có!", "warning");
                }
                else
                {
                    var manv = data.NhanViens.SingleOrDefault(n => n.SDT.Equals(model.SDT));
                    if (manv != null)
                    {
                        TaiKhoan tk = new TaiKhoan();
                        tk.TenTK = model.UserName;
                        tk.MatKhau = MaHoaMD5(model.Password);
                        tk.Email = model.Email;
                        tk.LoaiTK = false;
                        tk.MaNV = manv.MaNV;
                        data.TaiKhoans.Add(tk);
                        data.SaveChanges();
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
        public ActionResult UserManage()
        {
            var lst = data.TaiKhoans.ToList();
            return View(lst);
        }
        public ActionResult Role(String TenTK)
        {
            var lstRole = data.CT_Role.Where(n => n.TenTK.Equals(TenTK));
            var lst = data.Roles.ToList();
            List<Role_Temp> model = new List<Role_Temp>();
            List_Role lst_role = new List_Role();
            foreach (var item in lst)
            {
                Role_Temp temp = new Role_Temp();
                temp.IDRole = item.IDRole;
                temp.RoleName = item.RoleName;
                temp.TenTk = TenTK;
                if (lstRole == null) temp.Status = false;
                else
                {
                    if (lstRole.FirstOrDefault(n => n.IDRole.Equals(item.IDRole)) != null)
                    {
                        temp.Status = true;
                    }
                    else temp.Status = false;
                }
                model.Add(temp);
            }
            lst_role.RoleList = model;
            lst_role.Name = TenTK;
            return View(lst_role);
        }
        [HttpPost]
        public ActionResult SaveRole(List_Role model, IEnumerable<Role_Temp> lst)
        {
            string tentk = "";
            foreach (var item in lst)
            {
                tentk = item.TenTk;
                var check = data.CT_Role.FirstOrDefault(p => p.TenTK.Equals(item.TenTk) && p.IDRole == item.IDRole);
                if (check != null)
                {
                    if (!item.Status)
                    {
                        data.CT_Role.Remove(check);
                    }
                }
                else
                {
                    if (item.Status)
                    {
                        CT_Role ct_role = new CT_Role();
                        ct_role.TenTK = item.TenTk;
                        ct_role.IDRole = item.IDRole;
                        data.CT_Role.Add(ct_role);
                    }
                }
            }
            data.SaveChanges();
            return RedirectToAction("Role", tentk);
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