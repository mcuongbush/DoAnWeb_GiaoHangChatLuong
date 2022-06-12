namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("PhieuGuiHang")]
    public partial class PhieuGuiHang
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public PhieuGuiHang()
        {
            HoaDonVanChuyens = new HashSet<HoaDonVanChuyen>();
        }

        [Key]
        [StringLength(10)]
        public string SoPGH { get; set; }

        [Column(TypeName = "date")]
        public DateTime? NgayGui { get; set; }

        public bool? COD { get; set; }

        [StringLength(10)]
        public string MaKH { get; set; }

        [StringLength(10)]
        public string MaLVC { get; set; }

        [StringLength(10)]
        public string MaNV { get; set; }

        [StringLength(10)]
        public string MaKN { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<HoaDonVanChuyen> HoaDonVanChuyens { get; set; }

        public virtual KhachHang KhachHang { get; set; }

        public virtual KhachNhan KhachNhan { get; set; }

        public virtual LoaiVanChuyen LoaiVanChuyen { get; set; }

        public virtual NhanVien NhanVien { get; set; }
    }
}
