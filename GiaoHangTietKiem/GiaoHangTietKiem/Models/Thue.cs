namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Thue")]
    public partial class Thue
    {
        [Key]
        [StringLength(10)]
        public string MaThue { get; set; }

        public long GiaTri { get; set; }

        public int KhoangCach { get; set; }

        [Required]
        [StringLength(10)]
        public string MaLVC { get; set; }

        public virtual LoaiVanChuyen LoaiVanChuyen { get; set; }
    }
}
