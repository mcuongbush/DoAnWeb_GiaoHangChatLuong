//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace GiaoHangTietKiem.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Shop
    {
        public string MaShop { get; set; }
        public string TenShop { get; set; }
        public System.DateTime NgayDK { get; set; }
        public string TkNganHang { get; set; }
        public string MaKH { get; set; }
    
        public virtual KhachHang KhachHang { get; set; }
    }
}
