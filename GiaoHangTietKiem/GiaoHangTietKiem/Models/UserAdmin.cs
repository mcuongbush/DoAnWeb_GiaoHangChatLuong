using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Controllers.Model
{
    public class UserAdmin
    {
        [BindProperty]
        public string UserName { set; get; }
        [BindProperty]
        public string Password { set; get; }
        [BindProperty]
        public bool RememberMe { set; get; }
    }
}