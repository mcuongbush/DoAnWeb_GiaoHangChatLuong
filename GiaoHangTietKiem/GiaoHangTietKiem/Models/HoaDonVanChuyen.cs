namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("HoaDonVanChuyen")]
    public partial class HoaDonVanChuyen
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public HoaDonVanChuyen()
        {
            CTHDs = new HashSet<CTHD>();
            CTVanChuyens = new HashSet<CTVanChuyen>();
        }

        [Key]
        [StringLength(10)]
        public string SoHD { get; set; }

        [Column(TypeName = "date")]
        public DateTime? NgayLapHD { get; set; }

        public long? TongTien { get; set; }

        [StringLength(10)]
        public string SoPGH { get; set; }

        [StringLength(10)]
        public string MaNV { get; set; }

        public bool TrangThai { get; set; }

        [StringLength(10)]
        public string MaTD { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CTHD> CTHDs { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CTVanChuyen> CTVanChuyens { get; set; }

        public virtual PhieuGuiHang PhieuGuiHang { get; set; }

        public virtual NhanVien NhanVien { get; set; }

        public virtual TuyenDuong TuyenDuong { get; set; }
    }
}
