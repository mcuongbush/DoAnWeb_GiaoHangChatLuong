namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("CTVanChuyen")]
    public partial class CTVanChuyen
    {
        [Key]
        [StringLength(10)]
        public string MaCTVC { get; set; }

        [StringLength(10)]
        public string SoHD { get; set; }

        [StringLength(10)]
        public string MaNK { get; set; }

        public DateTime? NgayNhapKho { get; set; }

        public DateTime? NgayXuatKho { get; set; }

        public virtual NhaKho NhaKho { get; set; }

        public virtual HoaDonVanChuyen HoaDonVanChuyen { get; set; }
    }
}
