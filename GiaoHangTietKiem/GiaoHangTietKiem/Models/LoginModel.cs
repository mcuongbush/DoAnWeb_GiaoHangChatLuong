using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Controllers.Model
{

    public class LoginModel : PageModel
    {
        [BindProperty]
        [Required(ErrorMessage = "Mời bạn nhập số điện thoại")]
        public string UserName { set; get; }
        [BindProperty]
        public string Password { set; get; }
        [BindProperty]
        public bool RememberMe { set; get; }
    }
}