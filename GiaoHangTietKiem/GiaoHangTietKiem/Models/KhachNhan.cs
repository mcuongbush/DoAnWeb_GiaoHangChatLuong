namespace GiaoHangTietKiem
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("KhachNhan")]
    public partial class KhachNhan
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public KhachNhan()
        {
            PhieuGuiHangs = new HashSet<PhieuGuiHang>();
        }

        [Key]
        [StringLength(10)]
        public string MaKN { get; set; }

        [Required]
        [StringLength(50)]
        public string TenKN { get; set; }

        [Required]
        [StringLength(10)]
        public string SDT { get; set; }

        [StringLength(100)]
        public string DiaChi { get; set; }

        public bool GioiTinh { get; set; }

        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<PhieuGuiHang> PhieuGuiHangs { get; set; }
    }
}
