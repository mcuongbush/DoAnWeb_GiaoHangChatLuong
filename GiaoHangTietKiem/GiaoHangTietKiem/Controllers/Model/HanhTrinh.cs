using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GiaoHangTietKiem.Controllers.Model
{
    public class HanhTrinh
    {
        public HanhTrinh(DateTime? time, string tenNK, bool suKien)
        {
            Time = (DateTime)time;
            TenNK = tenNK;
            SuKien = suKien;
        }

        [BindProperty]
        public DateTime Time { set; get; }
        [BindProperty]
        public string TenNK { set; get; }
        [BindProperty]
        public bool SuKien { set; get; }
    }
}