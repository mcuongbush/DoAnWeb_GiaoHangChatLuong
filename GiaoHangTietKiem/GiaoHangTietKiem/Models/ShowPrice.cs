using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Controllers.Model
{
    public class ShowPrice
    {
        [BindProperty]
        public string Diemdi { set; get; }
        [BindProperty]
        public string Diemden { set; get; }
        [BindProperty]
        public string KM { set; get; }
    }
}