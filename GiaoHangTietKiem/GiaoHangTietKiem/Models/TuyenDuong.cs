namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("TuyenDuong")]
    public partial class TuyenDuong
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public TuyenDuong()
        {
            CT_TuyenDuong = new HashSet<CT_TuyenDuong>();
            HoaDonVanChuyens = new HashSet<HoaDonVanChuyen>();
        }

        [Key]
        [StringLength(10)]
        public string MaTD { get; set; }

        [Required]
        [StringLength(50)]
        public string TenTD { get; set; }

        public int ThoiGian { get; set; }

        [Required]
        [StringLength(10)]
        public string MaKhoBD { get; set; }

        [Required]
        [StringLength(10)]
        public string MaKhoKT { get; set; }

        [StringLength(10)]
        public string MaLVC { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CT_TuyenDuong> CT_TuyenDuong { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<HoaDonVanChuyen> HoaDonVanChuyens { get; set; }

        public virtual LoaiVanChuyen LoaiVanChuyen { get; set; }
    }
}
