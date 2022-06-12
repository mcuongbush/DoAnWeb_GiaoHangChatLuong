namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class CT_TuyenDuong
    {
        [Key]
        [StringLength(10)]
        public string Ma_CTTD { get; set; }

        [Required]
        [StringLength(10)]
        public string MaNK { get; set; }

        [Required]
        [StringLength(10)]
        public string MaTD { get; set; }

        [Required]
        [StringLength(10)]
        public string MaKhoDen { get; set; }

        public virtual NhaKho NhaKho { get; set; }

        public virtual TuyenDuong TuyenDuong { get; set; }
    }
}
