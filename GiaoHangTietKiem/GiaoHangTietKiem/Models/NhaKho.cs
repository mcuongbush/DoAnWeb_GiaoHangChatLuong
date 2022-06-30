namespace GiaoHangTietKiem.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;
    using System.Linq;

    [Table("NhaKho")]
    public partial class NhaKho
    {
        GiaoHangChatLuongContext data = new GiaoHangChatLuongContext();
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public NhaKho()
        {
            CT_TuyenDuong = new HashSet<CT_TuyenDuong>();
            CTVanChuyens = new HashSet<CTVanChuyen>();
            NhanViens = new HashSet<NhanVien>();
        }
        public NhaKho(string maNK)
        {
            NhaKho nk = data.NhaKhoes.FirstOrDefault(n => n.MaNK.Equals(maNK));
            MaNK = nk.MaNK;
            TenNK = nk.TenNK;
            DienTich = nk.DienTich;
            SucChua = nk.SucChua;
            DiaChi = nk.DiaChi;
            MaKV = nk.MaKV;
        }
        [Key]
        [StringLength(10)]
        public string MaNK { get; set; }

        [Required]
        [StringLength(100)]
        public string TenNK { get; set; }

        public double? DienTich { get; set; }

        public int? SucChua { get; set; }

        [StringLength(100)]
        public string DiaChi { get; set; }

        [StringLength(10)]
        public string MaKV { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CT_TuyenDuong> CT_TuyenDuong { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<CTVanChuyen> CTVanChuyens { get; set; }

        public virtual KhuVuc KhuVuc { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<NhanVien> NhanViens { get; set; }
    }
}
