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
    
    public partial class CT_TuyenDuong
    {
        public string Ma_CTTD { get; set; }
        public string MaNK { get; set; }
        public string MaTD { get; set; }
        public string MaKhoDen { get; set; }
    
        public virtual NhaKho NhaKho { get; set; }
        public virtual TuyenDuong TuyenDuong { get; set; }
    }
}
