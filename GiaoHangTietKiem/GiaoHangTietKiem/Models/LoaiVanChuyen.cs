namespace GiaoHangTietKiem
{
    using GiaoHangTietKiem.Models;
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("LoaiVanChuyen")]
    public partial class LoaiVanChuyen
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public LoaiVanChuyen()
        {
            PhieuGuiHangs = new HashSet<PhieuGuiHang>();
            TuyenDuongs = new HashSet<TuyenDuong>();
        }

        [Key]
        [StringLength(10)]
        public string MaLVC { get; set; }

        [Required]
        [StringLength(100)]
        public string TenLVC { get; set; }

        public double Gia { get; set; }


        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<PhieuGuiHang> PhieuGuiHangs { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<TuyenDuong> TuyenDuongs { get; set; }
    }
}
