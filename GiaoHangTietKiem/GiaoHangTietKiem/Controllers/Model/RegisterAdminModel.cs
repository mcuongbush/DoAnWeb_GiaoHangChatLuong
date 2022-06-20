using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Controllers.Model
{
    public class RegisterAdminModel
    {
        [BindProperty]
        [Required(ErrorMessage = "Mời bạn nhập tên đăng nhập")]
        public string UserName { set; get; }
        [BindProperty]
        [Required(ErrorMessage = "Mời bạn nhập mật khẩu")]
        public string Password { set; get; }
        [BindProperty]
        [Required(ErrorMessage = "Mời bạn nhập email")]
        public string Email { set; get; }
        [BindProperty]
        [Required(ErrorMessage = "Mời bạn nhập số điện thoại")]
        public string SDT { set; get; }
    }
}