namespace GiaoHangTietKiem.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("PhieuYeuCau")]
    public partial class PhieuYeuCau
    {
        [Key]
        [StringLength(10)]
        public string SoPYC { get; set; }

        [Column(TypeName = "date")]
        public DateTime? NgayLap { get; set; }

        [StringLength(10)]
        public string MaKH { get; set; }

        public decimal? KhoiLuong { get; set; }

        [StringLength(10)]
        public string MaKN { get; set; }

        [StringLength(10)]
        public string MaLVC { get; set; }

        public long? ThanhToan { get; set; }

        public virtual KhachHang KhachHang { get; set; }

        public virtual KhachNhan KhachNhan { get; set; }

        public virtual LoaiVanChuyen LoaiVanChuyen { get; set; }
    }
}
