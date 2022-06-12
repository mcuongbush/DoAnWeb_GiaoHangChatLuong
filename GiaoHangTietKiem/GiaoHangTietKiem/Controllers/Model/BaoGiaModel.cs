using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace GiaoHangTietKiem.Controllers.Model
{
    public class BaoGiaModel
    {
        [Required(ErrorMessage = "Mời bạn nhập số điện thoại")]
        public string TenKH { set; get; }
        [Required(ErrorMessage = "Mời bạn nhập mật khẩu")]
        public string Email { set; get; }
        public IEnumerable<SelectListItem> TenLVC { set; get; }
        public string SelectTenLVC { set { SelectTenLVC = ""; } get { return SelectTenLVC; } }
    }
}