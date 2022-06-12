namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Shop")]
    public partial class Shop
    {
        [Key]
        [StringLength(10)]
        public string MaShop { get; set; }

        [Required]
        [StringLength(50)]
        public string TenShop { get; set; }

        public DateTime NgayDK { get; set; }

        [Required]
        [StringLength(15)]
        public string TkNganHang { get; set; }

        [StringLength(10)]
        public string MaKH { get; set; }

        public virtual KhachHang KhachHang { get; set; }
    }
}
