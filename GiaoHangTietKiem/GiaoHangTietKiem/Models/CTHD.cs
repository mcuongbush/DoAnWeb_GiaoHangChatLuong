namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("CTHD")]
    public partial class CTHD
    {
        [Key]
        [StringLength(10)]
        public string MaCTHD { get; set; }

        [StringLength(10)]
        public string SoHD { get; set; }

        [StringLength(10)]
        public string MaHH { get; set; }

        public int SoLuong { get; set; }

        public double KG { get; set; }

        public virtual HangHoa HangHoa { get; set; }

        public virtual HoaDonVanChuyen HoaDonVanChuyen { get; set; }
    }
}
